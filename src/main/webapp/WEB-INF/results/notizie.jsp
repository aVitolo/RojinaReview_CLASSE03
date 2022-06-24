<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Notizia" %>
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
<% ArrayList<Notizia> notizie = (ArrayList<Notizia>) session.getAttribute("notizie");
    ArrayList<Tipologia> tipologie = (ArrayList<Tipologia>) session.getAttribute("tipologie");
    ArrayList<Piattaforma> piattaforme = (ArrayList<Piattaforma>) session.getAttribute("piattaforme"); %>

<head>
    <title>Notizie - Rojina</title>
</head>
<body>
    <%@ include file="../../navebar.html" %>

    <article id="mainNew">
        <img id="mainImage" src="data:image/jpg;base64, <%=new String(notizie.get(0).getImmagine())%>">
        <div id="mainNewTitle">
            <h1><%=notizie.get(0).getTitolo()%></h1>
            by <%=notizie.get(0).getGiornalista()%> on <%=notizie.get(0).getDataCaricamento()%>
        </div>
    </article>

    <section id="newsAndFiltersOrders">
        <section id="news">
            <% for(int i = 1; i < notizie.size(); i++){%> <! -- far partire da 0 o 1 considerando la main review in alto? -- >
            <article class="new">
                <img class="newImage" src="data:image/jpg;base64, <%=notizie.get(i).getImmagine()%>">
                <h2 class="newTitle"><%=notizie.get(i).getTitolo()%></h2>
                by <%=notizie.get(i).getGiornalista()%> on <%=notizie.get(i).getDataCaricamento()%>
            </article>
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
            </div>


        </section>
    </section>


    <%@ include file="../../footer.html" %>
</body>
</html>
