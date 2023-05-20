<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
  application.removeAttribute("page");
  if(session.getAttribute("un")==null){
	    RequestDispatcher error = null;
        error = getServletContext().getRequestDispatcher("/LoginPageUtente.jsp");
        error.forward(request, response);
   }
   application.setAttribute("page", "Acquisto.jsp");
   if(application.getAttribute("by_address_page")==null){
	   RequestDispatcher error = null;
	   String header = "Client Error";
	   String details = "accesso alla pagina non autorizzato...";
	   response.setStatus(403);
	   error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
	   error.forward(request, response);
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Acquisto effettuato</title>
</head>
<body>
   <h3>Acquisto effettuato!</h3>
   <a href="catalogo.jsp">torna al catalogo per altri acquisti</a>
</body>
</html>