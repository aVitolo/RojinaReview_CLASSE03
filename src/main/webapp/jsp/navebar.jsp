<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<header id="head">

    <section class="bar">
        <img class="logo" src="./images/utility/rojinah.png" alt="Logo" />
        <button class="expand" onclick="expandMenu()"> = </button>
    </section>

    <section class="navigazione" id="nav">
        <a href="">Home</a>
        <a href="">News</a>
        <a href="">Reviews</a>
        <a href="">Shop</a>
    </section>


    <section class="user" id="us">
        <c:choose>
            <c:when test = "${sessionScope.get('utente') != null}">
                <p> Bentornato,</p>
                <a href=""><c:out value='${utente.nickname}'/></a>
            </c:when>
            <c:otherwise>
                <button onclick="document.location.href='./html/login.html'">Login</button>
            </c:otherwise>
        </c:choose>
    </section>
</header>
</html>