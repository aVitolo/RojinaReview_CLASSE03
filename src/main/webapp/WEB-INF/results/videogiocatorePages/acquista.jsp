<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rojina Review</title>
    <link rel="stylesheet" href="./static/css/acquista.css">
    <link rel="stylesheet" href="./static/css/navebar.css">
    <link rel="stylesheet" href="./static/css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/utilities/navebar.jsp" %>
<c:choose>
    <c:when test="${sessionScope.get('videogiocatore') != null}">
        <c:set var="carello" scope="page" value="${sessionScope['videogiocatore'].carrello}" />
    </c:when>
    <c:otherwise>
        <c:set var="carello" scope="page" value="${sessionScope['ospite']}" />
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${carello.prodotti.size() > 0}">
    <section class="order">
        <c:if test="${sessionScope.get('videogiocatore') == null}">
            <h2 id="notLogged">Devi essere loggato per continuare con l'acquisto</h2>
        </c:if>
        <div class="products">
            <c:forEach items="${carello.prodotti}" var="prodotto">
                <div class="product" id="${prodotto.id}">
                    <section>
                        <a href="/Rojina_Review_war/getResource?type=shop&id=${prodotto.id}">
                            <img class="productImg" src="${prodotto.immagine}">
                        </a>
                    </section>
                    <section>
                        <h3 class="productName">${prodotto.nome}</h3>
                        <div style="
                             display: flex;
                             justify-content: center;
                             ">
                            <h3 class="price">${prodotto.prezzo}</h3>
                            <h3>€</h3>
                        </div>
                        <label class="updateArticol"><input type="number" min="1"  value="${prodotto.quantità}">Quantità</label>
                        <div style="
                             display: flex;
                             justify-content: center;
                             ">
                            <h3 class="subtotal"><fmt:formatNumber value="${prodotto.prezzo * prodotto.quantità}" maxFractionDigits="3"/></h3>
                            <h3>€</h3>
                        </div>
                        <button class="deleteArticol" onclick='removeFromCart("${prodotto.id}")'>Rimuovi</button>
                    </section>
                </div>
            </c:forEach>
            <h1 id="tot" style="text-align: center">Totale ${carello.totale} </h1>
        </div>
        <c:if test="${sessionScope.get('videogiocatore') != null}">
            <form method="post" action="/Rojina_Review_war/confirmOrder">
                <div class="address">
                    <h2>Indirizzo di spedizione</h2>
                    <c:forEach items="${sessionScope['videogiocatore'].indirizzi}" var="indirizzo" varStatus="loop">
                        <input class="boxed" type="radio" id="address${loop.index}" name="address">
                        <label for="address${loop.index}" onclick='autoFill("${indirizzo.via}","${indirizzo.numeroCivico}","${indirizzo.città}","${indirizzo.cap}","newAddress")'>
                                ${indirizzo.via} ${indirizzo.numeroCivico} ${indirizzo.città} ${indirizzo.cap}
                        </label>
                    </c:forEach>
                    <input type="radio" id="newA" name="address" value="true">
                    <label for="newA" onclick='resetForm("newAddress")'>
                        Nuovo
                    </label>
                    <div class="hide" id="newAddress">
                        <label for="via">
                            Via
                        </label>
                        <input type="text" id="via" name="via" required="required">
                        <label for="numeroCivico">
                            Numero Civico
                        </label>
                        <input type="text" id="numeroCivico" name="numeroCivico" required="required">
                        <label for="citta">
                            Citta
                        </label>
                        <input type="text" id="citta" name="citta" required="required">
                        <label for="cap">
                            CAP
                        </label>
                        <input type="text" id="cap" name="cap" required="required">
                    </div>
                </div>
                <div class="payment">
                    <h2>Metodo di Pagamento</h2>
                    <c:forEach items="${sessionScope['videogiocatore'].pagamenti}" var="pagamento" varStatus="loop">
                        <input class="boxed" type="radio" id="payment${loop.index}" name="payment">
                        <label for="payment${loop.index}" onclick='autoFill("${pagamento.nome}", "${pagamento.cognome}", "${pagamento.numeroCarta}"," ${pagamento.dataScadenza}","newPayment")'>
                                ${pagamento.nome} ${pagamento.cognome} ${pagamento.numeroCarta} ${pagamento.dataScadenza}
                        </label>
                    </c:forEach>
                    <input class="boxed" id="newP" type="radio" name="payment" value="true">
                    <label for="newP" onclick=resetForm("newPayment")>
                        Nuovo
                    </label>
                    <div class="hide" id="newPayment">
                        <label for="nome">
                            Nome
                        </label>
                        <input type="text" id="nome" name="nome" required="required">
                        <label for="Cognome">
                            Cognome
                        </label>
                        <input type="text" id="cognome" name="cognome" required="required">
                        <label for="numeroCarta">
                            Numero Carta
                        </label>
                        <input type="text" id="numeroCarta" name="numeroCarta" required="required">
                        <label for="dataScadenza">
                            Data Scadenza
                        </label>
                        <input type="date" id="dataScadenza" name="dataScadenza" required="required">
                    </div>
                </div>
                <input type="submit" value="Acquista">
            </form>
        </c:if>
    </section>
    </c:when>
    <c:otherwise>
        <section id="empty">
            <h1>Il tuo carello è vuoto</h1>
        </section>
    </c:otherwise>
</c:choose>
</body>
<script>

    function resetForm(divId) {
        let div = document.getElementById(divId);
        if (div.className === "hide")
            div.className = "form-input";
        let input = div.getElementsByTagName('input');
        for(let i=0;i<input.length;i++)
            input[i].value="";
    }

    function autoFill(input1, input2, input3, input4, divId) {
        let div = document.getElementById(divId);
        if (div.className === "form-input")
            div.className = "hide";
        let input = div.getElementsByTagName('input');
        input[0].value = input1;
        input[1].value = input2;
        input[2].value = input3;
        input[3].value = input4;
    }

    function  removeFromCart(idProdotto){
        requestText ="id=" + idProdotto;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                if (this.responseText === "-1") {
                    alert("Error")
                } else if(this.responseText ==="0.0"){
                    document.getElementsByClassName("order")[0].innerHTML = "<section id='empty'> <h1>Il tuo carello è vuoto</h1></section>"
                    document.getElementById("countCarrello").innerHTML = 0;
                }
                else{
                    document.getElementById(idProdotto).remove();
                    document.getElementById("tot").innerHTML = "Totale "+this.responseText;
                    let countCarello = document.getElementById("countCarrello").innerHTML;
                    countCarello = countCarello-1;
                    document.getElementById("countCarrello").innerHTML = countCarello;
                }
            }
        };
        xhttp.open("POST", "./RemoveFromCart", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(requestText);
    }


    for (let p of document.getElementsByClassName("product")){
        let idProdotto = p.getAttribute("id");
        let input = p.getElementsByTagName("input")[0];
        input.addEventListener('change', function () {
            let newQuantity = input.value;
            requestText = "id=" + idProdotto + "&quantita=" + newQuantity;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    if (this.responseText === "-1") {
                        //error
                    } else{
                        let subtotal = p.getElementsByClassName("subtotal")[0];
                        subtotal.innerHTML = newQuantity * p.getElementsByClassName("price")[0].innerHTML;
                        document.getElementById("tot").innerHTML = "Totale "+this.responseText;
                    }
                }
            };
            xhttp.open("POST", "./updateProductDisponibility", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send(requestText);
        });
    }
</script>
</html>
