<%@ page import="model.beans.Prodotto" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.VotoProdotto" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<% Prodotto p = (Prodotto) request.getAttribute("prodotto");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    Integer quantitàCarrello = (Integer) request.getAttribute("quantitàCarrello");
    VotoProdotto vp = (VotoProdotto) request.getAttribute("votoUtente");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
        canDo = 2;
    else if(session.getAttribute("utente") != null)
        canDo = 1;%>
<head>
    <title><%=p.getNome()%>
    </title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/prodotto.css">
    <link rel="stylesheet" href="css/master.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>

</head>

<body>
<%@ include file="navebar.jsp" %>
<section id="wrap">
    <section id="articolo">
        <div id="imageSection">
            <img id="imageProduct" src="<%=p.getImmagine()%>">
        </div>
        <div id="articolo-content">
        <h1 id="productTitle">
                    <%=p.getNome()%>
         </h1>
        <h3 id="disponibilità">
            Disponibilità: <%=p.getDisponibilità()-quantitàCarrello%>
        </h3>
        <h3 id="prezzo">
            Prezzo: <%=p.getPrezzo()%>€
        </h3>
        <p id="bodyText">
            <%=p.getDescrizione()%>
        </p>
        <section id="toCarret">
                <input type="hidden" value="<%=p.getId()%>" name="prodottoID" id="prodottoID">
                <input type="number" min="1" max="<%=p.getDisponibilità()-quantitàCarrello%>" name="quantita" id="quantita" value="1">
                <input type="submit" style="width:auto" value="Aggiungi al Carrello" onclick="addToUserCart('<%=canDo%>')">
        </section>
        </div>
    </section>

    <section id="votiUtenti">
            <h1> Il vostro parere </h1>
            <h1 id="mediaVoto"><fmt:formatNumber value="<%=p.getMediaVoto()%>" maxFractionDigits="1"/></h1>
            <h1>(<%=p.getNumeroVoti()%>)</h1>
        <form  id="voteAction" name="voteAction" method="post" action="/Rojina_Review_war/addVote" onsubmit="return canVote('<%=canDo%>');">
            <input type="hidden" name="type" value="shop">
            <input type="hidden" name="id" value="<%=p.getId()%>">
            <input type="hidden" name="table" value="prodotto">
            <input type="number" name="toVoto" id="toVoto" min="1" max="10" value="1">
            <input type="submit" value="Vota">
        </form>
        <%if(vp != null){%>
        <h1>Il tuo voto </h1>
        <h1 id="votoUtente"><fmt:formatNumber value="<%=vp.getVoto()%>" maxFractionDigits="0"/> </h1>
        <%}%>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="prodotto">
            <input type="hidden" name="id" value="<%=p.getId()%>">
            <input type="text" name="commentText" id="toComment" placeholder="Lascia un commento">
            <input type="submit" value="Commenta">
        </form>

        <%if(commenti != null){%>
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
        <%}%>

    </section>
</section>
<%@ include file="../../html/footer.html" %>
</body>

</html>