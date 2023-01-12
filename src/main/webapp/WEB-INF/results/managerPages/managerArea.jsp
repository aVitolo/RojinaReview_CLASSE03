<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("manager") == null) //se non sei giornalista non puoi entrare nell'area giornalista
        response.sendRedirect("./home");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="giornalista" scope="page" value="${sessionScope.get('manager')}" />

<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/managerArea.css">
</head>
<body>
<div class="sidebar">
    <header>${manager.nome} ${manager.cognome}</header>
    <div id="homeButton">
        <a href="/Rojina_Review_war/home">
            <img id="logo" src="./static/images/utility/rojinah.png">
        </a>
    </div>
    <ul class="managerMenu">
        <li>
            <a href="./managerUpdateDataAreaServlet">
                <span class="menuName">Modifica dati</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/visualizzaShop">
                <span class="menuName">Shop</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/visualizzaCommentiSegnalati">
                <span class="menuName">Segnalazioni</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/visualizzaRichieste">
                <span class="menuName">Richieste</span>
            </a>
        </li>
    </ul>
</div>
</body>
</html>
