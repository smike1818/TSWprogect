<%@ page language="java" import="java.util.*, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    ServletContext sc = this.getServletContext();
    String type = (String) sc.getAttribute("tipo");	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!--Main Navigation-->
<!--Main Navigation-->
<header>
  <!-- Jumbotron -->
    <div>
      <div>
      
        <!-- Left elements -->
        <div>
          <a href="https://mdbootstrap.com/" target="_blank" class="float-start">
          
            <!-- QUI SI INSERISCE IL LOGO -->
            <img src="https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.png" height="35" />
          
          </a>
        </div>
        <!-- Left elements -->

        <!-- Right elements -->
        <div>
          <div align="right">
          <nav>
             <a href="AdminControl.jsp" target="_blank"> Sign in </a>
             <a href="AdminControl.jsp" target="_blank">  Wishlist  </a>
             <a href="carrello.jsp">  My Cart  </a>
          </nav>
          </div>
        </div>
        <!-- Right elements -->

        <!-- Search elements (da attivare) -->
          <div>
            <div>
              <input type="search" id="form1">
              <input type="submit" value="search">
          </div>
         </div>
         <!-- Search elements -->
    </div>
  </div>
  <!-- Jumbotron -->

  <!-- Heading -->
  <div class="bg-primary mb-4">
    <div>
      <h3> 
         <%=type%>
      </h3>
      <!-- Breadcrumb -->
      <nav>
        <h6>
          <a href="catalogo.jsp?action=redirect" class="text-white-50"><%=type%></a>

        <% 
           String id = request.getParameter("id"); 
           if(id!=null){
        	   ArticoloBean bean = (ArticoloBean) request.getAttribute("bean");
        	   if(bean!=null){
        %>
                  <span class="text-white-50 mx-2"> > </span>
         <span class="text-white-50 mx-2"> <%=bean.getName()%> </span>
        <%
        	   }
           }
        %>

        <% 
           if(((String)sc.getAttribute("page")).equalsIgnoreCase("carrello")){        
        %>
         <span class="text-white-50 mx-2"> > </span>
         <span class="text-white-50 mx-2"><a href="carrello.jsp" class="text-white-50">Carrello</a></span>
         
         <%} %>
       </h6>
      </nav>
      <!-- Breadcrumb -->
    </div>
  </div>
  <!-- Heading -->
</header>
</body>
</html>