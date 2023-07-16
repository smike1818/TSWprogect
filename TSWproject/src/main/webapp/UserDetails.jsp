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
<html lang="it">
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
	    	<div class="content">
	    		<div class="main">
					<h3>Ordini di <span class="userdetails-span"><%=username %></span></h3>
					 <div class="table-container table-acquisti">
					
							<div class="table-row th">
								<div class="table-cell">Conto</div>
								<div class="table-cell">Data</div>
								<div class="table-cell">Totale</div>
								<div class="table-cell">Indirizzo</div>
							</div>
							
							<%
							    AcquistoBean bean = null;
								if (acquisti != null && acquisti.size() != 0) {
									Iterator<?> it = acquisti.iterator();
									while (it.hasNext()) {
									    bean = (AcquistoBean) it.next();
									  
							%>
							<div class="table-row acquisto-<%=bean.getID()%>">
								<div class="table-cell"><label class="label-responsive">IBAN: </label><%=bean.getConto().getIBAN()%></div>
								<div class="table-cell"><label class="label-responsive">Data: </label><%=bean.getDate()%></div>
								<div class="table-cell"><label class="label-responsive">Importo: </label><%=bean.getImporto()%></div>
								<div class="table-cell"><label class="label-responsive">Indirizzo: </label>
								                        via <%=bean.getIndirizzo().getVia() %>
								                       <%=bean.getIndirizzo().getCivico() %>
								                       (<%=bean.getIndirizzo().getCitta() %>)</div>
							</div>
							<%
								}} else {                   //quando non si sono prodotti nel database stampo a video il mess sotto
							%>
							<div class="table-row">
								<div class="table-cell no-acquisti"><%=username %> non ha ancora effettuato acquisti</div>        
							<div>
							
							<%}%>
							
					</div>
	    		</div>
	    	</div>
	    	</div>
	    	</div>
</body>
</html>

<%} %>
