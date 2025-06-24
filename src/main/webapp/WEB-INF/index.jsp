<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sistema de Gerenciamento</title>
</head>
<body>

<c:if test="${empty sessionScope.usuarioLogado}">
    <c:redirect url="/"/>
</c:if>

<h2>Sistema de Gerenciamento</h2>
<ul>
    <c:if test="${sessionScope.usuarioLogado.tipo_us eq 'ADMIN'}">
        <li><a href="${pageContext.request.contextPath}/autores">Autores</a></li>
        <li><a href="${pageContext.request.contextPath}/livros">Livros</a></li>
        <li><a href="${pageContext.request.contextPath}/emprestimos">Emprestimos</a></li>
    </c:if>
    <li><a href="${pageContext.request.contextPath}/meus-emprestimos">Meus Empréstimos</a></li>
    <li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
</ul>

</body>
</html>