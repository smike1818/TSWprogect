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
<html>
<head>
<meta charset="ISO-8859-1">
<title>CatalogoAdmin</title>
</head>
<body>

   <jsp:include page="headerAdmin.jsp"></jsp:include>
   <jsp:include page="ChoiseAdmin.jsp"></jsp:include>

<h2>Elenco Articoli</h2>

<!-- quando inserisco un elemento nel database e aggiorno la pagina me lo inserisce nuovamente. da migliorare -->
<table border="1" class="table-catalogo">

		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>Marca</th>
			<th>Quantità</th>
            <th class="tag-choise">Corde</th> 
			<th>Tipologia</th>
			<th>Prezzo</th>
			<th>Categoria</th>
			<th>Iva</th>
			<th class="type-col">Tipo </th>
			<th>Immagini</th>
		</tr>
		
		<%
		    ArticoloBean bean = null;
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
				    bean = (ArticoloBean) it.next();
				  
		%>
		<tr class="product">
			<td><%=bean.getID()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getMarca()%></td>
			<td><%=bean.getQuantita()%></td>
            <td class="tag-choise"><%=bean.getCorde()%><br>
			<td><%=bean.getTipologia()%></td>
			<td><%=bean.getPrezzo()%></td>
			<td><%=bean.getCategoria().getNome() %>
			<td><%=bean.getIva().getPercentuale() %>%</td>
			<td class="type-col"><%=bean.getTipo() %></td>
			<!-- pagina che mostra tutte le immagini di un determinato prodotto -->
			<td><a href="ImagePage.jsp?id=<%=bean.getID()%>">visualizza immagini</a></td>
			<!-- eliminazione dell'articolo dal database  -->
			
		</tr>
		<%
			}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
		%>
		<tr>
			<td class="no-products">No products available</td>        
		</tr>
		
		<%}%>
		
		</table><br>
	 
	 <jsp:include page="CategorieAdmin.jsp"></jsp:include>
	 
	 <h5><a href="InsertAdmin.jsp">clicca per inserire prodotti</a></h5><br>
	 
</body>
</html>