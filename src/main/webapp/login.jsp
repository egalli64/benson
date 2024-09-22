<%-- 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="pageTitle" scope="request" value="Login to Benson" />

<!DOCTYPE html>
<html>
<jsp:include page="/include/head.jsp" />
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
