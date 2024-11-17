<%-- 
    Benson - A simple Jakarta EE Web Application
    
    https://github.com/egalli64/benson
 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="pageTitle" scope="request" value="Welcome to Benson" />

<!DOCTYPE html>
<html>
<jsp:include page="/include/head.jsp" />
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
                <div id="myCarousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <a href="https://tomcat.apache.org/" target="_blank"> <img src="${ctx}/img/tomcat.jpg"
                                class="d-block w-100" alt="Apache Tomcat">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://jakarta.ee/" target="_blank"> <img src="${ctx}/img/jakarta-ee.png"
                                class="d-block w-100" alt="Jakarta EE">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://www.h2database.com/" target="_blank"> <img
                                src="${ctx}/img/h2.png" class="d-block w-100" alt="H2">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://www.w3.org/" target="_blank"> <img src="${ctx}/img/w3c.png"
                                class="d-block w-100" alt="W3C">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://getbootstrap.com/" target="_blank"> <img src="${ctx}/img/bootstrap.svg"
                                class="d-block w-100" alt="Bootstrap">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="https://render.com/" target="_blank"> <img src="${ctx}/img/render.svg"
                                class="d-block w-100" alt="Render">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-4"></div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script type="text/javascript">
					const carousel = new bootstrap.Carousel(document
							.querySelector('#myCarousel'), {
						interval : 3000
					});
				</script>
</body>
</html>
