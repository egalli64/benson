<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login to Benson</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="data:;base64,=">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
    crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <nav>
            <ul class="nav justify-content-end">
                <li class="nav-link"><a href="index.jsp">Home</a></li>
                <li class="nav-link"><a href="register.jsp">Register</a></li>
            </ul>
        </nav>
        <c:if test="${user eq null and wrong ne null}">
            <p>Wrong user name or password.</p>
        </c:if>
        <form action="/benson/login" method="post">
            <input name="name" placeholder="User name" value="${wrong}" required>
            <input type="password" name="password" placeholder="Password" required>
            <button>Login</button>
        </form>
    </div>
</body>
</html>
