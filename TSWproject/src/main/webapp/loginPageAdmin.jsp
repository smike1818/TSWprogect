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
		<div class="wrapper">
			<header>
			</header>
			<div class="content">
				<div class="main">
					<div class="login-form">
						<form action="loginadmin" id="login-form">
							<label for="un">Please enter your username</label><br>
							<input type="text" id="un" name="un" required class="add-Phone-number"><br>
							<label for="pw">Please enter your password</label><br>
							<input type="password" id="pw" name="pw" required class="add-Phone-number"><br>
							<input type="submit" value="Accedi"> 
						</form>
					</div>
				</div>
			</div>
			<footer>
				<jsp:include page="footerAdmin.jsp"></jsp:include>	
			</footer>
		</div>
	</body>
</html>