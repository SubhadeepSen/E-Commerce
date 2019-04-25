<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="css/element.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<title>Shop Now-Login</title>
	</head>
	<body>
		<header>
			<div class="nav-title-width"><a href="login" class="nav-title">ShopNow</a></div>
		</header>
		<div class="container">
			<h2>Login</h2>
			<div class="margin-top-5per">
				<p class="error-message">${message}</p>
				<form action="login" method="post">
					<input type="text" name="username" id="username" placeholder="Username" autocomplete="false">
					<br>
					<br>
					<input type="password" name="password" id="password" placeholder="Password" autocomplete="false">
					<br>
					<br>
					<button type="button" name="register" id="register" onclick="window.location.href='register'">Register</button>
					<input type="submit" name="loginBtn" id="loginBtn" value="Login">
				</form>
			</div>
		</div>
		<script type="text/javascript" src="js/login.js"></script>
	</body>
</html>