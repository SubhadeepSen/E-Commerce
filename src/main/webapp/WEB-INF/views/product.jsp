<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<link rel="stylesheet" href="css/product.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Products</title>
	</head>
	<body>
		<header>
			<div class="nav-title-width"><a href="login" class="nav-title">ShopNow</a></div>
			<div class="nav-item-width">
				<a href="orderHistory" class="nav-item">Order History</a>
				<a href="cartDetails" class="nav-item">Cart 
					<c:if test="${cartSize > 0}">
						<span class="cart">[${cartSize}]</span>
					</c:if>
				</a>
				<a href="logout" class="nav-item">Logout</a>
			</div>
		</header>
		<div class="container">
			<h2>Products</h2>
			<div class="left-align">
				<div class="margin-top-5per">
					<form method="POST" action="products">
						<c:forEach items="${products}" var="product">
							<a href="product/<c:out value="${product.id}"/>" class="prod-content">
								<div class="prod-desc"><c:out value="${product.category.name}" /></div>
								<hr>
								<div class="prod-desc"><c:out value="${product.name}" /></div>
								<div class="prod-desc">&#8377;<c:out value="${product.price}" /></div>
							</a>
	                      </c:forEach>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>