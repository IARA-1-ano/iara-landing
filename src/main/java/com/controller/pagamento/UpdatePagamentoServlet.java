package com.controller.pagamento;

import com.dao.PagamentoDAO;
import com.dao.PlanosDAO;
import com.dto.PagamentoDTO;
import com.dto.PlanosDTO;
import com.model.Pagamento;
import com.model.Planos;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet ("/area-restrita/update-pagamento")
public class UpdatePagamentoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Dados da request
        int id = Integer.parseInt(req.getParameter("id"));

        // Dados da resposta
        PagamentoDTO pagamento;
        boolean erro = true;

        try (PagamentoDAO dao = new PagamentoDAO()) {
            // Recupera os dados originais para display
            pagamento = dao.getPagamentoById(id);
            req.setAttribute("infosPagamento", pagamento);

            // Setta erro como false
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

        if (erro) {
            resp.sendRedirect(req.getContextPath() + "/erro.html");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("pagamento/editar.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Definindo formatacao de data
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //Dados da Request
        int id = Integer.parseInt(req.getParameter("id"));
        double valorPago = Double.parseDouble(req.getParameter("valorPago"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        LocalDate dataVencimento = LocalDate.parse(req.getParameter("dataVencimento"), dateTimeFormatter);
        LocalDate dataPagamento = LocalDate.parse(req.getParameter("dataPagamento"), dateTimeFormatter);
        String tipoPagamento = req.getParameter("tipoPagamento");
        Integer fkFabrica = Integer.parseInt(req.getParameter("fkFabrica"));
        Pagamento alterado = new Pagamento(id, valorPago, status, dataVencimento, dataPagamento, tipoPagamento, fkFabrica);

        // Dados da resposta
        String destino = "/erro.html";

        try (PagamentoDAO dao = new PagamentoDAO()) {
            // Recupera as informações originais do banco
            Pagamento original = dao.getCamposAlteraveis(id);

            // Salva as informações no banco
            dao.atualizar(original, alterado);

            // Setta o destino para a página de display
            destino = "/area-restrita/create-read-pagamento";

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

        resp.sendRedirect(req.getContextPath() + destino);
    }
}
