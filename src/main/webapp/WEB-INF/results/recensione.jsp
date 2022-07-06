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
    <link rel="stylesheet" href="css/master.css">


</head>
<body>
<%@ include file="navebar.jsp" %>
<section id="main">
    <div id="titleSection">
        <h1 id="gameTitle"><%=r.getGioco().getTitolo()%>
        </h1>
        <h2 id="reviewTitle"><%=r.getTitolo()%>
        </h2>
    </div>

    <div id="imageSection">
        <img id="imageReview" src="<%=r.getImmagine()%>">
    </div>
</section>

<p id="bodyText">
    <%=r.getTesto()%>
</p>

<section id="voti">
    <h2 id="rojinaVote">Rojina Review</h2>
    <div id="reviewVote">
        <%=r.getVoto()%>
    </div>

    <h2 id="numberUsers">Utenti (<%=r.getGioco().getNumeroVoti()%>)</h2>
    <div id="usersVote">
        <%=r.getGioco().getMediaVoto()%>
    </div>
</section>

<section id="writer">
    Recensione scritta da <%=r.getGiornalista()%> il <%=r.getDataCaricamento()%>
</section>

<section id="comments">
    <div id="numberComments">
        <%=commenti.size()%> commenti
    </div>

    <form action="" method="post">
        <input type="text" id="toComment">
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
    0.            <%}%>
</section>

<%@ include file="../../html/footer.html" %>
</body>
</html>
