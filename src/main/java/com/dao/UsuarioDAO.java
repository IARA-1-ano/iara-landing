package com.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.dto.UsuarioDTO;
import com.model.NivelAcesso;

public class UsuarioDAO extends DAO {
  public UsuarioDAO() throws SQLException {
    super();
  }

  public void remover(UsuarioDTO usuario) throws SQLException {
    // Recupera o id do usuário
    int id = usuario.getId();

    // Prepara o comando
    String sql = "DELETE FROM usuario WHERE id = ?";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

      // Completa o parâmetro faltante e executa o comando
      pstmt.setInt(1, id);
      pstmt.executeUpdate();

    } catch (SQLException e) {

      // Registra o erro no terminal e o propaga
      System.err.println(e.getMessage());
      throw e;
    }
  }

  public List<UsuarioDTO> listarUsuarios() throws SQLException {
    List<UsuarioDTO> usuarios = new ArrayList<>();

    // Prepara o comado e executa
    String sql = "SELECT * FROM usuario";

    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        // Armazenamento do resultado em variáveis
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        NivelAcesso nivelAcesso = NivelAcesso.fromInteger(rs.getInt("nivel_acesso"));
        
        // Conversão da data
        Date temp = rs.getDate("data_criacao");
        LocalDate dtCriacao = (temp == null ? null : temp.toLocalDate());

        boolean status = rs.getBoolean("status");
        int fkFabrica = rs.getInt("fk_fabrica");

        // Adição na lista
        usuarios.add(new UsuarioDTO(id, nome, email, nivelAcesso, dtCriacao, status, fkFabrica));
      }

      return usuarios;

    } catch (SQLException e) {

      // Registra o erro no terminal e o propaga
      System.err.println(e.getMessage());
      throw e;
    }
  }
}
