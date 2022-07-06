<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shop - Rojina</title>
    <link rel="stylesheet" href="./css/notizie.css">
    <link rel="stylesheet" href="./css/navebar.css">
    <link rel="stylesheet" href="./css/master.css">
</head>
<body>
<%@ include file="/WEB-INF/results/navebar.jsp" %>

<section class="notizie">

    <h1>Our Merch</h1>

    <%@ include file="/WEB-INF/results/filterShop.jsp" %>

    <section class="articoli" id="wrap">
        <c:forEach items="${applicationScope['prodotti']}" var="prodotto">
            <div class = "articolo" id="${prodotto.id}">
                <a href="/Rojina_Review_war/getResource?type=prodotto&id=${prodotto.id}">
                <img src = "${prodotto.immagine}" alt = "copertina" decoding="async">
                <div class = "articolo-content">
                    <h2>${prodotto.nome}</h2>
                    <p>${prodotto.prezzo} $</p>
                    <p class="voto">${prodotto.mediaVoto}</p>
                </div>
                </a>
            </div>
        </c:forEach>
    </section>

</section>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/navebar.js"></script>
<script type="text/javascript" src="/Rojina_Review_war/js/filter.js"></script>
<script type="text/javascript">
    var triggered = false;
    function yHandler(){
        if(triggered == true)
            return null;
        triggered = true;
        var wrap = document.getElementById('wrap');
        var contentHeight = wrap.offsetHeight;
        var yOffset = window.pageYOffset;
        var y = yOffset + window.innerHeight;
        if(y >= contentHeight) {
            var a = document.getElementsByClassName('articolo');
            var l = a[a.length-1].getAttribute("id");
            var c = document.getElementById('cButton').innerHTML;
            var o = document.getElementById('sButton').innerHTML;
            var r = "no";
            $(document).ready(function () {
                $.getJSON({
                    url: "/Rojina_Review_war/shop",
                    type: "post",
                    data: {"lastID":l,"reset":r,"categoria" : c,"ordine" : o},
                    error: function (xhr, status, error) {
                        //alert("error");
                    },
                    success: function (data) {
                        var articoli = document.getElementsByClassName('articoli')[0];
                        var newA ="";
                        for (d in data) {
                            var articolo = data[d];
                            var prezzo = articolo.prezzo;
                            var id = articolo["id"];
                            var immagine= articolo.immagine;
                            var nome = articolo.nome;
                            var voto = articolo.mediaVoto;
                            var a =
                                "<div class = \"articolo\" id="+id+">" +
                                "<a href='/Rojina_Review_war/getResource?type=prodotto&id="+ id +"'>"+
                                "<img src = '" +immagine +"' alt =\"copertina\" decoding=\"async\">" +
                                "<div class = \"articolo-content\">" +
                                "<h2>" + nome + "</h2>" +
                                "<p>" + prezzo + "</p>" +
                                "<p class=\"voto\">"+voto+"</p>"+
                                "</div>" +
                                "</div>" +
                                "</a>";
                            newA += a;
                        }
                        articoli.innerHTML = articoli.innerHTML + newA;
                        triggered = false;
                    }
                });
            });
        }

    }

    window.onscroll = yHandler;

    function filter(){
        var a = document.getElementsByClassName('articolo');
        var l = a[a.length-1].getAttribute("id");
        var c = document.getElementById('cButton').innerHTML;
        var o = document.getElementById('sButton').innerHTML;
        var r = "no";
        $(document).ready(function () {
            $.getJSON({
                url: "/Rojina_Review_war/shop",
                type: "post",
                data: {"lastID":l,"reset":r,"categoria" : c,"ordine" : o},
                error: function (xhr, status, error) {
                    //alert("error");
                },
                success: function (data) {
                    var articoli = document.getElementsByClassName('articoli')[0];
                    var newA ="";
                    for (d in data) {
                        var articolo = data[d];
                        var prezzo = articolo.prezzo;
                        var id = articolo["id"];
                        var immagine= articolo.immagine;
                        var nome = articolo.nome;
                        var voto = articolo.mediaVoto;
                        var a =
                            "<div class = \"articolo\" id="+id+">" +
                            "<a href='/Rojina_Review_war/getResource?type=prodotto&id="+ id +"'>"+
                            "<img src = '" +immagine +"' alt =\"copertina\" decoding=\"async\">" +
                            "<div class = \"articolo-content\">" +
                            "<h2>" + nome + "</h2>" +
                            "<p>" + prezzo + "</p>" +
                            "<p class=\"voto\">"+voto+"</p>"+
                            "</div>" +
                            "</div>" +
                            "</a>";
                        newA += a;
                    }
                    articoli.innerHTML = articoli.innerHTML + newA;
                }
            });
        });
    }
</script>
</body>
</html>
