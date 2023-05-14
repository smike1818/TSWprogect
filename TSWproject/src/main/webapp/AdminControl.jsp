<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    List<?> products = null;
    
    if(session.getAttribute("admin")==null){ 
    	 products = (List<?>) request.getAttribute("products");
    	 rend = "loginPageAdmin.jsp";
    }else{
       this.getServletContext().setAttribute("page","admin");
       products = (List<?>) request.getAttribute("products");	
	   if(products == null) 
		   rend = "./product";
	}
    
     if(rend!=null){
         response.sendRedirect(rend);
         return;
     }
	
	  int id;
	  if(products.size()!=0){
	      ArticoloBean last = (ArticoloBean) products.get(products.size()-1);
	      id = last.getID()+1;
	  }else
		  id=0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> Che strumento vuoi inserire? </h2>               

	<form action="product" method="post">
		<input type="hidden" name="action" value="choise"> 
		
		<label for="select">Scegli che articolo vuoi inserire</label><br>
		<select name="type" id="type">
		   <option value="Strumento">Strumento</option>
		   <option value="Pezzi di Ricambio">Altro</option>
		</select>
		<input type="submit" value="invia"><input type="reset" value="Reset">
	</form><br>
		
		<%
		    ServletContext sc = request.getServletContext();
		    Integer c = (Integer)sc.getAttribute("choise");       //choise sarà 0 se si vuole inserire uno strumento, 1 se altro
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
			<th>Image</th>
			<th>Delete</th>
		</tr>
		
		<%
		    String encode = null;
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
			
			<!-- gestione visualizzazione immagini, utilizzo base64. getImmagine() restituisce una stringa in formato base64 -->
			<td><img src="data:image/*;base64,<%= bean.getImmagine() %>" alt="no available" width="100" height="100"/></td>
			
			<!-- eliminazione dell'articolo dal database  -->
			<td><a href="product?action=delete&id=<%=bean.getID()%>">Delete</a></td>
			
		</tr>
		<%
			}}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
		%>
		<tr>
			<td colspan="<%=(c==0)?10:9%>">No products available</td>        
		</tr>
		<%
			}
		%>
		
</table><br>

		<form action="product" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="Add">
		   <label for="name">Name:</label><br> 
		   <input name="name" type="text" maxlength="50" required placeholder="enter name"><br> 
		   
		   <!-- non ho messo una selezione in quanto bisogna ancora scegliere che tipologie di accessori/strumenti utilizzare
		        da non dimenticare che se si aggiunge una select poi bisogna prevedere le tipologie di accessori e di strumenti in modo
		        separato, quindi gestita in modo diverso da ora  -->
		        
		   <!-- gestione ID -->
		   <input type="hidden" name="id" value="<%=id%>">
		   
		   <label for="tipologia">Tipologia:</label><br> 
		   <input name="tipologia" type="text" maxlength="50" required placeholder="enter tipologia"><br> 
		
		   <label for="description">Description:</label><br>
		   <textarea name="descrizione" maxlength="1000" rows="5" required placeholder="enter description"></textarea><br>
		
		   <!-- prevedere uso del "." e della "," nella scrittura del prezzo. protopido, da migliorare -->
		   <label for="price">Price:</label><br> 
		   <input name="price" type="number" step="0.01" value="0.00" min="0.00" required placeholder="enter price"><br>

		   <label for="quantity">Quantity:</label><br> 
		   <input name="quantity" type="number" min="1" value="1" required placeholder="enter quantity"><br>
		
           <label for="marca">Marca:</label><br> 
		   <input name="marca" type="text" maxlength="50" required placeholder="enter marca"><br> 
		
		  <%  if(c==0) { %>
		    <label for="corde">Corde:</label><br> 
		    <input name="corde" type="number" min="3" max="6" value="3" required><br>  
		   <%} %> 
		   <label for="colore">Colore:</label><br> 
		   <input name="colore" type="color" value="#ff0000" required><br>
		   
		   <!-- si possono inserire più immagini ma bisogna modificare il database --> 
		   <label for="immagine">Immagine:</label><br> 
		   <input name="image" type="file" accept="image/*"><br><br>
		   
	        
		<input type="submit" value="Add"><input type="reset" value="Reset">
	 </form>
	 <%}else{		 %>
	 <h3>catalogo + insert form</h3>
	 <% }%>
</body>
</html>