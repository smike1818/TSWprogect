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

<br><table border="1" class="table-acquisti">

		<tr>
			<th>Conto</th>
			<th>Data</th>
			<th>Totale</th>
			<th>Consumer</th>
			<th colspan=3>Indirizzo</th>
		</tr>
		
		<%
		    AcquistoBean bean = null;
			if (acquisti != null && acquisti.size() != 0) {
				Iterator<?> it = acquisti.iterator();
				while (it.hasNext()) {
				    bean = (AcquistoBean) it.next();
				  
		%>
		<tr>
			<td><%=bean.getConto().getIBAN()%></td>
			<td class="date-td"><%=bean.getDate()%></td>
			<td><%=bean.getImporto()%></td>
			<td><%=bean.getConsumer().getUsername() %>
			<td>via <%=bean.getIndirizzo().getVia() %></td>
			<td>(<%=bean.getIndirizzo().getCivico() %>) </td>
			<td><%=bean.getIndirizzo().getCitta() %></td>
		</tr>
		<%
			}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
		%>
		<tr>
			<td class="no-acquisti" colspan=6>nessun acquisto effettuato</td>        
		</tr>
		
		<%}%>
		
		</table><br>

   <script src="js/JQuery.js" type="text/javascript"></script>
   <script src="js/orderList.js" type="text/javascript"></script>

</body>
</html>
<% }%>
