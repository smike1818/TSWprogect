<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
      <script src="js/JQuery.js" type="text/javascript"></script>
      <script src="js/not_autorized.js" type="text/javascript"></script>
</head>
<body>
   <h5>Aggiungi indirizzo: </h5><br>
    <form action="address" method="post">
       <input type="hidden" name="action" value="new">
       <label for="via">Via:</label><br>
       <input type="text" name="via" required placeholder="enter via"><br>
       <label for="civico">Civico:</label><br>
       <input type="number" name="civico" required placeholder="enter civico"><br>
       <label for="citta">Città:</label><br>
       <input type="text" name="citta" required placeholder="enter città"><br>
       <label for="citta">CAP:</label><br>
       <input type="number" name="CAP" required placeholder="enter CAP"><br>       
       <input type=submit value="add">
    </form>
</body>
</html>