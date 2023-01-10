<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("giornalista") == null) //se non sei giornalista non puoi entrare nell'area giornalista
        response.sendRedirect("./home");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="giornalista" scope="page" value="${sessionScope.get('giornalista')}" />

<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/journalistArea.css">
</head>
<body>
<div class="sidebar">
    <header>${giornalista.nome} ${giornalista.cognome}</header>

    <div id="homeButton">
        <a href="/Rojina_Review_war/home">
            <img id="logo" src="./static/images/utility/rojinah.png">
        </a>
    </div>
    <ul class="journalistMenu">
        <li class="currentJournalistMenu">
            <a href="/Rojina_Review_war/journalistArea">
                <span class="menuName">Profilo</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/giornalistaModificaDati">
                <span class="menuName">Modifica Dati</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/journalistGames">
                <span class="menuName">Giochi</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/journalistReviews">
                <span class="menuName">Recensioni</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/journalistNews">
                <span class="menuName">Notizie</span>
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
