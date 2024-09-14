<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>Login to Benson</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="data:;base64,=">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <nav>
            <ul class="nav justify-content-end">
                <li class="nav-link"><a href="${ctx}/index.jsp">Home</a></li>
                <li class="nav-link"><a href="${ctx}/register.jsp">Register</a></li>
            </ul>
        </nav>
        <h1>Login to Benson</h1>
        <c:if test="${user eq null and wrong ne null}">
            <p>Wrong user name or password.</p>
        </c:if>
        <form action="${ctx}/login" method="post">
            <input name="name" placeholder="User name" value="${wrong}" autofocus required> <input
                type="password" name="password" placeholder="Password" required>
            <button>Login</button>
        </form>
    </div>
</body>
</html>
