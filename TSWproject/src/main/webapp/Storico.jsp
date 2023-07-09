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
	application.setAttribute("page", "Storico.jsp");
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
<header class="visited-pages-header">
	    	<jsp:include page="second-header.jsp"></jsp:include>
	    </header>

  <h3>STORICO DEGLI ORDINI</h3>
  <h5>Utente: <%=username %></h5><br><br>
  
  <div class="table-acquisti">
		
		<%
		    AcquistoBean bean = null;
			if (storico != null && storico.size() != 0) {
				Iterator<?> it = storico.iterator();
				while (it.hasNext()) {
				    bean = (AcquistoBean) it.next();
				  
		%>
	    <div class="acquisti-details" id="<%=bean.getID() %>">
	      <div class="first-block">
			<span><b>Conto:</b> <%=bean.getConto().getIBAN()%></span><br>
			<span class="date-td"><b>Data:</b> <%=bean.getDate()%></span><br>
			<span><b>Totale:</b> <%=String.format("%.2f", bean.getImporto())%>$</span><br>
		  </div>
		  <div class="second-block">
			<span> <b>Indirizzo:</b> via <%=bean.getIndirizzo().getVia() %></span>		  
			<span><%=bean.getIndirizzo().getCivico() %> </span>
			<span>(<%=bean.getIndirizzo().getCitta() %>)</span><br>
		  </div>		  
		  <div class="terzo-block">
			<button class="dettagli">dettagli</button> <br> 
			<button class="fattura" id="<%=bean.getID()%>">scarica fattura</button><br> 
		  </div>		  
		</div>
		
		<!-- div in cui andrò a mettere gli articoli -->
		<div class="artDetailsContainer" style="display: none;"></div>
		<%
			}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
		%>
		<div>
			<span class="no-acquisti"><%=username %> non ha ancora effettuato acquisti</span>        
		</div>
		
		<%}%>
		
		</div><br>
  
  
  <footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/jquery.cycle2.min.js"></script>
      <script src="js/storico.js" type="text/javascript"></script>
      <script src="js/fattura.js" type="text/javascript"></script>
</body>
</html>