<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<style>
			header{
				background-color: light blue;
			}
		</style>
	</head>
	<body>
	<!--Main Navigation-->
	<!--Main Navigation-->
	  <!-- Jumbotron -->
	    <div>
	      <div>
	        <!-- Right elements -->
	        <div>
	          <div align="right">
	          <nav>
		          <% if(session==null || session.getAttribute("un")==null){ %>
		               <a href="LoginPageUtente.jsp" target="_self"> Sign in </a><br>
		          <%}else{ %>
		               <a href="user" target="_self"> <%=(String) session.getAttribute("un") %> </a><br>
		          <%} %>
					<a href="carrello.jsp">  My Cart  </a><br>
	          </nav>
	          </div>
	        </div>
	        <!-- Right elements -->
	
	        <!-- Search elements (da attivare) -->
	          <div>
	            <div>
	              <input type="search" id="form1">
	              <input type="submit" value="search">
	          </div>
	         </div>
	         <!-- Search elements -->
	    </div>
	  </div>
	  <!-- Jumbotron -->
	
	  <!-- Heading -->
	  <div class="bg-primary mb-4">
	    <div>
	      <h3> 
	         Strumenti
	      </h3>
	      <!-- Breadcrumb -->
	      <nav>
	        <h6>
	        
	          <!-- quando clicco su Strumenti/Pezzi di Ricmbio, mi riporta alla pagina iniziale -->
	          <a href="catalogo.jsp" class="text-white-50">Strumenti</a>
	
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
	           if(((String)application.getAttribute("page")).equalsIgnoreCase("carrello.jsp")){        
	        %>
	         <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"><a href="carrello.jsp" class="text-white-50">Carrello</a></span>
	         
	         <%} %>
	         
	        <% if(((String)application.getAttribute("page")).equalsIgnoreCase("cardsPage.jsp")){%>
	         <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"><a href="carrello.jsp" class="text-white-50">Carrello</a></span>
	         <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"><a href="cardsPage.jsp" class="text-white-50">Conti</a></span>
	        
	        <%} %>
	        
	         <% if(((String)application.getAttribute("page")).equalsIgnoreCase("Indirizzo.jsp")){%>
	         <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"><a href="carrello.jsp" class="text-white-50">Carrello</a></span>
	         <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"><a href="cardsPage.jsp" class="text-white-50">Conti</a></span>
	         <span class="text-white-50 mx-2"> > </span>
	         <span class="text-white-50 mx-2"><a href="Indirizzo.jsp" class="text-white-50">Indirizzo</a></span>
	        
	        <%} %>
	       </h6>
	      </nav>
	      <!-- Breadcrumb -->
	    </div>
	  </div>
	  <!-- Heading -->
	</body>
</html>