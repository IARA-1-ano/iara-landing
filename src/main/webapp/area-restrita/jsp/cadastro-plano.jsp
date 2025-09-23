<%--
  Created by IntelliJ IDEA.
  User: lucasdonini-ieg
  Date: 22/09/2025
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <title>Página de cadastro - Planos</title>
</head>
<body>
<h1>Página de Teste para Cadastro</h1>
<form action="${pageContext.request.contextPath}/area-restrita/planos" method="post">
  <input type="hidden" name="action" value="create">
  <label>Nome:</label>
  <input type="text" name="nome">
  <label>Valor:</label>
  <input type="number" placeholder="R$" name="valor">
  <label>Descrição:</label>
  <input type="text" name="descricao">
  <input type="submit">
</form>
<a href="${pageContext.request.contextPath}/area-restrita/planos?action=read">Cancelar</a>
<%
  String erro = (String) request.getAttribute("erro");
  if (erro != null && !erro.isBlank()) {
%>
<p>
  <%= erro %>
</p>
<% } %>
</body>
</html>