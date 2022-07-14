<%@ page import="model.beans.Giornalista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Giornalista g = (Giornalista) session.getAttribute("giornalista");%>
<h1>Aggiorna i tuoi dati:</h1>
<br>
<table>
    <tr>
        <th>
            <p>Id</p>
        </th>
        <th>
            <p>Nome</p>
        </th>
        <th>
            <p>Cognome</p>
        </th>
        <th>
            <p>Email</p>
        </th>
    </tr>
    <tr>
        <form id="journalistUpdate" method="POST" action="./journalistUpdateData">
            <td>
                <input type="text" value="<%=g.getId()%>" name="id">
            </td>
            <td>
                <input type="text" value="<%=g.getNome()%>" name="nome">
            </td>
            <td>
                <input type="text" value="<%=g.getCognome()%>" name="cognome">
            </td>
            <td>
                <input type="text" value="<%=g.getEmail()%>" name="email">
            </td>
            <td>
                <input type="button" value="Aggiorna" onclick="info()">
            </td>
        </form>
    </tr>
</table>
<script>
    function info() {
        let resoult = confirm("Cambiando i seguenti dati sarai disconnesso, Sei sicuro? \n(Potrai riloggare subito dopo!!)");
        if (resoult === true) {
            document.getElementById("journalistUpdate").submit();
        }
    }
</script>