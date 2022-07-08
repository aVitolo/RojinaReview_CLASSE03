<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aree Utente</title>
    <link rel="stylesheet" href="css/master.css">
</head>
<body>
<c:set var="commenti" scope="page" value="${requestScope['commenti']}"/>
<%@ include file="/WEB-INF/results/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Voti e commenti</h1>
    <div class="votes">

    </div>
    <div class="comments">
        <c:forEach items="commenti" var="commento">
            <a href="/Rojina_Review_War/getResource?type=${commento.resource}&id=${commento.id}">
                <div class="commento">
                    <h3 class="userName">${commento.utente}</h3>
                    <p class="commentoText">${commento.testo}</p>
                    <h3 class="dataCommento">${commento.data}</h3>
                </div>
            </a>
        </c:forEach>
    </div>
</div>
</body>
</html>
