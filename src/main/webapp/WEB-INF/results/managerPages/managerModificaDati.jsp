<%--
  Created by IntelliJ IDEA.
  User: felin
  Date: 12/01/2023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
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
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
<c:set var="manager" scope="page" value="${sessionScope['manager']}" />
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
            <form id="userUpdate" method="POST" action="./managerUpdateDataServlet">
                <td>
                    <input type="text" value="${manager.email}" name="email">
                </td>
                <td>
                    <input type="password"  name="password">
                </td>
                <td>
                    <input type="text" value="${manager.nome}" name="nome">
                </td>
                <td>
                    <input type="text" value="${manager.cognome}" name="cognome">
                </td>
                <td>
                    <input type="submit" value="Aggiorna">
                </td>
            </form>
        </tr>
    </table>
</section>

</body>
</html>
