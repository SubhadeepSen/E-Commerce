<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<link rel="stylesheet" href="css/product.css">
		<link rel="stylesheet" href="css/reviewProduct.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Review</title>
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
			<h2>Review Your Order</h2>
			<div class="left-align margin-top-5per">
				<form action="placeOrder" method="post">
					<c:forEach items="${products}" var="product">
						<a href="product/<c:out value="${product.id}"/>" class="prod-content prod-content-height">
							<div class="prod-desc"><c:out value="${product.category.name}" /></div>
							<input type="hidden" name="prodId" value="${product.id}:${product.quantity}">
							<hr>
							<div class="prod-desc"><c:out value="${product.name}" /></div>
							<div class="prod-desc">&#8377;<c:out value="${product.price}" /></div>
							<div class="prod-desc quantity"><c:out value="${product.quantity}" /></div>
						</a>
	              	</c:forEach>
	              	<div class="float-right">
	              		<div class="center"><input type="text" id="couponCode" name="couponCode" placeholder="Coupon Code"></div>
	              		<p hidden="true" class="coupon" id="coupon"></p>
	              		<p hidden="true" class="coupon" id="coupon-msg"></p>
	              		<div class="prod-desc margin-top-15px" id="totalAmount">Total: &#8377;<c:out value="${totalPrice}" /></div>
				        <div class="center"><input type="submit" value="Place Order"></div>
	              	</div>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="js/coupon.js"></script>
	</body>
</html>