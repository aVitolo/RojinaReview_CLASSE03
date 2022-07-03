<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if(request.getSession().getAttribute("utente") != null)
    response.sendRedirect("./home");%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="./css/master.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="center">
    <h1>Create Account</h1>
    <form method="post" action="./RegisterUser">
        <div class="form_input">
            <input id="nickname" type="text" name="nickname" title="Minimum: 5 letters, Maximum: 30 letters, Don't accepted: {# - ' \ }" required>
            <span></span>
            <label>Nickname</label>
        </div>
        <div class="form_input">
            <input id="email" type="email" name="email" title="Minimum: 5 letters, Maximum: 30 letters, Don't accepted: {# - ' \}" required>
            <span></span>
            <label>Email</label>
        </div>
        <div class="form_input">
            <input id="password" type="password" name="password" title="Minimum: 6 letters, Maximum: 20 letters, Don't accepted: {# - ' \}" required>
            <span></span>
            <label>Password</label>
        </div>
        <input id="registerSubmit" type="submit" onclick="formValidation()" value="Create Account">
    </form>
</div>
<c:if test = "${message != null}">
    <script>
        $(document).ready(function(){
            $("#close").click(function(){
                $("#error").slideToggle();
            });
        });
    </script>
    <div class="center" id="error">
        <button id="close"><c:out value="${message}"/></button>
    </div>
</c:if>

<script defer>
    function formValidation(){
            //va aggiunto il check sui caratteri per ogni input
            let nickname = $("#nickname").val();
            let email = $("#email").val();
            let password = $("#password").val();

            //Check nickname
            if(nickname.length < 5 || nickname.length > 30) {
                alert("Wrong format for NICKNAME!\n+ Minimum: 5 letters\n+ Maximum: 30 letters\n+ Don't accepted: {# - ' \\ \" }");
                $("#nickname").val("");
            }

            //Check email
            if(email.length < 5 || email.length > 30) {
                alert("Wrong format for EMAIL!\n+ Minimum: 5 letters\n+ Maximum: 30 letters\n+ Don't accepted: {# - ' \\ \"}");
                $("#email").val("");
            }

            //Check password
            if(password.length < 6 || password.length > 20) {
                alert("Wrong format for PASSWORD!\n+ Minimum: 6 letters\n+ Maximum: 20 letters\n+ Don't accepted: {# - ' \\ \" }");
                $("#password").val("");
            }
        }
</script>
</body>
</html>
