<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Recensione" %>
<%@ page import="model.beans.Tipologia" %>
<%@ page import="model.beans.Piattaforma" %><%--
  Created by IntelliJ IDEA.
  User: zindre
  Date: 04/05/2022
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% ArrayList<Recensione> recensioni = (ArrayList<Recensione>) session.getAttribute("recensioni");
    ArrayList<Tipologia> tipologie = (ArrayList<Tipologia>) session.getAttribute("tipologie");
    ArrayList<Piattaforma> piattaforme = (ArrayList<Piattaforma>) session.getAttribute("piattaforme");  %>
<head>
    <title>Recensioni - Rojina</title>
</head>

<body>
    <%@ include file="../../navebar.html" %>

    <article id="mainReview">
        <img id="mainImage" src="data:image/jpg;base64, <%=new String(recensioni.get(0).getImmagine())%>">
        <div id="vote">
            <%=recensioni.get(0).getVoto()%>
        </div>
        <div id="mainReviewTitle">
            <h1><%=recensioni.get(0).getTitolo()%></h1>
            by <%=recensioni.get(0).getGiornalista()%> on <%=recensioni.get(0).getDataCaricamento()%>
        </div>
    </article>

    <section id="reviewsAndFiltersOrders">
        <section id="reviews">
                <% for(int i = 1; i < recensioni.size(); i++){%> <! -- far partire da 0 o 1 considerando la main review in alto? -- >
                        <a class="link" href="getRecensione?id=<%=recensioni.get(i).getId()%>">
                            <article class="review">
                                <img class="reviewImage" src="data:image/jpg;base64, <%=recensioni.get(i).getImmagine()%>">
                                <div class="vote"><%=recensioni.get(i).getVoto()%></div>
                                <h2 class="reviewTitle"><%=recensioni.get(i).getTitolo()%></h2>
                                by <%=recensioni.get(i).getGiornalista()%> on <%=recensioni.get(i).getDataCaricamento()%>
                            </article>
                        </a>
                <%}%>
        </section>

        <section id="filtersAndOrders">
            <div id="filters"> Filtra per
                <div id="tipologie"> Tipologie
                    <% for(Tipologia t : tipologie){%>
                        <div class="tipologia">
                            <label for=<%=t.getNome()%>><%=t.getNome()%></label>
                            <input type="radio" id=<%=t.getNome()%> name="tipologia">
                        </div>
                    <%}%>
                </div>

                <div id="piattaforme"> Piattaforme
                    <% for(Piattaforma p : piattaforme){%>
                    <div class="piattaforma">
                        <label for=<%=p.getNome()%>><%=p.getNome()%></label>
                        <input type="radio" id=<%=p.getNome()%> name="piattaforma">
                    </div>
                    <%}%>
                </div>
            </div>

                <div id="orders"> Ordina per
                    <div class="order">
                        <label for="mostRecent">Date (most recent)</label>
                        <input type="radio" id="mostRecent" name="order">
                    </div>
                    <div class="order">
                        <label for="leastRecent">Date (least recent)</label>
                        <input type="radio" id="leastRecent" name="order">
                    </div>
                    <div class="order">
                        <label for="gameTitle">Game title</label>
                        <input type="radio" id="gameTitle" name="order">
                    </div>
                    <div class="order">
                        <label for="reviewVote">Review vote</label>
                        <input type="radio" id="reviewVote" name="order">
                    </div>
                </div>


        </section>
    </section>


    <%@ include file="../../footer.html" %>
</body>


</html>
