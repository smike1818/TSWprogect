<%@ page language="java" import="bean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

String rend = null;
String temp = request.getParameter("id");
Integer id=-1;
ArticoloBean bean = null;

if(session.getAttribute("admin")==null){ 
    rend = "loginPageAdmin.jsp";
}else{
	
	if(temp==null){
	RequestDispatcher error = null;
    String header = "Client Error";
    String details = "id nullo...";
       request.setAttribute("errorMessageHeader", header);
       request.setAttribute("errorMessageDetails", details);
       response.setStatus(500);
       error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
       error.forward(request, response);
}else{
	try {
	    id = Integer.parseInt(temp); 	    
	} catch (NumberFormatException e) {
		 RequestDispatcher error = null;
         String header = "Client Error";
         String details = "id non valido...";
	        request.setAttribute("errorMessageHeader", header);
	        request.setAttribute("errorMessageDetails", details);
	        response.setStatus(500);
	        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
	        error.forward(request, response);
	}
}
	
bean = (ArticoloBean) request.getAttribute("bean");
if(bean==null){
	rend="./changeprod?id="+id;
}
}


if(rend!=null){
 response.sendRedirect(rend);
 return;
}else{

%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Change Product</title>
<link href="css/adminstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div class="wrapper">
         <header>
			<jsp:include page="headerAdmin.jsp"></jsp:include>
		</header>
	<div class="content">
     <div class="main">
				<div class="table-container" id="user-table">
					<div class="table-row">
						<h2 class="table-cell">Dettagli Prodotto</h2>
					</div>
					
					<span class="error-statement"></span>
					<input id="id-product" type="hidden" value="<%=bean.getID()%>">
					
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
						<span class="table-cell"><b><i>Prezzo:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="prezzo"><%=bean.getPrezzoBase() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button2">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Quantita':</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="quantita"><%=bean.getQuantita() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button3">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Descrizione:</i></b> </span><br>
						<div class="table-cell">
							<p class="user-field" id="descrizione"><%=bean.getDescrizione() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button4">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Marca:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="marca"><%=bean.getMarca() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button5">Edit</button>
						</div>
					</div>
					<div class="table-row">
						<span class="table-cell"><b><i>Tipologia:</i></b></span><br>
						<div class="table-cell">
						    <p class="user-field" id="tipologia"><%=bean.getTipologia() %></p>
						</div>
						<div class="table-cell">
							<button class="modern-button" id="button6">Edit</button>
						</div>
					</div>		
					</div>
				</div>
			  </div>
			  <footer>
				<jsp:include page="footerAdmin.jsp"></jsp:include>	
			</footer>
			</div>
			
			<script src="js/JQuery.js" type="text/javascript"></script>
	       <script src="js/changeProduct.js" type="text/javascript"></script>
	       <script src="js/validation.js" type="text/javascript"></script>
	       
</body>
</html>

<%}%>