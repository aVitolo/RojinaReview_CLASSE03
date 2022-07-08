<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area utente</title>
    <link rel="stylesheet" href="css/master.css">
    <link rel="stylesheet" href="css/orders.css">
</head>
<body>
<%@ include file="/WEB-INF/results/userArea.jsp" %>
<c:set var="utente" scope="page" value="${sessionScope['utente']}" />
<div class="menu">
    <h1 class="currentMenuName">Ordini</h1>
    <div class="orders">
        <c:forEach items="${utente.ordini}" var="ordine">
            <div class="order">
                <h3 class="orderId">${ordine.id}</h3>
                <h3 class="orderState">${ordine.stato}</h3>
                <h3 class="orderDate">${ordine.dataOrdine}</h3>
                <h3 class="orderTotal">${ordine.totale}</h3>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
