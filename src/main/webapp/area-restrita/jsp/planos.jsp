<%@ page import="java.util.List" %>
<%@ page import="com.model.Plano" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Plano> planos = (List<Plano>) request.getAttribute("planos");
%>
<html lang="pt-BR">
<head>
  <title>Landing Teste</title>
</head>
<body>
<h1>Planos</h1>
<a href="${pageContext.request.contextPath}/area-restrita/index">Voltar à área restrita</a>
<table border="1">
  <tr>
    <th>Id</th>
    <th>Nome</th>
    <th>Valor</th>
    <th>Descrição</th>
  </tr>
  <% for (Plano plano : planos) { %>
  <tr>
    <td><%= plano.getId() %>
    </td>
    <td><%= plano.getNome() %>
    </td>
    <td><%= plano.getValor() %>
    </td>
    <td><%= plano.getDescricao() %>
    </td>
    <td>
      <form action="${pageContext.request.contextPath}/area-restrita/planos" method="get">
        <input type="hidden" name="id" value="<%= plano.getId() %>">
        <input type="hidden" name="action" value="update">
        <button type="submit">Editar</button>
      </form>
      <form action="${pageContext.request.contextPath}/area-restrita/planos" method="post">
        <input type="hidden" name="id" value="<%= plano.getId() %>">
        <input type="hidden" name="action" value="delete">
        <button type="submit">Deletar</button>
      </form>
    </td>
  </tr>
  <% } %>
</table>
<a href="${pageContext.request.contextPath}/area-restrita/planos?action=create">Cadastrar novo Plano</a>
</body>
</html>
