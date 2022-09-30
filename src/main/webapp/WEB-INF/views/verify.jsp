<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Verification</title>

    <jsp:include page="fragments/includes.jsp"/>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center">
    <form action="${pageContext.request.contextPath}/guest/verify" method="post" class="d-flex flex-column">
        <div class="form-group">
            <label for="code">Code</label>
            <input type="number" name="code" id="code" class="form-control" placeholder="123.." required/>
        </div>

        <button type="submit" class="btn btn-dark" style="margin-top: 20px">
            Verify
        </button>
    </form>
</div>

<jsp:include page="fragments/messages.jsp"/>

</body>
</html>