<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Benson</title>
<link rel="icon" href="data:;base64,=">
</head>
<body>
    <nav>
        <ul>
            <c:choose>
                <c:when test="${user eq null}">
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                </c:when>
                <c:otherwise>
                    <li>Welcome, ${user.name}!</li>
                    <li><a href="logout">Logout</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
    <h1>The Benson Project</h1>
</body>
</html>
