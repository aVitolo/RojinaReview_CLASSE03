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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="center">
    <h1>Create Account</h1>
    <form method="post" action="./RegisterUser">
        <div class="form_input">
            <input id="nickname" type="text" name="nickname"
                   title="Minimum: 5 letters, Maximum: 30 letters, Don't accepted: {# - ' \ }" required>
            <span></span>
            <label>Nickname</label>
        </div>
        <div class="form_input">
            <input id="email" type="email" name="email"
                   title="Minimum: 5 letters, Maximum: 30 letters, Don't accepted: {# - ' \}" required>
            <span></span>
            <label>Email</label>
        </div>
        <div class="form_input">
            <input id="password" type="password" name="password"
                   title="Minimum: 6 letters, Maximum: 20 letters, Don't accepted: {# - ' \}" required>
            <span></span>
            <label>Password</label>
        </div>
        <input id="registerSubmit" type="submit" onclick="validateInputs()" value="Create Account">
        <div class="register_link">
            <p><a href="./userLogin.jsp">Login</a></p>
            <p><a href="./home">Torna alla home</a></p>
        </div>
    </form>
</div>
<c:if test="${message != null}">
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
<script defer src="./static/js/formValidation.js"></script>
</body>
</html>
