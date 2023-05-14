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
	   header = (String) request.getAttribute("errorMessageHeader");
	   details = (String) request.getAttribute("errorMessageDetails");
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
       <h4><code><%=details %></code></h4>
       <% if(details.equalsIgnoreCase("admin already logged...")){ %>i
           <h3><a href="AdminControl.jsp">Ritorna alla pagina amministratore</a></h3>
       <%}} %>
</body>
</html>