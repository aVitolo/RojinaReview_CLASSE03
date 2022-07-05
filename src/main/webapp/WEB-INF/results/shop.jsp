<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notizie - Rojina</title>
    <link rel="stylesheet" href="./css/notizie.css">
    <link rel="stylesheet" href="./css/foot.css">
    <link rel="stylesheet" href="./css/navebar.css">
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/navebar.jsp" %>

<section class="notizie">

    <h1>Our Merch</h1>

    <%@ include file="/WEB-INF/results/filterShop.jsp" %>

    <section class="articoli">
        <c:forEach items="${applicationScope['prodotti']}" var="prodotto">
            <a href="/Rojina_Review_war/getResource?type=prodotto&id=${prodotto.id}">
                <div class = "articolo">
                    <img src = "${prodotto.immagine}" alt = "copertina" decoding="async">
                    <div class = "articolo-content">
                        <h2>${prodotto.nome}</h2>
                        <p>${prodotto.prezzo} $</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </section>

</section>

<%@ include file="/html/footer.html" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/filter.js"></script>

</body>
</html>
