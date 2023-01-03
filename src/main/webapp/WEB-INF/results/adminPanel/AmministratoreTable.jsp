<%@ page import="rojinaReview.model.beans.Manager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Manager> list = (ArrayList<Manager>) request.getAttribute("list");%>
<table>
    <tr>
        <%for (String s : Manager.fieldsName) {%>
        <th><%=s%>
        </th>
        <%}%>
        <th id="search" onclick="search()">🔍</th>
        <th id="add" onclick="add()">➕️</th>
    </tr>
        <%for(Manager a : list){%>
    <tr id="<%=a.getId()%>">
        <td>
            <p>
                <%= a.getId()%>
            </p>
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
            <button name="<%=a.getId()%>" onclick="modifyEntry(this)">✏️</button>
            <button name="<%=a.getId()%>" onclick="deleteEntry(this)">🗑️</button>
        </td>
    </tr>
        <%}%>
    <script>
        var details = "'nome', 'cognome', 'email', 'pass'";
        var necessary = "(nome, cognome, email, pass)";
    </script>