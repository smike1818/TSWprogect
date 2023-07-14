<%@ page language="java" import="java.util.*,bean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

String rend = null;
Collection<?>acquisti = null;

if(session.getAttribute("admin")==null){ 
    rend = "loginPageAdmin.jsp";
}else{
   acquisti = (Collection<?>) request.getAttribute("acquisti");
   if(acquisti == null){
	   rend = "./acquistilist";
   }
}
   
if(rend!=null){
   response.sendRedirect(rend);
}else{

%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Lista di Ordini</title>
		<link href="css/adminstyle.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="wrapper">
		<header>
		<jsp:include page="headerAdmin.jsp"></jsp:include>
		</header>
		<br><span class="error-statement"></span><br>
		<!-- FORM INSERIMENTO DATE -->
		<div class="content">
			<div class="main">
				<div class="table-container">
					<div class="table-cell">
						<h5>inserisci le date</h5>
						<label for="inizio">Da:</label><br>
						<input type="date" id="inizio"><br>
						<label for="inizio">A:</label><br>
						<input type="date" id="fine"><br>
						<button class="modern-button" id="form-data-button">Click!</button>
					</div>
					<div class="table-container">
						<div class="table-row">
							<span class="table-cell">Conto</span>
							<span class="table-cell">Data</span>
							<span class="table-cell">Totale</span>
							<span class="table-cell">Consumer</span>
							<div class="table-cell">
								<span class="address">Indirizzo</span>
							</div>
							<span class="table-cell">scarica la fattura</span>
						</div>   
						<% 
							AcquistoBean bean = null;
							if (acquisti != null && acquisti.size() != 0) {
								Iterator<?> it = acquisti.iterator();
								while (it.hasNext()) {
									bean = (AcquistoBean) it.next();
						%>
						<div class="table-row">
							<span class="table-cell"><%=bean.getConto().getIBAN()%></span>
							<div class="table-cell">
								<span class="date-td"><%=bean.getDate()%></span>
							</div>
							<span class="table-cell"><%=bean.getImporto()%></span>
							<span class="table-cell"><%=bean.getConsumer().getCF() %></span>
							<span class="table-cell">via <%=bean.getIndirizzo().getVia() %></span>
							<span class="table-cell">(<%=bean.getIndirizzo().getCivico() %>)</span>
							<span class="table-cell"><%=bean.getIndirizzo().getCitta() %></span>
							<span class="table-cell"><a href="" class="modern-a">scarica la fattura</a></span>
						</div>
						<% } } else { %>
						<div class="table-row">
							<div class="table-cell">
								<span class="no-acquisti">nessun acquisto effettuato</span>
							</div>
						</div>
					<% } %>
					</div>
				</div>
			</div>
		</div>
		<footer>
			<jsp:include page="footerAdmin.jsp"></jsp:include>	
		</footer>
	</div>
		<script src="js/JQuery.js" type="text/javascript"></script>
		<script src="js/orderList.js" type="text/javascript"></script>
	</body>
</html>
<%}%>
