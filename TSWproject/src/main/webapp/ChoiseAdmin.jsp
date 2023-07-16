<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

if(session.getAttribute("admin")==null){ 
	 response.sendRedirect("loginPageAdmin.jsp");
}

%>    

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
  
  <div class="table-container">
     <div class="table-row no-border" id="choise-table">
      <div class="table-cell">
       <h2> Che strumento vuoi inserire? </h2>                  
	  
      <label for="select">Scegli che articolo vuoi inserire</label><br><br>
      <select name="type" id="choise-type">
	     <option value="strumento" selected>Strumento</option>
	     <option value="pezzidiricambio">Altro</option>
      </select>
      </div>
     </div>
   </div>	
	
   	 <script src="js/JQuery.js"></script>
	 <script src="js/admin.js"></script>
</body>
</html>