<%@ page import="model.beans.Prodotto" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<% Prodotto p = (Prodotto) request.getAttribute("prodotto");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    Integer quantitàCarrello = (Integer) request.getAttribute("quantitàCarrello");%>
<head>
    <title><%=p.getNome()%>
    </title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/prodotto.css">
    <link rel="stylesheet" href="css/master.css">
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
            <form  action="/Rojina_Review_war/addProductCart" method="post">
                <input type="hidden" value="<%=p.getId()%>" name="prodottoID" id="prodottoID">
                <input type="number" min="1" max="10" name="quantità" id="quantità" value="1">
                <input type="submit" style="width:auto" value="Aggiungi al Carrello">
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
    </section>

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
<script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
</html>