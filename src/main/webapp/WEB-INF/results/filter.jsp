<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./css/filter.css">
    <script>
        var table = "${articoli}";
    </script>
</head>
<body>
<section class="filter">

    <c:if test = "${articoli != 'shop'}">
    <div class="dropdown">
        <button onclick="expandFilter('pDrop')" class="dropbtn" id="pButton">Piattaforma</button>
        <button onclick="resetFilter('Piattaforma','pButton','pClose')" class="closebtn" id="pClose"><span>X</span>
        </button>
        <div id="pDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="pInput" onkeyup="filterFunction('pInput','pDrop')">
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <p onclick="setFilter('${piattaforma.nome}','pButton','pDrop','pClose')"
                   id="${piattaforma.nome}">${piattaforma.nome}</p>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="expandFilter('tDrop')" class="dropbtn" id="tButton">Tipologia</button>
        <button onclick="resetFilter('Tipologia','tButton','tClose')" class="closebtn" id="tClose"><span>X</span>
        </button>
        <div id="tDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="tInput" onkeyup="filterFunction('tInput','tDrop')">
            <c:forEach items="${applicationScope['tipologie']}" var="tipologia">
                <p onclick="setFilter('${tipologia.nome}','tButton','tDrop','tClose')"
                   id="${tipologia.nome}">${tipologia.nome}</p>
            </c:forEach>
        </div>
    </div>
    </c:if>

    <c:if test = "${articoli == 'shop'}">
    <div class="dropdown">
        <button onclick="expandFilter('cDrop')" class="dropbtn" id="cButton">Categoria</button>
        <button onclick="resetFilter('Categoria','cButton','cClose')" class="closebtn" id="cClose"><span>X</span>
        </button>
        <div id="cDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="cInput" onkeyup="filterFunction('cInput','cDrop')">
            <c:forEach items="${applicationScope['categorie']}" var="caterogia">
                <p onclick="setFilter('${caterogia.nome}','cButton','cDrop','cClose')"
                   id="${caterogia.nome}">${caterogia.nome}</p>
            </c:forEach>
        </div>
    </div>
    </c:if>

    <div class="dropdown">
        <button onclick="expandFilter('sDrop')" class="dropbtn" id="sButton">Ordina per</button>
        <button onclick="resetFilter('Ordina Per','sButton','sClose')" class="closebtn" id="sClose"><span>X</span>
        </button>
        <div id="sDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="sInput" onkeyup="filterFunction('sInput','sDrop')">
            <p onclick="setFilter('mostRecent', 'sButton','sDrop','sClose')" id="mostRecent">Most Recent</p>
            <p onclick="setFilter('leastRecent', 'sButton','sDrop','sClose')" id="leastRecent">Least Recent</p>
            <c:if test = "${articoli != 'news'}">
                <p onclick="setFilter('higherVote', 'sButton','sDrop','sClose')" id="higherVote">Highest Vote</p>
                <p onclick="setFilter('lowerVote', 'sButton','sDrop','sClose')" id="lowerVote">Lowest Vote</p>
            </c:if>
            <c:if test = "${articoli == 'shop'}">
                <p onclick="setFilter('lowestPrice','sButton','sDrop','sClose')" id="lowestPrice">Lowest Price</p>
                <p onclick="setFilter('highestPrice','sButton','sDrop','sClose')" id="highestPrice">Highest Price</p>
            </c:if>
        </div>
    </div>

    <button class="dropbtn" onclick="filterOrUpdate('yes','${articoli}')"> Filtra </button>

</section>
</body>
</html>
