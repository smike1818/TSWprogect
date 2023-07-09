<%@ page language="java" import="java.util.*, bean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   List<?>categorie = null;
   categorie = (List<?>) request.getAttribute("categorieFiltrate");
   application.setAttribute("page", "categorie.jsp");
   if(categorie==null){
	   RequestDispatcher error = null;
       String header = "Client Error";
       String details = "accesso illegale alla pagina...";
       response.setStatus(500);
       error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
       error.forward(request, response);
   }
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista di Categorie</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<header>
	       <jsp:include page="header.jsp"></jsp:include>
	    </header>
	    <header class="visited-pages-header">
	    	<jsp:include page="second-header.jsp"></jsp:include>
	    </header>
   		<ul class="categorie-list">
	       	<%
				CategoriaBean bean = null;
				if (categorie != null && categorie.size() != 0) {
					Iterator<?> it = categorie.iterator();
					while (it.hasNext()) {
					    bean = (CategoriaBean) it.next();
			%>
			<li id="<%=bean.getID()%>"><a href="CatalogoFiltrato.jsp?cat=<%=bean.getID()%>&tipo=<%=bean.getTipo()%>"><%=bean.getNome() %></a></li>
			<%}}else{ %>
			<li class="noCategories">nessuna categoria disponibile</li>
			<%} %>
   		</ul>        
      	<jsp:include page="footer.jsp"></jsp:include>
      	<script src="js/JQuery.js" type="text/javascript"></script>
	  	<script src="js/userFunctions.js" type="text/javascript"></script>
	</body>
</html>