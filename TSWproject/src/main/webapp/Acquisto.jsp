<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
  String pagee = (String) application.getAttribute("page");
  if(session.getAttribute("un")==null){
	    RequestDispatcher error = null;
        error = application.getRequestDispatcher("/LoginPageUtente.jsp");
        error.forward(request, response);
   }else if(pagee==null){
	   RequestDispatcher error = null;
	   String header = "Client Error";
	   String details = "accesso alla pagina non autorizzato...";
	   response.setStatus(403);
	   error = application.getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
	   error.forward(request, response);
   }else if (!pagee.equalsIgnoreCase("Acquisto.jsp")){
	   RequestDispatcher error = null;
	   String header = "Client Error";
	   String details = "accesso alla pagina non autorizzato...";
	   response.setStatus(403);
	   error = application.getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
	   error.forward(request, response);
   }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Acquisto effettuato</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

 <header>
    <jsp:include page="header.jsp"></jsp:include>
 </header>

   <h3>Acquisto effettuato!</h3>
   <a href="catalogo.jsp">torna al catalogo per altri acquisti</a>
   
   <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>