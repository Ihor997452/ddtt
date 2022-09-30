<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Students</title>
    <jsp:include page="fragments/includes.jsp"/>
</head>
<body>

  <div class="container d-flex flex-column justify-content-center align-items-center" style="width: 100%">
    <div class="d-flex flex-row justify-content-between" style="width: 100%;">
      <form action="${pageContext.request.contextPath}/logout" method="post">
        <button class="btn btn-dark" style="margin-top: 20px" type="submit">
          Log Out
        </button>
      </form>

      <c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
        <button style="margin: 20px" type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#addStudentModal">
            Add Student
        </button>
      </c:if>
        <a style="margin: 20px" class="btn btn-dark" href="${pageContext.request.contextPath}/">Back</a>
    </div>

      <h1>Class Name: ${sessionScope.clazz.name}</h1>
      <h3>Class Description: ${sessionScope.clazz.description}</h3>
      <br>
      <h1>Students</h1>

      <c:forEach items="${sessionScope.students}" var="student">
          <div class="card" style="width: 40rem; margin-bottom: 20px">
              <div class="card-body">
                  <h2 class="card-title">Email: ${student.email}</h2>
                  <h2 class="card-text text-muted">Name: ${student.name}. Surname: ${student.surname}</h2>

                  <c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
                      <form method="post" action="${pageContext.request.contextPath}/teacher/removeStudent">
                          <button style="margin-top: 20px;" type="submit" class="btn btn-dark">
                              Remove Student
                          </button>
                          <input type="hidden" name="student-id" id="student-id" value="${student.id}">
                      </form>
                  </c:if>
              </div>
          </div>
      </c:forEach>

      <jsp:include page="fragments/pagination.jsp"/>
  </div>

<jsp:include page="fragments/addStudentModal.jsp"/>
<jsp:include page="fragments/messages.jsp"/>

</body>
</html>

