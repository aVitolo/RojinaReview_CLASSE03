<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Giornalista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Giornalista> list = (ArrayList<Giornalista>)request.getAttribute("list");%>
<table>
    <tr>
        <%for(String s : Giornalista.fieldsName){%>
        <th><%=s%></th>
        <%}%>
        <th id="search" onclick="search()">🔍</th>
        <th id="add" onclick="add()">✖️</th>
    </tr>
        <%for(Giornalista a : list){%>
    <tr id="<%=a.getId()%>">
        <td>
            <%= a.getId()%>
        </td>
        <td>
            <%= a.getNome()%>
        </td>
        <td>
            <%= a.getCognome()%>
        </td>
        <td>
            <%= a.getEmail()%>
        </td>
        <td>
            <%= a.getPassword()%>
        </td>
        <td>
            <button name="<%=a.getId()%>" onclick="modifyEntry(this)">✏️ </button>
            <button name="<%=a.getId()%>" onclick="deleteEntry(this)">🗑️ </button>
        </td>
    </tr>

        <%}%>
    <script>
        var details = "'nome', 'cognome', 'email', 'pass', 'immagine' or null)";
        var necessary = "(nome, cognome, email, pass, immagine)";
    </script>
