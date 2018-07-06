<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>ToDoList</title>
    <style>
        <%@include file='/styles/w3.css' %>
    </style>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h2>ToDoList</h2>
</div>
<c:if test = "${not empty sessionScope.user}">
<div  class="w3-bar w3-blue-grey w3-opacity w3-right-align">
    <h5>Hello, <c:out value="${sessionScope.user.login}"/>
    <button class=" w3-btn w3-green w3-hover-light-blue w3-small"  onclick="location.href='/logout'">LogOut</button></h5>
</div>
</c:if>
<%@ include file="error.jsp"%>