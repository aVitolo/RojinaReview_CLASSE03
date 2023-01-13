<%@ page import="java.util.ArrayList" %>
<%@ page import="rojinaReview.model.beans.*" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Recensione recensione = (Recensione) request.getAttribute("recensione");
    ArrayList<Commento> commenti = recensione.getCommenti();
    ArrayList<Paragrafo> paragrafi = recensione.getParagrafi();
    Videogioco videogioco = (Videogioco) request.getAttribute("videogioco");
    Parere parere = (Parere) request.getAttribute("votoUtente");
    Giornalista giornalistaArticolo = (Giornalista) request.getAttribute("giornalistaArticolo");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
         canDo = 2;
    else if(session.getAttribute("videogiocatore") != null)
        canDo = 1;
    %>

<c:set var="recensione" value="${requestScope.get('recensione')}"/>
<c:set var="paragrafi" value="${recensione.paragrafi}"/>
<head>
    <title><%=recensione.getNomeVideogioco()%> - <%=recensione.getNome()%>
    </title>
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/foot.css">
    <link rel="stylesheet" href="./static/css/recensione.css">
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/formSegnalazione.css">
    <script src="./static/js/userFunctions.js" type="text/javascript"></script>
</head>
<body>
<%    if(session.getAttribute("error")!=null){
    String error= (String) session.getAttribute("error");
    session.removeAttribute("error");
    if(error.equals("noComm")){%>
        <script>
            showError(1)
        </script>
    <%
    }
    else if(error.equals("noMot")){%>
        <script>
            showError(1)
        </script>
<%  }
}%>
    }%>
<div id="page">
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
            <c:forEach items="${paragrafi}" var="paragrafo">
                <h2>${paragrafo.titolo}</h2>
                <c:if test="${paragrafo.immagine != null}">
                    <img src = "${paragrafo.immagine}" alt="immagineParagrafo" decoding="async">
                </c:if>
                <p>${paragrafo.testo}</p>
            </c:forEach>
            <p>Caricata il ${recensione.dataScrittura}</p>
        </div>
    </section>

    <section id="votiUtenti">
        <h1> Il vostro parere </h1>
        <h1 id="mediaVoto"><fmt:formatNumber value="<%=videogioco.getMediaVoto()%>" maxFractionDigits="1"/></h1>
        <h1>(<%=videogioco.getNumeroVoti()%>)</h1>
        <form  id="voteAction" name="voteAction" method="post" action="/Rojina_Review_war/addVote" onsubmit="return canVote('<%=canDo%>');">
            <input type="hidden" name="type" value="3">
            <input type="hidden" name="idProdottoORVideogioco" value="<%=videogioco.getId()%>">
            <input type="hidden" name="idContenuto" value="<%=recensione.getId()%>">
            <input type="hidden" name="contenuto" value="reviews">
            <input type="hidden" name="nome" value="<%=videogioco.getTitolo()%>">
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
            <img src = "${giornalistaArticolo.immagine}" alt = "copertina" decoding="async">
            <h1>${giornalistaArticolo.nome} ${giornalistaArticolo.cognome}</h1>
        </div>
    </div>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="1">
            <input type="hidden" name="idContenuto" value="<%=recensione.getId()%>">
            <input type="hidden" name="contenuto" value="reviews">
            <input type="text" name="commentText" id="toComment" placeholder="Lascia un commento">
            <input type="submit" value="Commenta">
        </form>

        <%if(commenti != null){%>
            <% for (Commento c : commenti) {%>
            <div class="comment">
                <h4 class="nickname"><%=c.getNicknameVideogiocatore()%>
                </h4>
                <div style="display: flex; justify-content: space-between;">
                    <p class="text">
                        <%=c.getTesto()%>
                    </p>
                    <button class="segnalaB" onclick="handleRequest(<%=c.getId()%>,<%=canDo%>)">Segnala</button>
                </div>
                <div class="date">
                    <%=c.getDataScrittura()%>
                </div>
            </div>
            <%}%>
        <%}%>
    </section>
</section>
</div>
<form method="post" action="inviaSegnalazioneServlet" style="display: none;" id="form_segnalazione">
    <p id="closeForm" onclick="reset()">X</p>
    <input type="hidden" value="" id="input_id" name="id_commento">
    <input type="hidden" value="1" name="flag">
    <input type="hidden" value="<%=recensione.getId()%>" name="id_contenuto">
    <select name="motivo">
        <option value="Seleziona un motivo">Seleziona una motivazione</option>
        <option value="Spam">Spam</option>
        <option value="Contenuto offensivo">Contenuto offensivo</option>
        <option value="Altro">Altro</option></select>
        <textarea rows="10" cols="30" name="commento_aggiuntivo"></textarea>
        <button type="submit"> Invia segnalazione</button>
</form>
</body>
<script type="text/javascript">
    function handleRequest(id,canDo){
        if(canReport(canDo)) {
            let page = document.getElementById("page")
            page.style.opacity = "0.5"

            let form = document.getElementById("form_segnalazione")
            form.style.display = "flex"
            document.getElementById("input_id").setAttribute("value", id)
        }
    }

    function reset(){
        let page = document.getElementById("page")
        page.style.opacity = "1"

        let form = document.getElementById("form_segnalazione")
        form.style.display = "none"
    }
</script>
</html>
