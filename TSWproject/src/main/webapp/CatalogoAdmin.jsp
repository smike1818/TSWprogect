<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    List<?> products = null;
    
    if(session.getAttribute("admin")==null){ 
    	 rend = "loginPageAdmin.jsp";
    }else{
       application.setAttribute("page","CatalogoAdmin.jsp");
       products = (List<?>) request.getAttribute("products");	
	   if(products == null) 
		   rend = "./catalogo";
	}
    
     if(rend!=null){
         response.sendRedirect(rend);
         return;
     }
%>
<%
        Integer c;
        String choise = (String)application.getAttribute("choise");
        if(choise!=null){
        	c = Integer.parseInt(choise);
        }else{
        	c=0;
        }
%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="ISO-8859-1">
		<title>CatalogoAdmin</title>
	</head>
	<body>
	<div class="wrapper">
		<header>
			<jsp:include page="headerAdmin.jsp"></jsp:include>
		</header>
		<div class="content">
			<div class="main">
		   <jsp:include page="ChoiseAdmin.jsp"></jsp:include>
		
		<h2>Elenco Articoli</h2>
		
		<!-- quando inserisco un elemento nel database e aggiorno la pagina me lo inserisce nuovamente. da migliorare -->
		<div class="table-container" id="prodotti">
				<div class="table-row th">
					<span class="table-cell">Code</span>
					<span class="table-cell">Name</span>
					<span class="table-cell">Marca</span>
					<span class="table-cell">Quantità</span>
		            <div class="table-cell tag-choise"><span>Corde</span></div>
					<span class="table-cell">Tipologia</span>
					<span class="table-cell">Prezzo</span>
					<span class="table-cell">Categoria</span>
					<span class="table-cell">Iva</span>
					<span class="type-col table-cell">Tipo </span>	
					<span class="table-cell">Immagini</span>
					<span class="table-cell">Modifica</span>
				</div>

				<%
				    ArticoloBean bean = null;
					if (products != null && products.size() != 0) {
						Iterator<?> it = products.iterator();
						while (it.hasNext()) {
						    bean = (ArticoloBean) it.next();
						  
				%>
				<div class="table-row">
						<div class="table-cell"><label class="label-responsive">Code: </label><span><%=bean.getID()%></span><br></div>
						<div class="table-cell"><label class="label-responsive">Nome: </label><span><%=bean.getName()%></span><br></div>
						<div class="table-cell"><label class="label-responsive">Marca: </label><span><%=bean.getMarca()%></span><br></div>
						<div class="table-cell"><label class="label-responsive">QTA: </label><span><%=bean.getQuantita()%></span><br></div>
			            <div class="table-cell tag-choise"><label class="label-responsive">Corde: </label><span><%=bean.getCorde()%></span><br></div>
						<div class="table-cell"><label class="label-responsive">Tipologia: </label><span><%=bean.getTipologia()%></span><br></div>
						<div class="table-cell"><label class="label-responsive">Prezzo: </label><span><%=bean.getPrezzo()%></span><br></div>
						<div class="table-cell"><label class="label-responsive">Categoria: </label><span><%=bean.getCategoria().getNome() %></span><br></div>
						<div class="table-cell"><label class="label-responsive">Iva: </label><span><%=bean.getIva().getPercentuale() %>%</span><br></div>
						<div><span class="type-col"><%=bean.getTipo() %></span></div>
						<!-- pagina che mostra tutte le immagini di un determinato prodotto -->
						<span class="table-cell"><a href="ImagePage.jsp?id=<%=bean.getID()%>" class="modern-a" id="product-details"> immagini</a></span>
						<!-- modifica dell'articolo dal database  -->
						<span class="table-cell"><a href="ChangeProduct.jsp?id=<%=bean.getID()%>" class="modern-a" id="product-details">Modifica</a></span>
				</div>
				<%
					}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
				%>
				<div class="table-row">
					<div class="table-cell">
						<span class="no-products">No products available</span>
					</div>     
				</div>
				
				<%}%>
				
				</div><br>
			 
			 <jsp:include page="CategorieAdmin.jsp"></jsp:include>
			 
			 <h5><a class="modern-a" href="InsertAdmin.jsp">clicca per inserire prodotti</a></h5><br>
			 </div>
		</div>
		<footer>
				<jsp:include page="footerAdmin.jsp"></jsp:include>	
			</footer>
		 </div>
	</body>
</html>