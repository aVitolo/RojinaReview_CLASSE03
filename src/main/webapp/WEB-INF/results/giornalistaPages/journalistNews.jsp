<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/notizie.css">
    <style>
        img{
            opacity: 1;
        }
    </style>

</head>
<body>
<%@ include file="/WEB-INF/results/giornalista/journalistArea.jsp" %>
<div class="menu" id="addNotizia">
    <div class="insertNotizia">
        <form  name="insertNotizia" action="/Rojina_Review_war/insertNew" method="post" enctype="multipart/form-data">
            <label for="titolo" class="labelNotizie">Titolo: </label>
            <input type="text" id="titolo" name="titolo">
            <label for="testo" class="labelNotizie">Testo: </label>
            <input type="text" id="testo" name="testo">
            <label for="immagine" class="labelNotizie">Immagine: </label>
            <input type="file" id="immagine" name="immagine">
            <label for="giochi" class="labelNotizie">Giochi menzionati: </label>
            <input type="text" id="giochi" name="giochi">

            <input type="submit" value="Submit">
        </form>
    </div>

    <section class="articoli">
        <c:forEach items="${requestScope['notizieGiornalista']}" var="articolo">
            <a href="/Rojina_Review_war/getResource?type=news&id=${articolo.id}">
                <div class="articolo">
                    <img src="${articolo.immagine}" , alt="copertina" decoding="async">
                    <div class="articolo-content">
                        <h2>${articolo.titolo}</h2>
                        <p>${fn:substring(articolo.testo, 0, 50)}</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </section>
</div>
</body>
</html>
