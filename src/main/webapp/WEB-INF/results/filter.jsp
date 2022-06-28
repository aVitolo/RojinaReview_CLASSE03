<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .filter{
            display: flex;
            justify-content: space-evenly;
            margin: 1%
        }

        .dropbtn {
            background-color: #04AA6D;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            border
        }

        .dropbtn:hover, .dropbtn:focus {
            background-color: #3e8e41;
        }

        #sInput, #tInput, #pInput {
            box-sizing: border-box;
            background-position: 14px 12px;
            background-repeat: no-repeat;
            font-size: 16px;
            padding: 14px 20px 12px 45px;
            border: none;
            border-bottom: 1px solid #ddd;
        }

        #sInput, #tInput, #pInput:focus {outline: 3px solid #ddd;}

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f6f6f6;
            min-width: 230px;
            overflow: auto;
            border: 1px solid #ddd;
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown a:hover {background-color: #ddd;}

        .show {display: block;}
    </style>
</head>
<body>
<section class="filter">
    <div class="dropdown">
        <button onclick="myFunction('pDrop')" class="dropbtn">Piattaforma</button>
        <div id="pDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="pInput" onkeyup="filterFunction('pInput','pDrop')">
            <c:forEach items="${applicationScope['piattaforme']}" var="piattaforma">
                <a href="">${piattaforma.nome}</a>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="myFunction('tDrop')" class="dropbtn">Tipologia</button>
        <div id="tDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="tInput" onkeyup="filterFunction('tInput','tDrop')">
            <c:forEach items="${applicationScope['tipologie']}" var="tipologia">
                    <a href="">${tipologia.nome}</a>
            </c:forEach>
        </div>
    </div>

    <div class="dropdown">
        <button onclick="myFunction('sDrop')" class="dropbtn">Ordina per</button>
        <div id="sDrop" class="dropdown-content">
            <input type="text" placeholder="Search.." id="sInput" onkeyup="filterFunction('sInput','sDrop')">
            <a href="">Most Recent</a>
            <a href="">Least Recent</a>
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
