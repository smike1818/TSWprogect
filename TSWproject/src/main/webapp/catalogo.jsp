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
	    <header>
	       <jsp:include page="header.jsp"></jsp:include>
	    </header>
	    <div class="body">
            <div class="main-body">
                <% if(session==null || session.getAttribute("un")==null){ %>
		        	<h1>Benvenuto</h1>
		    	<%}else{%>
            		<h1>Benvenuto <%= (String) session.getAttribute("un") %></h1>  
            	<%} %>
            </div>
            <div class="categorie">
            	<h1>Categorie</h1>
	            <div class="main">
	                <div class="main-element">
	                	<a href=""><img src="img/strato.png" width="10%"></a>
	                    <h1>Strumenti</h1><br>
	                    <p>
	                        Sezione dedicata ai nostri<br>
	                        strumenti musicali<br>
	                    </p>
	                </div>
	                <div class="main-element">
	                	<a href=""><img src="img/pickups.png" width="43.7%"></a>
	                    <h1>Accessori</h1><br>
	                    <p>
	                        tutto ci&ograve; di cui hai bisogno<br>
	                        per i tuoi strumenti<br>
	                    </p>
	                </div>
	            </div>
	         </div>
        </div>
        <div class="catalogo">
        	<h1>I nostri articoli in evidenza</h1>
		    <div class="list-products">
		        <jsp:include page="ListProducts.jsp"></jsp:include>
		    </div>
        </div>
	    
	    <jsp:include page="footer.jsp"></jsp:include>
	    
	    <script src="js/JQuery.js" type="text/javascript"></script>
	    <script src="js/userFunctions.js" type="text/javascript"></script>
	</body>
</html>