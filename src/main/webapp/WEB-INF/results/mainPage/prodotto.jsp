<%@ page import="rojinaReview.model.beans.Prodotto" %>
<%@ page import="rojinaReview.model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="rojinaReview.model.beans.Parere" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<% Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
    ArrayList<Commento> commenti = prodotto.getCommenti();
    Integer quantitàCarrello = (Integer) request.getAttribute("quantitàCarrello");
    Parere parere = (Parere) request.getAttribute("votoUtente");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("manager") != null)
        canDo = 2;
    else if(session.getAttribute("videogiocatore") != null)
        canDo = 1;%>
<head>
    <title><%=prodotto.getNome()%>
    </title>
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/foot.css">
    <link rel="stylesheet" href="./static/css/prodotto.css">
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/formSegnalazione.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="/Rojina_Review_war/static/js/navebar.js"></script>
    <script src="./static/js/userFunctions.js" type="text/javascript"></script>

</head>

<body>
<%if(session.getAttribute("error")!=null){
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
<div id="page">
<%@ include file="/WEB-INF/results/utilities/navebar.jsp" %>
<section id="wrap">
    <section id="articolo">
        <div id="imageSection">
            <img id="imageProduct" src="<%=prodotto.getImmagine()%>">
        </div>
        <div id="articolo-content">
        <h1 id="productTitle">
                    <%=prodotto.getNome()%>
         </h1>
            <h3>
                Disponibilità: <span id="disponibilità"><%=prodotto.getQuantità()-quantitàCarrello%></span>
        </h3>
            <h3>
                Prezzo: <span id="prezzo"><%=prodotto.getPrezzo()%>€</span>
        </h3>
        <p id="bodyText">
            <%=prodotto.getTesto()%>
        </p>
        <section id="toCarret">
                <input type="hidden" value="<%=prodotto.getId()%>" name="prodottoID" id="prodottoID">
                <input type="number" min="1" max="<%=prodotto.getQuantità()-quantitàCarrello%>" name="quantita" id="quantita" value="1">
                <input type="submit" style="width:auto" value="Aggiungi al Carrello" onclick="addToUserCart('<%=canDo%>')">
        </section>
        </div>
    </section>

    <section id="votiUtenti">
            <h1> Il vostro parere </h1>
            <h1 id="mediaVoto"><fmt:formatNumber value="<%=prodotto.getMediaVoto()%>" maxFractionDigits="1"/></h1>
            <h1>(<%=prodotto.getNumeroVoti()%>)</h1>
        <form  id="voteAction" name="voteAction" method="post" action="/Rojina_Review_war/addVote" onsubmit="return canVote('<%=canDo%>');">
            <input type="hidden" name="type" value="0">
            <input type="hidden" name="idProdottoORVideogioco" value="<%=prodotto.getId()%>">
            <input type="hidden" name="idContenuto" value="<%=prodotto.getId()%>">
            <input type="hidden" name="contenuto" value="shop">
            <input type="hidden" name="nome" value="<%=prodotto.getNome()%>">
            <input type="number" name="toVoto" id="toVoto" min="1" max="10" value="1">
            <input type="submit" value="Vota">
        </form>
        <%if(parere != null){%>
        <h1>Il tuo parere </h1>
        <h1 id="votoUtente"><fmt:formatNumber value="<%=parere.getVoto()%>" maxFractionDigits="0"/> </h1>
        <%}%>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="0">
            <input type="hidden" name="idContenuto" value="<%=prodotto.getId()%>">
            <input type="hidden" name="contenuto" value="shop">
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
    <input type="hidden" value="2" name="flag">
    <input type="hidden" value="<%=prodotto.getId()%>"  name="id_contenuto">
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
