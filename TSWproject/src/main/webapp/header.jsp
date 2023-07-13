<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<!DOCTYPE html>
<html lang="it">
	<head>
	<title></title>
	    <script src="js/JQuery.js" type="text/javascript"></script>
        <script src="js/not_autorized.js" type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
	<body>
	    	<div class="header-left">
            	<% if(session==null || session.getAttribute("un")==null){ %>
		             <div>
		             	<a href="LoginPageUtente.jsp" target="_self" class="text-white">
		             		<i style="font-size:40px" class="fa">&#xf007;</i>
		             	</a>
		             </div>
		        <%}else{ %>
		             <div>
		             	<a href="UserMods.jsp" target="_self" class="text-white">
		             		<i style="font-size:40px" class="fa">&#xf2c0;</i>
		             	</a>
		             </div>
		        <%} %>
		             <div>	
		            	<a href="carrello.jsp" class="text-white">
		            		<i style="font-size:40px" class="fa fa-shopping-cart"></i>
		            	</a>
		            </div>
		            <div>
		            	<a href="catalogo.jsp" class="text-white">
		            		<i style="font-size:40px" class="fa fa-home" aria-hidden="true" ></i>
		            	</a>
		            </div>
        	</div>
        	<div class="header-center">
            	<img src="MusicalStore.png" alt="Logo">
        	</div>
        	<div class="header-right">
		        	<div class="dropdown">
		         		<input type="search" class="search-product-input">
		         		<ul class="search-product-suggestions"></ul>
		        	</div>
		         	<button class="search-product-submit">Search</button>
        	</div>
	</body>
</html>