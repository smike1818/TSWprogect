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
import dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginControl {
	public class LoginServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	   
	    public void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
				UserBean user = new UserBean();
				user.setUsername(request.getParameter("un"));
				user.setPassword(request.getParameter("pw"));
			try {
				 //implementazione del DAO per prendere l'utente dal db
				//da errore su SQLException perchè non c'è scritto nulla nel try che genera 
				//un SQLException
			}catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
			
			if(user.isValid()) {
			HttpSession session= request.getSession(true);
			session.setAttribute("currentSessionUser",user);
			response.sendRedirect("userLogged.jsp");
			}
			else 
				response.sendRedirect("invalidLogin.jsp");
	    }
						
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			doGet(request, response);
		}

	}

}
