<%@ page language="java" import="bean.*, dao.ArticoloDAO, model.MusicalModelArticoloBean, java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
      application.setAttribute("page","dettaglio.jsp");
      ArticoloBean bean = (ArticoloBean) request.getAttribute("bean");
      String rend = null;
      if(bean==null){
          String id = request.getParameter("id");
          rend = "./details?id="+id;
          response.sendRedirect(rend);
          return;
      }
      
        int q;
        ArticoloDAO model = new MusicalModelArticoloBean();
        List<String>images =  model.getImages(bean.getID());
      
      
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio Prodotto</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
	    <jsp:include page="header.jsp"></jsp:include>
	</header>
    <header class="visited-pages-header">
	    	<jsp:include page="second-header.jsp"></jsp:include>
	    </header>
    
    <!-- inizio corpo dettaglio -->
    <% if(bean!=null){ %>
    <div align="center">
       
       <!-- immagini con scorrimento automatico e manuale -->
       
  <%
    if (images != null && images.size() >1) {
  %>
  
 <div class="images-block">
  <div class="cycle-slideshow" 
       data-cycle-fx="scrollHorz"
       data-cycle-prev=".prev"
       data-cycle-next=".next"
       data-cycle-timeout = 0 >   
    <div class="slice">
      <a class="prev" href="#">&lt;</a>
      <a class="next" href="#">&gt;</a>
    </div>
    <% for (String name : images) { %>
      <img src="img/<%=name%>" alt="no available"/>
    <% } %>
  </div>
</div>

<!-- non implemento lo scorrimento nel caso in cui c'è una sola immagine -->

<%}else if(images.size() == 1){ %>
    <div class="images-block">
       <img src="img/<%=images.get(0)%>" alt="no available" width=500px height=500px/>
    </div>
<% } %>
 
  
                         
       <div>
         <label for="name">Nome:  </label>
         <span><%=bean.getNome() %></span><br>
       </div>
       <div>
         <label for="descrizione">Descrizione:  </label>
         <p><%=bean.getDescrizione() %></p>
       </div>
       <div>
         <label for="prezzo">Prezzo:  </label>
         <span><%=bean.getPrezzo() %></span><br>
       </div>
       <div>
         <label for="colore">Colore:  </label>
         <span><%=bean.getColore() %></span><br>
       </div>
       <div>
          <label for="tipologia">Tipologia:  </label>
         <span><%=bean.getTipologia() %></span><br>
       </div>
       <div>
         <label for="marca">Marca:  </label>
         <span><%=bean.getMarca() %></span><br>
       </div>
        <div>
         <label for="marca">Categoria:  </label>
         <span><%=bean.getCategoria().getNome() %></span><br>
       </div>
       <div>
         <label for="quantita"><span class="details-quantity"><%=q=bean.getQuantita() %></span> prodott<%if(q==1){%>o<%}else{%>i<%} %> disponibil<%if(q==1){%>e<%}else{%>i<%} %> </label>
       </div>
       <div class="addtocart-block">
           <form action="cart" method="post" class="addtocart-form">
               <input type="hidden" name="action" value="cart">
               <input type="hidden" name="id" value="<%=bean.getID() %>">
		       <button type="submit" class="addtocart-submit">Add to Cart</button>
           </form>  
       </div>       
    </div>
    <%} %>
    <!-- fine corpo dettaglio -->
    
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/jquery.cycle2.min.js"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
    <script src="js/immagini.js" type="text/javascript"></script>
</body>
</html>