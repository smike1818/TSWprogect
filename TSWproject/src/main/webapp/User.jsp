<%@ page language="java" import="bean.*, java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    UserBean bean = null;
    List<?> telefoni = null;
    String username = (String) session.getAttribute("un");    //effettuo il controllo sull'utente, per verificare se l'utente                     
                                                              //sia registrato o meno
    if(username==null){ 
       rend = "LoginPageUtente.jsp";
    }else{
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
				
				<div class="main-phone-block">
				   <div class="phone-list-block">
				      <ul class="phone-list">
				      
				      <!-- mostro i numeri di telefono salvati al telefono -->
				      
				          <%   TelefonoBean phone = null;
                               if (telefoni != null && telefoni.size() != 0) {
		                          Iterator<?> it = telefoni.iterator();
		                          while (it.hasNext()) {
		                              phone = (TelefonoBean) it.next();

                          %>
                            <li id="<%=phone.getNumero()%>"><%=phone.getNumero()%>
                            <a href="phone?action=delete&phone=<%=phone.getNumero()%>">(elimina)</a>
                            
                            <!-- gestione numeri predefiniti -->
                            
                            <% if(!phone.getIsPrimary()){ %>                            
                               <a href="phone?action=prefer&phone=<%=phone.getNumero()%>">(seleziona come predefinito)</a>
                            <%}else{ %>
                               (PREDEFINITO)
                            <%} %>
                            </li>
                            
                          <%}}else{ %>
                            <li id="no-phones">nessun telefono inserito</li>
                          <%} %>
                          
				      </ul>
				   </div>
				   <div class="insert-phones">				  				       
				       
				       <form id="phone-form" action="phone" method="post">
				         <input type="hidden" name="action" value="add">
				         <input type="hidden" name="user" value="<%=bean.getCF()%>">
				         <label for="prefix">Prefisso:</label>
				         <select name="prefisso">
				         
				         <%for(int i=1; i<=998; i++){ %>                    <!-- lista di prefissi che gestisco con un for -->
				             <option id="prefix <%=i%>"
				             <%if(i==39){%>
				                selected
				             <%} %>
				             >+<%=i %></option>
				         <%} %>
				         
				         </select>
				         
				         <input type="number" name="numero" required placeholder="111-1111111">
				         <input type="submit" value="aggiungi">
				         <input type="reset" value="ripristina">
				       </form>
				   </div>
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