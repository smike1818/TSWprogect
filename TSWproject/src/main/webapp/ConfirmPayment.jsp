<%@ page language="java" import="java.util.*, bean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String rend = null;
List<?> cards = null;
List<?> indlist = null;
List<?>cart = (List<?>) session.getAttribute("cart");
String username = (String) session.getAttribute("un");    //effettuo il controllo sull'utente, per verificare se l'utente                     
                                                          //sia registrato o meno
if(username==null){ 
	 application.setAttribute("page","ConfirmPayment.jsp");
	 rend = "LoginPageUtente.jsp";
}else if(cart==null || cart.size()==0){               //controllo sul carrello
     String header = "Client Error";
     String details = "carrello vuoto...";
     rend = "error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details +"&status=401";
}else{
   application.setAttribute("page","ConfirmPayment.jsp");      //controllo in che pagina sto e lo salvo nel contesto
   cards = (List<?>) request.getAttribute("cards");	
   indlist = (List<?>) request.getAttribute("list-address");
   if(cards == null || indlist == null){
	   rend = "./confirm";     //mi vado a prendere la lista di metodi 
   }else if(cards.size()==0){
	   rend = "cardsPage.jsp";    //se la lista di carte è vuota significa che ne devo inserire almeno una
	                              //questa operazione la faccio in cardsPage.jsp
   }else if(indlist.size()==0){
	   rend = "Indirizzo.jsp";    //caso in cui non ci sono indirizzi salvati, quindi si devono aggiungere
   }
}   

 if(rend!=null){
	 response.sendRedirect(rend);
 }

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm Payment</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
 
    <header>
       <jsp:include page="header.jsp"></jsp:include>
    </header>
    <header class="visited-pages-header">
	    	<jsp:include page="second-header.jsp"></jsp:include>
	    </header>
    
    <section class="body">
       <h5>Conferma l'acquisto</h5>
       <div class="selected-parameters">
         <form action="purchase" method="post">
           <label id="metodo-selezionato-label">METODO DI PAGAMENTO: 
              <select id="metodo-selezionato" name="metodo-selezionato">
              
              <%
                 ContoBean bean = null;
  			     if (cards != null && cards.size() != 0) {
  				    Iterator<?> it = cards.iterator();
  			    	while (it.hasNext()) {
  				        bean = (ContoBean) it.next();			    
  				        if(bean.getIntestatario().getUsername().equalsIgnoreCase(username)){
              %>              
                <option
                <%
                   if(bean.getIsPrimary()){
                %>
                  selected                 
                <%} %> 
                value="<%=bean.getIBAN() %>"
                ><%=bean.getNumCarta()%> [<%=bean.getIBAN() %>]</option>                                
              
              <%}}}%>
              
              </select>
           </label>
           <br><label id="indirizzo-selezionato-label">INDIRIZZO: 
                <select id="indirizzo-selezionto" name="indirizzo-selezionato">
              
              <%
                 IndirizzoBean ind = null;
  			     if (indlist != null && indlist.size() != 0) {
  				    Iterator<?> it = indlist.iterator();
  			    	while (it.hasNext()) {
  			    		 ind = (IndirizzoBean) it.next();
              %>              
                <option 
                <%
                   if(ind.getIsPrimary()){
                %>
                  selected                 
                <%} %> 
                value="<%=ind.getVia() %>,<%=ind.getCivico()%>,<%=ind.getCitta()%>"
                ><%=ind.getVia() %> <%=ind.getCivico() %> , <%=ind.getCitta() %></option>                                
              
              <%}}%>
              
              </select>
           </label>
           
           <br><br><input type="submit" value="scegli"> 
           </form>
       </div>
       
       
    </section>
	   
	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- inseriti esclusivamente per poter nascondere la barra di ricerca -->
	<script src="js/JQuery.js" type="text/javascript"></script>
	<script src="js/userFunctions.js" type="text/javascript"></script>
</body>
</html>