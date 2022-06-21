<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" scope="page" value="${pageContext.request.contextPath}"/>

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
</head>
<body>
    <div class="container-fluid">
        <nav>
            <ul class="nav justify-content-end">
                <c:choose>
                    <c:when test="${user eq null}">
                        <li class="nav-link"><a href="${ctx}/login.jsp">Login</a></li>
                        <li class="nav-link"><a href="${ctx}/register.jsp">Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-link">Welcome, <a href="${ctx}/user/changePwd.jsp">${user.name}</a>!
                        </li>
                        <li class="nav-link"><a href="${ctx}/logout">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
        <h1>The Benson Project</h1>
        <c:if test="${user.administrator}">
            <h2>You are logged as administrator!</h2>
            <p>
                Go to the <a href="${ctx}/admin/index.jsp">administration</a> home page
            </p>
        </c:if>
        <c:if test="${message ne null}">
            <p>${message}</p>
        </c:if>
        <div class="row">
            <div class="col-4"></div>
            <div class="col-4">
                <div id="myCarousel" class="carousel slide" data-bs-ride="carousel" data-interval="1000">
                    <div class="carousel-inner">
                        <div class="carousel-item">
                            <a href="https://www.w3.org/" target="_blank"> <img src="${ctx}/img/w3c.png"
                                class="d-block w-100" alt="W3C">
                            </a>
                        </div>
                        <div class="carousel-item active">
                            <a href="https://tomcat.apache.org/" target="_blank"> <img
                                src="${ctx}/img/tomcat.jpg" class="d-block w-100" alt="Apache Tomcat">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://www.oracle.com/it/java/technologies/java-ee-glance.html"
                                target="_blank"> <img src="${ctx}/img/java-ee.png" class="d-block w-100"
                                alt="Java EE">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://www.postgresql.org/" target="_blank"> <img
                                src="${ctx}/img/postgresql.png" class="d-block w-100" alt="PostgreSQL">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://getbootstrap.com/" target="_blank"> <img
                                src="${ctx}/img/bootstrap.svg" class="d-block w-100" alt="Bootstrap">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://www.heroku.com/" target="_blank"> <img
                                src="${ctx}/img/heroku.jpg" class="d-block w-100" alt="Heroku">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-4"></div>
        </div>
    </div>
</body>
</html>
