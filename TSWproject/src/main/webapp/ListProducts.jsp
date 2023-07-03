<%@ page language="java" import="java.util.*, bean.ArticoloBean, dao.ArticoloDAO, model.MusicalModelArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%   
     //ora mi prendo tutti i prodotti nel catalogo
     List<?> products = (List<?>) request.getAttribute("products");
     if(products == null ){
        response.sendRedirect("./catalogo");	
        return;
     }
     ArticoloDAO model = new MusicalModelArticoloBean();
    
	//implemento un limite di valori che posso mostrare 
	//utilizzato per mostrare nella home page 5 prodotti
	
	Integer limit;
	limit = (Integer) request.getAttribute("limit");
	if(limit==null)   limit=0;    //se il limite non è impostato inserisco 0 che sta a idicare limite non settato

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
  <body>
    <div class="list-products">  	 
      <%
        ArticoloBean bean = null;
        int counter = 1; // Counter variable
        if (products != null && products.size() != 0) {
          Iterator<?> it = products.iterator();
          while (it.hasNext()) {
            bean = (ArticoloBean) it.next();
            Int divId = counter; // Unique ID for each product div
      %> 
        <div class="catalogo-items" align="center" id="<%=divId%>">          
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
        <%
              counter++; // Increment the counter
              //esco dal while se supero il limite 
              if(limit==1)  break;
              else limit--; 
            }
          }
        %>
      </div>
    <script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
    <script src="js/animations.js" type="text/javascript"></script>
  </body>
</html>