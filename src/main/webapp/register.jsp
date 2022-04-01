<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Register to Benson</title>
</head>
<body>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="login.html">Login</a></li>
        </ul>
    </nav>
    <c:if test="${user ne null}">
        <p>User ${user.name} registration failed!</p>
    </c:if>
    <form action="register" method="post">
        <input name="name" placeholder="User name" value=${user.name}>
        <input type="password" name="password" placeholder="Password">
        <button>Register</button>
    </form>
</body>
</html>
