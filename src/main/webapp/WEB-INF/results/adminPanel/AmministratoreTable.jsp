<%@ page import="model.beans.Amministratore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList< Amministratore > list = (ArrayList<Amministratore>)request.getAttribute("list");%>
<table>
    <tr>
        <%for(String s : Amministratore.fieldsName){%>
        <th><%=s%></th>
        <%}%>
        <th id="search" onclick="search()">🔍</th>
        <th id="add" onclick="add()">✖️</th>
    </tr>
        <%for(Amministratore a : list){%>
    <tr>
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
            <button>✏️ </button>
            <button>🗑️ </button>
        </td>
    </tr>

        <%}%>
