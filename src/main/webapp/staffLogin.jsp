<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (session.getAttribute("videogiocatore") != null || session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
    response.sendRedirect("./home");%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/login.css">

</head>
<body>

<div class="center">
    <h1>Login</h1>
    <form method="post" action="LoginStaff">
        <div class="form_input" id="radioLogin">
            <input type="radio" class="radioLogin" id="giornalistaRadio" name="userType" value="0" checked="checked">
            <label>Giornalista</label>
            <input type="radio" class="radioLogin" id="amministratoreRadio" name="userType" value="1">
            <label>Amministratore</label>
        </div>
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
            <a href="./registerStaff.jsp">Invia richiesta</a>
        </div>
    </form>
</div>
<c:if test="${message != null}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#close").click(function () {
                $("#error").slideToggle();
            });
        });
    </script>
    <div class="center" id="error">
        <button id="close"><c:out value="${message}"/></button>
    </div>
</c:if>
</body>
</html>

