<%@ page language="java" import="java.util.*, bean.ArticoloBean, dao.ArticoloDAO, model.MusicalModelArticoloBean" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  
   application.setAttribute("page","CatalogoFiltrato.jsp");
   String categoria = request.getParameter("cat");
   String tipo = request.getParameter("tipo");	
   if(categoria!=null && tipo!=null){
	   //mi salvo tutto nel contesto perchè ha uno scope maggiore di request
	   //in pratica effettuando il reindirizzamento i parametri della richiesta
	   //vengono eliminati, quindi è bisogna salvarli
	   application.setAttribute("cat",categoria);
	   application.setAttribute("tipo", tipo);
   }

  //ora mi prendo tutti i prodotti nel catalogo
  List<ArticoloBean> products = (List<ArticoloBean>) request.getAttribute("products");
     if(products == null ){
     response.sendRedirect("./catalogo");	
     return;
  }
  ArticoloDAO model = new MusicalModelArticoloBean();

//mi prelevo la categoria dal contesto
categoria = (String) application.getAttribute("cat");
application.removeAttribute("cat");

//verifico la validità di categoria
if(categoria!=null){
	application.setAttribute("can-show",true);
	try {
	    Integer cat = Integer.parseInt(categoria);
	    products.removeIf(product -> product.getCategoria().getID() != cat);    	    
	} catch (NumberFormatException e) {
		 RequestDispatcher error = null;
         String header = "Client Error";
         String details = "errore nella conversione tra string e intero (categoria)...";
         response.setStatus(500);
         error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
         error.forward(request, response);
	}
}else{
	application.removeAttribute("can-show");
}

//controllo del tipo
tipo = (String) application.getAttribute("tipo");
application.removeAttribute("tipo");

//controllo validità del tipo
if(tipo!=null){
	application.setAttribute("can-show",true);
	try {
	    Integer t = Boolean.parseBoolean(tipo)?1:0;
	    products.removeIf(product -> product.getTipo() != t);    	    
	} catch (NumberFormatException e) {
		 RequestDispatcher error = null;
         String header = "Client Error";
         String details = "errore nella conversione tra string e intero (tipo)...";
         response.setStatus(500);
         error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
         error.forward(request, response);
	}
}else{
	application.removeAttribute("can-show");
}


//nell'attributo can-show proteggo la pagina da accessi non consentiti
if(application.getAttribute("can-show")==null){
	 RequestDispatcher error = null;
     String header = "Client Error";
     String details = "accesso illegale alla pagina...";
     response.setStatus(403);
     error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
     error.forward(request, response);
}

//se non si può mostrare vado avanti e lo rimuovo dal contesto
application.removeAttribute("can-show");  
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogo Filtrato</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
        <header>
	       <jsp:include page="header.jsp"></jsp:include>
	    </header>
	       
	       <div class="list-products">
        	 
       <%
		    ArticoloBean bean = null;
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
				    bean = (ArticoloBean) it.next();
		%>
           
                  <div class="catalogo-items" align="center">
                  
                      <!-- IMMAGINE  -->
                      <a class="image-item" href="dettaglio.jsp?id=<%=bean.getID()%>">
                         <img src="img/<%=model.getFirstImage(bean.getID()) %>" alt="no available"/>
                      </a>
                      
                      <!-- NOME -->
                      <h5><a class="name-item" href="dettaglio.jsp?id=<%=bean.getID()%>"><%= bean.getName() %></a></h5><br>
                      
                      <!-- TIPO -->
                      <% if(bean.getTipo()==0){ %>
                          <input type="hidden" value="Strumenti" class="type-item">
                      <%}else{ %>
                          <input type="hidden" value="Pezzi di Ricambio" class="type-item">
                      <%} %>
                      
                    
                      <!-- se la quantità è 0 lo rendo non accessibile -->
                      <% if(bean.getQuantita()==0){ %>
                        <span class="product-no-available">prodotto non disponibile</span>
                      <%}else{ %>
                        <span class="product-available"></span>
                      <%} %>

                      <!-- DESCRIZIONE -->
                      <p class="item-description">
                         <%=bean.getDescrizione() %>
                      </p>
                      
                      <!-- MARCA -->
                      <span class="item-marca"><%=bean.getMarca() %></span>
                      
                      <!-- TIPOLOGIA -->
                      <span class="item-tipologia"><%=bean.getTipologia() %></span>
                      
                      <!-- PREZZO, in formato 0,00 -->
                      <h4 class="item-prezzo"><%=String.format("%.2f", bean.getPrezzo())%></h4><br>
                         
                      <!-- FORM AGGIUNTA AL CARRELLO -->
                      <div class="addtocart-block">
                         <form action="cart" method="post" class="addtocart-form">
                         <input type="hidden" name="action" value="cart">
                         <input type="hidden" name="id" value="<%=bean.getID() %>">
		                 <button type="submit" class="addtocart-submit"><i class="fa fa-shopping-cart"></i></button>
                      </form>  
                 	</div> 
                                                          
                  </div>
                  
                  <%}} %>
                  
                </div>
	       
	       
	        <jsp:include page="footer.jsp"></jsp:include>
	    
	    <script src="js/JQuery.js" type="text/javascript"></script>
	    <script src="js/userFunctions.js" type="text/javascript"></script>
</body>
</html>