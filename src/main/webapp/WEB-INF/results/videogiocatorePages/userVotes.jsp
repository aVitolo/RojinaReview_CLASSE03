<%@ page import="java.util.ArrayList" %>
<%@ page import="rojinaReview.model.beans.Commento" %>
<%@ page import="rojinaReview.model.beans.Parere" %>
<%@ page import="rojinaReview.model.beans.ParereProdotto" %>
<%@ page import="rojinaReview.model.beans.ParereGioco" %>
<%@ page import="rojinaReview.model.beans.ParereProdotto" %>
<%@ page import="rojinaReview.model.beans.ParereGioco" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aree Utente</title>
    <link rel="stylesheet" href="./static/css/master.css">
</head>
<body>
<% ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
   ArrayList<Parere> voti = (ArrayList<Parere>) request.getAttribute("voti");%>
<%@ include file="/WEB-INF/results/videogiocatorePages/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Voti e commenti</h1>
    <div class="votes">
        <h2 id="voti">Voti</h2>
        <%if (voti.size() == 0){%>
        <h3>Non hai votato nessun gioco o prodotto</h3>
        <%}%>
        <%for(Parere v : voti){
            if(v.getClass().getSimpleName().equalsIgnoreCase("VotoProdotto")){
                ParereProdotto vp = (ParereProdotto) v;%>
                <a href="/Rojina_Review_war/getResource?type=shop&id=<%=vp.getId()%>">
                    <div class="parere">
                        <h3 class="userName"><%=vp.getUtente()%></h3>
                        <div class="voteNumber"><%=vp.getVoto()%></div>
                        <h3 class="dataVoto"><%=vp.getDataVotazione()%></h3>
                        <h3 class="idProdotto"><%=vp.getId()%></h3>
                    </div>
                </a>
            <%}%>
            <%if(v.getClass().getSimpleName().equalsIgnoreCase("VotoGioco")){
                ParereGioco vg = (ParereGioco) v;%>
                <a href="/Rojina_Review_war/getGame?name=<%=vg.getGioco()%>">
                    <div class="parere">
                        <h3 class="userName"><%=vg.getUtente()%></h3>
                        <div class="voteNumber"><%=vg.getVoto()%></div>
                        <h3 class="dataVoto"><%=vg.getDataVotazione()%></h3>
                        <h3 class="nomeGioco"><%=vg.getGioco()%></h3>
                    </div>
                </a>
            <%}%>
        <%}%>

    </div>
    <div class="comments">
        <h2 id="commenti">Commenti</h2>
        <%for(Commento c : commenti){%>
            <a href="/Rojina_Review_war/getResource?type=<%=c.getResource()%>&id=<%=c.getId()%>">
                <div class="commento">
                    <h3 class="userName"><%=c.getUtente()%>></h3>
                    <p class="commentoText"><%=c.getTesto()%>></p>
                    <h3 class="dataCommento"><%=c.getData()%></h3>
                </div>
            </a>
        <%}%>
    </div>
</div>
</body>
</html>
