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
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista di Ordini</title>
		<link href="css/adminstyle.css" rel="stylesheet" type="text/css">
	</head>
<body>

  <header>
    <jsp:include page="headerAdmin.jsp"></jsp:include>
  </header>
  
  <br><span class="error-statement"></span><br>

<!-- FORM INSERIMENTO DATE -->

<div class="form-data">
  <h5>inserisci le date</h5>
  
  <label for="inizio">Da:</label>
  <input type="date" id="inizio"><br>
  
  <label for="inizio">A:</label>
  <input type="date" id="fine"><br>
  
  <button id="form-data-button">Click!</button>
</div>

<br><div class="table-acquisti">
    <div class="table-header">
        <span>Conto</span>
        <span>Data</span>
        <span>Totale</span>
        <span>Consumer</span>
        <span class="address" colspan="3">Indirizzo</span>
        <span>scarica la fattura</span>
    </div>
    
    <% AcquistoBean bean = null;
       if (acquisti != null && acquisti.size() != 0) {
           Iterator<?> it = acquisti.iterator();
           while (it.hasNext()) {
               bean = (AcquistoBean) it.next();
    %>
    <div class="table-row">
        <span><%=bean.getConto().getIBAN()%></span>
        <span class="date-td"><%=bean.getDate()%></span>
        <span><%=bean.getImporto()%></span>
        <span><%=bean.getConsumer().getCF() %></span>
        <span>via <%=bean.getIndirizzo().getVia() %></span>
        <span>(<%=bean.getIndirizzo().getCivico() %>)</span>
        <span><%=bean.getIndirizzo().getCitta() %></span>
        <span><a href="">scarica la fattura</a></span>
    </div>
    <% } } else { %>
    <div class="table-row">
        <span class="no-acquisti" colspan="6">nessun acquisto effettuato</span>
    </div>
    <% } %>
</div>
<br>


   <script src="js/JQuery.js" type="text/javascript"></script>
   <script src="js/orderList.js" type="text/javascript"></script>

</body>
</html>
<% }%>
