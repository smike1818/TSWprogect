<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
 		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="css/style.css" rel="stylesheet" type="text/css">
	    <link href="css/error.css" rel="stylesheet" type="text/css">
		<title>FORBIDDEN</title>
		
		<script>
            var urlWithoutPrefix = location.href.replace(/.*:\d+\//, "");
             console.log(urlWithoutPrefix);
             if (urlWithoutPrefix !== "TSWproject/403.jsp") {
                  location.replace("/TSWproject/403.jsp");
             }
        </script>     
</head>
<body class="error-page">
            <div>
               <img src="css/403.avif" alt="no-available" class="image-error">
            </div>
            <div class="text-error">
				<span><b> Attenzione!</b></span>
				 <br><br>
				<span> Pagina non disponibile!</span>
			</div>	
					
				<input class="error-comeback" type="button" value="Torna all'Homepage" 
				       onclick="window.location.href = 'catalogo.jsp';">
				
</body>
</html>