<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${empty autor.id_aut ? 'Novo Autor' : 'Editar Autor'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>${empty autor.id_aut ? 'Novo Autor' : 'Editar Autor'}</h1>

<c:if test="${not empty mensagem}">
    <p style="color: red;">${mensagem}</p>
</c:if>

<form action="${pageContext.request.contextPath}/autores/salvar" method="post">
    <input type="hidden" name="id_aut" value="${autor.id_aut}">

    <div class="form-group">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome_aut" value="${autor.nome_aut}" required>
    </div>

    <div class="form-group">
        <label for="nacionalidade">Nacionalidade:</label>
        <input type="text" id="nacionalidade" name="nacionalidade_aut" value="${autor.nacionalidade_aut}">
    </div>

    <div class="form-group">
        <label for="data_nascimento">Data Nascimento:</label>
        <input type="date" id="data_nascimento" name="data_nascimento_aut"
               value="${autor.data_nascimento_aut}" required>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-success">Salvar</button>
        <a href="${pageContext.request.contextPath}/autores" class="btn btn-secondary">Cancelar</a>
    </div>
</form>
</body>
</html>