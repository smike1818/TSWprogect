<%@ page language="java" import="java.util.*, bean.ArticoloBean, java.text.DecimalFormat, bean.ArticoloCart,
    dao.ArticoloDAO, model.MusicalModelArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%
    ServletContext sc = this.getServletContext();
    List<?>cart = (List<?>)session.getAttribute("cart");
    
    //utilizzato per gestire i prezzi e visualizzarli con massimo 2 cifre dopo la virgola
    DecimalFormat frmt = new DecimalFormat();
	frmt.setMaximumFractionDigits(2);
	   
    sc.setAttribute("page","carrello.jsp");
    if((cart==null)){  
    	response.sendRedirect("./cart?action=cart");
    	return;
    }
    float tot=0;
    ArticoloDAO model = new MusicalModelArticoloBean();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Section</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <section class="h-100 h-custom" style="background-color: #eee;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">
        <div class="card">
          <div class="card-body p-4">

            <div class="row">

              <div class="col-lg-7">
                <h5 class="mb-3"><a href="catalogo.jsp" class="text-body"><i
                      class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
                <hr>

                <div class="d-flex justify-content-between align-items-center mb-4">
                  <div>
                    <p class="mb-1">Shopping cart</p>
                    <p class="mb-0">You have <%=cart.size() %> items in your cart</p>
                  </div>
                  <div>
                    <p class="mb-0"><span class="text-muted">Sort by:</span> <a href="cart?action=sortCart"
                        class="text-body">price <i class="fas fa-angle-down mt-1"></i></a></p>
                  </div>
                </div>
     
                <%
                //mostro a video gli elementi del carrello
                ArticoloBean bean = null;
                if (cart != null && cart.size() != 0) {
                Iterator<?> it = cart.iterator();
    			   while (it.hasNext()) {
    				   ArticoloCart a = (ArticoloCart) it.next(); 
    				   
    				   //mi prelevo il bean e la quantità da ArticoloCart
    				   bean = a.getBean();
    				   int q = a.getQCorrente();
    				   
    				   
                %>
                
                <div class="card mb-3">
                  <div class="card-body">
                    <div class="d-flex justify-content-between">
                      <div class="d-flex flex-row align-items-center">
                        <div>
                          <img src="img/<%=model.getFirstImage(bean.getID()) %>" 
                           class="img-fluid rounded-3" alt="Shopping item" style="width: 65px;">
                        </div>
                        <div class="ms-3">
                          <h5><%=bean.getNome() %></h5>
                          <p class="small mb-0"><%=bean.getTipologia() %></p>
                        </div>
                      </div>
                      <div class="d-flex flex-row align-items-center">
                        <div style="width: 50px;">
                          <h5 class="fw-normal mb-0"><%=q%></h5>
                        </div>
                        <div style="width: 80px;">
                          <h5 class="mb-0"><%=frmt.format(bean.getPrezzo()*q) %></h5>
                        </div>
                        <div style="width: 80px;">
                          <h6 class="mb-0"><a href="cart?action=deleteByCart&id=<%=bean.getID()%>">Delete By Cart</a></h6>
                        </div>
                        
                        <a href="#!" style="color: #cecece;"><i class="fas fa-trash-alt"></i></a>
                      </div>
                    </div>
                  </div>
                </div>
                
                <% 
                //mi ricavo il prezzo totale che il cliente deve spendere per comprare l'intero carrello
                tot+=bean.getPrezzo()*q;}
    			
    			%>
                 <div>
                   <a href="cardsPage.jsp">buy</a>
                 </div>
    			   
    			<%
    			  }else{
    			%>
    			 <div class="ms-3">
                    <h5>nessun articolo nel carrello...</h5>
                 </div>
    			   <% 
    			}
    			 %>


              </div>
           </div>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</section>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>