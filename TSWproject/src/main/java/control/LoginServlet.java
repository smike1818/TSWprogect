package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.ModelUserDAO;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
            HttpSession session = request.getSession();
            RequestDispatcher dispatcher = null;
            
			String userName = request.getParameter("un");
	        String password = request.getParameter("pw");

	        UserBean user = new UserBean();
	        user.setPassword(password);
	        user.setUsername(userName);
	        	            
            ModelUserDAO mud = new ModelUserDAO();
            
			try {
				//mi salvo il carrello nel caso l'utente effettui il login con un carrello pieno
				List<?>cart = (List<?>)session.getAttribute("cart");
				mud.doRetrieveByUsr(user);
				String userSession = (String) session.getAttribute("un");
				
				//verifico se lo username dell'utente è in sessione ed è 
				//anche uguale a quello inserito dall'utente, perchè
				//è possibile che egli voglia cambiare utenza
				
	            if(userSession!=null){
	            	if(userName.equalsIgnoreCase(userSession)) {
	            	  RequestDispatcher error = null;
					  String header = "Client Error";
					  String details = "already logged...";
			   	        request.setAttribute("errorMessageHeader", header);
				        request.setAttribute("errorMessageDetails", details);
				        response.setStatus(500);
				        error = getServletContext().getRequestDispatcher("/error.jsp");
				        error.forward(request, response);
	                }
				}
	            
	            session.setAttribute("cart", cart);
				session.setAttribute("un", userName);
		        session.setAttribute("pw",password);
		        
		        String page = (String)getServletContext().getAttribute("page");
		        
		        if(page==null || page.equalsIgnoreCase("acquisto.jsp")
		        		|| page.equalsIgnoreCase("indirizzo.jsp"))    page = "catalogo.jsp";
		        
	        	dispatcher = getServletContext().getRequestDispatcher("/"+page);
	        	dispatcher.forward(request, response);
				
			} catch (SQLException e) {
				RequestDispatcher error = null;
				String header = "Client Error";
				String details = "client not registered ...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
				
			}
			
			
        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
}


