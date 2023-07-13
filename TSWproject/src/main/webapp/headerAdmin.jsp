<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
    <title></title>
        <meta charset="UTF-8">
        <link href="css/adminstyle.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <header>
 

		<div class="menu">
            <div class="closeButton"><a class="icon-close"><b>X</b></a></div>
                <ul>     
                  <li><a href="UserList.jsp">elenco utenti</a></li>
				  <li><a href="orderList.jsp">elenco ordini</a></li>
				  <li><a href="CatalogoAdmin.jsp">HOME</a></li>
				  <li><a href="CambiaIva.jsp">Cambia iva</a></li>
                </ul>
        </div>
        <div id="menuButton">
            <svg class="icon-menu" viewBox="0 0 20 20">
				<path d="M2 4h16M2 10h16M2 16h16"></path>
			</svg>
	   </div>
			
			
        </header>
        <script src="js/JQuery.js" type="text/javascript"></script>
        <script src="js/nav.js" type="text/javascript"></script>
    </body>
</html>