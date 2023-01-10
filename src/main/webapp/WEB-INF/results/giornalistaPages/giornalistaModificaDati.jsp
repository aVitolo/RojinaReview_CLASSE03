<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/notizie.css">
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
<%@ include file="/WEB-INF/results/giornalistaPages/journalistArea.jsp" %>
<%@ page import="rojinaReview.model.beans.Giornalista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Giornalista g = (Giornalista) session.getAttribute("giornalista");%>
<section>
<h1>Aggiorna i tuoi dati:</h1>
<br>
<table>
    <tr>
        <th>
            <p>Email</p>
        </th>
        <th>
            <p>Password</p>
        </th>
        <th>
            <p>Nome</p>
        </th>
        <th>
            <p>Cognome</p>
        </th>
    </tr>
    <tr>
        <form id="journalistUpdate" method="POST" action="./journalistUpdateData">
            <td>
                <input type="text" value="<%=g.getEmail()%>" name="email">
            </td>
            <td>
                <input type="password"  name="password">
            </td>
            <td>
                <input type="text" value="<%=g.getNome()%>" name="nome">
            </td>
            <td>
                <input type="text" value="<%=g.getCognome()%>" name="cognome">
            </td>
            <td>
                <input type="submit" value="Aggiorna">
            </td>
        </form>
    </tr>
</table>
</section>
</div>
</body>
</html>

