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
<a href="${pageContext.request.contextPath}/">Back</a>

<c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#addStudentModal">
        Add Student
    </button>
</c:if>
<h1>Students</h1>

<c:forEach items="${sessionScope.students}" var="student">
    <div style="border: black 2px">
        <h2>Student Email: ${student.email}</h2>
        <h2>Student name: ${student.name}</h2>
        <h2>Student Surname: ${student.surname}</h2>

        <c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
            <form method="post" action="${pageContext.request.contextPath}/teacher/removeStudent">
                <button type="submit" class="btn btn-dark">
                    Remove Student
                </button>
                <input type="hidden" name="student-id" id="student-id" value="${student.id}">
            </form>
        </c:if>
    </div>
</c:forEach>

<jsp:include page="fragments/pagination.jsp"/>
<jsp:include page="fragments/addStudentModal.jsp"/>
<jsp:include page="fragments/messages.jsp"/>

</body>
</html>

