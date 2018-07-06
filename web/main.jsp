<%@ page import="by.gsu.epamlab.util.Constants" %>
<%@ taglib uri="/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>ToDoList</title>
    <link rel="stylesheet" href="styles/w3.css">
    <style>
        <%@include file='/styles/w3.css' %>
    </style>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h2>ToDoList</h2>
</div>
<c:out value="${user.login}"/>
<%@ include file="/views/error.jsp"%>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='/login'">LogIn</button>
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/registration'">Register</button>
    </div>
</div>
</body>
</html>