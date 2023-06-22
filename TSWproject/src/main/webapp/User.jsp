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
		
	    <span class="error-statement"></span>
	    	
		    <div class="main-utente">
				<div class="utente">
					<div>
					    <label for="Name">Name: </label>
					    <p class="user-field" id="name"><%=bean.getNome() %>
					        <button id="button1">Edit</button>
					    </p>
					</div>
					
					<div>
					    <label for="Surname">Surname: </label>
					    <p class="user-field" id="surname"><%=bean.getCognome() %>
					        <button id="button2">Edit</button>
					    </p>
					</div>
					
					<div>
					    <label for="Username">Username: </label>
					    <p class="user-field" id="user"><%=bean.getUsername() %>
					        <button id="button3">Edit</button>
					    </p>
					</div>
					
					<div>
					    <label for="Email">Email: </label>
					    <p class="user-field" id="email"><%=bean.getEmail() %>
					        <button id="button4">Edit</button>
					    </p>
					</div>
					
					<div>
					    <label for="CF">Codice Fiscale: </label>
					    <p class="user-field" id="cf"><%=bean.getCF() %>
					        <button id="button5">Edit</button>
					    </p>
					</div>
					<a href="logout" target="_self">Logout</a><br>
				</div>
			</div>
		   	
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
		
		<script src="js/JQuery.js" type="text/javascript"></script>
		<script src="js/userMods.js" type="text/javascript"></script>
      	<script src="js/pagamenti.js" type="text/javascript"></script>
      	<script src="js/validation.js" type="text/javascript"></script>
	</body>
</html>

<% } %>