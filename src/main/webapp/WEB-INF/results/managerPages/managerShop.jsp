<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area Manager</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/notizie.css">
</head>
<body>
<%@ include file="/WEB-INF/results/managerPages/managerArea.jsp" %>
    <style>
        a.modifica {
            margin: 5px 5px;
            background: #e91e63;
            border: none;
            cursor: pointer;
            transtion-duration: .3s;
            border-radius: 2px;
            color: white;
            padding: 2%;
        }

        a.modifica:hover{
            background: #6B354D;
        }

        a.inserisci {
            margin: 0;
            top:0;
            right: 0;
            padding: 1%;
            position: fixed;
            background: #24262b;
            border: none;
            cursor: pointer;
            transtion-duration: .3s;
            border-radius: 2px;
            color: white;
        }

        a.inserisci:hover{
            color: #e91e63;
        }
    </style>
    <section>
        <a class="inserisci" href="/Rojina_Review_war/inserimentoProdotto">Inserisci prodotto</a>
    </section>
    <section class="notizie" style="background: none">
        <section class="articoli" >
            <c:forEach items="${requestScope['prodotti']}" var="prodotto">
                <div class="articolo">
                    <img src="${prodotto.immagine}" , alt="copertina" decoding="async">
                    <div class="articolo-content">
                        <h2>${prodotto.nome}</h2>
                        <a href="/Rojina_Review_war/formModificaProdottoServlet?prodotto=${prodotto.id}" class="modifica">Modifica Prodotto</a>
                    </div>
                </div>
            </c:forEach>
        </section>
    </section>
</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</html>
