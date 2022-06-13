<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Benson</title>
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
                <c:choose>
                    <c:when test="${user eq null}">
                        <li class="nav-link"><a href="login.jsp">Login</a></li>
                        <li class="nav-link"><a href="register.jsp">Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-link">Welcome, ${user.name}!</li>
                        <li class="nav-link"><a href="logout">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <h1>The Benson Project</h1>
    </div>
</body>
</html>
