<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Autores</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
            color: #333;
        }
        h1 {
            color: #007bff;
            margin-bottom: 20px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #e9ecef;
            color: #555;
            font-weight: bold;
        }
        tr:hover {
            background-color: #e2e6ea;
        }
        .actions a {
            text-decoration: none;
            padding: 5px 10px;
            border-radius: 4px;
            margin-right: 5px;
        }
        .actions a.edit {
            background-color: #ffc107;
            color: #333;
        }
        .actions a.delete {
            background-color: #dc3545;
            color: white;
        }
        .btn-novo {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            margin-bottom: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>
<h1>Autores</h1>

<a href="${pageContext.request.contextPath}/autores/novo" class="btn-novo">Novo Autor</a>

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
                    <button type="submit" class="edit">Editar</button>
                </form>

                <form action="${pageContext.request.contextPath}/autores/excluir/${autor.id_aut}" method="post" style="display:inline;">
                    <button type="submit" class="delete" onclick="return confirm('Tem certeza que deseja excluir este autor?')">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${not empty mensagem}">
    <c:choose>
        <c:when test="${mensagem.startsWith('Erro')}">
<%--            erro--%>
            <p style="color: #8f0008; background-color: #FFBABA; border: 1px solid; padding: 10px; border-radius: 5px;">
                    ${mensagem}
            </p>
        </c:when>
        <c:otherwise>
<%--            sucesso--%>
            <p style="color: #4F8A10; background-color: #DFF2BF; border: 1px solid; padding: 10px; border-radius: 5px;">
                    ${mensagem}
            </p>
        </c:otherwise>
    </c:choose>
</c:if>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/home">Voltar para Home</a>
</div>
</body>
</html>