<%@ page language="java" import="java.util.*, bean.ImageBean, bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%

String rend = null;
List<?> images = null;
Integer art = null;
String error;

error = (String) request.getAttribute("error-statement");
if(error==null)     error="";

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
 <div class="wrapper">
 <header>
  <jsp:include page="headerAdmin.jsp"></jsp:include>
  <p class="error-statement"><%=error %></p>
 </header>
  <div class="content">
   <div class="main">
    <h1>Pagina immagini</h1>
     <h3>articolo: <%=art%></h3>
       <div class="table-container" id="image-table">

<%

ImageBean bean = null;
if(images != null && images.size() > 1) {
	
%>

<div class="images-block">
<div class="table-cell" id="product-img">
<div class="cycle-slideshow"
    data-cycle-fx="scrollHorz"
    data-cycle-prev=".prev"
    data-cycle-next=".next"
    data-cycle-timeout = 0 >
      <div class="slice">
        <a class="prev" href="#">&lt;</a>
        <a class="next" href="#">&gt;</a>
      </div>

<%

Iterator<?> it = images.iterator();
while(it.hasNext()) {
   bean = (ImageBean) it.next();

%>

<img src="img/<%=bean.getNome()%>" alt="no available"/>

<%} %>

 </div>
   <br><br><input type="submit" class="delete-button" value="delete"><br>
   <input type="hidden" class="delete-input" value="<%=art %>">
 </div>
</div>

<%}else if(images.size() == 1){
    ImageBean FImage = null;
    Iterator<?> it = images.iterator();
    if(it.hasNext()) {
       FImage = (ImageBean) it.next();
%>

  <div class="table-cell">
    <img class="only-image" src="img/<%=FImage.getNome()%>" alt="no available"/>
  </div>
  
  <div class="delete-block">
    <input type="submit" class="delete-button" value="delete">
    <input type="hidden" class="delete-input" value="<%=art %>">
  </div>

<% }}else if(images.size() == 0){%>

<div class="table-cell images-block">
<img src="img/default.png" alt="no available"/>
</div>

<% } %>

<div class="table-cell image-form ">

<br><h3> inserimento delle immagini </h3><br>

<form action="imagespage" method="post" enctype="multipart/form-data" id="image-form">
<input type="hidden" name="action" value="addImage">
<input type="hidden" name="code" value="<%=art %>">
<label for="myFileInput" id="fileNameLabel">Seleziona un file</label>
<input name="images" type="file" id="myFileInput" multiple required accept="image/png"><br><br>
<input type="submit" value="Add" >
</form>

</div>
</div>
</div>
</div>
</div>

<script src="js/JQuery.js"></script>
<script src="js/jquery.cycle2.min.js"></script>
<script src="js/admin.js"></script>
<script src="js/validation.js"></script>
<script src="js/immagini.js" type="text/javascript"></script>
<script src="js/deleteimg.js" type="text/javascript"></script>
<script src="js/inputImageManagment.js" type="text/javascript"></script>
</body>
</html>