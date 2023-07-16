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
			<div class="menu-left">
				<a href="LoginPageUtente.jsp" target="_self" class="text-white">
					<i style="font-size:40px" class="fa">&#xf007;</i>
				</a>
			</div>
			<%}else{ %>
			<div class="menu-left">
				<a href="UserMods.jsp" target="_self" class="text-white">
					<i style="font-size:40px" class="fa">&#xf2c0;</i>
				</a>
			</div>
			<%} %>
			<div class="menu-left">	
				<a href="carrello.jsp" class="text-white">
					<i style="font-size:40px" class="fa fa-shopping-cart"></i>
				</a>
			</div>
			<div class="menu-left">
				<a href="catalogo.jsp" class="text-white">
					<i style="font-size:40px" class="fa fa-home" aria-hidden="true" ></i>
				</a>
			</div>
			<div class="menu">
				<div class="closeButton">
					<a class="icon-close">
						<b>X</b>
					</a>
						</div>
					<ul>   
						<% if(session==null || session.getAttribute("un")==null){ %>  
						<li><a href="LoginPageUtente.jsp">LOGIN</a></li>
						<%}else{ %>
						<li><a href="UserMods.jsp">USER</a></li>
						<%} %>
						<li><a href="carrello.jsp">Carrello</a></li>
						<li><a href="catalogo.jsp">HOME</a></li>
					</ul>
				</div>
				<div class="menuButton">
					<svg class="icon-menu" viewBox="0 0 20 20">
						<path d="M2 4h16M2 10h16M2 16h16"></path>
					</svg>
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
        	
        <script src="js/JQuery.js" type="text/javascript"></script>
        <script src="js/nav.js" type="text/javascript"></script>
	</body>
</html>