<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<link rel="stylesheet" href="css/orderHistory.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-History</title>
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
			<h2>Order History</h2>
			<div class="margin-top-5per">
	            <table>
	                <thead>
	                	<th>ID</th>
	                    <th>PRODUCTS</th>
	                    <th>DATE</th>
	                    <th>TOTAL PRICE</th>
	                    <th>DISCOUNT</th>
	                    <th>COUPON</th>
	                </thead>
	                <tbody>
		                <c:forEach items="${oldOrders}" var="order">
							<tr>
								<td><c:out value="${order.id}" /></td>
								<td>
									<c:forEach items="${order.orderedProducts}" var="product">
										<label>
											[<c:out value="${product.name}" />,
											&#8377;<c:out value="${product.price}" />,
											<c:out value="${product.category.name}" />]
										</label>
										<br>
									</c:forEach>
								</td>
								<td><c:out value="${order.date}" /></td>
								<td><c:out value="${order.totalPrice}" /></td>
								<td><c:out value="${order.discount}" /></td>
								<td><c:out value="${order.coupon.code}" /></td>
							</tr>
		                </c:forEach>
                   </tbody>
				</table>
			</div>
		</div>
	</body>
</html>