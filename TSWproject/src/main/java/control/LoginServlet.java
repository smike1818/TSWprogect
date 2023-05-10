package control;

import java.io.IOException;
import java.sql.SQLException;

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
            
			String userName = request.getParameter("un");
	        String password = request.getParameter("pw");
	        String action = (String) getServletContext().getAttribute("buyAfterLogin");

	        UserBean user = new UserBean();
	        user.setPassword(password);
	        user.setUsername(userName);
	        	            
            ModelUserDAO mud = new ModelUserDAO();
            
			try {
				
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
					  response.setStatus(400);
					  error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
					  error.forward(request, response);
	                }
				}
	            
				session.setAttribute("un", userName);
		        session.setAttribute("pw",password);
		        
		        if(action!=null) {
		         if(action.equalsIgnoreCase("buy")) {
		        	RequestDispatcher dispatcher = null;
		        	dispatcher = getServletContext().getRequestDispatcher("/product?action="+action);
		        	dispatcher.forward(request, response);
		         }
		        }
		        
				response.sendRedirect("catalogo.jsp");
				
			} catch (SQLException e) {
				
				RequestDispatcher error = null;
				String header = "Client Error";
				String details = "client not registered ...";
				response.setStatus(400);
				error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
				error.forward(request, response);
				
			}
			
			
        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
}


