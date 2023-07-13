<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="ISO-8859-1">
		<title>Login page Admin</title>
	</head>
	<link rel="stylesheet" href="css/adminstyle.css">
	<body>
		<div class="container">
			<div class="main-admin">
				<form action="loginadmin">
					<label for="un">Please enter your username</label>
					<input type="text" id="un" name="un" required>
					<label for="pw">Please enter your password</label>
					<input type="password" id="pw" name="pw" required>
					<input type="submit" value="Accedi">
				</form>
			</div>
		</div>
		<footer>
			<nav>
				<span><a href="UserList.jsp">elenco utenti</a></span>
				<span><a href="orderList.jsp">elenco ordini</a></span>
				<span><a href="CatalogoAdmin.jsp">HOME</a></span>
				<span><a href="CambiaIva.jsp">Cambia iva</a></span>
			</nav>
		</footer>
	</body>
</html>