package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ContoBean;
import bean.UserBean;
import dao.ContoDAO;
import dao.UserDAO;
import model.ModelContoDAO;
import model.ModelUserDAO;

public class CardsServlet extends HttpServlet{

	private static final long serialVersionUID = 7840574076685133744L;
	private static final Logger LOGGER = Logger.getLogger(AcquistoServlet.class.getName());
	
	ContoDAO model = new ModelContoDAO();
	UserDAO us = new ModelUserDAO();
	UserBean user = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");    //action salva l'azione effettuata dall'utente
		HttpSession session = request.getSession();
		
		try {
		    user = us.doRetrieveByUsr((String) session.getAttribute("un"));
		} catch (SQLException e) {
			 LOGGER.log(null, "context", e);
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "errore nel salvataggio della carta...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
		
		if(action!=null) {
			if(action.equalsIgnoreCase("add")) {
				String num_carta = request.getParameter("number-card");
				String iban = request.getParameter("IBAN");
				String cvv = request.getParameter("cvv");
				
				ContoBean bean = new ContoBean();
				bean.setCvv(cvv);
				bean.setIBAN(iban);
				bean.setNumCarta(num_carta);				
				
				if(user!=null) {
					bean.setIntestatario(user);
					try {
						model.doSave(bean);
					} catch (SQLException e) {
						 LOGGER.log(null, "context", e);
						 RequestDispatcher error = null;
		    			 String header = "Server Error";
		    			 String details = "errore nel salvataggio della carta, controlla se gia' hai salvato la carta...";
		    	   	        request.setAttribute("errorMessageHeader", header);
		    		        request.setAttribute("errorMessageDetails", details);
		    		        response.setStatus(500);
		    		        error = getServletContext().getRequestDispatcher("/error.jsp");
		    		        error.forward(request, response);
					}
				}
					
				
			}
			if(action.equalsIgnoreCase("delete")) {
				String iban = request.getParameter("IBAN");
				try {
					model.doDelete(iban);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 LOGGER.log(null, "context", e);
					 RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "errore nella cancellazione della carta...";
	    	   	        request.setAttribute("errorMessageHeader", header);
	    		        request.setAttribute("errorMessageDetails", details);
	    		        response.setStatus(500);
	    		        error = getServletContext().getRequestDispatcher("/error.jsp");
	    		        error.forward(request, response);
				}
			}
			if(action.equalsIgnoreCase("prefer")) {
				String iban = request.getParameter("IBAN");
				try {
					model.doPrefer(iban,user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 LOGGER.log(null, "context", e);
					 RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "errore nella scelta della carta...";
	    	   	        request.setAttribute("errorMessageHeader", header);
	    		        request.setAttribute("errorMessageDetails", details);
	    		        response.setStatus(500);
	    		        error = getServletContext().getRequestDispatcher("/error.jsp");
	    		        error.forward(request, response);
				}
			}
		}
		
		
		String order = "IBAN";
   	    try {
			request.setAttribute("cards", model.doRetrieveAll(order,user.getCF()));
		} catch (SQLException e) {
			 LOGGER.log(null, "context", e);
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "c'e' stato un errore nella stampa dei conti...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
   	    
   	    RequestDispatcher dispatcher = null;   
	    dispatcher = getServletContext().getRequestDispatcher("/cardsPage.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
