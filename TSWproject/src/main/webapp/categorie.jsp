<%@ page language="java" import="java.util.*, bean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   List<CategoriaBean>categorie = null;
   categorie = (List<CategoriaBean>) request.getAttribute("categorieFiltrate");
   String cat = null;
   
   //prelevo il tipo
   if(categorie!=null && categorie.size()>0){
    if(categorie.get(0).getTipo())
	   cat = "Pezzi di Ricambio";
   else
	   cat = "Strumenti";
   }else{
	   String type = request.getParameter("tipo");
	   if(type.equalsIgnoreCase("true")){
		   cat = "Pezzi di Ricambio";
	   }else{
		   cat = "Strumenti";
	   }
   }
	   
   
   application.setAttribute("page", "categorie.jsp");
   if(categorie==null){
	   RequestDispatcher error = null;
       String header = "Client Error";
       String details = "accesso illegale alla pagina...";
	   request.setAttribute("errorMessageHeader", header);
       request.setAttribute("errorMessageDetails", details);
       response.setStatus(500);
       error = getServletContext().getRequestDispatcher("/error.jsp");
       error.forward(request, response);
   }
%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Lista di Categorie</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="wrapper">
			<header>
		       <jsp:include page="header.jsp"></jsp:include>
		    </header>
		    <header class="visited-pages-header">
		    	<jsp:include page="second-header.jsp"></jsp:include>
		    </header>
			<div class="content">
				<div class="main">  	
			   		<div class="table-container" id="categorie-table">
			   		    <h3><%=cat %></h3><br>
			   			<div class="table-row">
				       	<%
							CategoriaBean bean = null;
							if (categorie != null && categorie.size() != 0) {
								Iterator<?> it = categorie.iterator();
								while (it.hasNext()) {
								    bean = (CategoriaBean) it.next();
						%>
							<div class="table-cell" id="<%=bean.getID()%>">
								<a class="modern-a" href="CatalogoFiltrato.jsp?cat=<%=bean.getID()%>&tipo=<%=bean.getTipo()%>"><%=bean.getNome() %></a>
							</div>
							<%}}else{ %>
							<div class="table-cell">
								<span class="noCategories">nessuna categoria disponibile</span>
							</div>
						<%} %>
			   			</div>
			   		</div>  
			   	</div>
			</div> 
	   		<footer>
		      	<jsp:include page="footer.jsp"></jsp:include>
	   		</footer>      
		</div>
      	<script src="js/JQuery.js" type="text/javascript"></script>
	  	<script src="js/userFunctions.js" type="text/javascript"></script>
	</body>
</html>