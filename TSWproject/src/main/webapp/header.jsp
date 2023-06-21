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
		               <li><a href="LoginPageUtente.jsp" target="_self" class="text-white"> Sign in </a></li>
		          <%}else{ %>
		               <li><a href="user" target="_self" class="text-white"> <%=(String) session.getAttribute("un") %> </a><li>
		          <%} %>
					   <li><a href="carrello.jsp" class="text-white"><i class="fa fa-shopping-cart"></i></a></li>
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
	         </div>
	         
	    <div class="second-header">
	      <h3 id="tipo-articolo"> 
	         Articoli
	      </h3>
	        <h5 id="catena-links">
	        
	          <!-- quando clicco su Strumenti/Pezzi di Ricmbio, mi riporta alla pagina iniziale -->
	          <a href="catalogo.jsp" class="text-white">Articoli</a>
	
	        <% 
	           String id = request.getParameter("id"); 
	           if(id!=null){
	        	   ArticoloBean bean = (ArticoloBean) request.getAttribute("bean");
	        	   if(bean!=null){
	        %>
	                  <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"> <%=bean.getName()%> </span>
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
	         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
	         
	         <%} %>
	         
	        <% if(pagee.equalsIgnoreCase("ConfirmPayment.jsp")){%>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="ConfirmPayment.jsp" class="text-white">Conferma</a></span>
	        
	        <%} %>
	        
	         <% if(pagee.equalsIgnoreCase("Acquisto.jsp")){%>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="carrello.jsp" class="text-white">Carrello</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="ConfirmPayment.jsp" class="text-white">Conferma</a></span>
	         <span class="text-white"> > </span>
	         <span class="text-white"><a href="Acquisto.jsp" class="text-white">Acquisto</a></span>
	        
	        <%}}else{
	        	application.setAttribute("page","catalogo.jsp");	
	        }
	        	%>
	       </h5>
	       </div>
	       
	</body>
</html>