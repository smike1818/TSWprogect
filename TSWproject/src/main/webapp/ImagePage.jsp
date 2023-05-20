<%@ page language="java" import="java.util.*, bean.ImageBean, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    String rend = null;
    List<?> images = null;
    Integer art;
    

 if(request.getParameter("id")!=null){
	art = Integer.parseInt(request.getParameter("id"));
    if(session.getAttribute("admin")==null){ 
	    images = (List<?>) request.getAttribute("images");
	    rend = "loginPageAdmin.jsp";
    }else{
        this.getServletContext().setAttribute("page","ImagePage.jsp");
        images = (List<?>) request.getAttribute("images");	
        if(images == null) 
	        rend = "./imagespage?code="+art;
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
<title>Image Page</title>
</head>
<body>
    <h1>Pagina immagini</h1>
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
	 <button onclick="showImageForm('<%=art%>')">clicca per inserire le immagini</button>
	 <div class="image-form"></div>
	 
	 <br><a href="CatalogoAdmin.jsp">Torna al Catalogo</a>
	 <script src="js/insertAdmin.js"></script>
</body>
</html>

<%}%>