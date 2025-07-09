<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Acesso Negado</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Acesso Negado!</h1>
    <p>${mensagemAcessoNegado}</p>
    <p>Você não tem permissão para acessar esta página.</p>
<%--    <p><a href="${pageContext.request.contextPath}/home">Voltar para Home</a></p>--%>
    <p><a href="javascript:history.back()">voltar para a página anterior</a></p>
</div>
</body>
</html>