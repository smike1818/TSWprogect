<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
    ServletContext sc = this.getServletContext();
    String filter = (String) request.getAttribute("filter");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
     if(filter!= null && filter.equals("no")){
%>
   <form action="product" method="post">
		<input type="hidden" name="action" value="ShowFilters"> 
		<input type="submit" value="mostra filtri">
</form><br>

<%}else{%>
    <div align="center">
     <h3> Filters for searching</h3>
     <form action="product" method="post">
		<input type="hidden" name="action" value="Filters"> 
		<label for="Tipo">Tipo</label>
		<select name="Tipo">
		  <option value="Strumenti">Strumenti</option>
		  <option value="Pezzi di Ricambio">Pezzi di Ricambio</option>
		</select>
		<br><br><h5><label for="Price">Price</label></h5>
		
		<!-- impostare che min<max in seguito-->
		<label for="min">Min <input type="number" min="0" max="4000" name="min" value="0"></label>
		<label for="max">Max <input type="number" min="0" max="4000" name="max" value="0"></label>
		
		<!-- impostare anche le marche tramite tecnologie frontend, per ora si usa un classico input di testo-->
		<br><br><h5><label for="marca">Marca</label></h5>
		<input type="text" name="marca" placeholder="enter marca" value="enter">
		
		<!-- inserire colore prossimamente -->
		<br><br><h5><label for="tipologia">Tipologia</label></h5>
		<input type=text name="tipologia" placeholder="enter tipologia" value="enter">		
		<br><br><input type="submit" value="filtra"><br><br><br><br>
     </form>
     </div>

<%} %>
</body>   
</html>
