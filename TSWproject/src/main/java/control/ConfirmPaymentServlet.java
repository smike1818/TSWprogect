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
import dao.ContoDAO;
import dao.IndirizzoDAO;
import dao.UserDAO;
import model.ModelUserDAO;
import model.ModelContoDAO;
import model.ModelIndirizzoDAO;

public class ConfirmPaymentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4747845724221617L;
	
	ContoDAO modelconto = new ModelContoDAO();
	IndirizzoDAO modelind = new ModelIndirizzoDAO();
	UserDAO modeluser = new ModelUserDAO();
	UserBean user = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("un");
		
		try {
			 user = modeluser.doRetrieveByUsr(username);
			 System.out.print(user.getCF());
		}catch(SQLException e) {
			 e.printStackTrace();
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "c'e' stato un errore nella corrispondenza con l'utente in sessione...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
		
		
   	    try {
			request.setAttribute("cards", modelconto.doRetrieveByUsr(user));
			request.setAttribute("list-address", modelind.doRetrieveByUsr(user));
		} catch (SQLException e) {
			 e.printStackTrace();
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "c'e' stato un errore nel mostrare la pagina...";
   	            request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
   	    
   	    RequestDispatcher dispatcher = null;   
	    dispatcher = getServletContext().getRequestDispatcher("/ConfirmPayment.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
