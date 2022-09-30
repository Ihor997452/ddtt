<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, userBean-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>New Password</title>

  <jsp:include page="fragments/includes.jsp"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/indexStyle.css" type="text/css">

</head>
<body>

<div class="container vh-100 d-flex justify-content-around align-items-center">

  <div class="register visible" id="register">
    <form action="${pageContext.request.contextPath}/guest/newPassword" method="post" class="d-flex flex-column"
          id="new-password-form">
      <div class="form-group">
        <label for="new-password">
          Password
        </label>
        <input type="password" class="form-control new-password-input"
               name="new-password" id="new-password"
               placeholder="" required>
      </div>

      <div class="form-group">
        <label for="new-password-confirm">
          Confirm Password
        </label>
        <input type="password" class="form-control new-password-input"
               name="new-password-confirm" id="new-password-confirm"
               placeholder="" required>
      </div>

      <button type="submit" class="btn btn-dark" style="margin-top: 20px;">
        Confirm
      </button>
    </form>
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/script/validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/script/newPasswordScript.js"></script>
<jsp:include page="fragments/messages.jsp"/>

</body>
</html>