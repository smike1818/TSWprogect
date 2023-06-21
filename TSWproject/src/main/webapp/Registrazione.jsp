<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Pagina Registrazione</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>

<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>

<h1>Area registrazione</h1>
    

    <form action="register" method="post" id="registration-form">
    
		<p>Inserisci i dati <br>
          Nome: <input type="text" name="name" required><br>
          Cognome:<input type="text" name="cognome" required> <br>
          Codice Fiscale:<input type="text" name="cf" required> <br>
          E-mail:<input type="email" name="email" required> <br>
          Username:<input type="text" name="us" required> <br>
          Password:<input type="password" name="pws" required> <br>
		  Conferma Password:<input type="password" name="pws1" required> <br>
		</p>
          <p>
            <input type="submit" value="Registrati!">
		 </p>
	</form>
	
	<footer>
	   <jsp:include page="footer.jsp"></jsp:include>
    </footer>
	
	<script src="js/JQuery.js" type="text/javascript"></script>
	<script src="js/validation.js" type="text/javascript"></script>
  </body>
</html>



