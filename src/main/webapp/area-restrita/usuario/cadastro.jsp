<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  Map<Integer, String> fabricas = (Map<Integer, String>) request.getAttribute("nomes_fabricas");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Landing Teste</title>
</head>
<body>
<h1>Cadastro - Administrador</h1>
<form action="${pageContext.request.contextPath}/area-restrita/create-read-usuario" method="post">
  <input type="text" name="nome" placeholder="Nome">
  <input type="email" name="email" placeholder="Email">
  <input type="text" pattern=".{8,}" title="A senha deve ter 8 ou mais caractéres" name="senha" placeholder="Senha">
  <select name="fk_fabrica" required>
    <% for (int id : fabricas.keySet()) { %>
    <option value="<%= id %>"><%= fabricas.get(id) + " - ID: " + id %>
    </option>
    <% } %>
  </select>
  <button type="submit">Cadastrar</button>
</form>
<a href="${pageContext.request.contextPath}/area-restrita/create-read-usuario">Cancelar</a>
</body>
</html>