package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.UserDAO;
import model.ModelUserDAO;

public class UserListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1012L;
	UserDAO mud = null;
	Collection<String> suggerimenti = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		    
		    String action = request.getParameter("action");
		    mud = new ModelUserDAO(); 
		    
		    if(action!=null) {
		    	if(action.equalsIgnoreCase("autocomplete")) {
		    		
		    		//setto la risposta come stringa JSON
		    		response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            
		            //prendo la stringa scritta dall'utente
		    		String value = request.getParameter("data");
		    		if(value!=null )
						try {
							//username che matchano con value
							suggerimenti = mud.doRetrieveByIncompleteUsr(value,"username");
							
						} catch (SQLException e) {
							
							 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'esecuzione della query");
						}       
		    		if(suggerimenti!=null) {
		    			response.getWriter().write(new Gson().toJson(suggerimenti));   //da Collection a stringa JSON che inserisco in response
		    		}
		    	}
		    }else {	        
	        
	        try {
	        	  request.setAttribute("users", mud.doRetrieveAll("username"));	 //ordino la lista di utenti per username
	        }catch(SQLException e) {
	        	RequestDispatcher error = null;
				String header = "Server Error";
				String details = "errore nel caricamento degli utenti...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		        error.forward(request, response);
	        }
	        
	        RequestDispatcher dispatcher = null;		    	   
		    dispatcher = getServletContext().getRequestDispatcher("/UserList.jsp");
		    dispatcher.forward(request, response);
		  }
        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	

}
