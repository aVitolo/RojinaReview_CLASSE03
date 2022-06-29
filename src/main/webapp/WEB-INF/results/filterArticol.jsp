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
        <button onclick="expandFilter('pDrop')" class="dropbtn">Piattaforma</button>
        <div id="pDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="pInput" onkeyup="filterFunction('pInput','pDrop')">
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <a href="">${piattaforma.nome}</a>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="expandFilter('tDrop')" class="dropbtn">Tipologia</button>
        <div id="tDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="tInput" onkeyup="filterFunction('tInput','tDrop')">
            <c:forEach items="${applicationScope['tipologie']}" var="tipologia">
                    <a href="">${tipologia.nome}</a>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="expandFilter('sDrop')" class="dropbtn">Ordina per</button>
        <div id="sDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="sInput" onkeyup="filterFunction('sInput','sDrop')">
            <a href="">Most Recent</a>
            <a href="">Least Recent</a>
        </div>
    </div>
</section>
</body>
</html>
