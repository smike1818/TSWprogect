<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

if(session.getAttribute("admin")==null){ 
	 response.sendRedirect("loginPageAdmin.jsp");
}

%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/not_autorized.js" type="text/javascript"></script>
</head>
<body>
		
   <h2> Che strumento vuoi inserire? </h2>                  
		
    <label for="select">Scegli che articolo vuoi inserire</label><br>
    <select name="type" id="choise-type">
	   <option value="strumento" selected>Strumento</option>
	   <option value="pezzidiricambio">Altro</option>
    </select>
	
   	 <script src="js/JQuery.js"></script>
	 <script src="js/admin.js"></script>
</body>
</html>