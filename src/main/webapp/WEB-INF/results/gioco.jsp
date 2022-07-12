
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="gioco" value="${requestScope['gioco']}"/>

<html>
<head>
    <title><c:out value="${gioco.titolo}"/></title>
    <link rel="stylesheet" href="css/master.css">
    <link rel="stylesheet" href="css/notizie.css">
</head>
<body>
<div class="container">
    <div class="game">
        <div class="copertina">
            <img src="${gioco.copertina}">
            <h1>${gioco.titolo}</h1>
        </div>
        <div class="info">
            <h2 id="infoTitle">Informazioni gioco</h2>
            <h3 class="infoAttribute">Piattaforme: </h3> <c:forEach items="${gioco.piattaforme}" var="piattaforma">${piattaforma.nome} </c:forEach>
            <h3 class="infoAttribute">Tipologie: </h3>   <c:forEach items="${gioco.tipologie}" var="tipologia">${tipologia.nome} </c:forEach>
            <h3 class="infoAttribute">Casa di sviluppo: </h3> ${gioco.casaDiSviluppo}
            <h3 class="infoAttribute">Data di rilascio: </h3> ${gioco.dataDiRilascio}
        </div>
        <a href="/Rojina_Review_war/getResource?type=reviews&id=${recensione}">
            <button id="reviewButton"><h3 id="reviewTitle">Recensione</h3></button>
        </a>
    </div>
    <div id="giocoNotizie">
        <h2 id="notizieTitle">Notizie correlate</h2>
        <div id="notizieGioco">
            <c:forEach items="${notizieGioco}" var="articolo">
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
        </div>
    </div>


</div>
</body>
</html>
