<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>

    <button onclick="showFilters()">clicca per vedere i filtri</button>

    <div id="filters-list">
     <h3> Filters for searching</h3>
     <form action="" method="post">
		<label for="Tipo">Tipo</label>
		<select name="Tipo">
		  <option value="Strumenti">Strumenti</option>
		  <option value="Pezzi di Ricambio">Pezzi di Ricambio</option>
		</select>
		<br><br><h5><label for="Price">Price</label></h5>
		
		<!-- impostare che min<max in seguito-->
		<label for="min">Min <input type="number" step="0.01" min="0.00" max="4000.00" name="min"  value="0.00"></label>
		<label for="max">Max <input type="number" step="0.01" min="0.00" max="4000.00" name="max" value="0.00"></label>
		
		<!-- impostare anche le marche tramite tecnologie frontend, per ora si usa un classico input di testo-->
		<br><br><h5><label for="marca">Marca</label></h5>
		<input type="text" name="marca" placeholder="enter marca" value="enter">
		
		<br><br><h5><label for="tipologia">Tipologia</label></h5>
		<input type=text name="tipologia" placeholder="enter tipologia" value="enter">		
		<br><br><input type="submit" value="filtra">
		
		<!-- farla per categoria, aspettare studio di AJAX -->
		
     </form>
     </div>

<script src="js/userFunctions.js" type="text/javascript"></script>
</body>   
</html>
