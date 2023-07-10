<%@ page language="java" import="java.util.*,bean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String rend = null;
    String username = (String) session.getAttribute("us");
    Collection<?>acquisti = null;
    
    if(session.getAttribute("admin")==null){ 
	    rend = "loginPageAdmin.jsp";
    }else if(username==null){
    	RequestDispatcher error = null;
		String header = "Server Error";
		String details = "username nullo...";
	    request.setAttribute("errorMessageHeader", header);
        request.setAttribute("errorMessageDetails", details);
        response.setStatus(500);
        error = getServletContext().getRequestDispatcher("/error.jsp");
        error.forward(request, response);	
    }else{
       acquisti = (Collection<?>) request.getAttribute("acquisti");
       if(acquisti == null){
    	   rend = "./userdetails?us="+username;
       }
   }   
    
   if(rend!=null){
	   response.sendRedirect(rend);
   }else{
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User details page</title>
		<link href="css/adminstyle.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="wapper"></div>
			<header>
		    	<jsp:include page="headerAdmin.jsp"></jsp:include>
		  	</header>
		  	<header class="visited-pages-header">
			    <jsp:include page="second-header.jsp"></jsp:include>
	    	</header>
	    	<div class="content">
	    		<div class="main">
					<h3>Ordini di <span class="userdetails-span"><%=username %></span></h3>
					<table border="1" class="table-acquisti">
					
							<tr>
								<th>Conto</th>
								<th>Data</th>
								<th>Totale</th>
								<th colspan=3>Indirizzo</th>
								
							</tr>
							
							<%
							    AcquistoBean bean = null;
								if (acquisti != null && acquisti.size() != 0) {
									Iterator<?> it = acquisti.iterator();
									while (it.hasNext()) {
									    bean = (AcquistoBean) it.next();
									  
							%>
							<tr class="acquisto-<%=bean.getID()%>">
								<td><%=bean.getConto().getIBAN()%></td>
								<td class="date-td"><%=bean.getDate()%></td>
								<td><%=bean.getImporto()%></td>
								<td>via <%=bean.getIndirizzo().getVia() %></td>
								<td>(<%=bean.getIndirizzo().getCivico() %>) </td>
								<td><%=bean.getIndirizzo().getCitta() %></td>
							</tr>
							<%
								}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
							%>
							<tr>
								<td class="no-acquisti" colspan=6><%=username %> non ha ancora effettuato acquisti</td>        
							</tr>
							
							<%}%>
							
							</table><br>
	    		</div>
	    	</div>


</body>
</html>

<%} %>
