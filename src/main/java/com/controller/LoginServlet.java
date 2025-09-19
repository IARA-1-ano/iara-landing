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
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    // Dados da requisição
    String action = req.getParameter("action");
    HttpSession session = req.getSession();

    // Dados da resposta
    String destino = "/html/erro.html";
    boolean redirect = true;

    try {
      switch(action) {
        case "login" -> {
          SuperAdmDTO usuario = login(req);

          if (usuario == null) {
            req.setAttribute("erro", "login falhou");
            destino = "jsp/login.jsp";
            redirect = false;

          } else {
            session.setAttribute("usuario", usuario);
            destino = "/area-restrita/index";
          }
        }

        case "logout" -> {
          logout(req);
          destino = "/index.html";
        }

        default -> throw new RuntimeException("valor inválido para o parâmetro 'action': " + action);
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
    }

    if (redirect) {
      resp.sendRedirect(req.getContextPath() + destino);
    } else {
      RequestDispatcher rd = req.getRequestDispatcher(destino);
      rd.forward(req, resp);
    }
  }

  private SuperAdmDTO login(HttpServletRequest req) throws SQLException, ClassNotFoundException {
    // Dados da requisição
    String email = req.getParameter("email");
    String senha = req.getParameter("senha");
    LoginDTO credenciais = new LoginDTO(email, senha);

    try (LoginDAO dao = new LoginDAO()) {
      // Tenta fazer login e prepara os dados da resposta de acordo
      return dao.login(credenciais);
    }
  }

  private void logout(HttpServletRequest req) {
    // Dados da request
    HttpSession session = req.getSession(false);

    if (session != null) {
      session.removeAttribute("usuario");
    }
  }
}
