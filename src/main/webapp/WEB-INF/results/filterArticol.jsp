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
        <button onclick="expandFilter('pDrop')" class="dropbtn" id="pButton">Piattaforma</button>
        <button onclick="resetFilter('Piattaforma','pButton','pClose')" class="closebtn" id="pClose">X</button>
        <div id="pDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="pInput" onkeyup="filterFunction('pInput','pDrop')">
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <p onclick="setFilter('${piattaforma.nome}','pButton','pDrop','pClose')" id="${piattaforma.nome}">${piattaforma.nome}</p>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="expandFilter('tDrop')" class="dropbtn" id="tButton">Tipologia</button>
        <button onclick="resetFilter('Tipologia','tButton','tClose')" class="closebtn" id="tClose">X</button>
        <div id="tDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="tInput" onkeyup="filterFunction('tInput','tDrop')">
            <c:forEach items="${applicationScope['tipologie']}" var="tipologia">
                    <p onclick="setFilter('${tipologia.nome}','tButton','tDrop','tClose')" id="${tipologia.nome}">${tipologia.nome}</p>
            </c:forEach>
        </div>
    </div>
    <div class="dropdown">
        <button onclick="expandFilter('sDrop')" class="dropbtn" id="sButton">Ordina per</button>
        <button onclick="resetFilter('Ordina Per','sButton','sClose')" class="closebtn" id="sClose">X</button>
        <div id="sDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="sInput" onkeyup="filterFunction('sInput','sDrop')">
            <p onclick="setFilter('mostRecent', 'sButton','sDrop','sClose')" id="mostRecent">Most Recent</p>
            <p onclick="setFilter('leastRecent', 'sButton','sDrop','sClose')" id="leastRecent">Least Recent</p>
        </div>
    </div>

    <button class="dropbtn" onclick="filter()"> Filtra </button>

</section>
</body>
</html>
