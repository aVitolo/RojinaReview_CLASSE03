<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Area giornalista</title>
  <link rel="stylesheet" href="./static/css/master.css">
  <link rel="stylesheet" href="./static/css/inserimentoProdotto.css">

</head>
<body>
<%@ include file="/WEB-INF/results/giornalistaPages/journalistArea.jsp" %>
<div class="center">

  <form method="get" action="./modificaParagrafo"  enctype="multipart/form-data">

    <div class="form_input">
      <p>Titolo</p>
      <input id="titolo" type="text" name="titolo" value="${paragrafo.titolo}" required>
    </div>

    <textarea type="text" id="testo" name="testo" required>${paragrafo.testo}</textarea>

    <div class="form_input">
      <input id="foto" type="file" name="foto">
    </div>

    <div class="form_input" style="visibility: hidden">
      <input id="id" type="hidden" value="${id}"  name="id">
    </div>
    <div style="display: flex; justify-content: space-evenly;">
      <input id="registerSubmit" type="submit" onclick="validateInputs()" value="Modifica Paragrafo">
      <a href="/Rojina_Review_war/formInsertReview">Torna alla recensione</a>
    </div>

  </form>

</div>
</div>
</body>
</html>
