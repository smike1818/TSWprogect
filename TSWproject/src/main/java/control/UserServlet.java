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

public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 105L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		    HttpSession session = request.getSession();
		    
		    String userName = (String) session.getAttribute("un");
		    String password = (String) session.getAttribute("pw");
		    
	        UserBean user = new UserBean();
	        user.setPassword(password);
	        user.setUsername(userName);
	        
	        ModelUserDAO mud = new ModelUserDAO();
	        
	        try {
	        	 user = mud.doRetrieveByUsr(userName);
	        	 session.setAttribute("user-details", user);
	        	 response.sendRedirect("User.jsp");
	        }catch(SQLException e) {
	        	RequestDispatcher error = null;
				String header = "Server Error";
				String details = "errore nel caricamento della pagina...";
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
