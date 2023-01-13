<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Area giornalista</title>
    <link rel="stylesheet" href="./static/css/master.css">
    <link rel="stylesheet" href="./static/css/recensioni.css">
</head>
<body>
<%@ include file="/WEB-INF/results/giornalistaPages/journalistArea.jsp" %>

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
        <a class="inserisci" href="/Rojina_Review_war/formInsertReview">Inserisci recensione</a>
    </section>
    <section class="recensioni" style="background: none">
        <section class="articoli">
            <c:forEach items="${requestScope['recensioniGiornalista']}" var="articolo">
                    <div class="articolo">
                        <img src="${articolo.immagine}" , alt="copertina" decoding="async">
                        <div class="articolo-content">
                            <h2>${articolo.nome}</h2>
                            <p>${fn:substring(articolo.testo, 0, 50)}</p>
                            <p class="parere">${articolo.votoGiornalista}</p>
                            <a class="modifica">Modifica Rcensione</a>
                        </div>
                    </div>
            </c:forEach>
        </section>
    </section>

</body>
</html>
