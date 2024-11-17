<%-- 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}" />
<c:set var="pageTitle" scope="request" value="Benson Admin" />

<!DOCTYPE html>
<html>
<jsp:include page="/include/head.jsp" />
<body>
    <div class="container-fluid">
        <nav>
            <ul class="nav justify-content-end">
                <li class="nav-link"><a href="${ctx}/index.jsp">Home</a></li>
                <li class="nav-link"><a href="${ctx}/user/changePwd.jsp">${user.name}</a></li>
                <li class="nav-link"><a href="${ctx}/logout">Logout</a></li>
            </ul>
        </nav>
        <h1>Benson Administration</h1>
        <c:if test="${message ne null}">
            <p>${message}</p>
        </c:if>
        <p>
            <a href="${ctx}/admin/account/all">List all accounts</a>
        <p>
    </div>
</body>
</html>
