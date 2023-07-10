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

import bean.ArticoloBean;
import dao.ArticoloDAO;
import dao.CategoriaDAO;
import dao.ImageDAO;
import model.ModelCategoriaDAO;
import model.ModelImageDAO;
import model.MusicalModelArticoloBean;

public class CatalogoServlet extends HttpServlet {

	private static final long serialVersionUID = 123131L;
	ArticoloDAO model = new MusicalModelArticoloBean();
	CategoriaDAO categ = new ModelCategoriaDAO();
	ImageDAO imagemodel = new ModelImageDAO();
	Collection<String> suggerimenti = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String auto = request.getParameter("auto");
		
		if(auto!=null) {
	    	if(auto.equalsIgnoreCase("autocomplete")) {
	    		
	    		//setto la risposta come stringa JSON
	    		response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            
	            //prendo la stringa scritta dall'utente
	    		String value = request.getParameter("data");
	    		String id = request.getParameter("key");
	    		
	    		//controllo per quando clicco sul bottone
	    		if(id!=null) {
	    			int key = Integer.parseInt(id);
	    			try {
						ArticoloBean test = model.doRetrieveByKey(key);
						if(!test.getNome().equalsIgnoreCase(value))
							response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "chiave e nome non corrispondenti");
					} catch (SQLException e) {
						response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'esecuzione della query");
					}
	    		}
	    		
	    		if(value!=null)
					try {
						//username che matchano con value
						suggerimenti = model.doRetrieveByIncompleteProduct(value);
						
					} catch (SQLException e) {
						
						 response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'esecuzione della query");
					}       
	    		if(suggerimenti!=null) {
	    			response.getWriter().write(new Gson().toJson(suggerimenti));   //da Collection a stringa JSON che inserisco in response
	    		}
	    	}
	    	if(auto.equalsIgnoreCase("idByName")) {
	    		
	    	}
	    }else {	 
		
		if(action!=null) {
				
			if(action.equalsIgnoreCase("deletefromcategorie")) {
				int code = Integer.parseInt(request.getParameter("id"));
				try {
					categ.doDelete(code);
				} catch (SQLException e) {
					     e.printStackTrace();
					     RequestDispatcher error = null;
		    			 String header = "Server Error";
		    			 String details = "c'e' stato un errore nella cancellazione delle categorie...";
		    	   	        request.setAttribute("errorMessageHeader", header);
		    		        request.setAttribute("errorMessageDetails", details);
		    		        response.setStatus(500);
		    		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		    		        error.forward(request, response);
				}
			}
	    }
	
	    String sort="codice";
	
	    try {
		    request.setAttribute("products",  model.doRetrieveAll(sort));
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    	 RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "c'e' stato un errore nella mostra del catalogo...";
    	        request.setAttribute("errorMessageHeader", header);
    	        request.setAttribute("errorMessageDetails", details);
    	        response.setStatus(500);
    	        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
    	        error.forward(request, response);
	   }
	    
	    try {
		    request.setAttribute("categories",  categ.doRetrieveAll(sort));
	    } catch (SQLException e) {
	    	 e.printStackTrace();
	    	 RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "c'è stato un errore, nella mostra delle categorie...";
    	        request.setAttribute("errorMessageHeader", header);
    	        request.setAttribute("errorMessageDetails", details);
    	        response.setStatus(500);
    	        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
    	        error.forward(request, response);
	   }
	    
	    RequestDispatcher dispatcher = null;
	    
	    String page = (String) getServletContext().getAttribute("page");	   
	    dispatcher = getServletContext().getRequestDispatcher("/"+page);
	    dispatcher.forward(request, response);
	    
	}}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
