<%@ page language="java" import="bean.ArticoloBean, dao.ArticoloDAO, model.MusicalModelArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
      application.setAttribute("page","dettaglio.jsp");
      ArticoloBean bean = (ArticoloBean) request.getAttribute("bean");
      String rend = null;
      if(bean==null){
          String id = request.getParameter("id");
          rend = "./details?id="+id;
      }
      int q;
      ArticoloDAO model = new MusicalModelArticoloBean();
      
      if(rend!=null)
    	  response.sendRedirect(rend);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    
    <!-- inizio corpo dettaglio -->
    <% if(bean!=null){ %>
    <div align="center">
       <div>
         <img src="img/<%=model.getFirstImage(bean.getID()) %>" alt="no available" width="500" height="500"/>
       </div>
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
         <label for="quantita"><span><%=q=bean.getQuantita() %></span> prodott<%if(q==1){%>o<%}else{%>i<%} %> disponibil<%if(q==1){%>e<%}else{%>i<%} %> </label>
       </div>
       <div>
            <form action="cart" method="post">
                     <input type="hidden" name="action" value="cart">
                     <input type="hidden" name="id" value="<%=bean.getID()%>">
                     <label for="quantity">Quantity:</label><br> 
		             <input name="quantity" type="number" min="1" max="<%=bean.getQuantita() %>" value="1">
		             <button type="submit">Add to Cart</button>
            </form>  
       </div>
    </div>
    <%} %>
    <!-- fine corpo dettaglio -->
    
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>