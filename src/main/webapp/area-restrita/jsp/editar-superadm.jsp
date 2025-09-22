<%@ page import="com.dto.SuperAdmDTO" %><%--
  Created by IntelliJ IDEA.
  User: lucasdonini-ieg
  Date: 08/09/2025
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  SuperAdmDTO adm = (SuperAdmDTO) request.getAttribute("infosSuperAdm");
  String erro = request.getParameter("erro");
%>
<html>
<head>
  <title>Landing Teste</title>
</head>
<body>
<h1>
  Editar Super Adm - ID: <%= adm.getId() %>
</h1>

<form action="${pageContext.request.contextPath}/area-restrita/superadms" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="id" value="<%= adm.getId() %>">
  <input type="text" name="nome" value="<%= adm.getNome() %>" placeholder="Novo nome">
  <input type="text" name="cargo" value="<%= adm.getCargo() %>" placeholder="Novo cargo">
  <input type="email" name="email" value="<%= adm.getEmail() %>" placeholder="Novo email">
  <input type="text" name="senha_atual" placeholder="Insira sua senha atual">
  <input type="text" name="nova_senha" pattern=".{8,}" title="A senha deve ter pelo menos 8 dígitos"
         placeholder="Insira sua nova senha">
  <button type="submit">Salvar</button>
</form>

<% if (erro != null) { %>
<p>
  <%= erro %>
</p>
<% } %>

<a href="${pageContext.request.contextPath}/area-restrita/superadms?action=read">Cancelar</a>
</body>
</html>
