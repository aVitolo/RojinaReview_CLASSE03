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
        <th id="add" onclick="add()">✖️</th>
    </tr>
        <%for(Notizia a : list){%>
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
            <%= a.getDataCaricamento()%>
        </td>
        <td>
            <button>✏️ </button>
            <button>🗑️ </button>
        </td>
    </tr>

        <%}%>

