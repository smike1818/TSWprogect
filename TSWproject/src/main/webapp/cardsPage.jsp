<%@ page language="java" import="java.util.*, bean.ContoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String rend = null;
List<?> cards = null;
String username = (String) session.getAttribute("un");
application.removeAttribute("page");
if(username==null){ 
	 rend = "LoginPageUtente.jsp";
}else{
   cards = (List<?>) request.getAttribute("products");	
   if(cards == null) 
	   rend = "./cards";
}

application.setAttribute("page","cardsPage.jsp");
 if(rend!=null){
	 RequestDispatcher dispatcher = null;
	 dispatcher = getServletContext().getRequestDispatcher("/"+rend);
 	 dispatcher.forward(request, response); 
 }

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cards Page</title>
</head>
<body>
 
    <header>
       <jsp:include page="header.jsp"></jsp:include>
    </header>
 
  <h5> Intestatario: <%=username %> </h5>  <br>
  <h5>Scegli un metodo di pagamento</h5> <br>

<table border='1'>

    <tr>
      <th>numero di carta</th>
      <th>iban</th>
      <th>seleziona</th>
      <th>cancella</th>
    </tr>
    
	<%
		    ContoBean bean = null;
			if (cards != null && cards.size() != 0) {
				Iterator<?> it = cards.iterator();
				while (it.hasNext()) {
				    bean = (ContoBean) it.next();			    
				    if(bean.getIntestatario().getUsername().equalsIgnoreCase(username)){
					
		%>
		    <tr>
		       <td><%=bean.getNumCarta() %></td>
		       <td><%=bean.getIBAN() %></td>
		       <td><a href="Indirizzo.jsp?IBAN=<%=bean.getIBAN()%>">scegli</a></td>
		       <td><a href="cards?action=delete&IBAN=<%=bean.getIBAN()%>">elimina</a></td>
		    </tr>
		    
		<%}}}else{ %>
		
		  <tr>
		    <td colspan=4>non hai carte inserite</td>
		 </tr>
		 
		<%} %>
	</table><br>
	
	<h5>inserisci metodo di pagamento</h5><br>
	
	<div id="card-form">
	   <form action="cards" method="post">
	      <input type="hidden" name="action" value="add">
	      <label for="number-card">numero di carta</label>
	      <input type="text" name="number-card" required placeholder="enter number card"><br>
	      
	      <label for="IBAN">IBAN</label>
	      <input type="text" name="IBAN" required placeholder="enter IBAN"><br>
	      
	      <label for="cvv">CVV</label>
	      <input type="text" name="cvv" required placeholder="enter cvv"><br>
	      
	      <input type="submit" value="add">
	   </form>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>