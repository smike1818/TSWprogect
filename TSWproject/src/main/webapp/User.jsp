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
       <a href="logout" target="_self">Esci</a>
</body>
</html>