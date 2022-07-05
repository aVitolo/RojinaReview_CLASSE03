<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/recensioni.css">
</head>
<body>
<%@ include file="/WEB-INF/results/journalistArea.jsp" %>
<div class="menu">
    <div class="insertGioco">
        <form name="insertGioco" action="/Rojina_Review_war/insertGame" method="post" enctype="multipart/form-data">
            <label for="titolo">Titolo: </label>
            <input type="text" id="titolo" name="titolo">
            <label for="casaDiSviluppo">Casa di sviluppo: </label>
            <input type="text" id="casaDiSviluppo" name="casaDiSviluppo">
            <label for="dataDiRilascio">Data di rilascio: </label>
            <input type="date" id="dataDiRilascio" name="dataDiRilascio" placeholder="yyyy-mm-dd" >
            <label for="copertina">Copertina: </label>
            <input type="file" id="copertina" name="copertina">
            <span id="tipologia">Tipologia: </span>
            <c:forEach items="${applicationScope['tipologie']}" var="tipologia">
                <input type="checkbox" id="${tipologia.nome}" name="${tipologia.nome}" value="${tipologia.nome}">
                <label for="${tipologia.nome}">${tipologia.nome}</label>
            </c:forEach>
            <br>
            <span id="piattaforma">Piattaforma: </span>
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <input type="checkbox" id="${piattaforma.nome}" name="${piattaforma.nome}" value="${piattaforma.nome}">
                <label for="${piattaforma.nome}">${piattaforma.nome}</label>
            </c:forEach>


            <input type="submit" value="Submit">
        </form>
    </div>

    <section class="articoli">
        <c:forEach items="${requestScope['giochiGiornalista']}" var="articolo">
            <div class = "articolo">
                <img src = "${articolo.copertina}", alt = "copertina" decoding="async">
                <div class = "articolo-content">
                    <h2>${articolo.titolo}</h2>
                    <p>${articolo.casaDiSviluppo}</p>
                    <p class="voto">${articolo.mediaVoto}</p>
                    <p class="voto"></p>
                </div>
            </div>
        </c:forEach>
    </section>
</div>
</body>
</html>
