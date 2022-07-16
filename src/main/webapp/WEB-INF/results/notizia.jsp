<%@ page import="model.beans.Notizia" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Notizia n = (Notizia) request.getAttribute("notizia");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
    int canDo = 0; //ospite
    if(session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
        canDo = 2;
    else if(session.getAttribute("utente") != null)
        canDo = 1;%>
<head>
    <title><%=n.getTitolo()%>
    </title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/notizia.css">
    <link rel="stylesheet" href="css/master.css">
    <script src="js/userFunctions.js" type="text/javascript"></script>

</head>

<body>

<%@ include file="navebar.jsp" %>
<section id="wrap">

    <section id="articolo">
        <div id="copertina">
            <img src = "${notizia.immagine}" alt = "copertina" decoding="async">
            <p id="type">News</p>
        </div>
        <div id = "articolo-content">
            <h1>${notizia.titolo}</h1>
            <p>${notizia.testo}</p>
            <p>Caricata il ${notizia.dataCaricamento}</p>
        </div>
        <section id="mentionedGames">
            <c:if test="${notizia.giochi != null}">
                <h2>Giochi menzionati:</h2>
                <% for (String gioco : n.getGiochi()) {%>
                <a href="/Rojina_Review_war/getGame?name=<%=gioco%>"><h3><%=gioco%></h3></a>
                <%}%>
            </c:if>
        </section>
        <div id="card">
            <div id="giornalista">
                <img src = "${notizia.immagineGiornalista}" alt = "copertina" decoding="async">
                <h2>${notizia.giornalista} </h2>
            </div>
        </div>
    </section>

    <section id="comments">
        <div id="numberComments">
            <%=commenti.size()%> commenti
        </div>

        <form  id="commentAction" action="/Rojina_Review_war/addComment" method="post" name="commentAction" onsubmit="return canComment('<%=canDo%>');">
            <input type="hidden" name="type" value="notizia">
            <input type="hidden" name="id" value="<%=n.getId()%>">
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
<script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
</html>
