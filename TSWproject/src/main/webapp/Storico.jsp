<%@ page language="java" import="java.util.*, bean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String rend = null;
Collection<?>storico = null;
String username = (String) session.getAttribute("un");    //effettuo il controllo sull'utente, per verificare se l'utente                     
                                                          //sia registrato o meno
if(username==null){ 
   rend = "LoginPageUtente.jsp";
}else{
	storico = (Collection<?>) request.getAttribute("storico");
	if(storico==null){
       rend = "./storico";
	}
}

if(rend!=null){
	response.sendRedirect(rend);
	return;
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Storico Ordini</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>

  <h3>STORICO DEGLI ORDINI</h3>
  <h5>Utente: <%=username %></h5>
  
  <table border="1" class="table-acquisti">

		<tr>
			<th>Conto</th>
			<th>Data</th>
			<th>Totale</th>
			<th colspan=3>Indirizzo</th>
			<th>Dettagli</th>
			
		</tr>
		
		<%
		    AcquistoBean bean = null;
			if (storico != null && storico.size() != 0) {
				Iterator<?> it = storico.iterator();
				while (it.hasNext()) {
				    bean = (AcquistoBean) it.next();
				  
		%>
		<tr class="acquisto-<%=bean.getID()%>">
			<td><%=bean.getConto().getIBAN()%></td>
			<td class="date-td"><%=bean.getDate()%></td>
			<td><%=bean.getImporto()%></td>
			<td>via <%=bean.getIndirizzo().getVia() %></td>
			<td>(<%=bean.getIndirizzo().getCivico() %>) </td>
			<td><%=bean.getIndirizzo().getCitta() %></td>
			
			<!-- AGGIUNGI LA PAGINA CHE HAI CREATO AL POSTO DI pagina_dettaglio -->
			<td><a href="pagina_dettaglio?acquisto=<%=bean.getID()%>">dettagli</a></td>   
			
			
			 
		</tr>
		<%
			}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
		%>
		<tr>
			<td class="no-acquisti" colspan=7><%=username %> non ha ancora effettuato acquisti</td>        
		</tr>
		
		<%}%>
		
		</table><br>
  
  
  <footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
</body>
</html>