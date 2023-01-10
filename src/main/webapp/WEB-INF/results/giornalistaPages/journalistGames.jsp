<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/notizie.css">
</head>
<body>
<%@ include file="/WEB-INF/results/giornalistaPages/journalistArea.jsp" %>
<div class="menu" id="addGame">
    <div class="insertGioco">
        <form name="insertGioco" action="/Rojina_Review_war/insertGame" method="post" enctype="multipart/form-data">
            <span class="attribute">Titolo: </span>
            <input type="text" id="titolo" name="titolo">
            <span class="attribute">&nbsp;&nbsp;Casa di sviluppo: </span>
            <input type="text" id="casaDiSviluppo" name="casaDiSviluppo">
            <span class="attribute">&nbsp;&nbsp;Data di rilascio: </span>
            <input type="date" id="dataDiRilascio" name="dataDiRilascio" placeholder="yyyy-mm-dd">
            <br>
            <span class="attribute">Copertina: </span>
            <input type="file" id="copertina" name="copertina">
            <br>
            <span id="tipologia" class="attribute">Genere: </span>
            <c:forEach items="${applicationScope['generi']}" var="genere">
                <input type="checkbox" id="${genere}" name="${genere}" value="${genere}">
                <label for="${genere}">${genere}&nbsp;&nbsp;</label>
            </c:forEach>
            <br>
            <span id="piattaforma" class="attribute">Piattaforma: </span>
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <input type="checkbox" id="${piattaforma}" name="${piattaforma}" value="${piattaforma}">
                <label for="${piattaforma}">${piattaforma}&nbsp;&nbsp;</label>
            </c:forEach>

            <br>
            <input type="submit" value="Inserisci gioco">
        </form>
    </div>

    <section class="articoli">
        <c:forEach items="${requestScope['giochiGiornalista']}" var="videogioco">
            <div class="articolo">
                <img src="${videogioco.copertina}" , alt="copertina" decoding="async">
                <div class="articolo-content">
                    <h2>${videogioco.titolo}</h2>
                    <p>${videogioco.casaDiSviluppo}</p>
                    <p class="parere">${videogioco.mediaVoto}</p>
                </div>
            </div>
        </c:forEach>
    </section>
</div>
</body>
</html>
