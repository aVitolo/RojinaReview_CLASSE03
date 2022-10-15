<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/recensioni.css">
    <style>
        img{
            opacity: 1;
        }
    </style>

</head>
<body>
<%@ include file="/WEB-INF/results/giornalista/journalistArea.jsp" %>
<div class="menu" id="addRecensione">
    <div class="insertRecensione">
        <form name="insertRecensione" action="/Rojina_Review_war/insertReview" method="post"
              enctype="multipart/form-data">
            <label for="titolo" class="labelRecensioni">Titolo: </label>
            <input type="text" id="titolo" name="titolo">
            <label for="gioco" class="labelRecensioni">Gioco: </label>
            <input type="text" id="gioco" name="gioco">
            <label for="testo" class="labelRecensioni">Testo: </label>
            <input type="text" id="testo" name="testo">
            <br>
            <label for="voto" class="labelRecensioni">Voto: </label>
            <input type="range" id="voto" name="voto" min="0" max="10">
            <label for="immagine" class="labelRecensioni">Immagine: </label>
            <input type="file" id="immagine" name="immagine">

            <input type="submit" value="Submit">
        </form>
    </div>

    <section class="articoli">
        <c:forEach items="${requestScope['recensioniGiornalista']}" var="articolo">
            <a href="/Rojina_Review_war/getResource?type=reviews&id=${articolo.id}">
                <div class="articolo">
                    <img src="${articolo.immagine}" , alt="copertina" decoding="async">
                    <div class="articolo-content">
                        <h2>${articolo.titolo}</h2>
                        <p>${fn:substring(articolo.testo, 0, 50)}</p>
                        <p class="voto">${articolo.voto}</p>
                    </div>
                </div>
            </a>
        </c:forEach>
    </section>
</div>
</body>
</html>
