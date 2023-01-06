<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/notizie.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>

    <section class="notizie">
        <section>

        </section>
        <section class="articoli">
            <c:forEach items="${requestScope['prodotti']}" var="prodotto">
                <div class="articolo">
                    <img src="${prodotto.immagine}" , alt="copertina" decoding="async">
                    <div class="articolo-content">
                        <h2>${prodotto.nome}</h2>
                        <p>${prodotto.prezzo}</p>
                    </div>
                </div>
            </c:forEach>
        </section>
    </section>
</body>
</html>
