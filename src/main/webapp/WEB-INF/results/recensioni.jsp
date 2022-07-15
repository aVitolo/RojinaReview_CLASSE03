<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recensioni - Rojina</title>
    <link rel="stylesheet" href="./css/recensioni.css">
    <link rel="stylesheet" href="./css/navebar.css">
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/navebar.jsp" %>
<section class="recensioni" id="wrap">

    <%@ include file="/WEB-INF/results/filter.jsp" %>

    <section class="articoli">
        <c:forEach items="${applicationScope['recensioni']}" var="articolo">
            <div class = "articolo" id="${articolo.id}">
                <a href="/Rojina_Review_war/getResource?type=reviews&id=${articolo.id}">
                <img src = "${articolo.immagine}" alt = "copertina" decoding="async">
                <div class = "articolo-content">
                    <h2>${articolo.titolo}</h2>
                    <p>${fn:substring(articolo.testo, 0, 50)}</p>
                    <p class="voto">${articolo.voto}</p>
                </div>
                </a>
            </div>
        </c:forEach>
    </section>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/filter.js"></script>
</body>
</html>
