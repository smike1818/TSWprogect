<%@ page language="java" import="java.util.*, bean.ArticoloBean, bean.CategoriaBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    List<?> products = null;
    List<?> cat = null;
    CategoriaBean categoria = null;
    String error;
    
    //se ci sono errori fatti dall'utente li mostro all'utente
    if((error = (String) request.getAttribute("error-statement"))==null)     error="";
    request.removeAttribute("error-statement");
    
    
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
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Insert Page</title>
<link href="css/adminstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
<header>
	 <jsp:include page="headerAdmin.jsp"></jsp:include>
	 <p class="error-statement"><%=error %></p>
</header>
<div class="content">
	<div class="main">
	
	 <jsp:include page="ChoiseAdmin.jsp"></jsp:include>
	 
	 
	 <br><br><br><br><h2>INSERIMENTO</h2><br><br><br><br>
	 
	 
	  <div class="table-container" id="inserimento">
		<div class="table-row no-border">
	
        <div class="table-cell">
         <h3>ARTICOLO:</h3><br><br>
		 <br><form id="insert-product-form" action="insertadmin" method="post">
		  <input type="hidden" name="action" value="Add">
		   <label for="name">Name:</label><br> 
		   <input name="name" type="text" maxlength="50" required placeholder="enter name"><br> 
		   
		   <!-- non ho messo una selezione in quanto bisogna ancora scegliere che tipologie di accessori/strumenti utilizzare
		        da non dimenticare che se si aggiunge una select poi bisogna prevedere le tipologie di accessori e di strumenti in modo
		        separato, quindi gestita in modo diverso da ora  -->
		        
		   <!-- gestione ID -->
		   <input type="hidden" name="id" value="<%=id%>">
		   <input type="hidden" name="choise" id="insert-choise">
		   
		   <label for="tipologia">Tipologia:</label><br> 
		   <input name="tipologia" type="text" maxlength="50" required placeholder="enter tipologia"><br> 
		
		   <label for="description">Description:</label><br>
		   <textarea name="descrizione" maxlength="1000" rows="5" required placeholder="enter description"></textarea><br>
		
		   <!-- prevedere uso del "." e della "," nella scrittura del prezzo. protopido, da migliorare -->
		   <label for="price">Price:</label><br> 
		   <input name="price" type="number" step="0.01" value="1.00" min="1.00" required placeholder="enter price"><br>

		   <label for="quantity">Quantity:</label><br> 
		   <input name="quantity" type="number" min="1" value="1" required placeholder="enter quantity"><br>
		
           <label for="marca">Marca:</label><br> 
		   <input name="marca" type="text" maxlength="50" required placeholder="enter marca"><br> 
		
		   <div class="insert-corde">
		    <label for="corde">Corde:</label><br> 
		    <input name="corde" type="number" min="3" max="6" value="3" required><br>  
		   </div>

		   <label for="colore">Colore:</label><br> 
		   <input name="colore" type="color" value="#ff0000" required><br>
		   
		    
		      <% if(cat!=null && cat.size()!=0){  %>
		      
		      <label id="label-categoria" for="categoria">Categoria</label><br>
		      <select name="categoria" id="categoria" >
		      
		      <%
		    	  Iterator<?> it = cat.iterator();
					while (it.hasNext()) {
					    categoria = (CategoriaBean) it.next();
		      %>
		          <option value=<%=categoria.getID() %> class=<%=categoria.getTipo() %>><%=categoria.getNome() %></option>	          		      
		   
		   <%}%>
		      </select>
		       <p class="no-categories" style="display:none"> inserire prima una categoria per poter continuare l'inserimento del prodotto</p><br><br>
		    <%}else{ %>
		          <p class="no-categories"> inserire prima una categoria per poter continuare l'inserimento del prodotto</p>
		      <%} %>
	    
		<input id="insert-product-submit" type="submit" value="Add">
	 </form>
       </div> 
	 
	 <div class="table-cell">
		 <br><br>
		 <div class="categories-form">
		    <h3> CATEGORIA:</h3><br><br><br>
	        <form id="categories-form" action="insertadmin" method="post">
	        <input type="hidden" name="action" value="cat">
	        <input type="hidden" name="tipo" value="">
	        <label for="name">Name:</label><br> 
	        <input name="name" type="text" maxlength="50" required placeholder="Enter name"><br>
	        <label for="name">Descrizione:</label><br> 
	        <textarea name="descrizione" maxlength="1000" rows="5" required placeholder="Enter description"></textarea><br>
	        <input type="submit" value="Add">
	        </form>
		 </div>
	 </div>
	 </div>	
	</div>
	 
	</div>
	
</div>
<footer>
				<jsp:include page="footerAdmin.jsp"></jsp:include>	
			</footer>
</div>
	 <script src="js/JQuery.js"></script>
	 <script src="js/admin.js"></script>
	 <script src="js/validation.js" type="text/javascript"></script>
	 
	 
</body>
</html>