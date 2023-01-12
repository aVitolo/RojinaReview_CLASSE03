<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/dettagliSegnalazioni.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
<section class="commenti">
    <h2>Dettagli</h2>
    <div class="opzioni">
        <a href="/Rojina_Review_war/gestisicSegnalazion?flag=0&id=${id}">
            <button>Rimuovi Segnalazioni</button>
        </a>
        <a href="/Rojina_Review_war/gestisicSegnalazion?flag=1&id=${id}">
                <button>Ban Utente</button>
        </a>
        <a href="/Rojina_Review_war/gestisicSegnalazion?flag=2&id=${id}">
            <button>Rimuovi Commento</button>
        </a>
    </div>
    <div class="commento">
        <p>Videogiocatore Segnalante</p>
        <p>Motivo</p>
        <p>CommentoAggiuntivo</p>
        <p>Data Segnalazione</p>
        <p></p>
    </div>
    <c:forEach items="${requestScope['segnalazioni']}" var="segnalazione">
        <div class="commento">
            <p>${segnalazione.videogiocatoreSegnalante}</p>
            <p>${segnalazione.motivo}</p>
            <p>${segnalazione.commentoAggiuntivo}</p>
            <p>${segnalazione.dataSegnalazione}</p>
        </div>
    </c:forEach>
</section>
</body>
</html>