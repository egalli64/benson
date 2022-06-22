<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>Change password</title>
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
                <li class="nav-link"><a href="${ctx}/index.jsp">Home</a></li>
            </ul>
        </nav>
        <h1>Change password</h1>
        <c:if test="${wrong ne null}">
            <p>${wrong}</p>
        </c:if>
        <form action="${ctx}/user/changePwd" method="post">
            <input value="${user.name}" readonly>
            <input type="password" name="current" placeholder="Current password" required autofocus>
            <input type="password" name="password" placeholder="New password" required>
            <button>Change</button>
        </form>
    </div>
</body>
</html>
