<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home - Rojina</title>
    <!- includo i fogli di stile -->
    <link rel="stylesheet" href="./static/css/home.css">
    <link rel="stylesheet" href="./static/css/foot.css">
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/master.css">
</head>
<body>
<div class="container">
    <!- includo la navebar -->
    <%@ include file="/WEB-INF/results/utilities/navebar.jsp" %>

    <!- home e la sezione dedicate alle notizie e alle recenzioni e si divide in due sezioni
       hot: notizia piu recente
       articoli: merge tra notizie e recensioni
    -->
    <section class="home">
        <section class="hot">
            <!-Al fianco della copertina vi e il logo con visulizazzione responsive-->
            <img src="./static/images/utility/logoNT.png" alt="logo" class="logo" decoding="async">
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
                        <h2><c:out value='${copertina.nome}'/></h2>
                        <p><c:out value='${fn:substring(copertina.testo, 0, 50)}'/></p>
                        <c:if test="${copertina.getClass().simpleName =='Recensione'}">
                            <p class="parere">${copertina.parere}</p>
                        </c:if>
                    </div>
                </a>
            </section>
            <img src="./static/images/utility/logoNT.png" alt="logo" class="logo" decoding="async">
        </section>

            <section class="articoli">
            <c:forEach items="${articoli}" var="articolo">
                <section class="articolo">
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
                            <h2>${articolo.nome}</h2>
                            <p>${fn:substring(articolo.testo, 0, 50)}</p>
                            <c:if test="${articolo.getClass().simpleName =='Recensione'}">
                                <p class="parere">${articolo.votoGiornalista}</p>
                            </c:if>
                        </div>
                    </a>
                </section>
            </c:forEach>
        </section>
    </section>

    <!- includo il footer -->
    <%@ include file="/static/html/footer.html" %>
</div>
</body>
</html>