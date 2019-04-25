<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<link rel="stylesheet" href="css/index.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Index</title>
	</head>
	<body>
		<header>
			<div class="nav-title-width"><a href="login" class="nav-title">ShopNow</a></div>
			<div class="nav-item-width">
				<c:if test="${username ne null}">
					<a href="logout" class="nav-item">Logout</a>
				</c:if>
			</div>
		</header>
		<div class="container">
			<div class="project-desc">
				<h3 class="center">Project Description</h3>
				<p>
					This is a very simple prototype of an E-Commerce application. Here one customer can register, if
					not registered and registered customer can login into the application for viewing the products, adding
					them into cart and can proceed further for placing the orders. The customer can also view the order history.
				</p>
				<p>
					Click here <a href="<c:out value="${baseUrl}"/>login" class="link">Login</a>, to go to the login page.
					For login into the application either you can register or you can use user Id as <b>john</b> and password as <b>12345</b>.
				</p>
				<p>
					If you are an admin user then come back this page after login for exploring the available <b>REST services</b> and <b>H2 Database</b>. 
					The default admin user Id is <b>sunny12345</b> and password is <b>12345</b>.
				</p>
				
				<c:if test="${adminUser eq true}">
					<p>
						Click here <a href="<c:out value="${baseUrl}"/>swagger-ui.html" class="link" target="_blank">REST Web Services</a>, 
						to explore the available services. <b>Basic c3VubnkxMjM0NToxMjM0NQ==</b>
					</p>
					<p>
						Click here <a href="<c:out value="${baseUrl}"/>db-console" class="link" target="_blank">Database Console</a>, 
						to explore the in memory H2 Database.
					</p>		
				</c:if>
				
				<h3 class="center">Technical Design and Description</h3>
				<p>This is a complete <b>Springboot Project</b>. In this project, I have used H2 in-memory database to create the tables with the help of Java Persistence API (JPA) and Java POJO
				classes(Entities). Once I'm done with creating the services to perform CRUD operations on entities, I created the REST controllers to
				expose these services as REST APIs with the help of springboot-web dependency. I have used springboot-security dependency to secure 
				these API endpoints with basic authentication and swagger dependency to document these APIs.<br>
				After developing the APIs, started working on the MVC layer with the help of springboot-web dependency to create the controllers to
				interact with the front-end. For front-end development, I have used HTML, Custom CSS and JQuery.
				</p>
				<img src="images/E-Commerce Architecture.jpg" alt="E-Commerce Architecture">
				<table>
					<tr>
						<td>Database</td>
						<td>H2 In Memory Database</td>
					</tr>
					<tr>
						<td>Database Access</td>
						<td>JPA</td>
					</tr>
					<tr>
						<td>Springboot</td>
						<td>2.x</td>
					</tr>
					<tr>
						<td>Java Version</td>
						<td>JDK 1.8</td>
					</tr>
					<tr>
						<td>REST API <br> Documentation</td>
						<td>Swagger 2.7</td>
					</tr>
					<tr>
						<td>Dependecies</td>
						<td>spring-boot-starter-web<br>
							spring-boot-devtools<br>
							spring-boot-starter-tomcat<br>
							spring-boot-starter-data-jpa<br>
							spring-boot-starter-security<br>
							tomcat-embed-jasper<br>
							javax.servlet:jstl<br>
							com.h2database:h2<br>
							springfox-swagger2<br>
							springfox-swagger-ui
						</td>
					</tr>
				</table>
				<br>
			</div>
		</div>
	</body>
</html>