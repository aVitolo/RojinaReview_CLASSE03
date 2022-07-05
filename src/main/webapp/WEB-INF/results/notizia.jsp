<%@ page import="model.beans.Notizia" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Notizia n = (Notizia) request.getAttribute("notizia");
    ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti"); %>
<head>
    <title><%=n.getTitolo()%></title>
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/master.css">
</head>

<body>
    <%@ include file="navebar.jsp" %>
    <section id="main">
        <div id="titleSection">
            <h1 id="newTitle"><%=n.getTitolo()%></h1>
        </div>

        <div id="imageSection">
            <img id="imageNew" src="<%=n.getImmagine()%>">
        </div>
    </section>

    <p id="bodyText">
        <%=n.getTesto()%>
    </p>

    <section id="mentionedGames">
        <% for(String gioco : n.getGiochi()){%>
            <h3><%=gioco%></h3>
        <%}%>
    </section>

    <section id="writer">
        Notizia scritta da <%=n.getGiornalista()%> il <%=n.getDataCaricamento()%>
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
