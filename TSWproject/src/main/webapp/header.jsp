<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
	<head>
	    <script src="js/JQuery.js" type="text/javascript"></script>
        <script src="js/not_autorized.js" type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
	<body>
	    
	     <div class="first-header">
		    <nav>
		       <ul class="voci-navbar">
		        <% if(session==null || session.getAttribute("un")==null){ %>
		             <li><a href="LoginPageUtente.jsp" target="_self" class="text-white"><i style="font-size:24px" class="fa">&#xf007;</i></a></li>
		        <%}else{ %>
		             <li><a href="UserMods.jsp" target="_self" class="text-white"><i style="font-size:24px" class="fa">&#xf2c0;</i></a><li>
		             <li><a href="" target="_self" class="text-white"><i style="font-size:24px" class="fa">&#xf004;</i></a><li>
		        <%} %>
		             <li><a href="carrello.jsp" class="text-white"><i style="font-size:24px" class="fa fa-shopping-cart"></i></a></li>
		             <li><a href="catalogo.jsp" class="text-white"><i style="font-size:24px" class="fa fa-home" aria-hidden="true" ></i></a></li>
		        </ul>
		    <!-- SEARCH ELEMENTS -->
		      <div class="search-products">
		        <div class="dropdown">
		         <input type="search" class="search-product-input">
		         <ul class="search-product-suggestions"></ul>
		        </div>
		         <button class="search-product-submit">Search</button>
		     </div>
		    </nav>   
	    <div class="second-header">
	      <!--<h3 id="tipo-articolo"> 
	         Articoli
	      </h3>-->
	        <h5 id="catena-links">
	        
	          <!-- quando clicco su Strumenti/Pezzi di Ricmbio, mi riporta alla pagina iniziale -->
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
	         
	         <%} %>
	         
	        <% if(pagee.equalsIgnoreCase("ConfirmPayment.jsp")){
	        	application.removeAttribute("loginPageShowed");
	        %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="" class="text-white">Conferma</a></span>
	        
	        <%} %>
	        
	        <% if(pagee.equalsIgnoreCase("cardsPage.jsp")){
	        	application.removeAttribute("loginPageShowed");
	        %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="" class="text-white">Metodi di pagamento</a></span>
	        
	        <%} %>
	        
	        <% if(pagee.equalsIgnoreCase("indirizzo.jsp")){
	        	application.removeAttribute("loginPageShowed");
	        %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="" class="text-white">Indirizzi</a></span>
	        
	        <%} %>
	        
	        <% if(pagee.equalsIgnoreCase("User.jsp")){
	        	application.removeAttribute("loginPageShowed");
	        %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="" class="text-white">Dettagli</a></span>
	        
	        <%} %>
	        
	         <% if(pagee.equalsIgnoreCase("UserMods.jsp")){
	        	 application.removeAttribute("loginPageShowed");
	         %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="" class="text-white">Utente</a></span>	        
	        <%} %>
	        
	        <% if(pagee.equalsIgnoreCase("Storico.jsp")){
	        	application.removeAttribute("loginPageShowed");
	        %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="UserMods.jsp" class="text-white">Utente</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Storico Ordini</a></span>
	        
	        <%} %>
	        
	        <% if(pagee.equalsIgnoreCase("categorie.jsp")){
	        	application.removeAttribute("loginPageShowed");
	        %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Categorie</a></span>	        
	        <%} %>
	        
	          <% if(pagee.equalsIgnoreCase("CatalogoFiltrato.jsp")){
	        	  application.removeAttribute("loginPageShowed");
	          %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="categorie.jsp" class="text-white">Categorie</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Prodotti</a></span>
	        
	        <%} %>	       
	        
	          <% if(pagee.equalsIgnoreCase("Registrazione.jsp")){
	        	  application.removeAttribute("loginPageShowed");
	          %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="LoginPageUtente.jsp" class="text-white">Login</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Registrazione</a></span>
	        <%} %>
	        
	        
	         <% if(pagee.equalsIgnoreCase("Acquisto.jsp")){
	        	 application.removeAttribute("loginPageShowed");
	         %>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Conferma</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Acquisto</a></span>
	        
	        <%}%>
	        
	        <% if(application.getAttribute("loginPageShowed")!=null){	%>
	          
	         <span class="text-white"> > </span>
	         <span class="text-white"><a class="text-white">Login</a></span>
	        
	        <%}}else{
	        	application.setAttribute("page","catalogo.jsp");	
	          }
	        %>
	       </h5>
	       </div>
	     </div>
	</body>
</html>