<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="by.gsu.epamlab.util.Constants" %>
<html>
<head>
    <title>Update Task</title>
    <link rel="stylesheet" href="styles/w3.css" type="text/css"/>
    <style>
        <%@include file='/styles/w3.css' %>
    </style>
</head>

<body class="w3-light-grey">
<div  class="w3-bar w3-blue-grey w3-opacity w3-right-align">
    <h5>Hello, <c:out value="${sessionScope.user.login}"/></h5>
    <button class=" w3-btn w3-green w3-hover-light-blue w3-small"  onclick="location.href='/logout'">LogOut</button>

</div>
<div class=" w3-container w3-blue-grey w3-opacity w3-right-align">
    <h2>ToDoList</h2>
</div>
<div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
        <h2>Update task</h2>
    </div>

        <div id="addTodo">
            <form method="post" class="w3-container w3-light-grey w3-padding ">
                <input type="hidden" name="id" value="${requestScope.task.id}" id="id">
                <table class="w3-table "><tr>
                    <td>
                        <input type="text" name="description" required="required" class="w3-input w3-border w3-round-large "  value="${requestScope.task.description}">
                    </td>

                        <td>
                            <input type="date" name="date"  required="required" class="w3-input w3-border w3-round-large" value="${requestScope.task.date}"  >
                        </td>

                    <td>
                        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Update</button>
                    </td> </tr>  </table>
            </form>
        </div>

    </div>

</body>
</html>