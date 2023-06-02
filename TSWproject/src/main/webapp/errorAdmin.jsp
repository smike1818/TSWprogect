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
</head>
<body>
       <h2>Error: <%=status%></h2>
       <h3><%=header %></h3>
       <h4><code><%=details %></code></h4><br>
       <h3><a href="CatalogoAdmin.jsp">Ritorna alla pagina del catalogo</a></h3>
</body>
</html>

 <%} %>