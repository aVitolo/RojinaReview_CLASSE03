<%@ page import="rojinaReview.model.beans.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("manager") != null)
        canDo = 2;
    else if(session.getAttribute("videogiocatore") != null)
        canDo = 1;%>

<c:set var="giornalistaArticolo" scope="page" value="${requestScope.get('giornalista')}" />
<c:set var="notizia" scope="page" value="${requestScope.get('notizia')}" />
<head>
    <title>${notizia.nome}</title>
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/foot.css">
    <link rel="stylesheet" href="./static/css/notizia.css">
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/formSegnalazione.css">
    <script src="./static/js/userFunctions.js" type="text/javascript"></script>
</head>

<body>
<div id="page">
<%@ include file="/WEB-INF/results/utilities/navebar.jsp" %>
<section id="wrap">

    <section id="articolo">
        <div id="copertina">
            <img src = "${notizia.immagine}" alt = "copertina" decoding="async">
            <p id="type">News</p>
        </div>
        <div id = "articolo-content">
            <h1>${notizia.nome}</h1>
            <p>${notizia.testo}</p>
            <p>Caricata il ${notizia.dataScrittura}</p>
        </div>
        <div id="card">
            <div id="giornalista">
                <img src = "${giornalistaArticolo.immagine}" alt = "copertina" decoding="async">
                <h2>${giornalistaArticolo.nome} ${giornalistaArticolo.cognome} </h2>
            </div>
        </div>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="2">
            <input type="hidden" name="idContenuto" value="${notizia.id}">
            <input type="hidden" name="contenuto" value="news">
            <input type="text" name="commentText" id="toComment" placeholder="Lascia un commento">
            <input type="submit" value="Commenta">
        </form>
        <%if(commenti != null){%>
        <% for (Commento c : commenti) {%>
        <div class="comment" id="<%=c.getId()%>">
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
    <input type="hidden" value="2" name="flag">
    <input type="hidden" value="${notizia.id}" name="id_contenuto">
    <select name="motivo">
    <option value="Spam">Spam</option>
    <option value="Contenuto offensivo">Contenuto offensivo</option>
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
