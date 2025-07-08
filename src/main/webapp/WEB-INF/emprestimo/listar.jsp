<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Empréstimos</title>
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
        .devolver-btn {
            background-color: #28a745;
            color: white;
            padding: 5px 10px;
            border-radius: 4px;
            text-decoration: none;
        }
    </style>
</head>
<body>

<h1>Empréstimos</h1>

<a href="${pageContext.request.contextPath}/emprestimos/novo">Novo Empréstimo</a>

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
<%--                    <a href="${pageContext.request.contextPath}/emprestimos/devolver/${emp.id_emp}" class="devolver-btn">Devolver</a>--%>
                    <form action="${pageContext.request.contextPath}/emprestimos/devolver/${emp.id_emp}" method="post" style="display:inline;">
                        <button type="submit" class="devolver-btn" onclick="return confirm('Tem certeza que deseja devolver esse livro?')">
                            Devolver
                        </button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty mensagem}">
    <c:choose>
        <c:when test="${mensagem.startsWith('Erro')}">
            <p style="color: #8f0008; background-color: #FFBABA; border: 1px solid; padding: 10px; border-radius: 5px;">
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

<a href="${pageContext.request.contextPath}/home" class="back-link">Voltar</a> <%-- se for usuario, volta para a pagina de login (nao tem acesso a home) --%>
</body>
</html>