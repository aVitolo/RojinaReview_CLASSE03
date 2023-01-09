<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(session.getAttribute("videogiocatore") == null) //se non sei videogiocatore non puoi entrare nell'area videogiocatore
    response.sendRedirect("./home");%>
<c:set var="videogiocatore" scope="page" value="${sessionScope.get('videogiocatore')}" />
<html>
<head>
    <title>Area videogiocatore</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/userArea.css">
</head>
<body>
<div class="sidebar">
    <header>${videogiocatore.nickname}</header>


    <div id="homeButton">
        <a href="/Rojina_Review_war/home">
            <img id="logo" src="./static/images/utility/rojinah.png">
        </a>
    </div>

    <ul>

        <li>
            <a href="/Rojina_Review_war/userArea">
                <span class="title">Profilo</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userChangeData">
                <span class="title">Modifica Dati</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userOrders">
                <span class="title">Ordini</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userInformations">
                <span class="title">Fatturazione</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userVotes">
                <span class="title">Voti e commenti</span>
            </a>
        </li>
        <li id="logout">
            <a href="/Rojina_Review_war/logout">
                <span class="title">Logout</span>
            </a>
        </li>
    </ul>

</div>

</body>
</html>
