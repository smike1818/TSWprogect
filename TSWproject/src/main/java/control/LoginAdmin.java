package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.ModelUserDAO;

public class LoginAdmin extends HttpServlet {

	private static final long serialVersionUID = 10000L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
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
					mud.doRetrieveByPermit(user);
					response.sendRedirect("AdminControl.jsp");
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				response.sendRedirect("loginPageAdmin.jsp");
			}
        }
        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
}


