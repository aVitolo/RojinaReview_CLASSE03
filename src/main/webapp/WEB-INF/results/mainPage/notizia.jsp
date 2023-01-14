<%@ page import="rojinaReview.model.beans.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Notizia notizia = (Notizia) request.getAttribute("notizia");
    ArrayList<Commento> commenti = notizia.getCommenti();
    ArrayList<Paragrafo> paragrafi = notizia.getParagrafi();
    HashMap<Integer, String> mentionedGames = notizia.getGiochi();
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("manager") != null)
        canDo = 2;
    else if(session.getAttribute("videogiocatore") != null)
        canDo = 1;%>

<c:set var="giornalistaArticolo" scope="page" value="${requestScope.get('giornalista')}" />
<c:set var="notizia" value="${requestScope.get('notizia')}"/>
<c:set var="paragrafi" value="${notizia.paragrafi}"/>

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
            <img src = "${notizia.immagine}" alt = "copertina" decoding="async">
            <p id="type">News</p>
        </div>
        <div id = "articolo-content">
            <h1>${notizia.nome}</h1>
            <p>${notizia.testo}</p>
            <c:forEach items="${paragrafi}" var="paragrafo">
                <h2>${paragrafo.titolo}</h2>
                <c:if test="${paragrafo.immagine != null}">
                    <img src = "${paragrafo.immagine}" alt="immagineParagrafo" decoding="async">
                </c:if>
                <p>${paragrafo.testo}</p>
            </c:forEach>
            <p>Caricata il ${notizia.dataScrittura}</p>
        </div>
        <div id="mentionedGames">
            <%for(Map.Entry<Integer, String> set : mentionedGames.entrySet()){%>
                <a href="/Rojina_Review_war/getResource?type=game&id=<%=set.getKey()%>"><%=set.getValue()%> &nbsp &nbsp &nbsp</a>
            <%}%>

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
