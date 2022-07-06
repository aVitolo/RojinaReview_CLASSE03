<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Giornalista</title>
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/journalistArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Profilo</h1>
    <div class="information">
        <div class="journalistName">
            <h3 class="journalistAttribute">Nome:</h3>
            ${sessionScope.get('giornalista').nome} ${sessionScope.get('giornalista').cognome}
        </div>
        <div class="journalistEmail">
            <h3 class="journalistAttribute">Email:</h3>
            ${sessionScope.get('giornalista').email}
        </div>
        <div class="journalistPassword">
            <h3 class="journalistAttribute">Password:</h3>
            ******
        </div>
        <div class="journalistImage">
            <img src="${sessionScope.get('giornalista').immagine}" , alt="image">
        </div>
        <div class="Logout">
            <a href="/Rojina_Review_war/journalistLogout">Logout</a>
        </div>
    </div>
</div>


</body>
</html>
