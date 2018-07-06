<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/views/header.jsp"/>
<div class="w3-card-4">
    <div class="w3-container w3-center w3-green">
        <h2>List -
            <c:out value="${param.section}" default="today"/></h2>
    </div>
    <div  class="w3-container w3-center" >
        <div  class="w3-bar w3-padding-large w3-padding-24">
            <table class="w3-table "><tr>
                <td>
            <form method="get">
            <button class=" w3-btn w3-green w3-hover-light-blue w3-round-large" type="submit">Today</button>
                <input type="hidden" name="section" value="today">
            </form></td><td>
            <form method="get">
            <button class=" w3-btn w3-green w3-hover-light-blue w3-round-large">Tomorrow</button>
                <input type="hidden" name="section" value="tomorrow">
            </form></td><td>
            <form method="get">
            <button class=" w3-btn w3-green w3-hover-light-blue w3-round-large">Someday</button>
                <input type="hidden" name="section" value="someday">
            </form></td><td><form method="get">
            <button class=" w3-btn w3-green w3-hover-light-blue w3-round-large">Fixed</button>
            <input type="hidden" name="section" value="fixed">
             </form></td><td>
            <form method="get">
            <button class=" w3-btn w3-green w3-hover-light-blue w3-round-large">Recycle Bin</button>
                <input type="hidden" name="section" value="deleted">
            </form></td>
            </tr>  </table>

        </div>
    </div>
    <%@ include file="/views/error.jsp"%>
    <c:choose>
        <c:when test="${param.section eq 'fixed'||param.section eq 'deleted'}">
         <div id="addTodo" style="display: none;">
        </c:when>
            <c:otherwise>
            <div id="addTodo">
            </c:otherwise>
    </c:choose>
        <form method="post" class="w3-container w3-light-grey w3-padding "enctype="multipart/form-data"> <!---->
        <table class="w3-table "><tr>
    <td width="70%">
        <input type="text" name="description" required="required" class="w3-input w3-border w3-round-large "  placeholder="input description">
    </td>
            <c:if  test="${param.section eq 'someday'}">
                <td>
                <input type="date" name="date"  required="required" class="w3-input w3-border w3-round-large"  placeholder="input date" >
                </td>
                </c:if >
            <td width="10%"><input type="file" name="file">	</td>
            <td>
        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Add</button>
        </td> </tr>  </table>
        </form>
    </div>
            <form method="post" action="changeState" enctype="multipart/form-data">
    <table class="w3-table w3-center w3-bordered w3-striped w3-border w3-hoverable">
        <thead>
        <tr class="w3-green">
            <th scope="col">Fixed</th>
            <th scope="col">Task</th>
            <th scope="col">Date</th>
            <th scope="col">File</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="toDoItem" items="${requestScope.todoList}">
                <tr>
                    <td>
                    <input class="w3-check" type="checkbox"name="checkbox" value="${toDoItem.id}"></td>
                    <td>${toDoItem.description}</td>
                    <td>${toDoItem.date}</td>

            <td width="10%">
                <c:choose>
                    <c:when test="${not empty toDoItem.fileName}">
                        <form action="fileOperation">
                            <input type="hidden" name="taskID" value="${toDoItem.id}">
                            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" name="action" value="downloadFile"> ${toDoItem.fileName}</button>
                        </form>
                        <form method="post" action="fileOperation">
                            <input type="hidden" name="taskID" value="${toDoItem.id}">
                            <button type="submit" class="w3-btn w3-red w3-round-large w3-margin-bottom" name="action" value="deleteFile">Delete</button>
                        </form>


                    </c:when>
                    <c:otherwise>
                        <form method="post" enctype="multipart/form-data" action="fileOperation">
                            <input  type="file" name="file">
                            <input type="hidden" name="taskID" value="${toDoItem.id}">
                            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" name="action" value="uploadFile">Add File</button>
                        </form>
                    </c:otherwise>
                </c:choose>
               </td>
           </tr>
            </c:forEach>
        </tbody>
    </table>
            <div class="w3-bar w3-padding-large w3-padding-24">
                <c:choose>
                <c:when test="${param.section eq 'deleted'}">
                    <button class="w3-btn w3-hover-light-blue w3-round-large"  name="action" value="active">Restore</button>
                    <button class="w3-btn w3-hover-light-red w3-round-large"  name="action" value="deleteFromDB">Delete</button>
                    </c:when>
                    <c:when test="${param.section eq 'fixed'}">
                    <button class="w3-btn w3-hover-light-blue w3-round-large"  name="action" value="active">unFix</button>
                    <button class="w3-btn w3-hover-red w3-round-large"  name="action" value="deleted">Delete</button>
                </c:when>
                    <c:otherwise>
                        <button class="w3-btn w3-hover-green w3-round-large" name="action" value="fixed" >Fix</button>
                        <button class="w3-btn w3-hover-light-blue w3-round-large"  name="action" value="update" >Update</button>
                        <button class="w3-btn w3-hover-red w3-round-large"  name="action" value="deleted">Delete</button>
                    </c:otherwise>

                </c:choose>


            </div>
            </form>
</div>
    <jsp:include page="/views/footer.jsp"/>
