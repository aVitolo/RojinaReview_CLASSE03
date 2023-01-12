<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/segnalazioni.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
<section class="commenti">
    <h2>Commenti Segnalati</h2>
    <div class="commento">
        <p>Commento</p>
        <p>Segnalto</p>
        <p>Contenuto</p>
        <p>Numero Segnalazioni</p>
        <p></p>
    </div>
    <c:forEach items="${requestScope['segnalazioni']}" var="segnalazione">
        <div class="commento">
            <p>${segnalazione.testo}</p>
            <p>${segnalazione.nicknameVideogiocatore}</p>
            <p>${segnalazione.nomeContenuto}</p>
            <p>${segnalazione.numeroSegnalazioni}</p>
            <a href="/Rojina_Review_war/dettagliSegnalazione?id=${segnalazione.id}"><button>Dettagli</button></a>
        </div>
    </c:forEach>
</section>
</body>
</html>
