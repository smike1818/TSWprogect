package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
	ContoDAO model = new ModelContoDAO();
	UserDAO us = new ModelUserDAO();
	UserBean user = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");    //action salva l'azione effettuata dall'utente
		ServletContext sc = request.getServletContext();
		HttpSession session = request.getSession();
		
		try {
		    user = us.doRetrieveByUsr((String) session.getAttribute("un"));
		} catch (SQLException e) {
			e.printStackTrace();
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "errore nel salvataggio della carta...";
			 response.setStatus(500);
			 error = sc.getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
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
						 e.printStackTrace();
						 RequestDispatcher error = null;
		    			 String header = "Server Error";
		    			 String details = "errore nel salvataggio della carta, controlla se gia' hai salvato la carta...";
		    			 response.setStatus(500);
		    			 error = sc.getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
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
					e.printStackTrace();
					e.printStackTrace();
					 RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "errore nella cancellazione della carta...";
	    			 response.setStatus(500);
	    			 error = sc.getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
				}
			}
			if(action.equalsIgnoreCase("prefer")) {
				String iban = request.getParameter("IBAN");
				try {
					model.doPrefer(iban,user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 e.printStackTrace();
					 RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "errore nella scelta della carta...";
	    			 response.setStatus(500);
	    			 error = sc.getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
				}
			}
		}
		
		
		String order = "IBAN";
   	    try {
			request.setAttribute("cards", model.doRetrieveAll(order));
		} catch (SQLException e) {
			 e.printStackTrace();
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "c'e' stato un errore nella stampa dei conti...";
			 response.setStatus(500);
			 error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
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
