<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Notizia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList< Notizia > list = (ArrayList<Notizia>)request.getAttribute("list");%>
<table>
    <tr>
        <%for(String s : Notizia.fieldsName){%>
        <th><%=s%></th>
        <%}%>
        <th id="search" onclick="search()">🔍</th>
        <th id="add" onclick="add()">➕️</th>
    </tr>
        <%for(Notizia a : list){%>
    <tr id="<%=a.getId()%>">
        <td>
            <%= a.getId()%>
        </td>
        <td>
            <%= a.getTesto()%>
        </td>
        <td>
            <%= a.getTitolo()%>
        </td>
        <td>
            <%= a.getDataCaricamento()%>
        </td>
        <td>
            <button name="<%=a.getId()%>" onclick="modifyEntry(this)">✏️ </button>
            <button name="<%=a.getId()%>" onclick="deleteEntry(this)">🗑️ </button>
        </td>
    </tr>

        <%}%>
    <script>
        var details = "'email','username','password', 'nome', 'cognome', età, 'immagine' or null";
        var necessary = "";
    </script>

