<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%	
     List<?> products = (List<?>) request.getAttribute("products");
     application.setAttribute("page","catalogo.jsp");
     if(products == null ){
	         response.sendRedirect("./catalogo");	
	         return;
	 }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Musical Store</title>
</head>
<body>
    <header>
       <jsp:include page="header.jsp"></jsp:include>
    </header>
    <aside>
        <jsp:include page="filter.jsp"></jsp:include>
    </aside>
    <div class="list-products">
        <jsp:include page="ListProducts.jsp"></jsp:include>
    </div>
       <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>