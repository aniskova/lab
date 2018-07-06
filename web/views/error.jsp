<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:choose>
    <c:when test="${not empty requestScope.error}">
        <div class="w3-panel w3-red w3-display-container w3-card-4 w3-round">
            <h5>Error: <c:out value="${requestScope.error}"/></h5>
        </div>
     </c:when>
</c:choose>
