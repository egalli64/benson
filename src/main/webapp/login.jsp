<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login to Benson</title>
</head>
<body>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
    </nav>

    <form action="login" method="post">
        <input name="name" placeholder="User name">
        <input type="password" name="password" placeholder="Password">
        <button>Login</button>
    </form>
</body>
</html>
