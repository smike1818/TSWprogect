<%@ page language="java" import="java.util.*, bean.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    String rend = null;
    Collection<?> users = null;
    
    if(session.getAttribute("admin")==null){ 
	    rend = "loginPageAdmin.jsp";
    }else{
        users = (Collection<?>) request.getAttribute("users");
        if(users==null) {
    	   rend = "./users";
        }
    }
    
    if(rend!=null){
    	response.sendRedirect(rend);
    	return;
    }else{
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Lista Utenti</title>
<link href="css/adminstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
  <header>
    <jsp:include page="headerAdmin.jsp"></jsp:include>
  </header>
  
  <br><span class="error-statement"></span>
  <!-- BARRA DI RICERCA -->
  
   <br><div class="search-user">
       <label for="username">Scrivi Username: </label><br>
       <div class="dropdown">
         <input type="text" id="search-user-input" ><br>
         <ul id="search-user-suggestions"></ul>
       </div> 
       <button id="search-user-submit">search</button>
      </div>
      
   <!-- LISTA DEGLI UTENTI REGISTRATI -->
  
   <br><div class="user-list-block">
    <fieldset class="box-list">
     <legend>Lista Utenti</legend>
      <ul class="user-list">
        
        <%
            UserBean bean = null;
		    if (users != null && users.size() != 0) {
			   Iterator<?> it = users.iterator();
			   while (it.hasNext()) {
			       bean = (UserBean) it.next();
        %>
              <li>
                 <span class="username-li"><%=bean.getUsername() %></span>
                 <span class="complete-name">(<%=bean.getNome() %> <%=bean.getCognome() %>)</span>
              </li>
        <%}}else{ %>
            <li> non ci sono utenti registrati</li>
          <%} %>
      </ul>
    </fieldset>
   </div>
   
   <script src="js/JQuery.js" type="text/javascript"></script>
   <script src="js/UserList.js" type="text/javascript"></script>
      
</body>
</html>

<% } %>