<%@ page language="java" import="java.util.*, bean.IndirizzoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%

    //-----link per modificare un indirizzo
    //------<a href="address?action=change">clicca per cambiarlo</a><br>
    //------nella servlet address c'è una funzione già creata di modific della password
    //------modificala se serve


     String rend = null;
     List<IndirizzoBean> indlist = null;
     
     if(session.getAttribute("un")==null){
	        rend = "LoginPageUtente.jsp";
     }else{
       String page_precedente = (String) application.getAttribute("page");       
       if(page_precedente == null || (!page_precedente.equalsIgnoreCase("cardsPage.jsp") && !page_precedente.equalsIgnoreCase("Indirizzo.jsp"))){     
    	   
    	   System.out.println(page_precedente);
    	   //controllo se l'utente registrato tenta di accedere direttamente alla pagina senza passare per cardsPage.jsp
    	   String header = "Client Error";
    	   String details = "esegui tutti i passaggi prima di accedere alla pagina...";
    	   rend = "error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details +"&status=401";
       }else{
    	   
    	   application.setAttribute("page","Indirizzo.jsp");    //salvo la pagina corrente
    	   String iban = request.getParameter("IBAN");
           if(iban==null){
        	   if(application.getAttribute("IBAN")==null){
        	     String header = "Client Error";
        	     String details = "carta non selezionata...";
        	     rend = "error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details +"&status=401";
        	   }
    	   }else{
    		   application.setAttribute("IBAN", iban);
    	   }
           indlist = (List<IndirizzoBean>) request.getAttribute("indirizzo");
           if(indlist==null){
    	       rend = "./address";
           }
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
<title>Address Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

 <header>
     <jsp:include page="header.jsp"></jsp:include>
 </header>

<%if(indlist==null || indlist.size()==0){%>
   <jsp:include page="AddIndirizzo.jsp"></jsp:include>
<%}else{ %>

<h4>Seleziona un indirizzo</h4>
<ol class="address-list">
  
 <%
   IndirizzoBean ind = null;
   if (indlist != null && indlist.size() != 0) {
		Iterator<?> it = indlist.iterator();
		while (it.hasNext()) {
		    ind = (IndirizzoBean) it.next();

%>

<!-- quando invio i dati dell'indirizzo alla servlet -->
  <li class="li-address">
     <a href="purchase?via=<%=ind.getVia()%>&civico=<%=ind.getCivico() %>&citta=<%=ind.getCitta() %>">
           <%=ind.getVia()%> <%=ind.getCivico() %>,<%=ind.getCitta() %></a>
  </li>
<%}}%>

</ol>
<span><button class="add-AddIndirizzo-link" >Aggiungi indirizzo</button></span><br>
	   
	   <!-- javascript al click del link di sopra importerà dinamicamente AddIndirizzo.jsp e lo mette nel div di sotto -->
	   
	   <div class="show-AddIndirizzo"></div>
	   
<jsp:include page="footer.jsp"></jsp:include>

    <script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
</body>

<%} %>
</html>