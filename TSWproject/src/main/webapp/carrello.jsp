<%@ page language="java" import="java.util.*, bean.ArticoloBean, java.text.DecimalFormat, bean.ArticoloCart,
    dao.ArticoloDAO, model.MusicalModelArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%
    List<?>cart = (List<?>)session.getAttribute("cart");
    
    //utilizzato per gestire i prezzi e visualizzarli con massimo 2 cifre dopo la virgola
    DecimalFormat frmt = new DecimalFormat();
	frmt.setMaximumFractionDigits(2);
	   
    application.setAttribute("page","carrello.jsp");
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
		<div class="wrapper">
	 		<header>
	    		<jsp:include page="header.jsp"></jsp:include>
	 		</header>
	 		<header class="visited-pages-header">
		    	<jsp:include page="second-header.jsp"></jsp:include>
		    </header>
		    <div class="content" id="cart-content">
		    	<div class="main" id="carrello">
			    	<section class="cart-section">
						<h1>Shopping cart</h1>
						<div class="on-top-cart">
							<p id="number-of-items">You have <%=cart.size() %> items in your cart</p>                    
						</div>
						<div class="table-container" id="cart">
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
							<div class="table-row">  
							<!-- IMMAGINE -->
							<div class="table-cell"> 
								<div class="image-block">
									<a class="image-item" href="dettaglio.jsp?id=<%=bean.getID()%>">
										<img src="img/<%=model.getFirstImage(bean.getID()) %>" class="image-item" alt="Shopping item">
									</a>
								</div>
							</div>
							<!-- NOME -->
							<div class="table-cell">
								<div class="name-item-block">
									<h4><%=bean.getNome() %></h4>
								</div>
							</div>
							<!-- MARCA -->
							<div class="table-cell">
				 				<div class="marca-item-block">
									<p class="marca-item"><%=bean.getMarca() %></p>
								</div>
							</div>
							<!-- PREZZO -->
							<div class="table-cell">
								<div class="prezzo-item-block">
									<h4><%=String.format("%.2f", price)%></h4>
								</div>
							</div>
							<!-- QUANTITA' -->
							<div class="table-row" id="q_and_d">
							 <div class="table-cell">
								<div class="add-quantity">
									<button class="meno modern-button"> - </button>
									<input type="number" value="<%=q%>" disabled class="input-number">
									<input type="hidden" value="<%=a.getQTotale()%>" class="max-quantity">
									<input type="hidden" value="<%=bean.getID() %>" class="id-item">
									<input type="hidden" value="<%=bean.getPrezzo() %>" class="prezzo-base">
									<button class="piu modern-button"> + </button>
								</div>
							</div>
							<!-- DELETE BY CART --> 
							<div class="table-cell">
								<div class="delete-item-block">
									<p class="delete-section"><a href="cart?action=deleteByCart&id=<%=bean.getID()%>" class="redbutton-a">Delete By Cart</a></p>
								</div>
							</div>
							</div>
							</div>
							<%  } %>
							<div class="bottom-cart"></div>
							<div class="total-price">
								<span class="text-price">Totale: <span class="price"><%=String.format("%.2f", tot)%></span></span>
							</div>
							<div class="buy-block">
								<a id="buy-link" href="ConfirmPayment.jsp" class="modern-a">buy</a>
							</div>
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
				</div>
		    </div>
			<footer>
	    		<jsp:include page="footer.jsp"></jsp:include>
			</footer>
		</div>
    	<script src="js/JQuery.js" type="text/javascript"></script>
    	<script src="js/userFunctions.js" type="text/javascript"></script>
	</body>
</html>