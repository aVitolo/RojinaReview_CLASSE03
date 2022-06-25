<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rojina Review</title>
    <style><%@include file="/css/navebar.css"%></style>
    <style><%@include file="/css/footer.css"%></style>
    <style><%@include file="/css/home.css"%></style>
    <style><%@include file="/css/global.css"%></style>
</head>
<body>
    <%@ include file="/html/navebar.html" %>
    <section class="home">
        <section class="hot">
            <section class="articolo">
                <img src = "./images/back.jpg" alt = "copertina" decoding="async">
                <div class = "articolo-content">
                    <h2><c:out value='${copertina.titolo}'/></h2>
                    <p><c:out value='${fn:substring(copertina.testo, 0, 50)}'/>"...</p>
                </div>
            </section>
        </section>
        <section class="arcitoli">
            <h1>News</h1>
            <h1>Review</h1>
            <section class = "notizie">

                    <c:forEach items="${notizie}" var="notizia">
                        <div class = "articolo">
                            <img src = "./images/back.jpg" alt = "copertina" decoding="async">
                            <div class = "articolo-content">
                                    <h2>${notizia.titolo}</h2>
                                    <p>${fn:substring(notizia.testo, 0, 50)}</p>
                            </div>
                        </div>
                    </c:forEach>
            </section>
            <section class = "recensioni">
                <c:forEach items="${recensioni}" var="recensione">
                    <div class = "articolo">
                        <img src = "./images/back.jpg" alt = "copertina" decoding="async">
                        <div class = "articolo-content">
                            <h2>${recensione.titolo}</h2>
                            <p>${fn:substring(recensione.testo, 0, 50)}</p>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </section>
    </section>
    <%@ include file="/html/footer.html" %>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/navebar.js"></script>
</html>