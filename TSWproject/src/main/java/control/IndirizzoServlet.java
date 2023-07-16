package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.IndirizzoBean;
import bean.UserBean;
import model.ModelIndirizzoDAO;
import model.ModelUserDAO;
import dao.IndirizzoDAO;
import dao.UserDAO;

public class IndirizzoServlet extends HttpServlet{

	private static final long serialVersionUID = 1167295257272109384L;
	IndirizzoDAO model = new ModelIndirizzoDAO();
	IndirizzoBean ind = null;
	UserBean user = null;
	UserDAO usermodel = new ModelUserDAO();
	HttpSession session = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

   	    String action = (String) request.getParameter("action");
   	    session = request.getSession();
   	    String username = (String) session.getAttribute("un");
   	    
   	 try {
			user = usermodel.doRetrieveByUsr(username);
		} catch (SQLException e) {
			e.printStackTrace();
			 RequestDispatcher error = null;
		     String header = "Client Error";
		     String details = "utente non trovato, impossibile continuare il salvataggio dlel'indirizzo...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
   	   
   	    if(action!=null) {
   	    	if(action.equalsIgnoreCase("new")) {
   	    		ind = new IndirizzoBean();
   	    		
   	    	    String via = request.getParameter("via");
   			    Integer civico = Integer.parseInt(request.getParameter("civico"));
   			    String citta = request.getParameter("citta");
   	    		Integer cap = Integer.parseInt(request.getParameter("CAP"));
   	    		
   	    		ind.setVia(via);
   	    		ind.setCivico(civico);
   	    		ind.setCitta(citta);
   	    		ind.setCap(cap);  	    		  	    	
   	    		
   	    		ind.setCliente(user);
   	    		
   	    		try {
					model.doSave(ind);
				} catch (SQLException e) {
					e.printStackTrace();
					 RequestDispatcher error = null;
				     String header = "Client Error";
				     String details = "errori nel salvataggio dell'indirizzo...";
			   	        request.setAttribute("errorMessageHeader", header);
				        request.setAttribute("errorMessageDetails", details);
				        response.setStatus(500);
				        error = getServletContext().getRequestDispatcher("/error.jsp");
				        error.forward(request, response);
				}
   	    		
   	    	}
   	    	if(action.equalsIgnoreCase("delete")) {
   	    		
   	    	  String via = request.getParameter("via");
   		      Integer civico = Integer.parseInt(request.getParameter("civico"));
   			  String citta = request.getParameter("citta");
   			
   	    		try {
					model.doDelete(via,civico,citta);
				} catch (SQLException e) {
					e.printStackTrace();
					e.printStackTrace();
					 RequestDispatcher error = null;
				     String header = "Client Error";
				     String details = "errore nella cancellazione dell'indirizzo...";
			   	        request.setAttribute("errorMessageHeader", header);
				        request.setAttribute("errorMessageDetails", details);
				        response.setStatus(500);
				        error = getServletContext().getRequestDispatcher("/error.jsp");
				        error.forward(request, response);
				}
   	    	}
   	    	if(action.equalsIgnoreCase("prefer")) {
   	    	  String via = request.getParameter("via");
  		      Integer civico = Integer.parseInt(request.getParameter("civico"));
  			  String citta = request.getParameter("citta");
  			
	    	try {
				model.doPrefer(via,civico,citta,user);
			} catch (SQLException e) {
				e.printStackTrace();
				e.printStackTrace();
				 RequestDispatcher error = null;
			     String header = "Client Error";
			     String details = "errore nella scelta dell'indirizzo...";
		   	        request.setAttribute("errorMessageHeader", header);
			        request.setAttribute("errorMessageDetails", details);
			        response.setStatus(500);
			        error = getServletContext().getRequestDispatcher("/error.jsp");
			        error.forward(request, response);
			}
   	    	}
   	    }
		   	  
   	    try {
			request.setAttribute("indirizzo", model.doRetrieveAll("via",user.getCF()));			
		} catch (SQLException e) {
			 e.printStackTrace();
		     RequestDispatcher dispatcher = null;   
			 dispatcher = getServletContext().getRequestDispatcher("/500.jsp");
			 dispatcher.forward(request, response);
		}
   	    
   	    RequestDispatcher dispatcher = null;   
	    dispatcher = getServletContext().getRequestDispatcher("/Indirizzo.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
