<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="by.gsu.epamlab.util.Constants" %>
<html>
<head>
    <title>ToDoList - Registration</title>
    <link rel="stylesheet" href="../styles/w3.css" type="text/css"/>
    <style>
        <%@include file='/styles/w3.css' %>
    </style>
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h2>ToDoList</h2>
</div>
<div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
        <h2>Registration</h2>
    </div>
    <%@ include file="/views/error.jsp"%>
    <form method="post" class="w3-selection w3-light-grey w3-padding" >
        <label>Login:
            <input type="text" name="<%=Constants.LOGIN%>" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" placeholder="input login"><br />
        </label>
        <label>Password:
            <input type="password" name="<%=Constants.PASSWORD%>" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" placeholder="input pass"><br />
        </label>
        <label>Retype password:
            <input type="password" name="<%=Constants.RE_PASSWORD%>" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" placeholder="retype pass"><br />
        </label>
        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Register</button>
    </form>
</div>
</body>
</html>
