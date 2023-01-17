<%--
  Created by IntelliJ IDEA.
  User: felin
  Date: 17/01/2023
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
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
<c:set var="notizia" value="${sessionScope.get('notizia')}"/>

<div class="center">
    <form name="updateNotizia" action="/Rojina_Review_war/updateNew" method="post"
          enctype="multipart/form-data">

        <div class="form_input">
            <input type="hidden" name="idNotizia" value="${notizia.id}">
            <p>Titolo</p>
            <input type="text" id="titolo" value="${notizia.nome}" name="titolo" required>
        </div>

        <div class="form_input">
            <p>Videogiochi menzionati</p>
            <input type="text" id="videogiochi" value="${notizia.getTitoliMenzionati()}" name="videogiochi"  >
        </div>

        <textarea type="text" id="testo" name="testo" required>${notizia.testo}</textarea>

        <div class="form_input">
            <input type="file" id="foto" name="immagine">
        </div>
        <div style="display: flex; justify-content: space-evenly;">
            <input id="registerSubmit" type="submit" onclick="freeStore()" value="Modifica Notizia">
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

    saveForm()




    document.getElementById("titolo").setAttribute("value",localStorage.getItem("titolo"));
    document.getElementById("testo").value= localStorage.getItem("testo");
    document.getElementById("videogiochi").setAttribute("value",localStorage.getItem("videogiochi"));

    function  saveForm(){
        localStorage.setItem("titolo",document.getElementById("titolo").value);
        localStorage.setItem("testo",document.getElementById("testo").value);
        localStorage.setItem("videogiochi",document.getElementById("videogiochi").value);
    }

    function freeStore(){
        localStorage.removeItem("titolo");
        localStorage.removeItem("testo");
        localStorage.removeItem("videogiochi");
    }

</script>
</html>
