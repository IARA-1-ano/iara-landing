<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Landing Teste</title>
</head>
<body>
  <h1>Landing page para Teste</h1>
  <a href="${pageContext.request.contextPath}/">Página inicial</a>

  <form action="${pageContext.request.contextPath}/login-handler" method="post">
    <input type="text" name="email" placeholder="Insira seu email">
    <input type="password" name="senha" placeholder="Insira a sua senha">
    <button type="submit">Entrar</button>
  </form>
</body>
</html>