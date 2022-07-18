<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/notizie.css">
    <style>
        section{
            margin: 25%;
            color: white;
            height: fit-content;
            padding: 2%;
            background: #24262b;
        }
        table{
            color: white;
        }
    </style>

</head>
<body>
<%@ include file="/WEB-INF/results/giornalista/journalistArea.jsp" %>
<%@ page import="model.beans.Giornalista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Giornalista g = (Giornalista) session.getAttribute("giornalista");%>
<section>
<h1>Aggiorna i tuoi dati:</h1>
<br>
<table>
    <tr>
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
</section>
<script>
    function info() {
        let resoult = confirm("Cambiando i seguenti dati sarai disconnesso, Sei sicuro? \n(Potrai riloggare subito dopo!!)");
        if (resoult === true) {
            document.getElementById("journalistUpdate").submit();
        }
    }
</script>
</div>
</body>
</html>

