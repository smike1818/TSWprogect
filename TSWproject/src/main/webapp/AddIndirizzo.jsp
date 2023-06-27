<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
      
</head>
<body>
   <h5>Aggiungi indirizzo: </h5><br>
   
   <span class="error-statement"></span>
   
    <form action="address" method="post" id="address-form">
       <input type="hidden" name="action" value="new">
       <label for="via">Via:</label><br>
       <input type="text" name="via" required placeholder="Giovanni Pascoli"><br>
       <label for="civico">Civico:</label><br>
       <input type="number" name="civico" required placeholder="999"><br>
       <label for="citta">Città:</label><br>
       <input type="text" name="citta" required placeholder="Roma"><br>
       <label for="citta">CAP:</label><br>
       <input type="number" name="CAP" required placeholder="12345"><br>       
       <input type=submit value="add">
    </form>
    
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/not_autorized.js" type="text/javascript"></script>
      <script src="js/validation.js" type="text/javascript"></script>
</body>
</html>