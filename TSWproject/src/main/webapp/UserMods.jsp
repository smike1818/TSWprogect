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
		    <div class="main">
				<div class="main-element">
				<h1>account</h1><br>
					<p>
						<input type="button" onClick="location.href='User.jsp'" value="dettagli"><br>
					</p>
				</div>
				<div class="main-element">
					<h1>pagamento</h1><br>
					<p>
						<input type="button" onClick="location.href='cardsPage.jsp'" value="metodi di pagamento"><br>
					</p>
				</div>
				<div class="main-element">
					<h1>indirizzi salvati</h1><br>
					<p>
						<input type="button" onClick="location.href='Indirizzo.jsp'" value="indirizzi salvati"><br>
					</p>
				</div>
			</div>
		   	
		   	<div class="main-body">
		    	<h1><a href="Storico.jsp" target="_self">Accedi allo storico degli ordini</a></h1>
		    </div>
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
		
		<script src="js/JQuery.js" type="text/javascript"></script>
		<script src="js/userMods.js" type="text/javascript"></script>
      	<script src="js/pagamenti.js" type="text/javascript"></script>
	</body>
</html>

<% } %>