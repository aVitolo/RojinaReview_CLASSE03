<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<c:if test="${sessionScope['ospite'] != null}">
    <c:set var="ospite" scope="page" value="${sessionScope['ospite']}"/>
</c:if>

<header id="head">

    <section class="bar">
        <img class="logo" style="cursor:pointer" src="./images/utility/rojinah.png" onclick="location.href='./home'" alt="Logo"/>
        <button class="expand" onclick="expandMenu()"> ≡</button>
    </section>

    <section class="navigazione" id="nav">
        <a href="/Rojina_Review_war/home">Home</a>
        <a href="/Rojina_Review_war/news">News</a>
        <a href="/Rojina_Review_war/reviews">Reviews</a>
        <a href="/Rojina_Review_war/shop">Shop</a>
    </section>


    <section class="user" id="us">
        <c:choose>
            <c:when test="${sessionScope.get('utente') != null}">
                <a href="">Bentornato, <c:out value='${utente.nickname}'/></a>
                <a href="/Rojina_Review_war/userArea"><img  class="icon" src="${utente.immagine}"></a>
                <button alt="logout" onclick="document.location.href='./logout'">Logout</button>
            </c:when>
            <c:when test="${sessionScope.get('giornalista') != null}">
                <a href="">Bentornato, <c:out value='${giornalista.nome}'/></a>
                <a href="/Rojina_Review_war/journalistArea"><img class="icon"src="images/utility/journalistIcon.png"></a>
                <button alt="logout" onclick="document.location.href='./logout'">Logout</button>
            </c:when>
            <c:when test="${sessionScope.get('admin') != null}">
                <a href="">Bentornato, <c:out value='${admin.nome}'/></a>
                <a href=""><img class="icon" src="images/utility/adminIcon.png"></a>
                <button alt="logout" onclick="document.location.href='./logout'">Logout</button>
            </c:when>
            <c:otherwise>
                <a href="">Carrello <c:out value='${fn:length(ospite.prodotti)}'/></a>
                <button onclick="document.location.href='./userLogin.jsp'">Login</button>
            </c:otherwise>
        </c:choose>
    </section>
</header>

</html>