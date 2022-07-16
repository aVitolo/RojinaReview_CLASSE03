<%@ page import="model.beans.Prodotto" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.VotoProdotto" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<% Prodotto p = (Prodotto) request.getAttribute("prodotto");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    Integer quantitàCarrello = (Integer) request.getAttribute("quantitàCarrello");
    VotoProdotto vp = (VotoProdotto) request.getAttribute("votoUtente");%>
<head>
    <title><%=p.getNome()%>
    </title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/prodotto.css">
    <link rel="stylesheet" href="css/master.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
    <script type="text/javascript" src="/Rojina_Review_war/js/addToCart.js"></script>
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
            <form  id="addProduct" action="/Rojina_Review_war/addProductCart" method="post">
                <input type="hidden" value="<%=p.getId()%>" name="prodottoID" id="prodottoID">
                <input type="number" min="1" max="<%=p.getDisponibilità()-quantitàCarrello%>" name="quantità" id="quantità" value="1">
                <input type="submit" style="width:auto" value="Aggiungi al Carrello" onclick="addToUserCart()">
            </form>
        </section>
        </div>
    </section>

    <section id="votiUtenti">
            <h1> Il vostro parere </h1>
            <h1 id="mediaVoto"><%=p.getMediaVoto()%></h1>
            <h1>(<%=p.getNumeroVoti()%>)</h1>
            <form  method="post">
                <input type="number" min="1" max="10">
                <input type="submit" value="Vota">
            </form>
        <%if(vp != null){%>
        <h1>Il tuo voto </h1>
        <h1 id="votoUtente"><%=vp.getVoto()%></h1>
        <%}%>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form action="/Rojina_Review_war/addComment" method="post">
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