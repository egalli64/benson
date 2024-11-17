<%-- 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}" />
<c:set var="pageTitle" scope="request" value="Benson Accounts" />

<!DOCTYPE html>
<html>
<jsp:include page="/include/head.jsp" />
<body>
    <div class="container-fluid">
        <nav>
            <ul class="nav justify-content-end">
                <li class="nav-link"><a href="${ctx}/admin/index.jsp">Administration</a></li>
                <li class="nav-link"><a href="${ctx}/user/changePwd.jsp">${user.name}</a></li>
                <li class="nav-link"><a href="${ctx}/logout">Logout</a></li>
            </ul>
        </nav>
        <h1>The (other) Benson accounts</h1>
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
                            href="${ctx}/admin/user/edit?id=${cur.id}"><button type="button" class="btn btn-info"
                                    title="edit ${cur.name}">&#x02A6D;</button></a></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</body>
</html>
