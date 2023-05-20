<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

if(session.getAttribute("admin")==null){ 
	 response.sendRedirect("/loginPageAdmin.jsp");
}

%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
   <h2> Che strumento vuoi inserire? </h2>
                  

	<form action="choise" method="post">
		
		<label for="select">Scegli che articolo vuoi inserire</label><br>
		<select name="type" id="type">
		   <option value="Strumento">Strumento</option>
		   <option value="Pezzi di Ricambio">Altro</option>
		</select>
		<input type="submit" value="invia"><input type="reset" value="Reset">
	</form><br>
	
</body>
</html>