<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>TechNova - Accueil</title>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/" class="nav-item">Accueil</a>
    <a href="${pageContext.request.contextPath}/users" class="nav-item">Gestion des Utilisateurs</a>
</div>
<div class="container">
    <h1>${message}</h1>

    <c:if test="${totalUsers != null}">
        <div class="dashboard-stats">
            <div class="stat-card">
                <h3>Nombre total d'utilisateurs</h3>
                <p class="stat-number">${totalUsers}</p>
            </div>

            <div class="stat-card warning">
                <h3>Utilisateurs expirés</h3>
                <p class="stat-number">${expiredUsers}</p>
            </div>
        </div>

        <c:if test="${not empty nationaliteStats}">
            <div class="nationality-section">
                <h2>Distribution par Nationalité</h2>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Nationalité</th>
                        <th>Nombre</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${nationaliteStats}" var="entry">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </c:if>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/users" class="btn btn-primary">Gérer les Utilisateurs</a>
    </div>
</div>
</body>
</html>