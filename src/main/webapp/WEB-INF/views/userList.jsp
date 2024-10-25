<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Utilisateurs</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Liste des Utilisateurs</h1>
    <a href="?action=add" class="btn btn-primary">Ajouter un Utilisateur</a>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Pièce d'Identité</th>
            <th>Nationalité</th>
            <th>Date d'Inscription</th>
            <th>Date d'Expiration</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.nom}</td>
                <td>${user.prenom}</td>
                <td>${user.pieceIdentite}</td>
                <td>${user.nationalite}</td>
                <td>${user.dateInscription}</td>
                <td>${user.dateExpiration}</td>
                <td>
                    <a href="?action=edit&id=${user.id}">Modifier</a>
                    <a href="?action=delete&id=${user.id}"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')">
                        Supprimer
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>