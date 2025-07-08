<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Livros</title>
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
        .action-links a.edit {
            background-color: #ffc107;
            color: #333;
            padding: 5px 10px;
            border-radius: 4px;
            text-decoration: none;
            margin-right: 5px;
        }
        .action-links a.delete {
            background-color: #dc3545;
            color: white;
            padding: 5px 10px;
            border-radius: 4px;
            text-decoration: none;
        }
        .new-livro-btn {
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
<h1>Livros</h1>

<a href="${pageContext.request.contextPath}/livros/novo" class="new-livro-btn">Novo Livro</a>

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
                    <button type="submit" class="edit">Editar</button>
                </form>

                <form action="${pageContext.request.contextPath}/livros/excluir/${livro.id_liv}" method="post" style="display:inline;">
                    <button type="submit" class="delete" onclick="return confirm('Tem certeza que deseja excluir este livro?')">Excluir</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${not empty mensagem}">
        <c:choose>
            <c:when test="${mensagem.startsWith('Erro')}">
                <p style="color: #D8000C; background-color: #FFBABA; border: 1px solid; padding: 10px; border-radius: 5px;">
                        ${mensagem}
                </p>
            </c:when>
            <c:otherwise>
                <p style="color: #4F8A10; background-color: #DFF2BF; border: 1px solid; padding: 10px; border-radius: 5px;">
                        ${mensagem}
                </p>
            </c:otherwise>
        </c:choose>
    </c:if>
</table>
<a href="${pageContext.request.contextPath}/home" class="back-link">Voltar</a>
</body>
</html>