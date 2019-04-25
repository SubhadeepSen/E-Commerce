<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<link rel="stylesheet" href="css/product.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Confirmation</title>
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
			<h2>Order Confirmation</h2>
			<div class="margin-top-5per">
				<p class="confirmation-text">Your order worth &#8377; <c:out value="${totalAmount}" /> with ID #<c:out value="${orderId}" /> has been placed successfully</p>
			</div>
		</div>
		<script type="text/javascript" src="js/login.js"></script>
	</body>
</html>