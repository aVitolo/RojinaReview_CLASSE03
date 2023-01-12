<%@ page import="rojinaReview.model.beans.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Notizia notizia = (Notizia) request.getAttribute("notizia");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    Giornalista giornalista = (Giornalista) request.getAttribute("giornalista");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
        canDo = 2;
    else if(session.getAttribute("videogiocatore") != null)
        canDo = 1;%>
<head>
    <title><%=notizia.getNome()%>
    </title>
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/foot.css">
    <link rel="stylesheet" href="./static/css/notizia.css">
    <link rel="stylesheet" href="./static/css/master.css">
    <script src="./static/js/userFunctions.js" type="text/javascript"></script>

</head>

<body>

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
                <img src = "${giornalista.immagine}" alt = "copertina" decoding="async">
                <h2>${giornalista.nome} </h2>
            </div>
        </div>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="2">
            <input type="hidden" name="idContenuto" value="<%=notizia.getId()%>">
            <input type="hidden" name="contenuto" value="news">
            <input type="text" name="commentText" id="toComment" placeholder="Lascia un commento">
            <input type="submit" value="Commenta">
        </form>
        <%if(commenti != null){%>
        <% for (Commento c : commenti) {%>
        <div class="comment" id="<%=c.getId()%>">
            <h4 class="nickname"><%=c.getNicknameVideogiocatore()%>
            </h4>
            <p class="text">
                <%=c.getTesto()%>
            </p>

            <div class="date">
                <%=c.getDataScrittura()%>
            </div>
            <button onclick="handleRequest(<%=c.getId()%>)">Segnala</button>
        </div>
        <%}%>
        <%}%>
        <form method="post" action="inviaSegnalazioneServlet" style="display: none; color: red" id="form_segnalazione">
            <input type="hidden" value="" id="input_id" name="id_commento">
            <input type="hidden" value="2" name="flag">
            <input type="hidden" value="<%=notizia.getId()%>" name="id_contenuto">
            <select name="motivo" style="width: 200px; color: red">
                <option value="È spam">È spam</option>
                <option value="Contenuto offensivo">Contenuto offensivo</option>
            <textarea rows="10" cols="30" name="commento_aggiuntivo"></textarea>
            <button type="submit"> Invia segnalazione</button>
        </form>
    </section>
</section>

</body>
<script type="text/javascript">
    function handleRequest(id){
        let form=document.getElementById("form_segnalazione")
        form.style.display="block"
        document.getElementById("input_id").setAttribute("value",id)
    }
</script>
<script type="text/javascript" src="/Rojina_Review_war/static/js/navebar.js"></script>
</html>
