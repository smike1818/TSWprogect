<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- aggiunto esclusivamente per il funzionamento dell'header -->
<% application.setAttribute("loginPageShowed", true); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login Page</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="wrapper">
		<header>
			 <jsp:include page="header.jsp"></jsp:include>
		</header>
		<header class="visited-pages-header">
		    <jsp:include page="second-header.jsp"></jsp:include>
		</header>
		
		<span class="error-statement"></span>
		<div class="content">
			<div class="main">
					<h1>Area Login</h1><br>
					<div class="login-form">
						<form action="login" method="post" id="login-form">
							<br><br>Please enter your username
							<input type="text" name="un" class="add-Phone-number" required placeholder="MarioRossi123"/><br>
							<br><br>Please enter your password
							<input type="password" name="pw" class="add-Phone-number" required/><br><br>
							<input type="submit" value="Accedi!" class="modern-button">
						</form><br>
						<a href="Registrazione.jsp" class="modern-a"> Non sei registrato? </a>
				 	</div>
			</div>
		</div>
			<footer>
				<jsp:include page="footer.jsp"></jsp:include>
			</footer>
		</div>
		<script src="js/JQuery.js" type="text/javascript"></script>
	    <script src="js/validation.js" type="text/javascript"></script>
	</body>
</html>