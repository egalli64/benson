<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>User Edit</title>
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
        <h1>Change User Data</h1>
        <p>The inserted fields will be updated (when possible)</p>
        <c:if test="${wrong ne null}">
            <p>${wrong}</p>
        </c:if>
        <form action="${ctx}/admin/user/update" method="post">
            <div>
                <label>Id <input name="id" value="${current.id}" readonly></label>
            </div>
            <div>
                <label>Name <input name="name" value="${current.name}" autofocus></label>
            </div>
            <div>
                <label>Password <input name="password" placeholder="New password"></label>
            </div>
            <div>
                <label>Administrator <input type="checkbox" name="administrator" <c:if test="${current.administrator}">checked</c:if>></label>
            </div>
            <div>
                <button>Change</button>
            </div>
        </form>
    </div>
</body>
</html>
