<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Novo Empréstimo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Novo Empréstimo</h1>

<form action="${pageContext.request.contextPath}/emprestimos/salvar" method="post">
    <div>
        <label>Livro:</label>
        <select name="id_livro_emp" required>
            <option value="">Selecione um livro</option>
            <c:forEach items="${livros}" var="livro">
                <option value="${livro.id_liv}">${livro.titulo_liv} (${livro.isbn_liv})</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <label>Usuário:</label>
        <select name="id_usuario_emp" required>
            <option value="">Selecione um usuário</option>
            <c:forEach items="${usuarios}" var="usuario">
                <option value="${usuario.id_us}">${usuario.nome_us} (${usuario.email_us})</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-save">Registrar Empréstimo</button>
    <a href="${pageContext.request.contextPath}/emprestimos" class="btn btn-cancel">Cancelar</a>
</form>
</body>
</html>