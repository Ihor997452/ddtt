<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
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

                <button style="margin: 20px" type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#newClassModal">
                    New Class
                </button>
            </c:if>

            <c:if test="${sessionScope.loggedUser.role.ordinal() == 0}">
                <button style="margin: 20px;" type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#addClassModal">
                    Enroll
                </button>
            </c:if>
        </div>

        <h1>Your Classes</h1>
        <form action="${pageContext.request.contextPath}/" method="get"
            class="d-flex d-flex justify-content-around align-items-end"
            style="width: 40rem">
            <div class="form-group" style="width: 100%; margin-right: 15px">
                  <label for="search">
                      Search
                  </label>
                  <input type="text" value="${param.search}" class="form-control" name="search" id="search">
            </div>

            <button type="submit" class="btn btn-dark">Search</button>
        </form>

        <c:forEach items="${sessionScope.classes}" var="clazz">
            <div class="card" style="width: 40rem; margin-bottom: 20px">
                <div class="card-body">
                    <h3 class="card-title">${clazz.name}</h3>
                    <h4 class="card-text text-muted" style="margin-bottom: 20px;">${clazz.description}</h4>
                    <a class="card-link" href="${pageContext.request.contextPath}/tasks?id=${clazz.id}">View Class Tasks</a>
                    <a class="card-link" href="${pageContext.request.contextPath}/students?id=${clazz.id}">View Class Students</a>

                    <c:if test="${sessionScope.loggedUser.role.ordinal() == 1}">
                        <form method="post" action="${pageContext.request.contextPath}/teacher/deleteClass">
                            <button type="submit" class="btn btn-dark" style="margin-top: 20px;">
                                Delete Class
                            </button>
                            <input type="hidden" name="class-id" id="class-id" value="${clazz.id}">
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.loggedUser.role.ordinal() == 0}">
                        <form method="post" action="${pageContext.request.contextPath}/student/dropClass">
                            <button type="submit" class="btn btn-dark" style="margin-top: 20px;">
                                Drop Class
                            </button>
                            <input type="hidden" name="class-id" id="class-id-d" value="${clazz.id}">
                        </form>
                    </c:if>
                </div>

            </div>
        </c:forEach>

        <jsp:include page="fragments/pagination.jsp"/>
    </div>

    <jsp:include page="fragments/newClassModal.jsp"/>
    <jsp:include page="fragments/addClassModal.jsp"/>
    <jsp:include page="fragments/messages.jsp"/>

</body>
</html>
