<%@ page language="java" import="java.util.*, bean.IndirizzoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%

    //-----link per modificare un indirizzo
    //------<a href="address?action=change">clicca per cambiarlo</a><br>
    //------nella servlet address c'è una funzione già creata di modific della password
    //------modificala se serve


     String rend = null;
     List<?> indlist = null;
     
     if(session.getAttribute("un")==null){
	        rend = "LoginPageUtente.jsp";
     }else{
      
    	   application.setAttribute("page","Indirizzo.jsp");    //salvo la pagina corrente
           indlist = (List<?>) request.getAttribute("indirizzo");
           if(indlist==null){
    	       rend = "./address";
           }
     }
     if(rend!=null){
    	 response.sendRedirect(rend);
    	 return;
     }
%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="ISO-8859-1">
		<title>Address Page</title>
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
			<div class="content">
				<div class="main">
					<%if(indlist==null || indlist.size()==0){%>
				   		<jsp:include page="AddIndirizzo.jsp"></jsp:include>
				   		</div>
				   		</div>
				   		<footer>
				   			<jsp:include page="footer.jsp"></jsp:include>
				   		</footer>
				   		</div>
					<%}else{ %>
					<h4>elenco indirizzi salvati</h4>
					<div class="table-container" id="indirizzi">
					 	<%
					   	IndirizzoBean ind = null;
					   	if (indlist != null && indlist.size() != 0) {
							Iterator<?> it = indlist.iterator();
							while (it.hasNext()) {
							    ind = (IndirizzoBean) it.next();
					
						%>
						<!-- quando invio i dati dell'indirizzo alla servlet -->
						<div class="table-row">
					           <span class="table-cell"><b><i>Indirizzo: </i></b><br><%=ind.getVia()%> <%=ind.getCivico() %>, <%=ind.getCitta() %> </span><br>
					           <span class="table-cell"><a href="address?action=delete&via=<%=ind.getVia()%>&civico=<%=ind.getCivico() %>&citta=<%=ind.getCitta() %>" class="redbutton-a">rimuovi</a></span>
					           <span class="table-cell">
					           <%if(!ind.getIsPrimary()){ %>
					               <a href="address?action=prefer&via=<%=ind.getVia()%>&civico=<%=ind.getCivico() %>&citta=<%=ind.getCitta() %>" class="modern-a">imposta come predefinito</a>
					           <%}else{ %>
					               <br><i>PREDEFINITO</i>
					           <%} %>
					           </span>
						</div>
						<%}}%>
					</div>
					<div>
						<button class="add-AddIndirizzo-link" >Aggiungi indirizzo</button>
					</div>
					<!-- javascript al click del link di sopra importerà dinamicamente AddIndirizzo.jsp e lo mette nel div di sotto -->
					<div class="show-AddIndirizzo"></div>
				</div>
			</div>
			<footer>
				<jsp:include page="footer.jsp"></jsp:include>
			</footer>
		</div>

    	<script src="js/JQuery.js" type="text/javascript"></script>
    	<script src="js/userFunctions.js" type="text/javascript"></script>
	</body>

<%} %>
</html>