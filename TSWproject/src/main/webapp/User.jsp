<%@ page language="java" import="bean.UserBean, bean.IndirizzoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    UserBean bean = null;
    String username = (String) session.getAttribute("un");    //effettuo il controllo sull'utente, per verificare se l'utente                     
                                                              //sia registrato o meno
    if(username==null){ 
       rend = "LoginPageUtente.jsp";
    }else{
        bean = (UserBean) session.getAttribute("user-details");
        if(bean==null)    rend = "/user";
    }
    
    if(rend!=null){
    	response.sendRedirect(rend);
    	return;
    }else{
    
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
    <fieldset>
       <legend>User Details</legend>
       <label for="Name">Name: </label>
       <p><%=bean.getNome() %></p>
       
       <label for="Surname">Surname: </label>
       <p><%=bean.getCognome() %></p>
       
       <label for="Username">Username: </label>
       <p><%=bean.getUsername() %></p>
       
       <label for="Email">Email: </label>
       <p><%=bean.getEmail() %></p>
       
       <label for="CF">Codice Fiscale: </label>
       <p><%=bean.getCF() %></p>  
       
    </fieldset>
       <a href="logout" target="_self">Logout</a><br>
       <a href="Storico.jsp" target="_self">Accedi allo storico degli ordini</a>
<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>

<% } %>