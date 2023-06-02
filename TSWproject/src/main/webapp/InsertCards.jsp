<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
  <h5>inserisci metodo di pagamento</h5><br>
	
	<div id="card-form">
	   <form action="cards" method="post">
	      <input type="hidden" name="action" value="add">
	      <label for="number-card">numero di carta</label>
	      <input type="text" name="number-card" required placeholder="enter number card"><br>
	      
	      <label for="IBAN">IBAN</label>
	      <input type="text" name="IBAN" required placeholder="enter IBAN"><br>
	      
	      <label for="cvv">CVV</label>
	      <input type="text" name="cvv" required placeholder="enter cvv"><br>
	      
	      <input type="submit" value="add">
	   </form>
	</div>
	
	<script src="js/JQuery.js" type="text/javascript"></script>
    <script src="js/userFunctions.js" type="text/javascript"></script>	
</body>
</html>