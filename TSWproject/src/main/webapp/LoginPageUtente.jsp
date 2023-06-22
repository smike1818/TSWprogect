<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login Page</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<header>
		 <jsp:include page="header.jsp"></jsp:include>
	</header>
		
	<h1 class="access-h1">Area Login</h1>
	
	<span class="error-statement"></span>
	
	<div class="login-form">
		<form action="login" method="post" id="login-form">
			Please enter your username
			<input type="text" name="un" required/><br>
			Please enter your password
			<input type="password" name="pw" required/><br>
			<input type="submit" value="Accedi!">
		</form><br>
		<a href="Registrazione.jsp"> Non sei registrato? </a>
	 </div>
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
		
		<script src="js/JQuery.js" type="text/javascript"></script>
	    <script src="js/validation.js" type="text/javascript"></script>
	</body>
</html>