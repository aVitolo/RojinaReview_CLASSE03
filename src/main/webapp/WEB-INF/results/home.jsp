<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
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
        <section class="copertina">

        </section>
        <sectio class= "news">
            <h1>Notizie</h1>
            <c:forEach items="${notizie}" var="notizia">
                <section class= ="new">
                <h1>"${notizia.titolo}</h1><br>
                </section>
            </c:forEach>
        </sectio>

        <section class= "reviews">
            <h1>Recensioni</h1>
            <c:forEach items="${recensioni}" var="recensione">
                <section class= ="review">
                    <h1>"${recensione.titolo}</h1><br>
                </section>
            </c:forEach>
        </section>
    </section>
    <%@ include file="/html/footer.html" %>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/navebar.js"></script>
</html>