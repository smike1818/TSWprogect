<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="it">
<head>
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/not_autorized.js" type="text/javascript"></script>
</head>
<body>

 <div class="filter-form">
    
    <div class="filters-list">
     <h3> Filters for searching</h3>
         <span class="error-statement"></span>
		<br><br><h5><label for="Price">Price</label></h5>
		
		<!-- impostare che min<max in seguito-->
		<br><label for="min">Min <input class="filter-input nascondi-frecce" id="filter-min" type="number" step="0.01" min="0.00" max="4000.00" name="min"></label>
		<br><label for="max">Max <input class="filter-input nascondi-frecce" id="filter-max" type="number" step="0.01" min="0.00" max="4000.00" name="max"></label>
		
		<!-- impostare anche le marche tramite tecnologie frontend, per ora si usa un classico input di testo-->
		<br><br><h5><label for="marca">Marca</label></h5>
		<input class="filter-input" id="filter-marca" type="text" name="marca" placeholder="enter marca">
		
		<br><br><h5><label for="tipologia">Tipologia</label></h5>
		<input class="filter-input" id="filter-tipologia" type=text name="tipologia" placeholder="enter tipologia">		
		<br><br><input class="modern-button" id="filter-submit" type="submit" value="filtra">
		
		<!-- farla per categoria, aspettare studio di AJAX -->
		
     </div>
  </div>
</body>   
</html>
