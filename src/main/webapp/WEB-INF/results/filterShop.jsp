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
        <button onclick="expandFilter('cDrop')" class="dropbtn">Categoria</button>
        <div id="cDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="cInput" onkeyup="filterFunction('cInput','cDrop')">
            <c:forEach items="${applicationScope['categorie']}" var="caterogia">
                <a href="">${caterogia.nome}</a>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="expandFilter('sDrop')" class="dropbtn">Ordina per</button>
        <div id="sDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="sInput" onkeyup="filterFunction('sInput','sDrop')">
            <a href="">Most Recent</a>
            <a href="">Least Recent</a>
            <a href="">Lowest price</a>
            <a href="">Highest price</a>
        </div>
    </div>
</section>

<script>
    /* When the user clicks on the button,
    toggle between hiding and showing the dropdown content */
    function myFunction(drop) {
        document.getElementById(drop).classList.toggle("show");
    }

    function filterFunction(input, drop) {
        var inp, filter, a, i, div, txtValue;
        inp = document.getElementById(input);
        filter = inp.value.toUpperCase();
        div = document.getElementById(drop);
        a = div.getElementsByTagName("a");
        for (i = 0; i < a.length; i++) {
            txtValue = a[i].textContent || a[i].innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                a[i].style.display = "";
            } else {
                a[i].style.display = "none";
            }
        }
    }
</script>
</body>
</html>
