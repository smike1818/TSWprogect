<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
  <h5>inserisci metodo di pagamento</h5><br>
	
	<span class="error-statement"></span>
	
	<div id="cards-form">
	   <form action="cards" method="post" id="card-form">
	      <input type="hidden" name="action" value="add">
	      <label for="number-card">numero di carta</label><br>
	      <input type="text" name="number-card" required placeholder="1234 1234 1234 1234" class="search-product-input"><br>
	      
	      <label for="IBAN">IBAN</label><br>
	      <input type="text" name="IBAN" required placeholder="only 27" class="search-product-input"><br>
	      
	      <label for="cvv">CVV</label><br>
	      <input type="text" name="cvv" required placeholder="111" class="search-product-input"><br>
	      
	      <input type="submit" value="add" class="modern-a">
	   </form>
	</div>
	
	<script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>
    <script src="js/validation.js" type="text/javascript"></script>
</body>
</html>