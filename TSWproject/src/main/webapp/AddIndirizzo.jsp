<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
   <span class="error-statement"></span>
   <div class="table-container" id="indirizzi">
   <div class="table-row no-border">
   		<h5 class="table-cell">Aggiungi indirizzo: </h5>
   </div>
    <form action="address" method="post" id="address-form">
       <input type="hidden" name="action" value="new" class="search-products-input">
       <label for="via">Via:</label><br>
       <input type="text" name="via" required placeholder="Giovanni Pascoli" class="search-product-input"><br>
       <label for="civico">Civico:</label><br>
       <input type="number" name="civico" required placeholder="999" class="search-product-input"><br>
       <label for="citta">Città:</label><br>
       <input type="text" name="citta" required placeholder="Roma" class="search-product-input"><br>
       <label for="citta">CAP:</label><br>
       <input type="number" name="CAP" required placeholder="12345" class="search-product-input"><br>       
       <input type=submit value="add" class="modern-a">
    </form>
   </div>
    
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/not_autorized.js" type="text/javascript"></script>
      <script src="js/validation.js" type="text/javascript"></script>
</body>
</html>