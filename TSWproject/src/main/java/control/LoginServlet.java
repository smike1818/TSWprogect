package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.ModelUserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Get the form data from request parameters
		String userName = request.getParameter("un");
        String password = request.getParameter("pw");
        
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("un") != null) {
        	response.sendRedirect("error.jsp");
        }
        else {
        // If form data is valid, create a new user and redirect to success page
            UserBean user = new UserBean();
            user.setPassword(password);
            user.setUsername(userName);
            
            ModelUserDAO mud = new ModelUserDAO();
			try {
				mud.doRetrieveByUsr(user);
	            response.sendRedirect("carrello.jsp");
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				response.sendRedirect("invalidLogin.jsp");
				e.printStackTrace();
			}
        }
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			doGet(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


