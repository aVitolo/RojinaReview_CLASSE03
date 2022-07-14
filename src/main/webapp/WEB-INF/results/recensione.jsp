<%@ page import="model.beans.Recensione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="model.beans.VotoGioco" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Recensione r = (Recensione) request.getAttribute("recensione");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    VotoGioco vg = (VotoGioco) request.getAttribute("votoUtente");%>
<head>
    <title><%=r.getGioco().getTitolo()%> - <%=r.getTitolo()%>
    </title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/recensione.css">
    <link rel="stylesheet" href="css/master.css">
</head>
<body>

<%@ include file="navebar.jsp" %>
<section id="wrap">

    <section id="articolo">
        <div id="copertina">
            <img src = "${recensione.immagine}" alt = "copertina" decoding="async">
            <p id="type">Review</p>
        </div>
        <div id = "articolo-content">
            <h1>${recensione.titolo}</h1>
            <p id="votoRecensione">${recensione.voto}</p>
            <p>${recensione.testo}</p>
            <p>Caricata il ${recensione.dataCaricamento}</p>
        </div>
    </section>

    <section id="votiUtenti">
        <h1> Il vostro parere </h1>
        <h1 id="mediaVoto"><%=r.getGioco().getMediaVoto()%></h1>
        <h1>(<%=r.getGioco().getNumeroVoti()%>)</h1>
        <form  method="post">
            <input type="number" id="toVoto" min="1" max="10">
            <input type="submit" value="Vota">
        </form>
        <%if(vg != null){%>
            <h1>Il tuo voto </h1>
            <h1 id="votoUtente"><%=vg.getVoto()%></h1>
        <%}%>
    </section>

    <div id="card">
        <div id="giornalista">
            <img src = "${recensione.immagineGiornalista}" alt = "copertina" decoding="async">
            <h1>${recensione.giornalista} </h1>
        </div>
    </div>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form action="" method="post">
            <input type="text" id="toComment" placeholder="Lascia un commento">
            <input type="submit" value="Commenta">
        </form>

        <% for (Commento c : commenti) {%>
        <div class="comment">
            <h4 class="nickname"><%=c.getUtente()%>
            </h4>
            <p class="text">
                <%=c.getTesto()%>
            </p>

            <div class="date">
                <%=c.getData()%>
            </div>
        </div>
        <%}%>
    </section>
</section>
<%@ include file="../../html/footer.html" %>
</body>
</html>
