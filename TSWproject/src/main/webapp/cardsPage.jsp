<%@ page language="java" import="java.util.*, bean.ContoBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String rend = null;
List<?> cards = null;

String username = (String) session.getAttribute("un");    //effettuo il controllo sull'utente, per verificare se l'utente                     
                                                          //sia registrato o meno
if(username==null){ 
	 application.setAttribute("page","cardsPage.jsp");
	 rend = "LoginPageUtente.jsp";
}else{
   application.setAttribute("page","cardsPage.jsp");      //controllo in che pagina sto e lo salvo nel contesto
   cards = (List<?>) request.getAttribute("cards");	
   if(cards == null){
	   rend = "./cards";
	}

}if(rend!=null){
	 response.sendRedirect(rend);
 }

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cards Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
 
    <header>
       <jsp:include page="header.jsp"></jsp:include>
    </header>
 
  <h5> Intestatario: <%=username %> </h5>  <br>
  <h5>Scegli un metodo di pagamento</h5> <br>

  <div class="table-container">
    <div class="table-header">
        <span class="table-cell">numero di carta</span>
        <span class="table-cell">iban</span>
        <span class="table-cell">setta come predefinito</span>
        <span class="table-cell">cancella</span>
    </div>    
    <% ContoBean bean = null;
       if (cards != null && cards.size() != 0) {
           Iterator<?> it = cards.iterator();
           while (it.hasNext()) {
               bean = (ContoBean) it.next();			    
               if (bean.getIntestatario().getUsername().equalsIgnoreCase(username)) {
    %>
    <div class="table-row">
        <span class="table-cell"><%=bean.getNumCarta() %></span>
        <span class="table-cell"><%=bean.getIBAN() %></span>
        <% if (!bean.getIsPrimary()) { %>
        <span class="table-cell"><a class="modern-a" href="cards?action=prefer&IBAN=<%=bean.getIBAN()%>">scegli</a></span>
        <% } else { %>
        <span class="table-cell">PREDEFINITA</span>
        <% } %>
        <span class="table-cell"><a class="redbutton-a" href="cards?action=delete&IBAN=<%=bean.getIBAN()%>">elimina</a></span>
    </div>
    <% } } } else { %>
    <div class="table-row">
        <span class="table-cell">non hai carte inserite</span>
    </div>
    <% } %>
</div>
<br>

	<span><button class="add-InsertCards-link" >Aggiungi metodo di pagamento</button></span><br>
	   
	   <!-- javascript al click del link di sopra importer� dinamicamente InsertCards.jsp e lo mette nel div di sotto -->
	   
	   <div class="show-InsertCards"></div>
	   
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
</body>
</html>