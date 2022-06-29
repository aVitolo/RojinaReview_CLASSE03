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

    <h1>Latest News</h1>

    <%@ include file="/WEB-INF/results/filterShop.jsp" %>

    <section class="articoli">
        <c:forEach items="${applicationScope['prodotti']}" var="prodotto">
            <div class = "articolo">
                <img src = "./images/utility/back.jpg" alt = "copertina" decoding="async">
                <div class = "articolo-content">
                    <h2>${prodotto.nome}</h2>
                    <p>${prodotto.prezzo} $</p>
                </div>
            </div>
        </c:forEach>
    </section>

</section>

<%@ include file="/html/footer.html" %>
</body>
</html>
