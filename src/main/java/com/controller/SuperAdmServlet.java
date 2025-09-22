package com.controller;

import com.dao.SuperAdmDAO;
import com.dto.CadastroSuperAdmDTO;
import com.dto.SuperAdmDTO;
import com.exception.ExcecaoDePagina;
import com.model.SuperAdm;
import com.utils.PasswordUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// TODO: criar opções de filtragem para o READ

@WebServlet("/area-restrita/superadms")
public class SuperAdmServlet extends HttpServlet {
  private static final String PAGINA_PRINCIPAL = "jsp/superadms.jsp";
  private static final String PAGINA_CADASTRO = "jsp/cadastro-superadm.jsp";
  private static final String PAGINA_EDICAO = "jsp/editar-superadm.jsp";
  private static final String PAGINA_ERRO = "/html/erro.html";
  private static final String ESSE_ENDPOINT = "/area-restrita/superadms";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    // Dados da request
    String action = req.getParameter("action");

    // Dados da resposta
    boolean erro = true;
    String destino = null;

    try {
      switch (action) {
        case "read" -> {
          List<SuperAdmDTO> superAdms = getListaSuperAdms();
          req.setAttribute("superAdms", superAdms);
          destino = PAGINA_PRINCIPAL;
        }

        case "update" -> {
          SuperAdmDTO superAdm = getInformacoesAlteraveis(req);
          req.setAttribute("infosSuperAdm", superAdm);
          destino = PAGINA_EDICAO;
        }

        default -> throw new RuntimeException("valor inválido para o parâmetro 'action': " + action);
      }

      erro = false;

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

    // Redireciona a request par a página jsp
    if (erro) {
      resp.sendRedirect(req.getContextPath() + PAGINA_ERRO);
    } else {
      RequestDispatcher rd = req.getRequestDispatcher(destino);
      rd.forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    // Dados da request
    String action = req.getParameter("action");

    // Dados da resposta
    String destino = PAGINA_ERRO;

    try {
      switch (action) {
        case "create" -> registrarSuperAdm(req);
        case "update" -> atualizarSuperAdm(req);
        case "delete" -> removerSuperAdm(req);
        default -> throw new RuntimeException("valor inválido para o parâmetro 'action': " + action);
      }

      destino = ESSE_ENDPOINT + "?action=read";

    } catch (ExcecaoDePagina e) {
      req.setAttribute("erro", e.getMessage());
      destino = e.getDestino();

    } catch (SQLException e) {
      // Se houver alguma exceção grave, registra no terminal
      System.err.println("Erro ao executar operação no banco:");
      e.printStackTrace(System.err);

    } catch (ClassNotFoundException e) {
      System.err.println("Falha ao carregar o driver postgresql:");
      e.printStackTrace(System.err);

    } catch (Throwable e) {
      System.err.println("Erro inesperado:");
      e.printStackTrace(System.err);

    }

    // Redireciona para a página de destino
    resp.sendRedirect(req.getContextPath() + destino);
  }

  private List<SuperAdmDTO> getListaSuperAdms() throws SQLException, ClassNotFoundException {
    try (SuperAdmDAO dao = new SuperAdmDAO()) {
      // Recupera os usuários do banco
      return dao.listarSuperAdms();
    }
  }

  private SuperAdmDTO getInformacoesAlteraveis(HttpServletRequest req) throws SQLException, ClassNotFoundException {
    // Dados da request
    String temp = req.getParameter("id");
    int id = Integer.parseInt(temp);

    try (SuperAdmDAO dao = new SuperAdmDAO()) {
      // Recupera os dados originais para display
      return dao.getSuperAdmById(id);
    }
  }

  private void registrarSuperAdm(HttpServletRequest req) throws SQLException, ClassNotFoundException {
    // Dados da request
    String senhaOriginal = req.getParameter("senha");
    String senhaHash = PasswordUtils.hashed(senhaOriginal);
    CadastroSuperAdmDTO credenciais = new CadastroSuperAdmDTO(
        req.getParameter("nome"),
        req.getParameter("cargo"),
        req.getParameter("email"),
        senhaHash
    );

    if (!senhaOriginal.matches(".{8,}")) {
      throw ExcecaoDePagina.senhaCurta(8, PAGINA_CADASTRO);
    }

    try (SuperAdmDAO dao = new SuperAdmDAO()) {
      // Cadastra o usuário e setta o destino para mostrar os usuários
      dao.cadastrar(credenciais);
    }
  }

  private void removerSuperAdm(HttpServletRequest req) throws SQLException, ClassNotFoundException {
    // Dados da request
    String temp = req.getParameter("id");
    int id = Integer.parseInt(temp);

    try (SuperAdmDAO dao = new SuperAdmDAO()) {
      // Deleta o superadm
      dao.remover(id);
    }
  }

  private void atualizarSuperAdm(HttpServletRequest req) throws SQLException, ClassNotFoundException {
    // Dados da request
    String temp = req.getParameter("id");
    int id = Integer.parseInt(temp);
    String nome = req.getParameter("nome");
    String cargo = req.getParameter("cargo");
    String email = req.getParameter("email");
    String senhaAtual = req.getParameter("senha_atual");
    String novaSenha = req.getParameter("nova_senha");
    SuperAdm alterado = new SuperAdm(id, nome, cargo, email, novaSenha);

    // Variáveis auxiliares
    String regexSenha = ".{8,}";

    try (SuperAdmDAO dao = new SuperAdmDAO()) {
      // Recupera as informações originais do banco
      SuperAdm original = dao.getCamposAlteraveis(id);

      // Se a senha foi alterada e a original estiver incorreta ou a nova for inválida, volta ao formulário
      if (!novaSenha.isBlank()) {
        if (!novaSenha.matches(regexSenha) || !PasswordUtils.comparar(senhaAtual, original.getSenha())) {
          ExcecaoDePagina e = !novaSenha.matches(regexSenha) ? ExcecaoDePagina.senhaCurta(8, null) : new ExcecaoDePagina("Senha incorreta", null);

          StringBuilder sb = new StringBuilder(ESSE_ENDPOINT);
          sb.append("?action=update&id=");
          sb.append(id);
          sb.append("&erro=");
          sb.append(e.getMensagemUrl());

          e.setDestino(sb.toString());
          throw e;
        }

        // Faz o hash da senha antes de salvar no banco
        String novaSenhaHash = PasswordUtils.hashed(alterado.getSenha());
        alterado.setSenha(novaSenhaHash);
      }

      // Salva as informações no banco
      dao.atualizar(original, alterado);
    }
  }
}
