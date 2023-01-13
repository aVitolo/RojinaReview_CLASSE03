<%@ page import="rojinaReview.model.beans.Recensione" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Area giornalista</title>
  <link rel="stylesheet" href="./static/css/master.css">
  <link rel="stylesheet" href="./static/css/inserimentoProdotto.css">
  <link rel="stylesheet" href="./static/css/inserimentoParagrafo.css">
</head>
<body>
<%@ include file="/WEB-INF/results/giornalistaPages/journalistArea.jsp" %>
<div class="center">
  <form name="insertRecensione" action="/Rojina_Review_war/insertReview" method="post"
        enctype="multipart/form-data">

    <div class="form_input">
      <p>Titolo</p>
      <input type="text" id="titolo" name="titolo" required>
    </div>

    <div class="form_input">
      <p>Videogioco</p>
      <input type="text" id="gioco" name="videogioco"  required>
    </div>

    <textarea type="text" id="testo" name="testo" required></textarea>
    <div class="form_input">
      <p>Voto</p>
      <input type="number" id="parere" name="parere" min="0" max="10" value="8" required>
    </div>
    <div class="form_input">
      <input type="file" id="foto" name="immagine">
    </div>
    <div style="display: flex; justify-content: space-evenly;">
      <input id="registerSubmit" type="submit" onclick="freeStore()" value="Inserisci Recensione">
      <a onclick="saveForm()" href="/Rojina_Review_war/formInsertParagrafo">Inserisci Paragrafo</a>
    </div>
  </form>
  <div>
    <div>
      <c:forEach items="${sessionScope['paragrafi']}" var="paragrafo" varStatus="loop">
        <div class="paragrafo" >
          <p class="titoloParagrafo" >${paragrafo.titolo}</p>
          <a onclick="saveForm() "href="/Rojina_Review_war/rimuoviParagrafo?id=${loop.index}">Rimuovi Paragrafo</a>
          <a onclick="saveForm() " href="/Rojina_Review_war/formModificaParagrafo?id=${loop.index}">Modifica Paragrafo</a>
        </div>
      </c:forEach>
    </div>
  </div>
</div>
</div>
</body>
<script>


  function  saveForm(){
    localStorage.setItem("titolo",document.getElementById("titolo").value);
    localStorage.setItem("testo",document.getElementById("testo").value);
    localStorage.setItem("parere",document.getElementById("parere").value);
    localStorage.setItem("gioco",document.getElementById("gioco").value);
  }

    function freeStore(){
      localStorage.removeItem("titolo");
      localStorage.removeItem("testo");
      localStorage.removeItem("parere");
      localStorage.removeItem("gioco");
    }

    document.getElementById("titolo").setAttribute("value",localStorage.getItem("titolo"));
    document.getElementById("testo").value= localStorage.getItem("testo");
    document.getElementById("parere").setAttribute("value",localStorage.getItem("parere"));
    document.getElementById("gioco").setAttribute("value",localStorage.getItem("gioco"));

</script>
</html>
