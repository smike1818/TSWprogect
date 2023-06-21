<%@ page language="java" import="java.util.*, bean.ArticoloBean, dao.ArticoloDAO, model.MusicalModelArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	List<?> products = (List<?>) request.getAttribute("products");
    if(products == null ){
    	response.sendRedirect("./catalogo");	
		return;
    }
	ArticoloDAO model = new MusicalModelArticoloBean();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
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
                         <img src="img/<%=model.getFirstImage(bean.getID()) %>" alt="no available" width="10%"/>
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
		                   <button type="submit" class="addtocart-submit">Add to Cart</button>
                         </form>  
                      </div> 
                                                          
                  </div>

        <%}} %>
      </div>
      
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/userFunctions.js" type="text/javascript"></script>
      
</body>
</html>