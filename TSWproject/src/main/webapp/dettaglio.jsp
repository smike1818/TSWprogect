<%@ page language="java" import="bean.ArticoloBean" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
      ServletContext sc = this.getServletContext();
      sc.setAttribute("page","details");
      ArticoloBean bean = (ArticoloBean) request.getAttribute("bean");
      if(bean==null){
          String id = request.getParameter("id");
          response.sendRedirect("./product?action=Details&id="+id);
      }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    
    <!-- inizio corpo dettaglio -->
    <% if(bean!=null){ %>
    <div align="center">
       <div>
         <img src="data:image/*;base64,<%= bean.getImmagine() %>" alt="no available" width="500" height="500"/>
       </div>
       <div>
         <label for="name">Nome:  </label>
         <span><%=bean.getNome() %></span><br>
       </div>
       <div>
         <label for="descrizione">Descrizione:  </label>
         <p><%=bean.getDescrizione() %></p>
       </div>
       <div>
         <label for="prezzo">Prezzo:  </label>
         <span><%=bean.getPrezzo() %></span><br>
       </div>
       <div>
         <label for="colore">Colore:  </label>
         <span><%=bean.getColore() %></span><br>
       </div>
       <div>
          <label for="tipologia">Tipologia:  </label>
         <span><%=bean.getTipologia() %></span><br>
       </div>
       <div>
         <label for="marca">Marca:  </label>
         <span><%=bean.getPrezzo() %></span><br>
       </div>
    </div>
    <%} %>
    <!-- fine corpo dettaglio -->
    
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>