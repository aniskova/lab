<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="by.gsu.epamlab.util.Constants" %>

<html>
<head>
  <title>ToDoList - LogIn</title>
  <link rel="stylesheet" href="../styles/w3.css" type="text/css"/>
  <style>
    <%@include file='/styles/w3.css' %>
  </style>
</head>

<body class="w3-light-grey">

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
  <h2>ToDoList</h2>
</div>
<%@ include file="error.jsp"%>
  <div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
      <h2>LogIn</h2>
    </div>
    <form method="post" class="w3-container w3-light-grey w3-padding ">
      <label >Login:</label>
        <input type="text" name="<%=Constants.LOGIN%>" class="w3-input w3-border w3-round-large " style="width: 30%" placeholder="input login"
               value="${param.login}"><br />
      <label>Password:</label>
        <input type="password" name="<%=Constants.PASSWORD%>" class="w3-input w3-border w3-round-large" style="width: 30%" placeholder="input pass"><br />

      <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">LogIn</button>

    </form>

  </div>
</div>
</body>
</html>
