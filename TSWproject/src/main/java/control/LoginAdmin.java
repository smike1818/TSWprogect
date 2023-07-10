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

public class LoginAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public LoginAdmin() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String userName = request.getParameter("un");
        String password = request.getParameter("pw");
        
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("admin") != null) {
        	 RequestDispatcher error = null;
			 String header = "Client Error";
			 String details = "admin already logged...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		        error.forward(request, response);
        }
        else {
        // If form data is valid, create a new user and redirect to success page
            UserBean user = new UserBean();
            user.setPassword(password);
            user.setUsername(userName);
            
            ModelUserDAO mud = new ModelUserDAO();
			try {
					mud.doRetrieveByPermit(user);
					session.setAttribute("admin", true);
					response.sendRedirect("CatalogoAdmin.jsp");
			} catch (SQLException e) {
				response.sendRedirect("loginPageAdmin.jsp");
			}
        }
        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
}


