<%@ page import="java.util.ArrayList" %>
<%@ page import="rojinaReview.model.beans.*" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Recensione recensione = (Recensione) request.getAttribute("recensione");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    Videogioco videogioco = (Videogioco) request.getAttribute("videogioco");
    Parere parere = (Parere) request.getAttribute("votoUtente");
    Giornalista giornalista = (Giornalista) request.getAttribute("giornalista");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
         canDo = 2;
    else if(session.getAttribute("videogiocatore") != null)
        canDo = 1;%>
<head>
    <title><%=recensione.getNomeVideogioco()%> - <%=recensione.getNome()%>
    </title>
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/foot.css">
    <link rel="stylesheet" href="./static/css/recensione.css">
    <link rel="stylesheet" href="./static/css/master.css">
    <script src="./static/js/userFunctions.js" type="text/javascript"></script>
</head>
<body>

<%@ include file="/WEB-INF/results/utilities/navebar.jsp" %>
<section id="wrap">

    <section id="articolo">
        <div id="copertina">
            <img src = "${recensione.immagine}" alt = "copertina" decoding="async">
            <p id="type">Review</p>
        </div>
        <div id = "articolo-content">
            <h1>${recensione.nome}</h1>
            <p id="votoRecensione">${recensione.votoGiornalista}</p>
            <p>${recensione.testo}</p>
            <p>Caricata il ${recensione.dataScrittura}</p>
        </div>
    </section>

    <section id="votiUtenti">
        <h1> Il vostro parere </h1>
        <h1 id="mediaVoto"><fmt:formatNumber value="${parere}" maxFractionDigits="1"/></h1>
        <h1>(<%=videogioco.getNumeroVoti()%>)</h1>
        <form  id="voteAction" name="voteAction" method="post" action="/Rojina_Review_war/addVote" onsubmit="return canVote('<%=canDo%>');">
            <input type="hidden" name="type" value="recensione">
            <input type="hidden" name="id" value="<%=videogioco.getId()%>">
            <input type="hidden" name="table" value="gioco">
            <input type="hidden" name="nomeGioco" value="<%=videogioco.getTitolo()%>">
            <input type="number" name="toVoto" id="toVoto" min="1" max="10" value="1">
            <input type="submit" value="Vota">
        </form>
        <%if(parere != null){%>
            <h1>Il tuo parere </h1>
            <h1 id="votoUtente"><fmt:formatNumber value="<%=parere.getVoto()%>" maxFractionDigits="0"/> </h1>
        <%}%>
    </section>

    <div id="card">
        <div id="giornalista">
            <img src = "${giornalista.immagine}" alt = "copertina" decoding="async">
            <h1>${giornalista.nome} </h1>
        </div>
    </div>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="recensione">
            <input type="hidden" name="id" value="<%=recensione.getId()%>">
            <input type="text" name="commentText" id="toComment" placeholder="Lascia un commento">
            <input type="submit" value="Commenta" >
        </form>

        <%if(commenti != null){%>
            <% for (Commento c : commenti) {%>
            <div class="comment">
                <h4 class="nickname"><%=c.getNicknameVideogiocatore()%>
                </h4>
                <p class="text">
                    <%=c.getTesto()%>
                </p>

                <div class="date">
                    <%=c.getDataScrittura()%>
                </div>
                <button>Segnala</button>
            </div>
            <%}%>
        <%}%>
    </section>
</section>
</body>
</html>
