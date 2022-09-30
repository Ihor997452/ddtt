<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, userBean-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>

    <jsp:include page="fragments/includes.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/indexStyle.css" type="text/css">

</head>
<body>

<div class="container vh-100 d-flex justify-content-around align-items-center">

    <div class="content">
        <h1 class="heading">
            Lorem ipsum.
        </h1>

        <p>
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi aut eum neque officia, quas qui tenetur unde! Adipisci amet, facilis laborum minima natus sit? Aliquid fugiat magni odit sed temporibus.
        </p>

        <a href="#" class="btn btn-dark" style="width: 150px !important;" id="signIn">
            Sign In
        </a>
        <a href="#" class="btn btn-dark" style="width: 150px !important;" id="signUp">
            Sign Up
        </a>
    </div>

    <div class="login invisible" id="login">
        <form action="${pageContext.request.contextPath}/guest/login" method="post" class="d-flex flex-column" id="login-form">
            <input type="hidden" name="command" value="login">

            <div class="form-group">
                <label for="email-login">
                    Email
                </label>
                <input type="email" class="form-control login-input"
                       name="email-login" id="email-login" placeholder="example@mail.com" required>
            </div>

            <div class="form-group">
                <label for="password-login">
                    Password
                </label>
                <input type="password" class="form-control login-input"
                       name="password-login" id="password-login"
                       placeholder="qwerty" required>
            </div>

            <button type="submit" class="btn btn-dark" style="margin-top: 20px;">
                Sign In
            </button>
            <a href="#" class="link-secondary" id="forgot-password">
                Forgot Password
            </a>
        </form>
    </div>

    <div class="register invisible" id="register">
        <form action="${pageContext.request.contextPath}/guest/register" method="post" class="d-flex flex-column" id="register-form">
            <div class="form-group">
                <label for="email-register">
                    Email
                </label>
                <input type="email" class="form-control register-input"
                       name="email-register" id="email-register" placeholder="example@mail.com" required>
            </div>

            <div class="form-group">
                <label for="role-register">Select Role</label>
                <select class="form-control register-input" id="role-register" name="role-register" required>
                    <option value="0">Student</option>
                    <option value="1">Teacher</option>
                </select>
            </div>

            <div class="form-group">
                <label for="name-register">
                    Name
                </label>
                <input type="text" class="form-control register-input"
                       name="name-register" id="name-register"
                       placeholder="" required>
            </div>

            <div class="form-group">
                <label for="surname-register">
                    Surname
                </label>
                <input type="text" class="form-control register-input"
                       name="surname-register" id="surname-register"
                       placeholder="" required>
            </div>

            <div class="form-group">
                <label for="password-register">
                    Password
                </label>
                <input type="password" class="form-control register-input"
                       name="password-register" id="password-register"
                       placeholder="" required>
            </div>

            <div class="form-group">
                <label for="password-register-confirm">
                    Confirm Password
                </label>
                <input type="password" class="form-control register-input"
                       name="password-register-confirm" id="password-register-confirm"
                       placeholder="" required>
            </div>

            <button type="submit" class="btn btn-dark" style="margin-top: 20px;">
                Sign Up
            </button>
        </form>
    </div>

    <div class="restore invisible" id="restore">
        <form action="${pageContext.request.contextPath}/guest/restore" method="post" class="d-flex flex-column" id="restore-form">
            <input type="hidden" name="command" value="restore">

            <h3>
                Restore Password
            </h3>

            <div class="form-group">
                <label for="email-restore">
                    Email
                </label>
                <input type="email" class="form-control" name="email-restore" id="email-restore" placeholder="example@mail.com" required>
            </div>

            <button type="submit" class="btn btn-dark" style="margin-top: 20px;">
                Restore Password
            </button>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/script/validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/script/indexScript.js"></script>
<jsp:include page="fragments/messages.jsp"/>

</body>
</html>