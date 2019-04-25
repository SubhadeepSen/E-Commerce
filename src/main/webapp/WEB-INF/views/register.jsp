<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Register</title>
	</head>
	<body>
		<header>
			<div class="nav-title-width"><a href="login" class="nav-title">ShopNow</a></div>
		</header>
		<div class="container">
			<h2>Register</h2>
			<div class="margin-top-5per">
				<p class="error-message">${message}</p>
				<form action="register" method="post" id="register">
					<input type="text" name="firstname" id="firstname" placeholder="Firstname" autocomplete="false">
					<br>
					<br>
					<input type="text" name="lastname" id="lastname" placeholder="Lastname" autocomplete="false">
					<br>
					<br>
					<input type="text" name="emailAddress" id="emailAddress" placeholder="Email Address" autocomplete="false">
					<br>
					<br>
					<input type="text" name="dob" id="dob" placeholder="Date of Birth (yyyy-mm-dd)" autocomplete="false">
					<br>
					<br>
					<input type="text" name="username" id="username" placeholder="Username" autocomplete="false">
					<br>
					<br>
					<input type="password" name="password" id="password" placeholder="Password" autocomplete="false">
					<br>
					<br>
					<input type="text" name="phoneNumber" id="phoneNumber" placeholder="Phone Number" autocomplete="false">
					<br>
					<br>
					<input type="text" name="address" id="address" placeholder="Address" autocomplete="false">
					<br>
					<br>
					<button type="button" name="registerBtn" id="registerBtn">Register</button>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="js/register.js"></script>
	</body>
</html>