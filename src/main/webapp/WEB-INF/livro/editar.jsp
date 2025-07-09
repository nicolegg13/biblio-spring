<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${empty livro.id_liv ? 'Novo Livro' : 'Editar Livro'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>${empty livro.id_liv ? 'Novo Livro' : 'Editar Livro'}</h1>

<form action="${pageContext.request.contextPath}/livros/salvar" method="post">
    <input type="hidden" name="id_liv" value="${livro.id_liv}">
    <input type="hidden" name="disponivel_liv" value="${livro.disponivel_liv}">

    <div>
        <label>Título:</label>
        <input type="text" name="titulo_liv" value="${livro.titulo_liv}" required>
    </div>

    <div>
        <label>ISBN:</label>
        <input type="text" name="isbn_liv" value="${livro.isbn_liv}" required>
    </div>

    <div>
        <label>Ano Publicação:</label>
        <input type="number" name="ano_publicacao_liv" value="${livro.ano_publicacao_liv}" required>
    </div>

    <div>
        <label>Autor:</label>
        <select name="id_autor_liv" required>
            <option value="">Selecione um autor</option>
            <c:forEach items="${autores}" var="autor">
                <option value="${autor.id_aut}"
                    ${livro.id_autor_liv eq autor.id_aut ? 'selected' : ''}>
                        ${autor.nome_aut}
                </option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-save">Salvar</button>
    <a href="${pageContext.request.contextPath}/livros" class="btn btn-cancel">Cancelar</a>
</form>
</body>
</html>