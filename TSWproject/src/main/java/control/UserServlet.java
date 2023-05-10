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
	        	 mud.doRetrieveByUsr(user);
	        	 session.setAttribute("user-details", user);
	        	 
	        }catch(SQLException e) {
	        	RequestDispatcher error = null;
				String header = "Server Error";
				String details = "errore nel caricamento della pagina...";
				response.setStatus(500);
				error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
				error.forward(request, response);
	        }
	        
	        response.sendRedirect("User.jsp");

        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

}
