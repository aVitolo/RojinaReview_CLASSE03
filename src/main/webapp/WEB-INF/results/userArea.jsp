<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(session.getAttribute("utente") == null) //se non sei utente non puoi entrare nell'area utente
    response.sendRedirect("./home");%>

<html>
<head>
    <title>Area utente</title>
    <link rel="stylesheet" href="css/master.css">
</head>
<body>
<div class="userMain">
    <div id="homeButton">
        <a href="/Rojina_Review_war/home">
            <img src="images/utility/rojinah.png">
        </a>
    </div>
    <ul class="userMenu">
        <li class="currentUserMenu">
            <a href="/Rojina_Review_war/userArea">
                <span class="iconArea"></span>
                <span class="menuName">Profilo</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userOrders">
                <span class="iconArea"></span>
                <span class="menuName">Ordini</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userInformations">
                <span class="iconArea"></span>
                <span class="menuName">Informazioni di fatturazione</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/userVotes">
                <span class="iconArea"></span>
                <span class="menuName">Voti e commenti</span>
            </a>
        </li>
    </ul>

</div>

</body>
</html>
