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
   <table border="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>Delete</th>
		</tr>
		
		<%
		  CategoriaBean bean = null;
		  if (categories != null && categories.size() != 0) {
			Iterator<?> it = categories.iterator();
			while (it.hasNext()) {
			    bean = (CategoriaBean) it.next();
		%>
		<tr>
		    <td><%=bean.getID() %></td>
		    <td><%=bean.getNome() %></td>
		    <td><%=bean.getDescrizione() %></td>
		    <td><a href="catalogo?action=deletefromcategorie&id=<%=bean.getID()%>">Delete</a></td>
		    
		<%}}else{ %>
		
		    <td colspan=4>No categories available</td> 
		    
		<%} %>
		
		</tr>
	</table>
</body>
</html>