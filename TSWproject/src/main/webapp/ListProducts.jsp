<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	List<ArticoloBean> products = (List<ArticoloBean>) request.getAttribute("products");
    if(products == null){
    	response.sendRedirect("./product");	
		return;
    }
	List<String>filters = (List<String>) getServletContext().getAttribute("filtersList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Musical Store</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
         <%
         Collection<ArticoloBean>fil = new ArrayList<ArticoloBean>();
         if(filters != null && filters.size() != 0){
             int tip = Integer.parseInt((String) filters.get(0));
             int min = Integer.parseInt((String) filters.get(1));
    	     int max = Integer.parseInt((String) filters.get(2));
    	     String marca = (String) filters.get(3);
    	     String tipologia = (String) filters.get(4);
         
    	 
             if (products != null && products.size() != 0) {
        	    boolean flag;
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					flag=true;
					ArticoloBean bean = (ArticoloBean) it.next();
        	            if(bean.getTipo()!=tip)
        	            	flag=false;
        	        	if(min != 0 && max !=0){
        	        		if(!(bean.getPrezzo()>=min && bean.getPrezzo()<=max))
        	        			flag=false;
        	        	}
        	        	if(!marca.equalsIgnoreCase("enter")){
        	        		if(!bean.getMarca().equalsIgnoreCase(marca))
        	        			flag=false;
        	        	}
        	        	if(!tipologia.equalsIgnoreCase("enter")){
        	        		if(!bean.getMarca().equalsIgnoreCase(tipologia))
        	        			flag=false;
        	        	}
        	        	
        	        if(flag==true)
        	        	fil.add(bean);
				}
          }
         
         }
         if (fil.size() == 0){
        	 if (products != null && products.size() != 0) {
 				Iterator<?> it = products.iterator();
 				while (it.hasNext()) {
 					ArticoloBean bean = (ArticoloBean) it.next();
 					String type = (String) getServletContext().getAttribute("tipo");
 					if(type != null){
 				      int t = (type.equalsIgnoreCase("Strumenti"))?0:1; 
 					  if(bean.getTipo()==t)
 					    fil.add(bean);
 					}
                }
        	 }
         }
         for(ArticoloBean bean : fil){ 	 
        %>
      
        <div align="center">
          <div>
            <div>
              <div>
                <div>
                  <div>
                    <div>
                      <a href="dettaglio.jsp?id=<%=bean.getID()%>">
                         <img src="data:image/*;base64,<%= bean.getImmagine() %>" alt="no available" width="200" height="200"/>
                      </a>
                    </div>
                  </div>
                  <div>
                    <h5><%= bean.getName() %></h5>

                    <p class="text mb-4 mb-md-0">
                       <%=bean.getDescrizione() %>
                    </p>
                  </div>
                  <div>
                    <div>
                      <h4><%=bean.getPrezzo() %></h4>
                      <span class="text-danger"><s>$49.99</s></span>
                    </div>
                    <h6 class="text-success">Free shipping</h6>
                    <div>
                       <form action="product" method="post">
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

        <%} %>
    
</body>
</html>