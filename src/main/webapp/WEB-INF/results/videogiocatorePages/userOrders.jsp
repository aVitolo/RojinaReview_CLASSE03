<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area videogiocatore</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/orders.css">
</head>
<body>
<%@ include file="/WEB-INF/results/videogiocatorePages/userArea.jsp" %>
<c:set var="videogiocatore" scope="page" value="${sessionScope['videogiocatore']}" />

    <h1 class="currentMenuName">Ordini</h1>
    <div class="orders">
        <c:forEach items="${videogiocatore.ordini}" var="ordine">
            <div class="order">
                <section class="resume top">
                    <h3 class="orderId">${ordine.id}</h3>
                    <h3 class="orderState">${ordine.stato}</h3>
                    <h3 class="orderDate">${ordine.dataOrdine}</h3>
                    <h3 class="orderTotal">${ordine.totale}€</h3>
                </section>
                <section class="products">
                    <c:forEach items="${ordine.prodotti}" var="prodotto">
                        <div class="product">
                            <section>
                                <a href="/Rojina_Review_war/getResource?type=shop&id=${prodotto.prodotto.id}">
                                    <img class="productImg" src="${prodotto.prodotto.immagine}">
                                </a>
                            </section>
                            <section>
                                <h3 class="productName">${prodotto.prodotto.nome}</h3>
                                <h3 class="price">${prodotto.prezzoAcquisto}€</h3>
                                <h3 class="quantity">${prodotto.quantita}</h3>
                                <h3 class="subtotal"><fmt:formatNumber value="${prodotto.prezzoAcquisto * prodotto.quantita}" maxFractionDigits="3"/>€</h3>
                            </section>
                        </div>
                    </c:forEach>
                </section>
                <section class="resume">
                    <h3> ${ordine.indirizzo.via} ${ordine.indirizzo.numeroCivico} ${ordine.indirizzo.città} ${ordine.indirizzo.cap} </h3>
                    <h3> ${ordine.pagamento.nome} ${ordine.pagamento.cognome} ${ordine.pagamento.numeroCarta} ${ordine.pagamento.dataScadenza} <h3>
                </section>
            </div>
        </c:forEach>
    </div>


</body>
</html>
