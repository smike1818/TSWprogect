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
import dao.UserDAO;
import dao.AcquistoDAO;
import model.ModelUserDAO;
import model.ModelAcquistoDAO;


public class StoricoUserServlet extends HttpServlet {

	UserDAO model = new ModelUserDAO();
	AcquistoDAO modelacquisto = new ModelAcquistoDAO();
	UserBean user = null;
	HttpSession session = null;
	private static final long serialVersionUID = -6690581543480264L;
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		session = request.getSession();
		String username = (String) session.getAttribute("un");
		
		if(username!=null) {

		  try {
			  user = model.doRetrieveByUsr(username);
		  } catch (SQLException e) {
			  e.printStackTrace();	
			  RequestDispatcher error = null;
 		       String header = "Server Error";
 		       String details = "errore nel reperire l'utente dal database...";
 		        response.setStatus(500);
 		        error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
 		        error.forward(request, response);
		  }
		
		}else{
			 RequestDispatcher dispatcher = null;   
			 dispatcher = getServletContext().getRequestDispatcher("/LoginPageUtente.jsp");
			 dispatcher.forward(request, response);
		}

		
		if(user!=null) {
			try {
				request.setAttribute("storico", modelacquisto.doRetrieveByUser(user.getCF()));
			} catch (SQLException e) {				
				e.printStackTrace();
				 RequestDispatcher error = null;
	 		       String header = "Server Error";
	 		       String details = "errore nel reperire gli ordini dal database...";
	 		        response.setStatus(500);
	 		        error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
	 		        error.forward(request, response);

			}
		}else{
			    RequestDispatcher error = null;
		        String header = "Server Error";
		        String details = "utente non trovato...";
		        response.setStatus(401);
		        error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
		        error.forward(request, response);
		}
		
		  RequestDispatcher dispatcher = null;   
	       dispatcher = getServletContext().getRequestDispatcher("/Storico.jsp");
	       dispatcher.forward(request, response);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

}
