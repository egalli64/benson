<%-- 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="pageTitle" scope="request" value="Register to Benson" />

<!DOCTYPE html>
<html>
<jsp:include page="/include/head.jsp" />
<body>
    <div class="container-fluid">
        <nav>
            <ul class="nav justify-content-end">
                <li class="nav-link"><a href="${ctx}/index.jsp">Home</a></li>
                <li class="nav-link"><a href="${ctx}/login.html">Login</a></li>
            </ul>
        </nav>
        <h1>Register new user</h1>
        <c:if test="${wrong ne null}">
            <p>User ${wrong} registration failed!</p>
        </c:if>
        <form action="${ctx}/register" method="post">
            <input name="name" placeholder="User name" value="${wrong}" autofocus required> <input
                type="password" name="password" placeholder="Password" required>
            <button>Register</button>
        </form>
    </div>
</body>
</html>
