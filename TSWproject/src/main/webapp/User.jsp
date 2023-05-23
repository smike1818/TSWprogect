<%@ page language="java" import="bean.UserBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    UserBean bean = (UserBean) session.getAttribute("user-details");
    if(bean==null)    response.sendRedirect("catalogo.jsp");
    
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Page</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
       <a href="logout" target="_self">Esci</a>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>