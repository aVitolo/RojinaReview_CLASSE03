<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 27/06/2022
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (request.getSession().getAttribute("utente") != null)
    response.sendRedirect("./home");%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../../../css/master.css">
    <link rel="stylesheet" href="../../css/log.css">
</head>
<body>
<div class="center">
    <h1>Login</h1>
    <p id="loginError">Utente non presente nel Database!! Riprova...</p>
    <form method="post" action="./userLoginCheck">
        <div class="form_input">
            <input type="email" name="email" required>
            <span></span>
            <label>Email</label>
        </div>
        <div class="form_input">
            <input type="password" name="password" required>
            <span></span>
            <label>Password</label>
        </div>
        <input type="submit" value="Login">
        <div class="register_link">
            <p>Non sei Registrato? <a href="#">Registrati</a></p>
        </div>
    </form>
</div>
</body>
</html>

