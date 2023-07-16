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
		   <span class="error-statement"></span><br>
		</header>
		<!-- FORM INSERIMENTO DATE -->
		<div class="content">
			<div class="main">
				<div class="table-container">
					<div class="table-row no-border everycenter" id="data-insert">
						<h5>inserisci le date</h5>
						<label for="label-date">Da: </label>
						<input type="date" id="inizio"><br>
						<label for="label-date">A: </label>
						<input type="date" id="fine"><br>
						<button class="modern-button" id="form-data-button">Click!</button>
						<button class="redbutton-a" id="reset-date">Reset</button>
					</div>
					<div class="table-container">
					   <h2 class="table-row no-border">LISTA ORDINI:</h2><br><br><br><br>
						<div class="table-row th">
						    <span class="table-cell"></span>
							<span class="table-cell">Conto</span>
							<span class="table-cell">Data</span>
							<span class="table-cell">Totale</span>
							<span class="table-cell">Consumer</span>
							<span class="table-cell address">Indirizzo</span>
							<span class="table-cell"></span>
						</div>   
						
						<% 
							AcquistoBean bean = null;
							if (acquisti != null && acquisti.size() != 0) {
								Iterator<?> it = acquisti.iterator();
								while (it.hasNext()) {
									bean = (AcquistoBean) it.next();
						%>
						
						<div class="table-row purchase-item">
							<span class="table-cell"><label class="label-responsive">IBAN:</label> <%=bean.getConto().getIBAN()%></span>
							<span class="table-cell date"><label class="label-responsive">Data:</label> <%=bean.getDate()%></span>
							<span class="table-cell"><label class="label-responsive">Totale: </label><%=bean.getImporto()%></label></span>
							<span class="table-cell"><label class="label-responsive">Codice Fiscale: </label><%=bean.getConsumer().getCF() %></label></span>
							<span class="table-cell"><label class="label-responsive">Indirizzo:</label> via <%=bean.getIndirizzo().getVia() %>
							                         <%=bean.getIndirizzo().getCivico() %>
							                         (<%=bean.getIndirizzo().getCitta() %>)</label></span>
							<span class="table-cell"><button class="fattura modern-button" id="<%=bean.getID()%>">Fattura</button></span>
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
		<script src="js/fattura.js" type="text/javascript"></script>
	</body>
</html>
<%}%>
