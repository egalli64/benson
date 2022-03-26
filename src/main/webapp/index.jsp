<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Benson</title>
</head>
<body>
    <h1>Welcome to the Benson Project!</h1>
    <c:choose>
        <c:when test="${user eq null}">
            <p>Please, login to proceed</p>
        </c:when>
        <c:otherwise>
            <p>Welcome, ${user.name}!</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
