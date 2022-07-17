<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area utente</title>
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<c:set var="utente" scope="page" value="${sessionScope.get('utente')}" />
<%@ include file="/WEB-INF/results/utente/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Profilo</h1>
    <div class="information">
        <div class="userName">
            <h3 class="userAttribute">Nickname:</h3>
            ${utente.nickname}
        </div>
        <div class="userEmail">
            <h3 class="userAttribute">Email:</h3>
            ${utente.email}
        </div>
        <div class="userPassword">
            <h3 class="userAttribute">Password:</h3>
            ******
        </div>

        <div class="userImage">
            <img src="${utente.immagine}", alt="userImage">
        </div>





        <c:if test="${utente.nome != null}">
            <div class="userName">
                <h3 class="userAttribute">Nome:</h3>
                ${utente.nome} ${utente.cognome}
            </div>
        </c:if>
        <c:if test="${utente.eta != 0}">
            <div class="userEtà">
                <h3 class="userAttribute">Età:</h3>
                    ${utente.eta}
            </div>
        </c:if>


    </div>
</div>


</body>
</html>
