<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Autores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Autores</h1>

<a href="${pageContext.request.contextPath}/autores/novo" class="btn btn-primary">Novo Autor</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Nacionalidade</th>
        <th>Ano de Nascimento</th>
        <th class="actions">Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${autores}" var="autor">
        <tr>
            <td>${autor.id_aut}</td>
            <td>${autor.nome_aut}</td>
            <td>${autor.nacionalidade_aut}</td>
            <td>${autor.data_nascimento_aut}</td>
            <td class="actions">
                <form action="${pageContext.request.contextPath}/autores/editar/${autor.id_aut}" method="get" style="display:inline;">
                    <button type="submit" class="btn btn-sm btn-warning">Editar</button> </form>

                <form action="${pageContext.request.contextPath}/autores/excluir/${autor.id_aut}" method="post" style="display:inline;">
                    <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Tem certeza?')">Excluir</button> </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${not empty mensagem}">
    <c:choose>
        <c:when test="${mensagem.startsWith('Erro')}">
            <p class="msg-feedback msg-erro">${mensagem}</p>
        </c:when>
        <c:otherwise>
            <p class="msg-feedback msg-sucesso">${mensagem}</p> </c:otherwise>
    </c:choose>
</c:if>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/home">Voltar para Home</a>
</div>
</body>
</html>