<%@ page import="com.dto.AtualizacaoUsuarioDTO" %>
<%@ page import="com.model.NivelAcesso" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: lucasdonini-ieg
  Date: 08/09/2025
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  AtualizacaoUsuarioDTO usuario = (AtualizacaoUsuarioDTO) request.getAttribute("infosUsuario");
  Map<Integer, String> fabricas = (Map<Integer, String>) request.getAttribute("fabricas");
%>
<html>
<head>
  <title>Landing Teste</title>
</head>
<body>
<h1>Editar usuário - ID: <%= usuario.getId() %>
</h1>
<form action="${pageContext.request.contextPath}/area-restrita/update-usuario" method="post">
  <input type="hidden" name="id" value="<%= usuario.getId() %>">
  <input type="text" name="nome" value="<%= usuario.getNome() %>" placeholder="Novo nome">
  <input type="text" name="email" value="<%= usuario.getEmail() %>" placeholder="Novo email">
  <select name="nivel_acesso">
    <% for (NivelAcesso n : NivelAcesso.values()) { %>
    <option value="<%= n.nivel() %>" <%= n == usuario.getNivelAcesso() ? "selected" : "" %>><%= n.toString() %>
    </option>
    <% } %>
  </select>
  <select name="status">
    <% for (boolean b : List.of(true, false)) { %>
    <option value="<%= b %>" <%= b == usuario.getStatus() ? "selected" : "" %>><%= b ? "Ativo" : "Inativo" %>
    </option>
    <% } %>
  </select>
  <select name="fk_fabrica">
    <% for (int id : fabricas.keySet()) { %>
    <option value="<%= id %>" <%= id == usuario.getFkFabrica() ? "selected" : "" %>><%= fabricas.get(id) + " - ID: " + id %></option>
    <% } %>
  </select>
  <button type="submit">Salvar</button>
</form>
</body>
</html>
