<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%	
     List<?> products = (List<?>) request.getAttribute("products");
     application.setAttribute("page","catalogo.jsp");
     if(products == null ){
	         response.sendRedirect("./catalogo");	
	         return;
	 }
     //<jsp:include page="footer.jsp"></jsp:include>
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Musical Store</title>
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
	                <% if(session==null || session.getAttribute("un")==null){ %>
			        	<h1>Benvenuto</h1>
			    	<%}else{%>
	            		<h1>Benvenuto <%= (String) session.getAttribute("un") %></h1>  
	            	<%} %>
		         	<div class="table-container" id="categorie">
			         	<div class="table-row">
			         		<h1 class="table-cell">Categorie</h1>
			         	</div>
			         	<div class="table-row no-border">
			         		<div class="table-cell" id="cat1">
			         			<a href="categories?tipo=true"><img src="img/pickups.png" width="50%"></a>
			         			<p>	
			         				accessori
			         			</p>
		         			</div>
		         		<div class="table-cell" id="cat2">
		         			<a href="categories?tipo=false"><img src="img/strato.png" width="15%"></a>
		         			<p>	
		         				strumenti
		         			</p>
		         		</div>
		         	</div>
		        	</div>
		        	<div class="catalogo">
			        	<h1>I nostri articoli in evidenza</h1>
						    <div class="products-list">
						        <%
						           //setto un limite di 5 elementi da mostrare nella pagina principale
						           request.setAttribute("limit",5);
						        %>
					        	<jsp:include page="ListProducts.jsp"></jsp:include>
					    	</div>
		        		</div>
		    	<h1 style="color: white">La redazione consiglia</h1>
		        	<iframe allow="autoplay *; encrypted-media *; fullscreen *; clipboard-write" frameborder="0" height="450" style="width:100%;overflow:hidden;border-radius:10px;" sandbox="allow-forms allow-popups allow-same-origin allow-scripts allow-storage-access-by-user-activation allow-top-navigation-by-user-activation" src="https://embed.music.apple.com/it/playlist/gaylist/pl.u-pMylDZmHW2zYYLa"></iframe>
		    	</div>
		    </div>
		    <jsp:include page="footer.jsp"></jsp:include>
		</div>
	    <script src="js/JQuery.js" type="text/javascript"></script>
	    <script src="js/userFunctions.js" type="text/javascript"></script>
		<script src="js/animations.js" type="text/javascript"></script>
	</body>
</html>