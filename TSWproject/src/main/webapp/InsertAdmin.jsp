<%@ page language="java" import="java.util.*, bean.ArticoloBean, bean.CategoriaBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    List<?> products = null;
    List<?> cat = null;
    CategoriaBean categoria = null;
    
    if(session.getAttribute("admin")==null){ 
    	 rend = "loginPageAdmin.jsp";
    }else{
       this.getServletContext().setAttribute("page","InsertAdmin.jsp");
       products = (List<?>) request.getAttribute("products");	
       cat = (List<?>) request.getAttribute("categories");
	   if(products == null || cat == null)
		   rend = "./insertadmin";
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
	
	 <jsp:include page="ChoiseAdmin.jsp"></jsp:include>
	
		<%
		    Integer c = (Integer)application.getAttribute("choise");       //choise sarà 0 se si vuole inserire uno strumento, 1 se altro
		    if(c!=null){
		%>
	

		<form action="insertadmin" method="post">
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
		   
		   <label for="categoria">Categoria</label><br>
		   <select name="categoria" id="categoria" >
		    
		      <% if(cat!=null && cat.size()!=0){ 
		    	  Iterator<?> it = cat.iterator();
					while (it.hasNext()) {
					    categoria = (CategoriaBean) it.next();
		      %>
		          <option value=<%=categoria.getID() %>><%=categoria.getNome() %></option>	          
		      <%}}else{ %>
		          <option value="nessuno" disabled>nessuno</option>
		      <%} %>
		   </select><br>
	        
		<input type="submit" value="Add"> 
	 </form>
	 
		   
	 <%}else{		 %>
	 <h3>PAGINA INSERIMENTO PRODOTTI ADMIN</h3>
	 <%}%>
	 
	 <!-- quetso bottone deve essere nascosto una volta cliccato il bottone -->
	 <button onclick="showCategoriesForm()">clicca per inserire categoria</button>
	 <div class="categorie"></div>
	 
	 <br><a href="CatalogoAdmin.jsp">Torna al Catalogo</a>
	 
	 <script src="js/insertAdmin.js"></script>
	 
	 
</body>
</html>