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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
         	 
        	 
       <%
		    ArticoloBean bean = null;
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
				    bean = (ArticoloBean) it.next();
				    if(bean.getQuantita()!=0){
		%>
      
        <div align="center">
          <div>
            <div>
              <div>
                <div>
                  <div>
                    <div>
                      <a href="dettaglio.jsp?id=<%=bean.getID()%>">
                         <img src="img/<%=model.getFirstImage(bean.getID()) %>" alt="no available" width="200" height="200"/>
                      </a>
                    </div>
                  </div>
                  <div>
                    <h5><a href="dettaglio.jsp?id=<%=bean.getID()%>"><%= bean.getName() %></a></h5>

                    <p class="text mb-4 mb-md-0">
                       <%=bean.getDescrizione() %>
                    </p>
                  </div>
                  <div>
                    <div>
                      <h4><%=bean.getPrezzo() %></h4>
                    </div>
                    <h6 class="text-success">Free shipping</h6>
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
                </div>
              </div>
            </div>
          </div>
        </div>

        <%}}} %>
    
</body>
</html>