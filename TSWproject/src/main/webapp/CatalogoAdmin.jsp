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

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo e Categorie per Admin</title>
</head>
<body>

   <jsp:include page="ChoiseAdmin.jsp"></jsp:include>
		
		<%
		    Integer c = (Integer)application.getAttribute("choise");       //choise sarà 0 se si vuole inserire uno strumento, 1 se altro
		    if(c!=null){
		%>


<h2>Elenco Articoli</h2>

<!-- quando inserisco un elemento nel database e aggiorno la pagina me lo inserisce nuovamente. da migliorare -->
<table border="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>Marca</th>
			<th>Quantità</th>
			<% if(c==0){ %> <th>Corde</th> <%} %>
			<th>Tipologia</th>
			<th>Prezzo</th>
			<th>Categoria</th>
			<th>Immagini</th>
			<th>Delete</th>
		</tr>
		
		<%
		    ArticoloBean bean = null;
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
				    bean = (ArticoloBean) it.next();
					
					if(bean.getTipo()==c){
		%>
		<tr>
			<td><%=bean.getID()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getMarca()%></td>
			<td><%=bean.getQuantita()%></td>
			<% if(c==0){ %> <td><%=bean.getCorde()%><br> <%} %>
			<td><%=bean.getTipologia()%></td>
			<td><%=bean.getPrezzo()%></td>
			<td><%=bean.getCategoria().getNome() %>
			<!-- pagina che mostra tutte le immagini di un determinato prodotto -->
			<td><a href="ImagePage.jsp?id=<%=bean.getID()%>">visualizza immagini</a></td>
			<!-- eliminazione dell'articolo dal database  -->
			<td><a href="catalogo?action=deletefromcatalogo&id=<%=bean.getID()%>">Delete</a></td>
			
		</tr>
		<%
			}}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
		%>
		<tr>
			<td colspan="<%=(c==0)?10:9%>">No products available</td>        
		</tr>
		
		<%}%>
		
		</table><br>
		
		
	 <%}else{		 %>
	 <h3>PAGINA DEL CATALOGO ADMIN</h3>
	 <% }%>
	 
	 <jsp:include page="CategorieAdmin.jsp"></jsp:include>
	 
	 <h5><a href="InsertAdmin.jsp">clicca per inserire prodotti</a></h5><br>
	 
</body>
</html>