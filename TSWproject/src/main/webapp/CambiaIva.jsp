<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
if(session.getAttribute("admin")==null){ 
    response.sendRedirect("loginPageAdmin.jsp");
}
%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="UTF-8">
		<title>Cambia IVA</title>
	</head>
	<body>
	<div class="wrapper">
		<header>
			<jsp:include page="headerAdmin.jsp"></jsp:include>
			<p class="error-statement"></p>
			<p class="correct-statement"></p>
		</header>
		<div class="content">
			<div class="main">
				<div class="cambioIva">
				    <h2>Pagina di cambio Iva</h2>
				    <input type="number" step="0.01" min="1.00" max="100.00" id="change-iva-input"><br>
				    <button class="modern-button" id="change-iva-submit">Cambia</button>
				</div>
			</div>       
		</div>
		<footer>
			<jsp:include page="footerAdmin.jsp"></jsp:include>	
		</footer>
	</div>
		    
			 <script src="js/JQuery.js"></script>
			 <script src="js/admin.js"></script>
	</body>
</html>