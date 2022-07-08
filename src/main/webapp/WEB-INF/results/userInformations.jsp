<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area utente</title>
    <link rel="stylesheet" href="css/master.css">
</head>
<body>
<c:set var="utente" scope="page" value="${sessionScope['utente']}" />
<%@ include file="/WEB-INF/results/userArea.jsp" %>
<div class="menu">
    <h1 class="currentMenuName">Informazioni Utente</h1>
    <div class="informations">
        <div class="addresses">
            <h3 class="userAttribute">Indirizzi:</h3>
            <c:forEach items="${utente.indirizzi}" var="indirizzo">
                <div class="address">
                    ${indirizzo.via} ${indirizzo.numeroCivico} ${indirizzo.città} ${indirizzo.cap}
                </div>
            </c:forEach>
            <div class="insertAddress">
                <form name="insertAddress" action="/Rojina_Review_war/insertAddress" method="post">
                    <label for="via">Via: </label>
                    <input type="text" id="via" name="via">
                    <label for="numeroCivico">Numero civico: </label>
                    <input type="text" id="numeroCivico" name="numeroCivico">
                    <label for="città">Città: </label>
                    <input type="text" id="città" name="città">
                    <label for="cap">CAP: </label>
                    <input type="text" id="cap" name="cap">

                    <input type="submit" value="Aggiungi">
                </form>
            </div>
        </div>
        <div class="numbers">
            <h3 class="userAttribute">Telefoni:</h3>
            <c:forEach items="${utente.telefoni}" var="telefono">
                <div class="number">
                    ${telefono.numero}
                </div>
            </c:forEach>
            <div class="insertNumber">
                <form name="insertNumber" action="/Rojina_Review_war/insertNumber" method="post">
                    <label for="telefono">Numero: </label>
                    <input type="text" id="telefono" name="telefono">

                    <input type="submit" value="Aggiungi">
                </form>
            </div>
        </div>
        <div class="paymentMethods">
            <h3 class="userAttribute">Metodi di pagamento:</h3>
            <c:forEach items="${utente.pagamenti}" var="pagamento">
                <div class="paymentMethod">
                    ${pagamento.nome} ${pagamento.cognome} ${pagamento.numeroCarta} ${pagamento.dataScadenza}
                </div>
            </c:forEach>
            <div class="insertPayment">
                <form name="insertPayment" action="/Rojina_Review_war/insertPayment" method="post">
                    <label for="nome">Nome: </label>
                    <input type="text" id="nome" name="nome">
                    <label for="cognome">Cognome: </label>
                    <input type="text" id="cognome" name="cognome">
                    <label for="numeroCarta">Numero carta: </label>
                    <input type="text" id="numeroCarta" name="numeroCarta">
                    <label for="dataScadenza">Data scadenza: </label>
                    <input type="date" id="dataScadenza" name="dataScadenza">

                    <input type="submit" value="Aggiungi">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
