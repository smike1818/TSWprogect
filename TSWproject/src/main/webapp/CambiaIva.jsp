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
 <jsp:include page="headerAdmin.jsp"></jsp:include>

    <p class="error-statement"></p>
    <p class="correct-statement"></p>
       
    <h2>Pagina di cambio Iva</h2>
    <input type="number" step="0.01" min="1.00" max="100.00" id="change-iva-input">
    <button id="change-iva-submit">Cambia</button>
    
	 <script src="js/JQuery.js"></script>
	 <script src="js/admin.js"></script>
</body>
</html>