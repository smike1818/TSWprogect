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
		<script src="js/JQuery.js" type="text/javascript"></script>
		<script src="js/userMods.js" type="text/javascript"></script>
      	<script src="js/pagamenti.js" type="text/javascript"></script>
	</head>
	<body>
		<header>
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		    <fieldset>
		       	<legend>User Details</legend>
		       	<label for="Name">Name: </label>
		       	<p id="name"><%=bean.getNome() %>
  				<button id="button1">Edit</button> <!-- Specify the button ID here -->
		       	</p>
		       
		       	<label for="Surname">Surname: </label>
		       	<p id="surname"><%=bean.getCognome() %>
		       	<button id="button2">Edit</button> <!-- Specify the button ID here -->
				</p>
		       
		       	<label for="Username">Username: </label>
		       	<p id="user"><%=bean.getUsername() %>
		       	<button id="button3">Edit</button> <!-- Specify the button ID here -->
		       	</p>
		       
		       	<label for="Email">Email: </label>
		       	<p id="email"><%=bean.getEmail() %>
		       	<button id="button4">Edit</button> <!-- Specify the button ID here -->
		       	</p>
		       
		       <label for="CF">Codice Fiscale: </label>
		       <p id="cf"><%=bean.getCF() %>
		       <button id="button5">Edit</button> <!-- Specify the button ID here -->
		       </p>
		       <input type=button onClick="location.href='cardsPage.jsp'"value='metodi di pagamento'><br><br>
		        <input type=button onClick="location.href='Indirizzo.jsp'"value='indirizzi salvati'><br>
		       <!-- <button id="generateBtn">show payment methods</button>
		       	<div id="content">
    				<div id="jspCodeContainer" style="display: none;"></div>
 				</div>
  				<button id="addButton" style="display: none;">Add Payment Method</button>-->
		    </fieldset>
		   	<a href="logout" target="_self">Logout</a><br>
		    <a href="Storico.jsp" target="_self">Accedi allo storico degli ordini</a>	  
		</body>
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
	</body>
</html>

<% } %>