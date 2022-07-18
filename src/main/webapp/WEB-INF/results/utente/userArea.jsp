<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(session.getAttribute("utente") == null) //se non sei utente non puoi entrare nell'area utente
    response.sendRedirect("./home");%>
<c:set var="utente" scope="page" value="${sessionScope.get('utente')}" />
<html>
<head>
    <title>Area utente</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/userArea.css">
</head>
<body>
<div class="sidebar">
    <header>${utente.nickname}</header>


    <div id="homeButton">
        <a href="/Rojina_Review_war/home">
            <img id="logo" src="./images/utility/rojinah.png">
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
