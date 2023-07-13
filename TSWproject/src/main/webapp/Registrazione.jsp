<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<% application.setAttribute("page", "Registrazione.jsp");  %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Pagina Registrazione</title>
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
		<div class="content">
		<div class="main">
		  	<h1 class="access-h1">Area registrazione</h1>
		    
		    <span class="error-statement"></span>
			<div class="registrationForm">
			    <form action="register" method="post" id="registration-form">
			    
			          <br><br>Nome:<br> <input class="registration-input" type="text" name="name" required placeholder="Mario"><br>
			          Cognome:<br> <input class="registration-input" type="text" name="cognome" required placeholder="Rossi"> <br>
			          Codice Fiscale:<br> <input class="registration-input" type="text" name="cf" required placeholder="XXXXXX00X00X000X"> <br>
			          E-mail:<br> <input class="registration-input" type="email" name="email" required placeholder="example123@gmail.com"> <br>
			          Username:<br> <input class="registration-input" type="text" name="us" required placeholder="MarioRossi12"> <br>
			          Password:<br> <input class="registration-input" type="password" name="pws" required> <br>
					  Conferma Password:<br><input class="registration-input" type="password" name="pws1" required> <br>
					  
			          <p>
			            <input class="modern-button" type="submit" value="Registrati!">
					 </p>
				</form>
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



