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
        <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#newClassModal">
            New Class
        </button>
    </c:if>

    <c:if test="${sessionScope.loggedUser.role.ordinal() == 0}">
        <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#addClassModal">
            Enroll
        </button>
    </c:if>
    <h1>Your Classes</h1>

    <c:forEach items="${sessionScope.classes}" var="clazz">
        <div style="border: black 2px">
            <h2>Class name: ${clazz.name}</h2>
            <h3>Class Description: ${clazz.description}</h3>
            <a href="${pageContext.request.contextPath}/tasks?id=${clazz.id}">View Class Tasks</a>
            <a href="${pageContext.request.contextPath}/students?id=${clazz.id}">View Class Students</a>
            <p>Teacher: "${sessionScope.loggedUser.name} ${sessionScope.loggedUser.surname}"</p>

            <c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
                <form method="post" action="${pageContext.request.contextPath}/teacher/deleteClass">
                    <button type="submit" class="btn btn-dark">
                        Delete Class
                    </button>
                    <input type="hidden" name="class-id" id="class-id" value="${clazz.id}">
                </form>
            </c:if>


            <c:if test="${sessionScope.loggedUser.role.ordinal() == 0}">
                <form method="post" action="${pageContext.request.contextPath}/student/dropClass">
                    <button type="submit" class="btn btn-dark">
                        Drop Class
                    </button>
                    <input type="hidden" name="class-id" id="class-id-d" value="${clazz.id}">
                </form>
            </c:if>
        </div>
    </c:forEach>

    <jsp:include page="fragments/pagination.jsp"/>
    <jsp:include page="fragments/newClassModal.jsp"/>
    <jsp:include page="fragments/addClassModal.jsp"/>
    <jsp:include page="fragments/messages.jsp"/>

</body>
</html>
