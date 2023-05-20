<%@ page language="java" import="java.util.*, bean.IndirizzoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
     application.removeAttribute("page");
     if(session.getAttribute("un")==null){
    	    RequestDispatcher error = null;
	        error = getServletContext().getRequestDispatcher("/LoginPageUtente.jsp");
	        error.forward(request, response);
     }
     application.setAttribute("page", "Indirizzo.jsp");
     String iban = request.getParameter("IBAN");
     if(iban==null){
    	 if(application.getAttribute("IBAN")==null){
    	    RequestDispatcher error = null;
	        String header = "Client Error";
	        String details = "non puoi accedere a questa pagina senza aver effettuato tutti i passaggi...";
	        response.setStatus(403);
	        error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
	        error.forward(request, response);
    	 } 
     }else{
          application.setAttribute("IBAN",iban);
     }
     IndirizzoBean ind = (IndirizzoBean) request.getAttribute("indirizzo");
     if(ind==null){
    	 response.sendRedirect("address");
    	 return;
     }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Address Page</title>
</head>
<body>

 <header>
     <jsp:include page="header.jsp"></jsp:include>
 </header>

<%if(ind.isEmpty()){%>
    <h5>Aggiungi indirizzo: </h5><br>
    <form action="address" method="post">
       <input type="hidden" name="action" value="new">
       <label for="via">Via:</label><br>
       <input type="text" name="via" required placeholder="enter via"><br>
       <label for="civico">Civico:</label><br>
       <input type="number" name="civico" required placeholder="enter civico"><br>
       <label for="citta">Città:</label><br>
       <input type="text" name="citta" required placeholder="enter città"><br>
       <label for="citta">CAP:</label><br>
       <input type="number" name="CAP" required placeholder="enter CAP"><br>       
       <input type=submit value="add">
    </form>
<%}else{ %>
  <h5>questo è il tuo indirizzo:</h5>
  <p>Via <%=ind.getVia() %> <%=ind.getCivico() %>,<%=ind.getCitta() %></p>
  <a href="address?action=change">clicca per cambiarlo</a><br>
  <a href="purchase">clicca per completare l'acquisto</a>
<%}%>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>