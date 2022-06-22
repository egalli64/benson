<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}" />

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
                <li class="nav-link"><a href="${ctx}/admin/index.jsp">Administration</a></li>
                <li class="nav-link"><a href="${ctx}/user/changePwd.jsp">${user.name}</a></li>
                <li class="nav-link"><a href="${ctx}/logout">Logout</a></li>
            </ul>
        </nav>
        <h1>The (other) Benson users</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Administrator</th>
                    <th scope="col">Commands</th>
                </tr>
            </thead>
            <c:forEach var="cur" items="${users}">
                <c:if test="${cur.id ne user.id}">
                    <tr>
                        <td>${cur.id}</td>
                        <td>${cur.name}</td>
                        <td><c:if test="${cur.administrator}">&check;</c:if></td>
                        <td><a href="${ctx}/admin/user/delete?id=${cur.id}"><button type="button"
                                    class="btn btn-danger" title="delete ${cur.name}">&#x02717;</button></a> <a
                            href="${ctx}/admin/user/edit?id=${cur.id}"><button type="button"
                                    class="btn btn-info" title="edit ${cur.name}">&#x02A6D;</button></a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>
