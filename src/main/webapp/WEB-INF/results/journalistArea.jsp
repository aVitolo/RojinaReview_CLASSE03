<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("giornalista") == null) //se non sei giornalista non puoi entrare nell'area giornalista
        response.sendRedirect("./home");
%>

<html>
<head>
    <title>Area giornalista</title>
</head>
<body>
<div class="journalistMain">
    <div id="homeButton">
        <a href="/Rojina_Review_war/home">
            <img src="images/utility/rojinah.png"
        </a>
    </div>
    <ul class="journalistMenu">
        <li class="currentJournalistMenu">
            <a href="/Rojina_Review_war/journalistArea">
                <span class="iconArea"></span>
                <span class="menuName">Profilo</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/journalistGames">
                <span class="iconArea"></span>
                <span class="menuName">Giochi</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/journalistReviews">
                <span class="iconArea"></span>
                <span class="menuName">Recensioni</span>
            </a>
        </li>
        <li>
            <a href="/Rojina_Review_war/journalistNews">
                <span class="iconArea"></span>
                <span class="menuName">Notizie</span>
            </a>
        </li>
    </ul>

</div>

</body>
</html>
