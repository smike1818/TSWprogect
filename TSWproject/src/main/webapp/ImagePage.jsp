<%@ page language="java" import="java.util.*, bean.ImageBean, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%

    String rend = null;
    List<?> images = null;
    Integer art = null;;
    String error;
    

    if((error = (String) request.getAttribute("error-statement"))==null)     error="";
    request.removeAttribute("error-statement");
    
    if(session.getAttribute("admin")==null){ 
	    images = (List<?>) request.getAttribute("images");
	    rend = "loginPageAdmin.jsp";
    }    	
    

if(request.getParameter("id")!=null){
	art = Integer.parseInt(request.getParameter("id"));
    this.getServletContext().setAttribute("page","ImagePage.jsp");
    images = (List<?>) request.getAttribute("images");	
    if(images == null) {
	     rend = "./imagespage?code="+art;
     }
}

   if(rend!=null){
     response.sendRedirect(rend);
     return;
   }
   
   
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Image Page</title>
<link href="css/adminstyle.css" rel="stylesheet" type="text/css">

<!-- quando ricarico la pagina rimuovo la variabile error così che non viene mostrata in modo fastidioso -->

</head>
<body>
    <jsp:include page="headerAdmin.jsp"></jsp:include>
    <h1>Pagina immagini</h1>
    <p class="error-statement">ATTENZIONE! Si devono inserire solo immagini dalla cartella 'img'</p>
    <!-- viene visto solo in caso di errore -->
    <p class="error-statement"><%=error %></p>
    
    <h3>articolo: <%=art%></h3>
    <p>click image for delete<p>
    <table border=1>
       <tr>
        <%
        ImageBean bean = null;
		if (images != null && images.size() != 0) {
			Iterator<?> it = images.iterator();
			while (it.hasNext()) {
			    bean = (ImageBean) it.next();
        %>
         <td><a href="imagespage?action=delete&nome=<%=bean.getNome()%>&code=<%=bean.getArticolo().getID()%>">
            <img src="img/<%=bean.getNome()%>" alt="no available" width="100" height="100">
         </a></td>
        <%}}else{ %>
          <td colspan=<%=images.size() %>>not images</td>
        <%} %>
      </tr>
    </table>
    
     <!-- quetso bottone deve essere nascosto una volta cliccato il bottone -->
	 <br><button id="show-image-form">clicca per inserire le immagini</button>
	 <div class="image-form">
	    <br><h3> inserimento delle immagini </h3><br>
        <form action="imagespage" method="post" enctype="multipart/form-data" id="image-form">
           <input type="hidden" name="action" value="addImage">
           <input type="hidden" name="code" value="<%=art %>">
           <input name="images" type="file" multiple required accept="image/*"><br>
           <input type="submit" value="Add">
           </form>
     </div>
	 
	 <br><br><a href="CatalogoAdmin.jsp">Torna al Catalogo</a>
	 
	 <script src="js/JQuery.js"></script>
	 <script src="js/admin.js"></script>
	  <script src="js/validation.js"></script>
</body>
</html>