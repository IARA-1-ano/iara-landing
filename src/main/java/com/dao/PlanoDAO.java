package com.dao;

import com.model.Plano;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO extends DAO {
  public PlanoDAO() throws SQLException, ClassNotFoundException {
    super();
  }

  //Cadastrar novo Plano
  public void cadastrar(Plano plano) throws SQLException {
    //Comando SQL
    String sql = "INSERT INTO planos(nome, valor, descricao) VALUES (?,?,?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) { //Preparando comando SQL
      //Definindo variáveis no código SQL
      pstmt.setString(1, plano.getNome());
      pstmt.setDouble(2, plano.getValor());
      pstmt.setString(3, plano.getDescricao());
      //Salvando alterações no banco
      pstmt.execute();
      //Confirmando transações
      this.conn.commit();
    } catch (SQLException e) {
      //Cancelando transações
      conn.rollback();
      //Enviando exceção
      throw e;
    }

    }

  public Plano getPlanoById(int id) throws SQLException {
    // Prepara o comando
    String sql = "SELECT * FROM planos WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        return new Plano(
            id,
            rs.getString("nome"),
            rs.getDouble("valor"),
            rs.getString("descricao"));
      }
    }
  }

  public Plano getPlanoByNome(String nome) throws SQLException {
    // Prepara o comando
    String sql = "SELECT * FROM planos WHERE nome = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nome);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        return new Plano(
            rs.getInt("id"),
            nome,
            rs.getDouble("valor"),
            rs.getString("descricao"));
      }
    }
  }

  //Remover plano
  public void remover(int id) throws SQLException {
    // Prepara o comando
    String sql = "DELETE FROM planos WHERE id = ?";

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

  public void atualizar(Plano original, Plano alterado) throws SQLException {
    // Desempacotamento do model alterado
    int id = alterado.getId();
    String nome = alterado.getNome();
    Double valor = alterado.getValor();
    String descricao = alterado.getDescricao();

    // Monta o comando de acordo com os campos alterados
    StringBuilder sql = new StringBuilder("UPDATE planos SET ");
    List<Object> valores = new ArrayList<>();

    if (!original.getNome().equals(nome)) {
      sql.append("nome = ?, ");
      valores.add(nome);
    }

    if (original.getValor() != valor) {
      sql.append("valor = ?, ");
      valores.add(valor);
    }

    if (!original.getDescricao().equals(descricao)) {
      sql.append("descricao = ?, ");
      valores.add(descricao);
    }

    // Sái do metodo se nada foi alterado
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

  //Listar planos
  public List<Plano> listarPlanos(String campoFiltro, Object valorFiltro, String campoSequencia, String direcaoSequencia) throws SQLException {
    List<Plano> planos = new ArrayList<>();

        // Prepara o comando
        StringBuilder sql = new StringBuilder("SELECT * FROM planos");

        // Verificando campo do filtro
        if (campoFiltro != null) {
            switch (campoFiltro) {
                case "id" -> sql.append(" WHERE id = ?");
                case "nome" -> sql.append(" WHERE nome = ?");
                case "valor" -> sql.append(" WHERE valor = ?");
                case "descricao" -> sql.append(" WHERE descricao = ?");
                default -> throw new RuntimeException("valor inválido para chave de filtragem");
            }
        }

        //Verificando campo para ordenar a consulta
        if (campoSequencia != null) {
            switch (campoSequencia) {
                case "id" -> sql.append(" ORDER BY id");
                case "nome" -> sql.append(" ORDER BY nome");
                case "valor" -> sql.append(" ORDER BY valor");
                case "descricao" -> sql.append(" ORDER BY descricao");
                default -> throw new RuntimeException("valor inválido para chave de ordenação");
            }

            //Verificando direção da sequencia
            switch (direcaoSequencia) {
                case "crescente" -> sql.append(" ASC");
                case "decrescente" -> sql.append(" DESC");
            }
        }

        try (PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql))) {
            //Definindo parâmetro vazio
            if (campoFiltro != null) {
                pstmt.setObject(1, valorFiltro);
            }

            //Instanciando um ResultSet
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valor");
                String descricao = rs.getString("descricao");

        planos.add(new Plano(id, nome, valor, descricao));
      }
    }

    return planos;
  }

  //Campos Alteráveis
  public Plano getCamposAlteraveis(int id) throws SQLException {
    // Prepara o comando
    String sql = "SELECT * FROM planos WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          String nome = rs.getString("nome");
          Double valor = rs.getDouble("valor");
          String descricao = rs.getString("descricao");

          return new Plano(id, nome, valor, descricao);
        } else {
          throw new SQLException("Erro ao recuperar as informações do super adm");
        }
      }
    }
  }
}
