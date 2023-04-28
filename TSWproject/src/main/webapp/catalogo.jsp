<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    ServletContext sc = this.getServletContext();
    String type = (String) sc.getAttribute("tipo");	
    sc.setAttribute("page","catalogo");
    String rend = request.getParameter("action");
    if(rend == null || rend.equalsIgnoreCase("redirect")){  
    	if(type==null)
    	    response.sendRedirect("./product?action=begin");
    	else
    		response.sendRedirect("./product?action=begin&tipo="+type);
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
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="filter.jsp"></jsp:include>
    <jsp:include page="ListProducts.jsp"></jsp:include>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>