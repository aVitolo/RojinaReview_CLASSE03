<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recensioni - Rojina</title>
    <link rel="stylesheet" href="./static/css/recensioni.css">
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/utilities/navebar.jsp" %>
<section class="recensioni" id="wrap">

    <%@ include file="/WEB-INF/results/utilities/filter.jsp" %>

    <section class="articoli">
        <c:forEach items="${requestScope['recensioni']}" var="articolo">
            <div class = "articolo" id="${articolo.id}">
                <a href="/Rojina_Review_war/getResource?type=reviews&id=${articolo.id}">
                <img src = "${articolo.immagine}" alt = "copertina" decoding="async">
                <div class = "articolo-content">
                    <h2>${articolo.nome}</h2>
                    <p>${fn:substring(articolo.testo, 0, 50)}</p>
                    <p class="parere">${articolo.votoGiornalista}</p>
                </div>
                </a>
            </div>
        </c:forEach>
    </section>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/static/js/navebar.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/static/js/filter.js"></script>
</body>
</html>
