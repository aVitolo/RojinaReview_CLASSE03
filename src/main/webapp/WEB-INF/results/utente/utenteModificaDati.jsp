<%@ page import="model.beans.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utente u = (Utente) session.getAttribute("utente");%>
<h1>Aggiorna i tuoi dati:</h1>
<br>
<table>
    <tr>
        <th>
            <p>Email</p>
        </th>
        <th>
            <p>Nickname</p>
        </th>
        <th>
            <p>Nome</p>
        </th>
        <th>
            <p>Cognome</p>
        </th>
        <th>
            <p>Età</p>
        </th>
    </tr>
    <tr>
        <form id="userUpdate" method="POST" action="./userUpdateData">
            <td>
                <input type="text" value="<%=u.getEmail()%>" name="email">
            </td>
            <td>
                <input type="text" value="<%=u.getNickname()%>" name="nickname">
            </td>
            <td>
                <input type="text" value="<%=u.getNome()%>" name="nome">
            </td>
            <td>
                <input type="text" value="<%=u.getCognome()%>" name="cognome">
            </td>
            <td>
                <input type="text" value="<%=u.getEta()%>" name="eta">
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
            document.getElementById("userUpdate").submit();
        }
    }
</script>
