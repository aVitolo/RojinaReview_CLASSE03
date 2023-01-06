<%@ page import="rojinaReview.model.beans.Manager" %><%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 09/07/2022
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Manager account;%>
<%account =((Manager)session.getAttribute("admin"));
    String nome = account.getNome();
    String cognome = account.getCognome();%>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="./css/adminPanel.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="./js/adminPanel.js"></script>
</head>
<body>
<div class="sidebar">
    <header>🟢<%=nome%> <%=cognome%> </header>
        <ul>
            <li>
                <a href="./AdminPanel?className=Utente">
                    <span class="title">👨🏽Utenti</span>
                </a>
            </li>
            <li>
                <a href="./AdminPanel?className=Giornalista">
                    <span class="title">👩🏽‍💻Giornalisti</span>
                </a>
            </li>
            <li>
                <a href="./AdminPanel?className=Amministratore">
                    <span class="title">👨🏻‍💼Admins</span>
                </a>
            </li>
            <li>
                <a href="./AdminPanel?className=Notizia">
                    <span class="title">📰News</span>
                </a>
            </li>
            <li>
                <a href="./AdminPanel?className=Recensione">
                    <span class="title">📝Reviews</span>
                </a>
            </li>
            <li>
                <a href="./AdminPanel?className=Prodotto">
                    <span class="title">📦Shop</span>
                </a>
            </li>
            <li>
                <a href="./home">
                    <span class="title" id="pink1" >🏠Home </span>
                </a>
            </li>
            <li>
                <a href="./logout">
                    <span class="icon"></span>
                    <span class="title" id="pink2" >❌Sign Out</span>
                </a>
            </li>
        </ul>
</div>
<div class="navbar">
    <% if(request.getAttribute("className") != null){%>
        <p><%=request.getAttribute("className")%> Panel </p>
    <%}else{%>
        <p>Admin Panel</p>
    <%}%>
</div>
<div class="manage">
   <% if(request.getAttribute("classView") != null){
        String path = (String)request.getAttribute("classView"); %>
    <jsp:include page="<%=path%>"/>
    <%}%>
</div>
<script>
    const className = "<%=request.getAttribute("className")%>";
</script>
</body>
</html>
