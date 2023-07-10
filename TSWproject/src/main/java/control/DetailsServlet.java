package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticoloDAO;
import model.MusicalModelArticoloBean;

public class DetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 7840574076685133744L;
	ArticoloDAO model = new MusicalModelArticoloBean();
	
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ident = request.getParameter("id");
		Integer id;

		if (ident != null && !ident.isEmpty()) {
		  try {
		    id = Integer.parseInt(ident);
		    getServletContext().setAttribute("ident", id);
		  } catch (NumberFormatException e) {
		    // Gestisci il caso in cui la stringa non possa essere convertita in un numero
		    id = (Integer) getServletContext().getAttribute("ident");
		    if (id == null) {
		      RequestDispatcher error = null;
		      String header = "Client Error";
		      String details = "Errore nell'apertura della pagina...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		    }
		  }
		} else {
		  id = (Integer) getServletContext().getAttribute("ident");
		  if (id == null) {
		    RequestDispatcher error = null;
		    String header = "Client Error";
		    String details = "Errore nell'apertura della pagina...";
   	        request.setAttribute("errorMessageHeader", header);
	        request.setAttribute("errorMessageDetails", details);
	        response.setStatus(500);
	        error = getServletContext().getRequestDispatcher("/error.jsp");
	        error.forward(request, response);
		  }
		}

   	     try {
			request.setAttribute("bean", model.doRetrieveByKey(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	    
   	    RequestDispatcher dispatcher = null;   
	    dispatcher = getServletContext().getRequestDispatcher("/dettaglio.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
