package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.AcquistoDAO;
import dao.UserDAO;
import model.ModelAcquistoDAO;
import model.ModelUserDAO;

public class UserDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 4381135502932341L;
	
	private String username = null;
	private UserDAO userdao = null;
	private AcquistoDAO acquistodao = null;
	private UserBean user = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		    
		    username = request.getParameter("us");
		    userdao = new ModelUserDAO();
		    acquistodao = new ModelAcquistoDAO();
		    
		    if(username!=null) {
		    
		    try {
				user = userdao.doRetrieveByUsr(username);
			} catch (SQLException e) {
				RequestDispatcher error = null;
				String header = "Server Error";
				String details = "utente non trovato...";
				response.setStatus(500);
				error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
				error.forward(request, response);		
			}
		   
		    try {
				request.setAttribute("acquisti", acquistodao.doRetrieveByUser(user.getCF()));
			} catch (SQLException e) {
				RequestDispatcher error = null;
				String header = "Server Error";
				String details = "errore nella stampa degli acquisti...";
				response.setStatus(500);
				error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
				error.forward(request, response);	
			}
		    
		    }else {
		    	RequestDispatcher error = null;
				String header = "Server Error";
				String details = "username nullo...";
				response.setStatus(500);
				error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
				error.forward(request, response);	
		    }
		    
	        
	        RequestDispatcher dispatcher = null;		    	   
		    dispatcher = getServletContext().getRequestDispatcher("/UserDetails.jsp");
		    dispatcher.forward(request, response);
		  }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

}
