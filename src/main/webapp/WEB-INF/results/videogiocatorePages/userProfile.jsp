<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area videogiocatore</title>
    <link rel="stylesheet" href="./static/css/master.css">
</head>
<body>
<c:set var="videogiocatore" scope="page" value="${sessionScope.get('videogiocatore')}" />
<%@ include file="/WEB-INF/results/videogiocatorePages/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Profilo</h1>
    <div class="information">
        <div>
            <h3 class="userAttribute">Nickname:</h3>
            ${videogiocatore.nickname}
        </div>
        <div class="userEmail">
            <h3 class="userAttribute">Email:</h3>
            ${videogiocatore.email}
        </div>

        <div class="userImage">
            <img src="${videogiocatore.immagine}", alt="userImage">
        </div>





        <c:if test="${videogiocatore.nome != null}">
            <div>
                <h3 class="userAttribute">Nome:</h3>
                ${videogiocatore.nome} ${videogiocatore.cognome}
            </div>
        </c:if>



    </div>
</div>


</body>
</html>
