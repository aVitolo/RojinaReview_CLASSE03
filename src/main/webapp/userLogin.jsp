<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (session.getAttribute("utente") != null || session.getAttribute("giornalista") != null || session.getAttribute("admin") != null)
    response.sendRedirect("./home");%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="./css/login.css">

</head>
<body>

<div class="center">
    <h1>Login</h1>
    <form method="post" action="./LoginUser">
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
            <p>Non sei Registrato? <a href="./registerUser.jsp">Registrati</a></p>
            <p>Sei un Giornalista o Admin? <a href="./staffLogin.jsp">Clicca Quì</a></p>
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
