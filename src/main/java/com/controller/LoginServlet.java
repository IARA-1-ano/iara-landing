package com.controller;

import com.dao.LoginDAO;
import com.dto.LoginDTO;
import com.dto.SuperAdmDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login-handler")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Dados da requisição
    String email = req.getParameter("email");
    String senha = req.getParameter("senha");
    LoginDTO credenciais = new LoginDTO(email, senha);
    HttpSession session = req.getSession();

    // Dados da resposta
    SuperAdmDTO superAdm;
    String destino = "login.html";
    StatusResposta status = StatusResposta.ERRO_INTERNO;

    try (LoginDAO dao = new LoginDAO()) {
      // Tenta fazer login e prepara os dados da resposta de acordo
      superAdm = dao.login(credenciais);

      if (superAdm != null) {
        // Guarda o usuário na sessão
        session.setAttribute("usuario", superAdm);
        destino = "index.html";
        status = StatusResposta.OK;

      } else {
        status = StatusResposta.ACESSO_NEGADO;
      }
    } catch (SQLException e) {
      // Se houver alguma exceção, registra no terminal
      System.err.println("Erro ao executar operação no banco:");
      e.printStackTrace(System.err);

    } catch (ClassNotFoundException e) {
      System.err.println("Falha ao carregar o driver postgresql:");
      e.printStackTrace(System.err);

    } catch (Throwable e) {
      System.err.println("Erro inesperado:");
      e.printStackTrace(System.err);

    } finally {
      // Adiciona o status como atributo da request
      req.setAttribute("status", status.toString());

      // Redireciona a request par a página jsp
      RequestDispatcher rd = req.getRequestDispatcher(destino);
      rd.forward(req, resp);
    }
  }
}
