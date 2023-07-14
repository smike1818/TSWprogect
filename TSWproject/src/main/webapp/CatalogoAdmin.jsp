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
				<div class="table-row">
					<span class="table-cell">Code</span>
					<span class="table-cell">Name</span>
					<span class="table-cell">Description</span>
					<span class="table-cell">Marca</span>
					<span class="table-cell">Quantità</span>
		            <div class="table-cell"><span class="tag-choise">Corde</span></div>
					<span class="table-cell">Tipologia</span>
					<span class="table-cell">Prezzo</span>
					<span class="table-cell">Categoria</span>
					<span class="table-cell">Iva</span>
					<div class="table-cell"><span class="type-col">Tipo </span></div>
					<span class="table-cell">Immagini</span>
					<span class="table-cell"></span>
				</div>
				
				<%
				    ArticoloBean bean = null;
					if (products != null && products.size() != 0) {
						Iterator<?> it = products.iterator();
						while (it.hasNext()) {
						    bean = (ArticoloBean) it.next();
						  
				%>
				<div class="table-row">
						<span class="table-cell"><%=bean.getID()%></span>
						<span class="table-cell"><%=bean.getName()%></span>
						<span class="table-cell"><%=bean.getDescrizione()%></span>
						<span class="table-cell"><%=bean.getMarca()%></span>
						<span class="table-cell"><%=bean.getQuantita()%></span>
			            <div class="table-cell">
			            	<span class="tag-choise"><%=bean.getCorde()%></span>
			            </div>
						<span class="table-cell"><%=bean.getTipologia()%></span>
						<span class="table-cell"><%=bean.getPrezzo()%></span>
						<span class="table-cell"><%=bean.getCategoria().getNome() %></span>
						<span class="table-cell"><%=bean.getIva().getPercentuale() %>%</span>
						<div class="table-cell"><span class="type-col"><%=bean.getTipo() %></span></div>
						<!-- pagina che mostra tutte le immagini di un determinato prodotto -->
						<span class="table-cell"><a href="ImagePage.jsp?id=<%=bean.getID()%>" class="modern-a"> immagini</a></span>
						<!-- modifica dell'articolo dal database  -->
						<span class="table-cell"><a href="ChangeProduct.jsp?id=<%=bean.getID()%>" class="modern-a">Modifica</a></span>
				</div>
				<%
					}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
				%>
				<div class="table-row">
					<div class="table-cell">
						<span class="no-products" colspan=10>No products available</span>
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