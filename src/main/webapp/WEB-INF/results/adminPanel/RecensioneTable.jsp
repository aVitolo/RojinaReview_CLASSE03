<%@ page import="java.util.ArrayList" %>
<%@ page import="model.beans.Recensione" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Recensione> list = (ArrayList<Recensione>)request.getAttribute("list");%>
<table>
    <tr>
        <%for(String s : Recensione.fieldsName){%>
        <th><%=s%></th>
        <%}%>
        <th id="search" onclick="search()">🔍</th>
        <th id="add" onclick="add()">✖️</th>
    </tr>
        <%for(Recensione a : list){%>
    <tr>
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
            <%= a.getVoto()%>
        </td>
        <td>
            <%= a.getDataCaricamento()%>
        </td>
        <td>
            <button>✏️ </button>
            <button>🗑️ </button>
        </td>
    </tr>

        <%}%>
