<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Commento" %>
<%@ page import="model.utilities.Voto" %>
<%@ page import="model.beans.VotoProdotto" %>
<%@ page import="model.beans.VotoGioco" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aree Utente</title>
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<% ArrayList<Commento> commenti = (ArrayList<Commento>) request.getAttribute("commenti");
   ArrayList<Voto> voti = (ArrayList<Voto>) request.getAttribute("voti");%>
<%@ include file="/WEB-INF/results/utente/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Voti e commenti</h1>
    <div class="votes">
        <h2 id="voti">Voti</h2>
        <%for(Voto v : voti){
            if(v.getClass().getSimpleName().equalsIgnoreCase("VotoProdotto")){
                VotoProdotto vp = (VotoProdotto) v;%>
                <a href="/Rojina_Review_war/getResource?type=shop&id=<%=vp.getId()%>">
                    <div class="voto">
                        <h3 class="userName"><%=vp.getUtente()%></h3>
                        <div class="voteNumber"><%=vp.getVoto()%></div>
                        <h3 class="dataVoto"><%=vp.getDataVotazione()%></h3>
                        <h3 class="idProdotto"><%=vp.getId()%></h3>
                    </div>
                </a>
            <%}%>
            <%if(v.getClass().getSimpleName().equalsIgnoreCase("VotoGioco")){
                VotoGioco vg = (VotoGioco) v;%>
                <a href="/Rojina_Review_war/getGame?name=<%=vg.getGioco()%>">
                    <div class="voto">
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
