<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rojina Review</title>
    <link rel="stylesheet" href="css/acquista.css">
    <link rel="stylesheet" href="css/navebar.css">
    <link rel="stylesheet" href="css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/navebar.jsp" %>
<c:choose>
    <c:when test="${sessionScope.get('utente') != null}">
        <c:set var="carello" scope="page" value="${sessionScope['utente'].carrello}" />
    </c:when>
    <c:otherwise>
        <c:set var="carello" scope="page" value="${sessionScope['ospite']}" />
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${carello.prodotti.size() > 0}">
    <section class="order">
        <div class="products">
            <c:forEach items="${carello.prodotti}" var="prodotto">
                <div class="product">
                    <section>
                        <a href="/Rojina_Review_war/getResource?type=shop&id=${prodotto.prodotto.id}">
                            <img class="productImg" src="${prodotto.prodotto.immagine}">
                        </a>
                    </section>
                    <section>
                        <h3 class="productName">${prodotto.prodotto.nome}</h3>
                        <h3 class="price">${prodotto.prezzoAttuale}€</h3>
                        <h3 class="quantity">${prodotto.quantità}</h3>
                        <h3 class="subtotal"><fmt:formatNumber value="${prodotto.prezzoAttuale * prodotto.quantità}" maxFractionDigits="3"/>€</h3>
                    </section>
                </div>
            </c:forEach>
        </div>
        <div class="address">
            <c:if test="${sessionScope.get('utente') != null}">
                <c:forEach items="${sessionScope['utente'].indirizzi}" var="indirizzo" varStatus="loop">
                    <input class="boxed" type="radio" id="${loop.index}" name="address" checked="checked">
                    <label for="${loop.index}">
                            ${indirizzo.via} ${indirizzo.numeroCivico} ${indirizzo.città} ${indirizzo.cap}
                    </label>
                </c:forEach>
                <input type="radio" id="newA" name="address">
                <label for="newA">
                       Nuovo
                </label>
            </c:if>
        </div>
        <div class="payment">
            <c:if test="${sessionScope.get('utente') != null}">
                <c:forEach items="${sessionScope['utente'].pagamenti}" var="pagamento" varStatus="loop">
                    <input class="boxed" type="radio" id="${loop.index}" name="payment">
                    <label for="${loop.index}">
                        ${pagamento.nome} ${pagamento.cognome} ${pagamento.numeroCarta} ${pagamento.dataScadenza}
                    </label>
                </c:forEach>
                <input class="boxed" id="newP" type="radio" name="payment">
                <label for="newP">
                    Nuovo
                </label>
            </c:if>
        </div>
        <button>
            Acquista
        </button>
    </section>
    </c:when>
    <c:otherwise>
        <section id="empty">
            <h1>Il tuo carello è vuoto</h1>
        </section>
    </c:otherwise>
</c:choose>
</body>
</html>
