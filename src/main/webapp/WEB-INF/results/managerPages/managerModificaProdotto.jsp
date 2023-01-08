<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Area Manager</title>
  <link rel="stylesheet" href="./static/css/master.css">
  <link rel="stylesheet" href="./static/css/inserimentoProdotto.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
<div class="center">
  <form method="post" action="./modificaProdotto">
    <div class="form_input">
      <input id="Nome" type="text" name="nome" value="${prodotto.nome}" required>
      <span></span>
      <label>Nome</label>
    </div>
    <div class="form_input">
      <input id="Descrizione" type="text" name="descrizione" value="${prodotto.testo}" required>
      <span></span>
      <label>Descrizine</label>
    </div>
    <div class="form_input">
      <input id="Quantita" type="text" name="quantita" value="${prodotto.quantità}" required>
      <span></span>
      <label>Quantita</label>
    </div>
    <div class="form_input">
      <input id="Prezzo" type="text" name="prezzo" value="${prodotto.prezzo}" required>
      <span></span>
      <label>Prezzo</label>
    </div>
    <div>
      <input type="radio" class="radioCategoria" id="casaRadio" name="productType" value="Casa"
          <c:if test="${prodotto.categoria =='Casa'}">
             checked="checked">
          </c:if>
      <label>Casa</label>
      <input type="radio" class="radioCategoria" id="giocattoliRadio" name="productType" value="Giocattoli"
        <c:if test="${prodotto.categoria =='Giocattoli'}">
             checked="checked">
        </c:if>
      <label>Giocattoli</label>
      <input type="radio" class="radioCategoria" id="abbigliamentoRadio" name="productType" value="Abbigliamento"
        <c:if test="${prodotto.categoria =='Abbigliamento'}">
               checked="checked">
        </c:if>>
      <label>Abbigliamento</label>
    </div>
    <div class="form_input">
      <input id="fotoProdotto" type="file">
      <label>Foto Prodotto</label>
    </div>
    <div class="form_input" style="visibility: hidden">
      <input id="idProdotto" type="hidden" value="${prodotto.id}">
    </div>
    <input id="registerSubmit" type="submit" onclick="validateInputs()" value="Invia Richiesta">
  </form>
</div>
</body>
</html>
