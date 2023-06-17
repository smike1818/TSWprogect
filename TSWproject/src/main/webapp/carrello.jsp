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
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
 <header>
    <jsp:include page="header.jsp"></jsp:include>
 </header>
    <section class="cart-section">
                <h5><a href="catalogo.jsp" class="text-body"><i
                      class="redirect-catalogo"></i>Continue shopping</a>
                </h5>
 
                  <h3 id="title-cart">Shopping cart</h3>
                  <div class="on-top-cart">
                    <p id="number-of-items">You have <%=cart.size() %> items in your cart</p>                    
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
    				   double price = bean.getPrezzo()*q ;
    				   tot += price;
    				   
    				   
                %>
                
                <div class="item-in-cart">
                       <!-- IMMAGINE -->
                        <div class="image-block">
                         <a class="image-item" href="dettaglio.jsp?id=<%=bean.getID()%>">
                          <img src="img/<%=model.getFirstImage(bean.getID()) %>" 
                           class="image-item" alt="Shopping item">
                         </a>
                        </div>
                        
                        <!-- NOME -->
                        <div class="name-item-block">
                          <h5 class="name-item"><%=bean.getNome() %></h5>
                        </div>                        
                        
                        <!-- MARCA -->
                        <div class="marca-item-block">
                            <p class="marca-item"><%=bean.getMarca() %></p>
                        </div>
                        
                        <!-- PREZZO -->
                        <div class="prezzo-item-block">
                            <h5><%=String.format("%.2f", price)%></h5>
                        </div>
                        
                        <!-- QUANTITA' -->
                        <div class="add-quantity">
                          <button class="meno"> - </button>
                          <input type="number" value="<%=q%>" disabled class="input-number">
                          <input type="hidden" value="<%=a.getQTotale()%>" class="max-quantity">
                          <input type="hidden" value="<%=bean.getID() %>" class="id-item">
                          <input type="hidden" value="<%=bean.getPrezzo() %>" class="prezzo-base">
                          <button class="piu"> + </button>
                        </div>
                        
                        <!-- DELETE BY CART --> 
                        <div class="delete-item-block">
                          <p class="delete-section"><a href="cart?action=deleteByCart&id=<%=bean.getID()%>">Delete By Cart</a></p>
                        </div>
                        
                </div>
                
                <%  } %>
                 <div class="bottom-cart"></div>
                 <div class="total-price">
                   <span class="text-price">Totale: <span class="price"><%=String.format("%.2f", tot)%></span></span>
                 </div>
                 <div class="buy-block">
                   <a id="buy-link" href="ConfirmPayment.jsp">buy</a>
                 </div>
    			   
    			<%
    			  }else{
    			%>
    			 <div class="no-items">
                    <h5>nessun articolo nel carrello...</h5>
                 </div>
    			   <% 
    			}
    			 %>

</section>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
</body>
</html>