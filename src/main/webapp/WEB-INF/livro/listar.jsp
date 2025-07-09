<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Livros</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Livros</h1>

<a href="${pageContext.request.contextPath}/livros/novo" class="btn btn-primary">Novo Livro</a>

<table>
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>ISBN</th>
        <th>Ano Publicação</th>
        <th>Autor</th>
        <th>Disponível</th>
        <th>Ações</th>
    </tr>
    <c:forEach items="${livros}" var="livro">
        <tr>
            <td>${livro.id_liv}</td>
            <td>${livro.titulo_liv}</td>
            <td>${livro.isbn_liv}</td>
            <td>${livro.ano_publicacao_liv}</td>
            <td>${livro.nome_autor}</td>
            <td>${livro.disponivel_liv ? "Sim" : "Não"}</td>
            <td class="action-links">
                <form action="${pageContext.request.contextPath}/livros/editar/${livro.id_liv}" method="get" style="display:inline;">
                    <button type="submit" class="btn btn-sm btn-warning">Editar</button> </form>

                <form action="${pageContext.request.contextPath}/livros/excluir/${livro.id_liv}" method="post" style="display:inline;">
                    <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Tem certeza?')">Excluir</button> </form>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${not empty mensagem}">
        <c:choose>
            <c:when test="${mensagem.startsWith('Erro')}">
                <p class="msg-feedback msg-erro">${mensagem}</p>
            </c:when>
            <c:otherwise>
                <p class="msg-feedback msg-sucesso">${mensagem}</p>
            </c:otherwise>
        </c:choose>
    </c:if>
</table>
<a href="${pageContext.request.contextPath}/home" class="back-link">Voltar</a>
</body>
</html>