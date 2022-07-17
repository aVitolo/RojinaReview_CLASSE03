<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/notizie.css">

</head>
<body>
<%@ include file="/WEB-INF/results/journalistArea.jsp" %>
<div class="menu">
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
            <span id="tipologia" class="attribute">Tipologia: </span>
            <c:forEach items="${applicationScope['tipologie']}" var="tipologia">
                <input type="checkbox" id="${tipologia.nome}" name="${tipologia.nome}" value="${tipologia.nome}">
                <label for="${tipologia.nome}">${tipologia.nome}&nbsp;&nbsp;</label>
            </c:forEach>
            <br>
            <span id="piattaforma" class="attribute">Piattaforma: </span>
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <input type="checkbox" id="${piattaforma.nome}" name="${piattaforma.nome}" value="${piattaforma.nome}">
                <label for="${piattaforma.nome}">${piattaforma.nome}&nbsp;&nbsp;</label>
            </c:forEach>

            <br>
            <input type="submit" value="Inserisci gioco">
        </form>
    </div>

    <section class="articoli">
        <c:forEach items="${requestScope['giochiGiornalista']}" var="articolo">
            <div class="articolo">
                <img src="${articolo.copertina}" , alt="copertina" decoding="async">
                <div class="articolo-content">
                    <h2>${articolo.titolo}</h2>
                    <p>${articolo.casaDiSviluppo}</p>
                    <p class="voto">${articolo.mediaVoto}</p>
                </div>
            </div>
        </c:forEach>
    </section>
</div>
</body>
</html>
