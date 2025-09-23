package com.dao;

import com.dto.CadastroFabricaDTO;
import com.model.Endereco;
import com.model.Fabrica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabricaDAO extends DAO {
  public FabricaDAO() throws SQLException, ClassNotFoundException {
    super();
  }

  public void cadastrar(CadastroFabricaDTO credenciais) throws SQLException {
    // Variáveis
    String nome = credenciais.getNome();
    String cnpj = credenciais.getCnpj();
    String email = credenciais.getEmail();
    String nomeEmpresa = credenciais.getNomeEmpresa();
    String ramo = credenciais.getRamo();
    int idEndereco = credenciais.getFkEndereco();
    int fkPlano = credenciais.getFkPlano();
    boolean status = true;

    // Preparação do comando
    String sql = """
        INSERT INTO fabrica (nome, cnpj_unidade, email_corporativo, nome_industria, status, ramo, id_endereco, fk_plano)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      // Preenchimento dos placeholders
      pstmt.setString(1, nome);
      pstmt.setString(2, cnpj);
      pstmt.setString(3, email);
      pstmt.setString(4, nomeEmpresa);
      pstmt.setBoolean(5, status);
      pstmt.setString(6, ramo);
      pstmt.setInt(7, idEndereco);
      pstmt.setInt(8, fkPlano);

      // Execução do update
      pstmt.executeUpdate();

      // Commit das alterações
      conn.commit();

    } catch (SQLException e) {

      // Faz o rollback das alterações
      conn.rollback();
      throw e;
    }
  }

  public List<Fabrica> listarFabricas(String campoFiltro, Object valorFiltro, String campoSequencia, String direcaoSequencia) throws SQLException {
    List<Fabrica> fabricas = new ArrayList<>();

    //Prepara o comando
    StringBuilder sql = new StringBuilder("""
        SELECT f.id as "id_fabrica", f.*, e.id as "id_endereco", e.*
        FROM fabrica f
        JOIN endereco e ON e.id = f.id_endereco
       """);

    //Verificando o campo do filtro
      if (campoFiltro!=null){
          switch(campoFiltro){
              case "id_industria" -> sql.append(" WHERE f.id_industria = ?");
              case "id_endereco" -> sql.append(" WHERE e.id = ?");
              case "nome" -> sql.append(" WHERE f.nome = ?");
              case "cnpj_unidade" -> sql.append(" WHERE f.cnpj_unidade = ?");
              case "status" -> sql.append(" WHERE f.status = ?");
              case "email_corporativo" -> sql.append(" WHERE f.email_corporativo = ?");
              case "nome_industria" -> sql.append(" WHERE f.nome_industria = ?");
              case "ramo" -> sql.append(" WHERE f.ramo = ?");
              case "fk_plano" -> sql.append(" WHERE f.fk_plano = ?");
              case "cep" -> sql.append(" WHERE e.cep = ?");
              case "numero" -> sql.append(" WHERE e.numero = ?");
              case "rua" -> sql.append(" WHERE e.rua = ?");
              default -> sql.append(" WHERE e.complemento = ?");
          }
      }

      //Verificando campo e direcao para ordernar a consulta
      if (campoSequencia!=null){
          switch (campoSequencia) {
              case "id_industria" -> sql.append(" ORDER BY f.id_industria");
              case "id_endereco" -> sql.append(" ORDER BY e.id");
              case "nome" -> sql.append(" ORDER BY f.nome");
              case "cnpj_unidade" -> sql.append(" ORDER BY f.cnpj_unidade");
              case "status" -> sql.append(" ORDER BY f.status = ?");
              case "email_corporativo" -> sql.append(" ORDER BY f.email_corporativo");
              case "nome_industria" -> sql.append(" ORDER BY f.nome_industria");
              case "ramo" -> sql.append(" ORDER BY f.ramo");
              case "fk_plano" -> sql.append(" ORDER BY f.fk_plano");
              case "cep" -> sql.append(" ORDER BY e.cep");
              case "numero" -> sql.append(" ORDER BY e.numero");
              case "rua" -> sql.append(" ORDER BY e.rua");
              default -> sql.append(" ORDER BY e.complemento");
          }

          //Verificando direção da sequencia
          switch(direcaoSequencia){
              case "crescente" -> sql.append(" ASC");
              case "decrescente" -> sql.append(" DESC");
          }
      }

    try (PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql))) {
        //Definindo parâmetro vazio
        if (campoFiltro!=null){
            pstmt.setObject(1, valorFiltro);
        }

        //Instanciando um ResultSet
        ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        // Informações da fábrica
        int idFabrica = rs.getInt("id_fabrica");
        String nome = rs.getString("nome");
        String cnpj = rs.getString("cnpj_unidade");
        boolean status = rs.getBoolean("status");
        String email = rs.getString("email_corporativo");
        String nomeEmpresa = rs.getString("nome_industria");
        String ramo = rs.getString("ramo");
        int fkPlano = rs.getInt("fk_plano");

        // Informações do endereco
        int idEndereco = rs.getInt("id_endereco");
        String cep = rs.getString("cep");
        int numero = rs.getInt("numero");
        String rua = rs.getString("rua");
        String complemento = rs.getString("complemento");

        // Cria e armazena os objetos
        Endereco endereco = new Endereco(idEndereco, cep, numero, rua, complemento);
        Fabrica fabrica = new Fabrica(idFabrica, nome, cnpj, status, email, nomeEmpresa, ramo, endereco, fkPlano);
        fabricas.add(fabrica);
      }

      return fabricas;
    }
  }

  public void atualizar(Fabrica original, Fabrica alteracoes) throws SQLException {
    // Desempacotamento do model
    int id = alteracoes.getId();
    String nome = alteracoes.getNome();
    String cnpj = alteracoes.getCnpj();
    Boolean status = alteracoes.getStatus();
    String email = alteracoes.getEmail();
    String nomeEmpresa = alteracoes.getNomeEmpresa();
    String ramo = alteracoes.getRamo();
    int fkPlano = alteracoes.getFkPlano();

    // Contrução do script dinâmico
    StringBuilder sql = new StringBuilder("UPDATE fabrica SET ");
    List<Object> valores = new ArrayList<>();

    if (!original.getNome().equals(nome)) {
      sql.append("nome = ?, ");
      valores.add(nome);
    }

    if (!original.getCnpj().equals(cnpj)) {
      sql.append("cnpj_unidade = ?, ");
      valores.add(cnpj);
    }

    if (original.getStatus() != status) {
      sql.append("status = ?, ");
      valores.add(status);
    }

    if (!original.getEmail().equals(email)) {
      sql.append("email_corporativo = ?, ");
      valores.add(email);
    }

    if (!original.getNomeEmpresa().equals(nomeEmpresa)) {
      sql.append("nome_industria = ?, ");
      valores.add(nomeEmpresa);
    }

    if (!original.getRamo().equals(ramo)) {
      sql.append("ramo = ?, ");
      valores.add(ramo);
    }

    // Retorno se nada foi alterado
    if (valores.isEmpty()) {
      return;
    }

    // Remoção do último ", "
    sql.setLength(sql.length() - 2);

    // Adiciona a cláusula WHERE
    sql.append(" WHERE id = ?");
    valores.add(id);

    // Execução do comando
    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
      for (int i = 0; i < valores.size(); i++) {
        pstmt.setObject(i + 1, valores.get(i));
      }

      pstmt.executeUpdate();
      conn.commit();

    } catch (SQLException e) {

      // Faz o rollback das modificações e propaga a exceção
      conn.rollback();
      throw e;
    }
  }

  public void remover(int id) throws SQLException {
    String sql = "DELETE FROM fabrica WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
      conn.commit();

    } catch (SQLException e) {

      // Faz o rollback das modificações
      conn.rollback();

      // Registra o erro no terminal e o propaga
      System.err.println(e.getMessage());
      throw e;
    }
  }

  public Fabrica getFabricaById(int id) throws SQLException {
    // Prepara o comando
    String sql = """
         SELECT
            f.id as "id_fabrica", f.*,
            e.id as "id_endereco", e.*
        FROM fabrica f
        JOIN endereco e ON e.id = f.id_endereco
        WHERE F.ID = ?
        """;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          // Informações da fábrica
          int idFabrica = rs.getInt("id_fabrica");
          String nome = rs.getString("nome");
          String cnpj = rs.getString("cnpj_unidade");
          boolean status = rs.getBoolean("status");
          String email = rs.getString("email_corporativo");
          String nomeEmpresa = rs.getString("nome_industria");
          String ramo = rs.getString("ramo");
          int fkPlano = rs.getInt("fk_plano");

          // Informações do endereco
          int idEndereco = rs.getInt("id_endereco");
          String cep = rs.getString("cep");
          int numero = rs.getInt("numero");
          String rua = rs.getString("rua");
          String complemento = rs.getString("complemento");

          // Cria e retorna o objeto
          Endereco endereco = new Endereco(idEndereco, cep, numero, rua, complemento);
          return new Fabrica(idFabrica, nome, cnpj, status, email, nomeEmpresa, ramo, endereco, fkPlano);

        } else {
          throw new SQLException("Falha ao recuperar o usuário");
        }
      }
    }
  }

  public Map<Integer, String> getNomes() throws SQLException {
    // Prepara o comando o instancia o objeto da resposta
    String sql = "SELECT id, nome FROM fabrica";
    Map<Integer, String> nomes = new HashMap<>();

    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        String nome = rs.getString("nome");
        int id = rs.getInt("id");
        nomes.put(id, nome);
      }
    }

    return nomes;
  }
}