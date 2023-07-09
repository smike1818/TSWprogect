<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
	<head>
	    <script src="js/JQuery.js" type="text/javascript"></script>
        <script src="js/not_autorized.js" type="text/javascript"></script>
	</head>
	<body>
	    <div class="visited-pages-container">
		 	<p id="catena-links">    
				<a href="catalogo.jsp" class="text-white">Articoli</a>
		        <% 
		           String id = request.getParameter("id"); 
		           if(id!=null){
		        	   ArticoloBean bean = (ArticoloBean) request.getAttribute("bean");
		        	   if(bean!=null){
		        %>
		         <span class="text-white"> > </span>
		         <span class="text-white"> <%=bean.getName()%> </span>
		        <%
		        	   }
		           }
		        %>
		
		        <% 
		           String pagee = (String)application.getAttribute("page");
		           if(pagee!=null){
		              if(pagee.equalsIgnoreCase("carrello.jsp")){ 
		        %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="" class="text-white">Carrello</a></span>	         
		         	        
		        <%}else if(pagee.equalsIgnoreCase("ConfirmPayment.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="" class="text-white">Conferma</a></span>	        
		        
		        <%}else if(pagee.equalsIgnoreCase("cardsPage.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="" class="text-white">Metodi di pagamento</a></span>
		        
		        <%}else if(pagee.equalsIgnoreCase("indirizzo.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="" class="text-white">Indirizzi</a></span>
		        
		        <%}else if(pagee.equalsIgnoreCase("User.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="" class="text-white">Dettagli</a></span>
		        
		         <%}else if(pagee.equalsIgnoreCase("UserMods.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="" class="text-white">Utente</a></span>	        
		        
		        <%}else if(pagee.equalsIgnoreCase("Storico.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Storico Ordini</a></span>	        
		        
		        <%}else if(pagee.equalsIgnoreCase("categorie.jsp")){%>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Categorie</a></span>	        
		        
		          <% }else if(pagee.equalsIgnoreCase("CatalogoFiltrato.jsp")){%>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Categorie</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Prodotti</a></span>
		               	        
		          <%}else if(pagee.equalsIgnoreCase("Registrazione.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="LoginPageUtente.jsp" class="text-white">Login</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Registrazione</a></span>
		        
		        
		         <%}else if(pagee.equalsIgnoreCase("Acquisto.jsp")){ %>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Conferma</a></span>
		         <span class="text-white"> > </span>
		         <span class="text-white"><a class="text-white">Acquisto</a></span>
		        	        
		        <%}}else{
		        	application.setAttribute("page","catalogo.jsp");	
		          }
		        %>
		         
	       		</p>
	    	</div>
	</body>
</html>