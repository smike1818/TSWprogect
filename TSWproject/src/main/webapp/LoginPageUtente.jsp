<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login Page</title>
	</head>
	<body>
		<form action="login" action="post">
			Please enter your username
			<input type="text" name="un" required/><br>
			Please enter your password
			<input type="password" name="pw" required/><br>
			<input type="submit" value="Accedi!">
		</form><br>
		<a href="Registrazione.jsp"> Non sei registrato? </a>
	</body>
</html>