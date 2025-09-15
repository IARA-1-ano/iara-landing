<%@ page import="com.dto.PagamentoDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ryanmoraes-ieg
  Date: 11/09/2025
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Landing Teste</title>
</head>
<body>
    <%
  List<PagamentoDTO> pagamentos = (List<PagamentoDTO>) request.getAttribute("pagamentos");
  %>
<h1>Pagamentos</h1>
<a href="${pageContext.request.contextPath}/area-restrita/index">Voltar à área restrita</a>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Valor Pago</th>
        <th>Status</th>
        <th>Data de Vencimento</th>
        <th>Data de Pagamento</th>
        <th>Tipo de Pagamento</th>
        <th>ID da Fabrica</th>
    </tr>
    <% for (PagamentoDTO pagamento : pagamentos) { %>
    <tr>
        <td><%= pagamento.getId() %>
        </td>
        <td><%= pagamento.getValorPago() %>
        </td>
        <td><%= pagamento.getStatus() %>
        </td>
        <td><%= pagamento.getDataVencimento() %>
        </td>
        <td><%= pagamento.getDataPagamento() %>
        </td>
        <td><%= pagamento.getTipoPagamento() %>
        </td>
        <td><%= pagamento.getFkFabrica() %>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/area-restrita/update-pagamento" method="get">
                <input type="hidden" name="id" value="<%= pagamento.getId() %>">
                <button type="submit">Editar</button>
            </form>
            <form action="${pageContext.request.contextPath}/area-restrita/delete-pagamento" method="get">
                <input type="hidden" name="id" value="<%= pagamento.getId() %>">
                <button type="submit">Deletar</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<a href="${pageContext.request.contextPath}/area-restrita/pagamento/cadastro.html">Cadastrar novo Plano</a>
</body>
</html>
