<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="ordine" value="${requestScope['ordine']}" />
<html>
<head>
    <title>Ordine - ${ordine.id}</title>
    <link rel="stylesheet" href="css/master.css">

</head>
<body>
<div id="ordine">
    <div id="upperOrder">
        <a href="${ordine.tracking}" title="Traccia il mio pacco"><h2 id="statoOrdine">${ordine.stato}</h2></a>
        <h2 id="totale">${ordine.totale}€</h2>
    </div>
    <div id="products">
        <c:forEach items="${ordine.prodotti}" var="prodotto">
            <div class="product">
                <a href="/Rojina_Review_war/getResource?type=shop&id=${prodotto.prodotto.id}">
                    <img class="productImg" src="${prodotto.prodotto.immagine}">
                    <h3 class="productName">${prodotto.prodotto.nome}</h3>
                </a>
                <h3 class="price">${prodotto.prezzoAcquisto}€</h3>
                <h3 class="quantity">${prodotto.quantita}</h3>
                <h3 class="subtotal"><fmt:formatNumber value="${prodotto.prezzoAcquisto * prodotto.quantita}" maxFractionDigits="2"/>€</h3>
            </div>
        </c:forEach>
    </div>
    <h2 id="orderID">#${ordine.id}</h2>
    <h2 id="orderDate">${ordine.dataOrdine}</h2>
    <div id="datiIndirizzo">
        <h2 id="address">Indirizzo:</h2>
        ${ordine.indirizzo.via} ${ordine.indirizzo.numeroCivico} ${ordine.indirizzo.città} ${ordine.indirizzo.cap}
    </div>
    <div id="datiPagamento">
        <h2 id="payment">Pagamento:</h2>
        ${ordine.pagamento.nome} ${ordine.pagamento.cognome} ${ordine.pagamento.numeroCarta} ${ordine.pagamento.dataScadenza}
    </div>
</div>
</body>
</html>
