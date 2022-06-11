<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login to Benson</title>
<link rel="icon" href="data:;base64,=">
</head>
<body>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="register.jsp">Register</a></li>
        </ul>
    </nav>
    <c:if test="${user eq null and wrong ne null}">
		<p>Wrong user name or password.</p>
    </c:if>
    <form action="login" method="post">
        <input name="name" placeholder="User name" value="${wrong}" required>
        <input type="password" name="password" placeholder="Password" required>
        <button>Login</button>
    </form>
</body>
</html>
