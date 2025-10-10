package com.dao;

import com.dto.SuperAdmDTO;
import com.model.SuperAdm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SuperAdmDAO extends DAO {
  // Constantes
  public static final Map<String, String> camposFiltraveis = Map.of(
      "id", "Id",
      "nome", "Nome",
      "cargo", "Cargo",
      "email", "Email"
  );

  // Construtor
  public SuperAdmDAO() throws SQLException, ClassNotFoundException {
    super();
  }

  // Outros Métodos
  // === CREATE ===
  public void cadastrar(SuperAdm credenciais) throws SQLException {
    // Armazena as informações do cadastro em variáveis
    String nome = credenciais.getNome();
    String email = credenciais.getEmail();
    String cargo = credenciais.getCargo();
    String senha = credenciais.getSenha();

    // Prepara o comando
    String sql = "INSERT INTO super_adm (nome, email, cargo, senha) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // Completa os parâmetros faltantes
      pstmt.setString(1, nome);
      pstmt.setString(2, email);
      pstmt.setString(3, cargo);
      pstmt.setString(4, senha);

      // Executa o update
      pstmt.executeUpdate();

      // Commita as alterações no banco
      conn.commit();

    } catch (SQLException e) {
      // Faz o rollback das alterações e propaga a exceção
      conn.rollback();
      throw e;
    }
  }

  // === READ ===
  public List<SuperAdmDTO> listar(String campoFiltro, String valorFiltro, String campoSequencia, String direcaoSequencia) throws SQLException {
    List<SuperAdmDTO> superAdms = new ArrayList<>();

    // Prepara o comando
    String sql = "SELECT id, nome, cargo, email FROM super_adm";

    // Verificando campo do filtro
    if (campoFiltro != null && camposFiltraveis.containsKey(campoFiltro)) {
      sql += " WHERE %s::varchar = ?".formatted(campoFiltro);
    }

    //Verificando campo para ordenar a consulta
    if (campoSequencia != null && camposFiltraveis.containsKey(campoSequencia)) {
      sql += " ORDER BY %s %s".formatted(campoSequencia, direcaoSequencia);

    } else {
      sql += " ORDER BY id ASC";
    }

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      //Definindo parâmetro vazio
      if (campoFiltro != null && camposFiltraveis.containsKey(campoFiltro)) {
        pstmt.setString(1, valorFiltro);
      }

      //Instanciando um ResultSet
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int id = rs.getInt("id");
          String nome = rs.getString("nome");
          String cargo = rs.getString("cargo");
          String email = rs.getString("email");

          superAdms.add(new SuperAdmDTO(id, nome, cargo, email));
        }
      }
    }

    return superAdms;
  }

  public SuperAdmDTO pesquisarPorId(int id) throws SQLException {
    // Prepara o comando
    String sql = "SELECT nome, cargo, email FROM super_adm WHERE id = ?";
    SuperAdmDTO superAdm;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        String nome = rs.getString("nome");
        String cargo = rs.getString("cargo");
        String email = rs.getString("email");

        superAdm = new SuperAdmDTO(id, nome, cargo, email);
      }
    }

    return superAdm;
  }

  public SuperAdmDTO pesquisarPorEmail(String email) throws SQLException {
    // Prepara o comando
    String sql = "SELECT id, nome, cargo FROM super_adm WHERE email = ?";
    SuperAdmDTO superAdm;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, email);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String cargo = rs.getString("cargo");

        superAdm = new SuperAdmDTO(id, nome, cargo, email);
      }
    }

    return superAdm;
  }

  public SuperAdm getCamposAlteraveis(int id) throws SQLException {
    // Prepara o comando
    String sql = "SELECT * FROM super_adm WHERE id = ?";
    SuperAdm superAdm;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          throw new SQLException("Erro ao recuperar as informações do super adm");
        }

        String nome = rs.getString("nome");
        String cargo = rs.getString("cargo");
        String email = rs.getString("email");
        String senha = rs.getString("senha");

        superAdm = new SuperAdm(id, nome, cargo, email, senha);
      }
    }

    return superAdm;
  }

  // === UPDATE ===
  public void atualizar(SuperAdm original, SuperAdm alterado) throws SQLException {
    // Desempacotamento do model alterado
    int id = alterado.getId();
    String nome = alterado.getNome();
    String cargo = alterado.getCargo();
    String email = alterado.getEmail();
    String senha = alterado.getSenha();

    // Monta o comando de acordo com os campos alterados
    StringBuilder sql = new StringBuilder("UPDATE super_adm SET ");
    List<Object> valores = new ArrayList<>();

    if (!Objects.equals(nome, original.getNome())) {
      sql.append("nome = ?, ");
      valores.add(nome);
    }

    if (!Objects.equals(cargo, original.getCargo())) {
      sql.append("cargo = ?, ");
      valores.add(cargo);
    }

    if (!Objects.equals(email, original.getEmail())) {
      sql.append("email = ?, ");
      valores.add(email);
    }

    if (senha != null && !senha.equals(original.getSenha()) && !senha.isBlank()) {
      sql.append("senha = ?, ");
      valores.add(senha);
    }

    // Sái do método se nada foi alterado
    if (valores.isEmpty()) {
      return;
    }

    // Remove o último espaço e a última vírgula
    sql.setLength(sql.length() - 2);

    // Adiciona o WHERE
    sql.append(" WHERE id = ?");
    valores.add(id);

    // Prepara, preenche e executa o comando
    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
      for (int i = 0; i < valores.size(); i++) {
        pstmt.setObject(i + 1, valores.get(i));
      }

      pstmt.executeUpdate();

      // Commita as alterações
      conn.commit();

    } catch (SQLException e) {
      // Faz o rollback das alterações e propaga a exceção
      conn.rollback();
      throw e;
    }
  }

  // === DELETE ===
  public void remover(int id) throws SQLException {
    // Prepara o comando
    String sql = "DELETE FROM super_adm WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // Completa os parâmetros faltantes
      pstmt.setInt(1, id);

      // Executa o comando e commita as mudanças
      pstmt.executeUpdate();
      conn.commit();

    } catch (SQLException e) {
      // Faz o rollback das modificações e propaga a exceção
      conn.rollback();
      throw e;
    }
  }
}