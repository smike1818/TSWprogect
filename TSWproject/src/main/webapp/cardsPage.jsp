<%@ page language="java" import="java.util.*, bean.ContoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String rend = null;
List<?> cards = null;

String username = (String) session.getAttribute("un");    //effettuo il controllo sull'utente, per verificare se l'utente                     
                                                          //sia registrato o meno
if(username==null){ 
	 application.setAttribute("page","cardsPage.jsp");
	 rend = "LoginPageUtente.jsp";
}else{
   application.setAttribute("page","cardsPage.jsp");      //controllo in che pagina sto e lo salvo nel contesto
   cards = (List<?>) request.getAttribute("cards");	
   if(cards == null) 
	   rend = "./cards";
}

 if(rend!=null){
	 response.sendRedirect(rend);
 }

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cards Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
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
		       <td><a href="cards?action=delete&IBAN=<%=bean.getIBAN()%>">elimina</a></td>
		    </tr>
		    
		<%}}}else{ %>
		
		  <tr>
		    <td colspan=3>non hai carte inserite</td>
		 </tr>
		 
		<%} %>
	</table><br>	
	<span><button class="add-InsertCards-link" >Aggiungi metodo di pagamento</button></span><br>
	   
	   <!-- javascript al click del link di sopra importerà dinamicamente InsertCards.jsp e lo mette nel div di sotto -->
	   
	   <div class="show-InsertCards"></div>
	   
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
</body>
</html>