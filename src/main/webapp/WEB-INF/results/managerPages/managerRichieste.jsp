<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/richieste.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
<section class="wrap">
    <section class="richiesta-back">
        <h2>Giornalista</h2>
        <div class="richiesta-header">
            <p>Nome</p>
            <p>Cognome</p>
            <p>Email</p>
        </div>
        <c:forEach items="${requestScope['giornalisti']}" var="giornalista">
        <div class="richiesta" id="g${giornalista.id}">
            <p>${giornalista.nome}</p>
            <p>${giornalista.cognome}</p>
            <p>${giornalista.email}</p>
            <button onclick="hanldRequest('Giornalista','0','${giornalista.id}')">Rifiuta</button>
            <button onclick="hanldRequest('Giornalista','1','${giornalista.id}')">Accetta</button>
            </c:forEach>
    </section>
    <section class="richiesta-back">
        <h2>Manager</h2>
        <div class="richiesta-header">
            <p>Nome</p>
            <p>Cognome</p>
            <p>Email</p>
        </div>
        <c:forEach items="${requestScope['managers']}" var="manager">
            <div class="richiesta" id="m${manager.id}">
                <p>${manager.nome}</p>
                <p>${manager.cognome}</p>
                <p>${manager.email}</p>
                <button onclick="hanldRequest('Manager','0','${manager.id}')" >Rifiuta</button>
                <button onclick="hanldRequest('Manager','1','${manager.id}')" >Accetta</button>
            </div>
        </c:forEach>
    </section>
</section>
</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    function hanldRequest(table,action,id){
        servlet = "/Rojina_Review_war/gestisciRichiesteServlet";
        $.ajax({
                url: servlet,
                type: "post",
                data: {
                    "table": table,
                    "action": action,
                    "id": id,
                },
                success: function() {
                    console.log(id);
                    if(table==='Manager'){
                        m='#m'+id;
                        console.log(m);
                        $(m).remove();
                    }
                    else{
                        g='#g'+id;
                        console.log(g);
                        $(g).remove();
                    }
                }

        });
    }
</script>
</html>
