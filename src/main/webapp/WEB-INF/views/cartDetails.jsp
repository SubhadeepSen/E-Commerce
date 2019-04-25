<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<link rel="stylesheet" href="css/product.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Cart</title>
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
			<h2>Your Cart</h2>
			<div class="left-align margin-top-5per">
				<c:if test="${cartSize eq 0}">
					<p class="no-cart-item">No Item is available in your cart</p>
				</c:if>
				<form action="checkout" method="post">
					<div>
						<c:forEach items="${cartProducts}" var="product">
							<div class="prod-content cart-content">
								<span class="remove-cart" id="<c:out value="${product.id}"/>">X</span>
								<a href="product/<c:out value="${product.id}"/>">
									<input type="hidden" name="prodId" value="${product.id}:${product.quantity}">
									<div class="prod-desc"><c:out value="${product.category.name}"/></div>
									<hr>
									<div class="prod-desc"><c:out value="${product.name}"/></div>
									<div class="prod-desc"><c:out value="${product.quantity}"/></div>
									<div class="prod-desc">&#8377;<c:out value="${product.price}"/></div>
								</a>	
							</div>
	                	</c:forEach>
					</div>
                	<c:if test="${cartProducts.size() ne 0}">
                		<div class="center">
                			<input type="submit" value="Checkout">
                		</div>
                	</c:if>	
				</form>
			</div>
		</div>
		<script type="text/javascript" src="js/cartDetails.js"></script>
	</body>
</html>