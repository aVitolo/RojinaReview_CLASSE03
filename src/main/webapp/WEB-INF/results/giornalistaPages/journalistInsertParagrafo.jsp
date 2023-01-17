<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
  int type = (int) session.getAttribute("type");
  int idArticle = (int) session.getAttribute("idArticle");
  boolean update = (boolean) session.getAttribute("update");
%>
<head>
  <title>Area giornalista</title>
  <link rel="stylesheet" href="./static/css/master.css">
  <link rel="stylesheet" href="./static/css/inserimentoProdotto.css">

</head>
<body>
<%@ include file="/WEB-INF/results/giornalistaPages/journalistArea.jsp" %>
<div class="center">

  <form name="insertParagrafo"  action="/Rojina_Review_war/insertParagrafo" method="post"
        enctype="multipart/form-data">

    <div class="form_input">
      <p>Titolo</p>
      <input type="text" id="titolo" name="titolo"  required>
    </div>

    <textarea type="text" id="testo" name="testo" required>Inserisci testo ...</textarea>

    <div class="form_input">
      <input type="file" id="foto" name="immagine" accept=".jpg">
    </div>

    <div style="display: flex; justify-content: space-evenly;">
      <input id="registerSubmit" type="submit" onclick="validateInputs()" value="Inserisci Paragrafo">
      <%if(update){%>
      <a href="/Rojina_Review_war/formModificaNotizia?id=<%=idArticle%>">Torna alla notizia da modificare</a>
      <%}
      else{
        if(type == 1){%>
      <a href="/Rojina_Review_war/formInsertReview">Torna alla recensione</a>
      <%}
        else if(type == 2){%>
      <a href="/Rojina_Review_war/formInsertNew">Torna alla notizia</a>
      <%}
        }%>
    </div>
  </form>

</div>
</div>
</body>
</html>
