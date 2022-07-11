<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 09/07/2022
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="./css/adminPanel.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/adminPanel.js"></script>
</head>
<body>
<script>
    function search(){
        prompt("Cerca Ora", "")
    }
</script>
<div class="sidebar">
    <header>🟢Carlo Colizzi</header>
        <ul>
            <li>
                <a href="home">
                    <span class="icon"></span>
                    <span class="title" >🎮Dashboard</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">👨🏽Utenti</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">👩🏽‍💻Giornalisti</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">👨🏻‍💼Admins</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">📰News</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">📝Reviews</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">📦Shop</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title">⚙️Settings</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="icon"></span>
                    <span class="title" id="signOut">❌Sign Out</span>
                </a>
            </li>
        </ul>
</div>
<div class="navbar">
    <p>Gestione Admins</p>
</div>
<div class="manage">
    <table>
        <tr>
            <th>
                <p>ID</p>
            </th>
            <th>
                <p>Nome</p>
            </th>
            <th>
                <p>Cognome</p>
            </th>
            <th onclick="search()" style="cursor: pointer">🔍</th>
            <th style="cursor: pointer">✖️</th>
        </tr>
        <tr>
            <td>
                1
            </td>
            <td>
                Carlo
            </td>
            <td>
                Colizzi
            </td>
            <td>
            <button>✏️ </button>
            <button>🗑️ </button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
