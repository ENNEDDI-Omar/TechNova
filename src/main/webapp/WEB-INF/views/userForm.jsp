<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${user == null ? 'Ajouter' : 'Modifier'} un Utilisateur</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>${user == null ? 'Ajouter' : 'Modifier'} un Utilisateur</h1>

    <form method="post">
        <c:if test="${user != null}">
            <input type="hidden" name="id" value="${user.id}">
        </c:if>

        <div class="form-group">
            <label for="nom">Nom:</label>
            <input type="text" id="nom" name="nom" value="${user.nom}" required>
        </div>

        <div class="form-group">
            <label for="prenom">Prénom:</label>
            <input type="text" id="prenom" name="prenom" value="${user.prenom}" required>
        </div>

        <div class="form-group">
            <label for="pieceIdentite">Pièce d'Identité:</label>
            <input type="text" id="pieceIdentite" name="pieceIdentite"
                   value="${user.pieceIdentite}" ${user != null ? 'readonly' : 'required'}>
        </div>

        <div class="form-group">
            <label for="nationalite">Nationalité:</label>
            <input type="text" id="nationalite" name="nationalite"
                   value="${user.nationalite}" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                ${user == null ? 'Ajouter' : 'Modifier'}
            </button>
            <a href="${pageContext.request.contextPath}/users" class="btn btn-secondary">
                Annuler
            </a>
        </div>
    </form>
</div>
</body>
</html>