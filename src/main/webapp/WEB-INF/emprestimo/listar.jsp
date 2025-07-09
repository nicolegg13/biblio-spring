<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Empréstimos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Empréstimos</h1>

<a href="${pageContext.request.contextPath}/emprestimos/novo" class="btn btn-primary">Novo Empréstimo</a>

<table>
    <tr>
        <th>ID</th>
        <th>Livro</th>
        <th>Usuário</th>
        <th>Data Empréstimo</th>
        <th>Data Devolução Prevista</th>
        <th>Status</th>
        <th>Ações</th>
    </tr>
    <c:forEach items="${emprestimos}" var="emp">
        <tr>
            <td>${emp.id_emp}</td>
            <td>${emp.titulo_livro}</td>
            <td>${emp.nome_usuario}</td>
            <td>${emp.data_emprestimo_emp}</td>
            <td>${emp.data_devolucao_prevista_emp}</td>
            <td>${emp.status_emp}</td>
            <td>
                <c:if test="${emp.status_emp eq 'ATIVO'}">
                    <form action="${pageContext.request.contextPath}/emprestimos/devolver/${emp.id_emp}" method="post" style="display:inline;">
                        <button type="submit" class="btn btn-sm btn-warning" onclick="return confirm('Confirmar devolução?')">Devolver</button> </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
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

<a href="${pageContext.request.contextPath}/home" class="back-link">Voltar</a> <%-- se for usuario, volta para a pagina de login (nao tem acesso a home) --%>
</body>
</html>