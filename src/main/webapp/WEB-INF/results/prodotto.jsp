<%@ page import="model.beans.Prodotto" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<% Prodotto p = (Prodotto) request.getAttribute("prodotto");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti"); %>
<head>
    <title><%=p.getNome()%></title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/master.css">
    <link rel="stylesheet" href="css/prodotto.css">
</head>

<body>
<%@ include file="navebar.jsp" %>
<section id="main">
    <div id="titleSection">
        <h1 id="productTitle"><%=p.getNome()%></h1>
    </div>

    <div id="imageSection">
        <img id="imageProduct" src="<%=p.getImmagine()%>">
    </div>
</section>

<h3 id="disponibilità">Disponibilità: <%=p.getDisponibilità()%></h3>
<h3 id="prezzo">Prezzo: <%=p.getPrezzo()%></h3>

<p id="bodyText">
    <%=p.getDescrizione()%>
</p>


<section id="voto">
    <h3 id="mediaVoto">Media gradimento: <%=p.getMediaVoto()%></h3>
    <div id="votazione">

    </div>
</section>

<section id="comments">
    <div id="numberComments">
        <%=commenti.size()%> commenti
    </div>

    <form action="" method="post">
        <input type="text" id="toComment">
        <input type="submit">
    </form>

    <% for(Commento c : commenti){%>
    <div class="comment">
        <h4 class="nickname"><%=c.getUtente()%></h4>
        <p class="text">
            <%=c.getTesto()%>
        </p>

        <div class="date">
            <%=c.getData()%>
        </div>
    </div>
    <%}%>
</section>

<%@ include file="../../html/footer.html" %>
</body>
</html>