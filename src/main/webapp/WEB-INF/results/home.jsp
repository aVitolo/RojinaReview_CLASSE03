<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home - Rojina</title>
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/foot.css">
    <link rel="stylesheet" href="./css/navebar.css">
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/results/navebar.jsp" %>
    <section class="home">
        <section class="hot">
            <img src="./images/utility/logoNT.png" alt="logo" class="logo" decoding="async">
            <section class="articolo">
                <c:choose>
                    <c:when test="${copertina.getClass().simpleName =='Recensione'}">
                        <a href="/Rojina_Review_war/getResource?type=reviews&id=${copertina.id}">
                    </c:when>
                    <c:when test="${copertina.getClass().simpleName =='Notizia'}">
                        <a href="/Rojina_Review_war/getResource?type=news&id=${copertina.id}">
                    </c:when>
                </c:choose>
                    <img src="<c:out value='${copertina.immagine}'/>" alt="copertina" decoding="async">
                    <div class="articolo-content">
                        <h3><c:out value="${copertina.getClass().simpleName}"/></h3>
                        <h2><c:out value='${copertina.titolo}'/></h2>
                        <p><c:out value='${fn:substring(copertina.testo, 0, 50)}'/></p>
                        <c:if test="${copertina.getClass().simpleName =='Recensione'}">
                            <p class="voto">${copertina.voto}</p>
                        </c:if>
                    </div>
                </a>
            </section>
            <img src="./images/utility/logoNT.png" alt="logo" class="logo" decoding="async">
        </section>

            <section class="articoli">
            <c:forEach items="${articoli}" var="articolo">
                <div class="articolo">
                    <c:choose>
                        <c:when test="${articolo.getClass().simpleName =='Recensione'}">
                            <a href="/Rojina_Review_war/getResource?type=reviews&id=${articolo.id}">
                        </c:when>
                        <c:when test="${articolo.getClass().simpleName =='Notizia'}">
                            <a href="/Rojina_Review_war/getResource?type=news&id=${articolo.id}">
                        </c:when>
                    </c:choose>
                        <img src="${articolo.immagine}" alt="copertina" decoding="async">
                        <div class="articolo-content">
                            <h3>${articolo.getClass().simpleName}</h3>
                            <h2>${articolo.titolo}</h2>
                            <p>${fn:substring(articolo.testo, 0, 50)}</p>
                            <c:if test="${articolo.getClass().simpleName =='Recensione'}">
                                <p class="voto">${articolo.voto}</p>
                            </c:if>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </section>
    </section>
    <%@ include file="/html/footer.html" %>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/filter.js"></script>
</body>
</html>