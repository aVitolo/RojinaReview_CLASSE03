<%@ page import="java.util.ArrayList" %>
<%@ page import="rojinaReview.model.beans.Commento" %>
<%@ page import="rojinaReview.model.beans.Parere" %>
<%@ page import="rojinaReview.model.beans.Videogiocatore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aree Utente</title>
    <link rel="stylesheet" href="./static/css/master.css">
</head>
<body>
<% Videogiocatore videogiocatore = (Videogiocatore) session.getAttribute("videogiocatore");
    ArrayList<Commento> commenti = videogiocatore.getCommenti();
   ArrayList<Parere> pareri = videogiocatore.getPareri();%>
<%@ include file="/WEB-INF/results/videogiocatorePages/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Pareri e commenti</h1>
    <div class="votes">
        <h2 id="voti">Pareri</h2>
        <%if (pareri.size() == 0){%>
        <h3>Non hai votato nessun gioco o prodotto</h3>
        <%}%>
        <%for(Parere v : pareri){
            if(v.getType() == 0){
                %>
                <a href="/Rojina_Review_war/getResource?type=shop&id=<%=v.getIdProdottoORVideogioco()%>">
                    <div class="parere">
                        <div class="voteNumber"><%=v.getVoto()%></div>
                        <h3 class="dataVoto"><%=v.getDataVotazione()%></h3>
                    </div>
                </a>
            <%}%>
            <%if(v.getType() == 3){
                %>
                <a href="/Rojina_Review_war/getResource?type=game&id=<%=v.getIdProdottoORVideogioco()%>">
                    <div class="parere">
                        <div class="voteNumber"><%=v.getVoto()%></div>
                        <h3 class="dataVoto"><%=v.getDataVotazione()%></h3>
                    </div>
                </a>
            <%}%>
        <%}%>

    </div>
    <div class="comments">
        <h2 id="commenti">Commenti</h2>
        <%for(Commento c : commenti){%>
            <a href="/Rojina_Review_war/getResource?type=<%=c.getResource()%>&id=<%=c.getIdContenuto()%>">
                <div class="commento">
                    <p class="commentoText"><%=c.getTesto()%></p>
                    <h3 class="dataCommento"><%=c.getDataScrittura()%></h3>
                </div>
            </a>
        <%}%>
    </div>
</div>
</body>
</html>
