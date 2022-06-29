<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        .filter{
            display: flex;
            justify-content: space-evenly;
            margin: 1%;
        }

        .dropbtn {
            margin-bottom: 1%;
            background-color: #e91e63;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            width: 222px;
            border-radius: 2px;
        }

        .dropbtn:hover{
            background-color: #6B354D;
        }

        #sInput, #tInput, #pInput {
            box-sizing: border-box;
            background-position: 14px 12px;
            background-repeat: no-repeat;
            font-size: 16px;
            padding: 14px 20px 12px 45px;
            border: none;
            background: #24262b;
            color: #ffffff;
            outline: none;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #24262b;
            width: 222px;
            max-height: 222px;
            min-height: fit-content;
            overflow-y: scroll;
            border: 1px solid #24262b;
            z-index: 1;
        }

        .dropdown-content::webkit-scrollbar{
            display: none;
        }

        .dropdown-content a {
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            color: #f3f3f3;
        }

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
