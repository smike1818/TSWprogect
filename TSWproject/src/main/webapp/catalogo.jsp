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
        <jsp:include page="filter.jsp"></jsp:include>
    <div class="list-products">
        <jsp:include page="ListProducts.jsp"></jsp:include>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
</body>
</html>