<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./css/filter.css">
</head>
<body>
<section class="filter">
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

    <div class="dropdown">
        <button onclick="expandFilter('sDrop')" class="dropbtn" id="sButton">Ordina per</button>
        <button onclick="resetFilter('Ordina per','sButton','sClose')" class="closebtn" id="sClose"><span>X</span>
        </button>
        <div id="sDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="sInput" onkeyup="filterFunction('sInput','sDrop')">
            <p onclick="setFilter('mostRecent','sButton','sDrop','sClose')" id="mostRecent">Most Recent</p>
            <p onclick="setFilter('leastRecent','sButton','sDrop','sClose')" id="leastRecent">Least Recent</p>
            <p onclick="setFilter('lowestPrice','sButton','sDrop','sClose')" id="lowestPrice">Lowest price</p>
            <p onclick="setFilter('highestPrice','sButton','sDrop','sClose')" id="highestPrice">Highest price</p>
        </div>
    </div>

    <button class="dropbtn" onclick=filter()> Filtra</button>

</section>
</body>
</html>
