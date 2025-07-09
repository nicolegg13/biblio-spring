<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home - Sistema Biblioteca</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Bem-vindo, ${sessionScope.usuarioLogado.nome_us}!</h1>
<p>Você está logado como: ${sessionScope.usuarioLogado.tipo_us}</p>

<nav>
    <ul>
        <c:if test="${sessionScope.usuarioLogado.tipo_us eq 'ADMIN'}">
            <li><a href="${pageContext.request.contextPath}/autores">Gerenciar Autores</a></li>
            <li><a href="${pageContext.request.contextPath}/livros">Gerenciar Livros</a></li>
            <li><a href="${pageContext.request.contextPath}/emprestimos">Gerenciar Empréstimos</a></li>
        </c:if>
        <c:if test="${sessionScope.usuarioLogado.tipo_us eq 'USUARIO'}">
            <li><a href="${pageContext.request.contextPath}/emprestimos">Meus Empréstimos</a></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
    </ul>
</nav>
</body>
</html>