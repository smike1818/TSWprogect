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
		<title>NOT FOUND</title>
		
<script>
    var urlWithoutPrefix = location.href.replace(/.*:\d+\//, "");
    console.log(urlWithoutPrefix);		
    if (urlWithoutPrefix !== "TSWproject/404.jsp") {
        location.replace("/TSWproject/404.jsp");
    }
</script>

      
</head>
<body class="error-page">
            <div>
               <img src="css/404.avif" alt="no-available" class="image-error">
            </div>
            <div class="text-error">
				<span><b> Attenzione!</b></span>
				 <br><br>
				<span> La pagina che stai cercando non esiste!</span>
			</div>	
					
				<input class="error-comeback" type="button" value="Torna all'Homepage" 
				       onclick="window.location.href = 'catalogo.jsp';">
				
			
</body>
</html>