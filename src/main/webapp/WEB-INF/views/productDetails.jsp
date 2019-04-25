<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../css/element.css">
		<link rel="stylesheet" href="../css/productDetails.css">
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<title>Shop Now-Product Details</title>
	</head>
	<body>
		<header>
			<div class="nav-title-width"><a href="../login" class="nav-title">ShopNow</a></div>
			<div class="nav-item-width">
				<a href="../orderHistory" class="nav-item">Order History</a>
				<a href="../cartDetails" class="nav-item">Cart 
					<c:if test="${cartSize > 0}">
						<span class="cart">[${cartSize}]</span>
					</c:if>
				</a>
				<a href="../logout" class="nav-item">Logout</a>
			</div>
		</header>
		<div class="container">
			<h2>Product Details</h2>
			<div class="margin-top-5per">
				<div class="prod-details">
					<div class="prod-desc">
					<span class="title">ID:</span>
					<span class="description"><c:out value="${product.id}" /></span>
					<input type="hidden" id="productId" value="<c:out value="${product.id}" />">
					</div>
					<div class="prod-desc">
						<span class="title">Name:</span>
						<span class="description"><c:out value="${product.name}" /></span>
					</div>
					<div class="prod-desc">
						<span class="title">Category:</span>
						<span class="description"><c:out value="${product.category.name}" /></span>
					</div>
					<div class="prod-desc">
						<span class="title">Price:</span>
						<span class="description">&#8377;<c:out value="${product.price}" /></span>
					</div>
					<div class="margin-top-30px">
						<p id="error" class="error-msg" hidden="true">
							Please enter a valid quantity
						</p>
						<input type="text" name="quantity" id="quantity" placeholder="Quantity" value="1" autocomplete="false">
						<br><br>
						<form action="../checkout" method="post" id="checkout">
							<input type="hidden" id="prodId" name="prodId" value="${product.id}:">
							<button type="button" name="addToCartBtn" id="addToCartBtn">Add to Cart</button>
							<button type="button" name="checkoutBtn" id="checkoutBtn">Checkout</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="../js/productDetails.js"></script>
	</body>
</html>