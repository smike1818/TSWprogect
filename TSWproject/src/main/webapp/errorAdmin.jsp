<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <% 
   //creazione di una pagina d'errore dinamica dove costruisco
   String header = request.getParameter("errorMessageHeader");
   String details = request.getParameter("errorMessageDetails");
   Integer status = response.getStatus();
   if(header == null && details == null){
	   //se header e details non vengono caricati tramite i parametri della richiesta
	   //li carico tramite gli attributi
	   header = "Client Error";
	   details = "non puoi accedere a questa pagina";
	   status = 403;
   }
   if(header != null && details != null){

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/error.css" rel="stylesheet" type="text/css">
</head>
<body class="error-page">
       <div>
           <img src="css/error.jpg" alt="no-available" class="image-error">
       </div>
       <div class="text-error">
          <h2>Error: <%=status %></h2>
          <h3><%=header %></h3><br>
          <h4><code><%=details %></code></h4>
       </div>
       
       <input class="error-comeback" type="button" value="Torna all'Homepage" 
				       onclick="window.location.href = 'CatalogoAdmin.jsp';">
</body>
</html>


 <%} %>