<%@ page language="java" import="java.util.*, bean.CategoriaBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    List<?> categories = null;
    
    if(session.getAttribute("admin")==null){ 
    	 rend = "loginPageAdmin.jsp";
    }else{
       this.getServletContext().setAttribute("page","CatalogoAdmin.jsp");
       categories = (List<?>) request.getAttribute("categories");	
	   if(categories == null) 
		   rend = "./catalogo";
	}
    
     if(rend!=null){
         response.sendRedirect(rend);
         return;
     }
%>

<!DOCTYPE html>
<html lang="it">
<head>
<title></title>
<meta charset="ISO-8859-1">
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/not_autorized.js" type="text/javascript"></script>
</head>
<body>

   <br><h2>categorie inserite</h2><br>
   <div class="table-container">
		<div class="table-row">
			<span class="table-cell">Code</span>
			<span class="table-cell">Name</span>
			<span class="table-cell">Description</span>
			<!-- <th>Delete</th> -->
		</div>
		
		<%
		  CategoriaBean bean = null;
		  if (categories != null && categories.size() != 0) {
			Iterator<?> it = categories.iterator();
			while (it.hasNext()) {
			    bean = (CategoriaBean) it.next();
		%>
		<div class="table-row">
		    <span class="table-cell"><%=bean.getID() %></span>
		    <span class="table-cell"><%=bean.getNome() %></span>
		    <span class="table-cell"><%=bean.getDescrizione() %></span>
		 </div>
		   <!-- <td><a href="catalogo?action=deletefromcategorie&id=<%=bean.getID()%>">Delete</a></td>  --> 
		    
		<%}}else{ %>
		<div class="table-row">
		    <span class="table-cell">No categories available</span> 
		 </div>
		<%} %>
		
		</div>
</body>
</html>