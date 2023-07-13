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
   if(cards == null){
	   rend = "./cards";
	}

}if(rend!=null){
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
		<div class="wrapper">
    		<header>
       			<jsp:include page="header.jsp"></jsp:include>
    		</header>
    		<header class="visited-pages-header">
	    		<jsp:include page="second-header.jsp"></jsp:include>
	    	</header> 
	    	<div class="content">
	    		<div class="main">
		  			<h1>Scegli un metodo di pagamento</h1>
		
		  			<div class="table-container" id="pagamenti">  
		    			<% 	ContoBean bean = null;
		       				if (cards != null && cards.size() != 0) {
		           				Iterator<?> it = cards.iterator();
		           				while (it.hasNext()) {
		               				bean = (ContoBean) it.next();			    
		               				if (bean.getIntestatario().getUsername().equalsIgnoreCase(username)) {
		    			%>
		    			<div class="table-row">
					        <span class="table-cell"><b><i>N° : </i></b><br> <%=bean.getNumCarta() %></span><br>
					        <span class="table-cell"><b><i>IBAN :</i> </b><br> <%=bean.getIBAN() %></span><br>
		        			<% if (!bean.getIsPrimary()) { %>
					        <span class="table-cell"><a class="modern-a" href="cards?action=prefer&IBAN=<%=bean.getIBAN()%>">scegli</a></span>
					        <% } else { %>
					        <span class="table-cell"><br><i>PREDEFINITO</i></span><br>
					        <% } %>
					        <span class="table-cell"><a class="redbutton-a" href="cards?action=delete&IBAN=<%=bean.getIBAN()%>">elimina</a></span>
		    			</div>
		   	 		<% } } } else { %>
		    		<div class="table-row">
		        		<span class="table-cell">non hai carte inserite</span>
		    		</div>
		    		<% } %>
		    			<div class="table-row no-border">
			    			<span class="table-cell"><button class="add-InsertCards-link" >Aggiungi metodo di pagamento</button></span><br>
				   			<!-- javascript al click del link di sopra importerï¿½ dinamicamente InsertCards.jsp e lo mette nel div di sotto -->
				   		</div>	
				   		<div class="show-InsertCards"></div>
						
		    		</div>
		    	</div>
		    </div>
			<jsp:include page="footer.jsp"></jsp:include>
	
		</div>
		<script src="js/JQuery.js" type="text/javascript"></script>
	    <script src="js/userFunctions.js" type="text/javascript"></script>
	</body>
</html>