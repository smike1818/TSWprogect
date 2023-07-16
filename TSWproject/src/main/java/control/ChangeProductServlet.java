package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDAO;
import model.MusicalModelArticoloBean;

public class ChangeProductServlet extends HttpServlet{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7840574685133744L;
	private static final Logger LOGGER = Logger.getLogger(ChangeProductServlet.class.getName());
	
	ArticoloDAO model = new MusicalModelArticoloBean();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        String action = request.getParameter("action");
        
        if(action!=null) {
        	if(action.equalsIgnoreCase("ajax")) {

                String newText = request.getParameter("newText");
                String field = request.getParameter("parameter");
                Integer id = Integer.parseInt(request.getParameter("id"));
                // Validate the newText parameter
                if (newText == null || newText.trim().isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                ArticoloDAO mdu = new MusicalModelArticoloBean();
                try {
        			 mdu.modifyProduct(newText, field, id);    
        		} catch (SQLException e) {
        			LOGGER.log(null, "context", e);
        		}
            }
        }
        
		String ident = request.getParameter("id");
		Integer id = -1;

		if (ident != null && !ident.isEmpty()) {
		  try {
		    id = Integer.parseInt(ident);
		  } catch (NumberFormatException e) {
		      RequestDispatcher error = null;
		      String header = "Client Error";
		      String details = "Errore nell'apertura della pagina...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		        error.forward(request, response);
		    }
		  }else {
			  RequestDispatcher error = null;
		      String header = "Client Error";
		      String details = "Errore nell'apertura della pagina...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		        error.forward(request, response);
		  }

   	    try {
			request.setAttribute("bean", model.doRetrieveByKey(id));
		} catch (SQLException e) {
			RequestDispatcher error = null;
		      String header = "Client Error";
		      String details = "errore nel mostrare il prodotto...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		        error.forward(request, response);
		}
   	    
   	    RequestDispatcher dispatcher = null;   
	    dispatcher = getServletContext().getRequestDispatcher("/ChangeProduct.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
