package com.dao;

import com.model.Pagamento;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PagamentoDAO extends DAO {
  // Constantes
  public static final Map<String, String> camposFiltraveis = Map.of(
      "id", "Id",
      "valor", "Valor Pago",
      "status", "Status",
      "tipo_pagamento", "Tipo de Pagamento",
      "data_vencimento", "Data de Vencimento",
      "data_pagamento", "Data do Pagamento",
      "id_fabrica", "Fábrica"
  );

  // Construtor
  public PagamentoDAO() throws SQLException, ClassNotFoundException {
    super();
  }

  // Métodos Estáticos
  public static Object converterValor(String campo, String valor) {
    if (campo == null || campo.isBlank()) {
      return null;
    }

    return switch (campo) {
      case "id", "id_fabrica" -> Integer.parseInt(valor);
      case "valor" -> Double.parseDouble(valor);
      case "data_vencimento", "data_pagamento" -> LocalDate.parse(valor);
      case "tipo_pagamento" -> String.valueOf(valor);
      default -> throw new IllegalArgumentException();
    };
  }

  // Outros Métodos
  // === CREATE ===
  public void cadastrar(Pagamento pagamento) throws SQLException {
    //Comando SQL
    String sql = """
        INSERT INTO pagamento(valor, status, data_vencimento, data_pagamento, tipo_pagamento, id_fabrica)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) { //Preparando comando SQL
      //Comando SQL de Query do valor do plano referente a fkFabrica
      LocalDate dtPagamento = pagamento.getDataPagamento();
      LocalDate dtVencimento = pagamento.getDataVencimento();
      pstmt.setDouble(1, pagamento.getValor());
      pstmt.setBoolean(2, pagamento.getStatus());
      pstmt.setDate(3, (dtVencimento == null ? null : Date.valueOf(dtVencimento)));
      pstmt.setDate(4, (dtPagamento == null ? null : Date.valueOf(dtPagamento)));
      pstmt.setString(5, pagamento.getTipoPagamento());
      pstmt.setInt(6, pagamento.getIdFabrica());

      //Salvando alterações no banco
      pstmt.executeUpdate();

      //Confirmando transações
      conn.commit();

    } catch (SQLException e) {
      conn.rollback();
      throw e;
    }
  }

  // === READ ===
  public List<Pagamento> listar(String campoFiltro, Object valorFiltro, String campoSequencia, String direcaoSequencia) throws SQLException {
    List<Pagamento> pagamentos = new ArrayList<>();

    // Prepara o comando
    String sql = "SELECT * FROM pagamento";

    //Verificando o campo do filtro
    if (campoFiltro != null && camposFiltraveis.containsKey(campoFiltro)) {
      sql += " WHERE %s = ?".formatted(campoFiltro);
    }

    //Verificando campo e direcao para ordernar a consulta
    if (campoSequencia != null && camposFiltraveis.containsKey(campoSequencia)) {
      sql += " ORDER BY %s %s".formatted(campoSequencia, direcaoSequencia);

    } else {
      sql += " ORDER BY id ASC";
    }

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      //Definindo parâmetro vazio
      if (campoFiltro != null && camposFiltraveis.containsKey(campoFiltro)) {
        pstmt.setObject(1, valorFiltro);
      }

      //Instanciando um ResultSet
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          int id = rs.getInt("id");
          double valorPago = rs.getDouble("valor");
          boolean status = rs.getBoolean("status");

          Date dtVencimentoDate = rs.getDate("data_vencimento");
          LocalDate dtVencimento = (dtVencimentoDate == null ? null : dtVencimentoDate.toLocalDate());

          Date dtPagtoDate = rs.getDate("data_pagamento");
          LocalDate dtPagto = (dtPagtoDate == null ? null : dtPagtoDate.toLocalDate());

          String tipoPagamento = rs.getString("tipo_pagamento");
          int idFabrica = rs.getInt("id_fabrica");

          pagamentos.add(new Pagamento(id, valorPago, status, dtVencimento, dtPagto, tipoPagamento, idFabrica));
        }
      }
    }

    return pagamentos;
  }

  public Pagamento pesquisarPorId(int id) throws SQLException {
    // Prepara o comando
    String sql = "SELECT * FROM pagamento WHERE id = ?";
    Pagamento pagamento;

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        double valorPago = rs.getDouble("valor");
        boolean status = rs.getBoolean("status");

        Date dtVencimentoDate = rs.getDate("data_vencimento");
        LocalDate dtVencimento = (dtVencimentoDate == null ? null : dtVencimentoDate.toLocalDate());

        Date dtPagtoDate = rs.getDate("data_pagamento");
        LocalDate dtPagto = (dtPagtoDate == null ? null : dtPagtoDate.toLocalDate());

        String tipoPagamento = rs.getString("tipo_pagamento");
        int idFabrica = rs.getInt("id_fabrica");

        pagamento = new Pagamento(id, valorPago, status, dtVencimento, dtPagto, tipoPagamento, idFabrica);
      }
    }

    return pagamento;
  }

  public Pagamento getCamposAlteraveis(int id) throws SQLException {
    Pagamento p = pesquisarPorId(id);

    if (p == null) {
      throw new SQLException("Falha ao recuperar pagamento");
    }

    return p;
  }

  // === UPDATE ===
  public void atualizar(Pagamento original, Pagamento alterado) throws SQLException {
    // Desempacotamento do model alterado
    int id = alterado.getId();
    double valorPago = alterado.getValor();
    boolean status = alterado.getStatus();
    LocalDate dataVencimento = alterado.getDataVencimento();
    LocalDate dataPagamento = alterado.getDataPagamento();
    String tipoPagamento = alterado.getTipoPagamento();
    int idFabrica = alterado.getIdFabrica();

    // Monta o comando de acordo com os campos alterados
    StringBuilder sql = new StringBuilder("UPDATE pagamento SET ");
    List<Object> valores = new ArrayList<>();

    if (original.getValor() != valorPago) {
      sql.append("valor = ?, ");
      valores.add(valorPago);
    }

    if (original.getStatus() != status) {
      sql.append("status = ?, ");
      valores.add(status);
    }

    if (!Objects.equals(dataVencimento, original.getDataVencimento())) {
      sql.append("data_vencimento = ?, ");
      valores.add(dataVencimento);
    }

    if (!Objects.equals(dataPagamento, original.getDataPagamento())) {
      sql.append("data_pagamento = ?, ");
      valores.add(dataPagamento);
    }

    if (!Objects.equals(tipoPagamento, original.getTipoPagamento())) {
      sql.append("tipo_pagamento = ?, ");
      valores.add(tipoPagamento);
    }

    if (original.getIdFabrica() != idFabrica) {
      sql.append("id_fabrica = ?, ");
      valores.add(idFabrica);
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
    String sql = "DELETE FROM pagamento WHERE id = ?";

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
