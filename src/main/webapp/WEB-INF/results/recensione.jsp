<%@ page import="model.beans.Recensione" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Commento" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Recensione r = (Recensione) request.getAttribute("recensione");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti"); %>
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
            <p id="type">News</p>
        </div>
        <div id = "articolo-content">
            <h2>${recensione.titolo}</h2>
            <p>${recensione.testo}</p>
            <div id="giornalista">
                <h3>Notizia scritta da ${recensione.giornalista} il ${recensione.dataCaricamento}</h3>
                <img src = "${recensione.immagineGiornalista}" alt = "copertina" decoding="async">
            </div>
        </div>
    </section>

    <section id="votiUtenti">
        <h3>Il vostro parere</h3>
        <div id="resume">
            <p id="numberUsers">Utenti (<%=r.getGioco().getNumeroVoti()%>)</p>
            <p id="usersVote"> <%=r.getGioco().getMediaVoto()%>   </p>
        </div>
        <form  method="post">
            <input type="number" id="toVoto" min="1" max="10">
            <input type="submit">
        </form>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form action="" method="post">
            <input type="text" id="toComment" placeholder="Lascia un commento">
            <input type="submit">
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
