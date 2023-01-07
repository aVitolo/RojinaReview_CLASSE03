<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/richieste.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
<section class="wrap">
    <section class="richiesta-back">
        <h2>Giornalista</h2>
        <div class="richiesta-header">
            <p>Nome</p>
            <p>Cognome</p>
            <p>Email</p>
        </div>
        <c:forEach items="${requestScope['giornalisti']}" var="giornalista">
        <div class="richiesta">
            <p>${giornalista.nome}</p>
            <p>${giornalista.cognome}</p>
            <p>${giornalista.email}</p>
            <button>Accetta</button>
            <button>Rifiuta</button>
            </c:forEach>
    </section>
    <section class="richiesta-back">
        <h2>Manager</h2>
        <div class="richiesta-header">
            <p>Nome</p>
            <p>Cognome</p>
            <p>Email</p>
        </div>
        <c:forEach items="${requestScope['managers']}" var="manager">
            <div class="richiesta">
                <p>${manager.nome}</p>
                <p>${manager.cognome}</p>
                <p>${manager.email}</p>
                <button>Accetta</button>
                <button>Rifiuta</button>
            </div>
        </c:forEach>
    </section>
</section>
</body>
</html>
