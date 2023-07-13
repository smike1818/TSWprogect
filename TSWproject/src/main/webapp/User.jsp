<%@ page language="java" import="bean.*, java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    UserBean bean = null;
    List<?> telefoni = null;   
    String username = (String) session.getAttribute("un");	//effettuo il controllo sull'utente, per verificare se l'utente sia registrato o meno
    if(username==null){ 
       rend = "LoginPageUtente.jsp";
    }else{
    	application.setAttribute("page", "User.jsp");
        bean = (UserBean) session.getAttribute("user-details");
        if(bean==null)    rend = "/user";
        telefoni = (List<?>) request.getAttribute("user-phones");
        if(telefoni==null)  rend = "./phone";      
    }
    
    if(rend!=null){
    	response.sendRedirect(rend);
    	return;
    }else{
    
%>


<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="ISO-8859-1">
		<title>User Page</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="wrapper">
		<header>
			<jsp:include page="header.jsp"></jsp:include>
		</header>
		<header class="visited-pages-header">
	    	<jsp:include page="second-header.jsp"></jsp:include>
	    </header>
		
	    <span class="error-statement"></span>
	    <div class="content">
	    	<div class="main">
				<div class="table-container" id="user-table">
					<div class="table-row">
						<h2 class="table-cell">Dettagli utente</h2>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Name:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="name"><%=bean.getNome() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button1">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Surname:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="surname"><%=bean.getCognome() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button2">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Username:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="user"><%=bean.getUsername() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button3">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Email:</i></b> </span><br>
						<div class="table-cell">
							<p class="user-field" id="email"><%=bean.getEmail() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button4">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Codice Fiscale:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="cf"><%=bean.getCF() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button5">Edit</button>
						</div>
					</div>	
					<div class="table-row no-border">
						<div class="table-cell">
							<a class="redbutton-a" href="logout" target="_self">Logout</a><br>
						</div>
					</div>
				</div>
				<div class="table-container" id="phoneList">	
					<h1>Recapiti salvati</h1><br>	      
						<!-- mostro i numeri di telefono salvati al telefono -->      
				    	<%	TelefonoBean phone = null;
							if (telefoni != null && telefoni.size() != 0) {
					        	Iterator<?> it = telefoni.iterator();
					        	while (it.hasNext()) {
					            	phone = (TelefonoBean) it.next();
			             %>
			             <div class="table-row">
				    		<div class="table-cell" id="<%=phone.getNumero()%>">
				    			<b><i>Tel:</i></b><br><%=phone.getNumero()%>
				    		</div>
				    		<div class="table-cell">
				               	<a class="redbutton-a" href="phone?action=delete&phone=<%=phone.getNumero()%>">elimina</a>
							</div>
							<div class="table-cell">
				               	<!-- gestione numeri predefiniti -->         
				               	<% if(!phone.getIsPrimary()){ %>                            
				               	<a class="modern-a" href="phone?action=prefer&phone=<%=phone.getNumero()%>">seleziona come predefinito</a>
				           		<%}else{ %>
				               	<br><i>PREDEFINITO</i><br><br>
				               	<%} %>
					       	</div>
				        </div>
			            <%}}else{ %>
			          	<div class="table-row">
			           		<div class="table-cell" id="no-phones">nessun telefono inserito</div>
			           	</div>
			           	<%} %>
			           	<div class="table-row no-border">
							<div class="table-cell">
								<h1>Aggiungi Recapito</h1><br>			  				       		       
								<form id="phone-form" action="phone" method="post">
									<input type="hidden" name="action" value="add">
									<input type="hidden" name="user" value="<%=bean.getCF()%>">
									<select name="prefisso">         
									<%for(int i=1; i<=998; i++){ %>                    <!-- lista di prefissi che gestisco con un for -->
									    <option id="prefix <%=i%>"
									    <%if(i==39){%>
									    	selected
									    <%} %>
										>+<%=i %></option>
									<%} %>      
									</select>
									<input type="number" name="numero" required placeholder="111-1111111" class="add-Phone-number">
									<input type="submit" value="aggiungi" class="modern-button">
									<input type="reset" value="ripristina" class="redbutton-a">
								</form>
							</div>
						</div> 
					</div>	
				</div>
			</div>
		<footer>
			<jsp:include page="footer.jsp"></jsp:include>
		</footer>
	</div>
	<script src="js/JQuery.js" type="text/javascript"></script>
	<script src="js/validation.js" type="text/javascript"></script>
	<script src="js/userMods.js" type="text/javascript"></script>
    <script src="js/pagamenti.js" type="text/javascript"></script>      	
</body>
</html>

<% } %>