<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Home</title>
  <jsp:include page="fragments/includes.jsp"/>
</head>
<body>

<form action="${pageContext.request.contextPath}/logout" method="post">
  <button class="btn btn-dark" style="margin-top: 20px" type="submit">
    Log Out
  </button>
</form>

<c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
  <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#newTaskModal">
    New Task
  </button>
</c:if>
<a href="${pageContext.request.contextPath}/">Back</a>

<h1>Class Name: ${sessionScope.clazz.name}</h1>
<h3>Class Description: ${sessionScope.clazz.description}</h3>

<h1>Tasks</h1>

<c:forEach items="${sessionScope.tasks}" var="task">
  <div style="border: black 2px">
    <h2>${task.name}</h2>
    <h3>${task.description}</h3>

    <c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
      <form method="post" action="${pageContext.request.contextPath}/teacher/deleteTask">
        <button type="submit" class="btn btn-dark">
          Delete Task
        </button>
        <input type="hidden" name="task-id" id="task-id" value="${task.id}">
      </form>
    </c:if>
  </div>
</c:forEach>

<jsp:include page="fragments/pagination.jsp"/>
<jsp:include page="fragments/newTaskModal.jsp"/>
<jsp:include page="fragments/messages.jsp"/>

</body>
</html>
