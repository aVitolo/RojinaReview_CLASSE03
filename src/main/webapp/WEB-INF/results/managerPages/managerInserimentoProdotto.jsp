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

  <form method="post" action="./aggiungiProdotto"  enctype="multipart/form-data">

    <div class="form_input">
      <p>Nome</p>
      <input id="Nome" type="text" name="nome" required>
    </div>

    <textarea type="text" id="testo" name="descrizione" required>Inserisci testo ...</textarea>

    <div class="form_input">
      <p>Quantita</p>
      <input id="Quantita" type="text" name="quantita" required>
    </div>

    <div class="form_input">
      <p>Prezzo</p>
      <input id="Prezzo" type="text" name="prezzo" required>
    </div>

    <div>
      <input type="radio" class="radioCategoria" id="casaRadio" name="productType" value="Casa" checked="checked">
      <label>Casa</label>
      <input type="radio" class="radioCategoria" id="giocattoliRadio" name="productType" value="Giocattoli">
      <label>Giocattoli</label>
      <input type="radio" class="radioCategoria" id="abbigliamentoRadio" name="productType" value="Abbigliamento">
      <label>Abbigliamento</label>
    </div>

    <div class="form_input">
      <input type="file" id="foto" name="foto">
    </div>

    <input id="registerSubmit" type="submit" onclick="validateInputs()" value="Invia Prodotto">
  </form>
</div>
</body>
</html>
