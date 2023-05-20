package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action!=null) {
			
			if(action.equalsIgnoreCase("deletefromcatalogo")) {
				int code = Integer.parseInt(request.getParameter("id"));
				try {
					model.doDelete(code);
					imagemodel.doDeleteAll(code);
				} catch (SQLException e) {
					  RequestDispatcher error = null;
		    			 String header = "Server Error";
		    			 String details = "c'è stato un errore nella cancellazione del catalogo...";
		    			 response.setStatus(500);
		    			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
		    			 error.forward(request, response);
				}
				
			}if(action.equalsIgnoreCase("deletefromcategorie")) {
				int code = Integer.parseInt(request.getParameter("id"));
				try {
					categ.doDelete(code);
				} catch (SQLException e) {
					  RequestDispatcher error = null;
		    			 String header = "Server Error";
		    			 String details = "c'è stato un errore nella cancellazione delle categorie...";
		    			 response.setStatus(500);
		    			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
		    			 error.forward(request, response);
				}
			}
		}
		
	    String sort="codice";
	
	    try {
		    request.setAttribute("products",  model.doRetrieveAll(sort));
	    } catch (SQLException e) {
	    	 RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "c'è stato un errore nella mostra del catalogo...";
 			 response.setStatus(500);
 			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
 			 error.forward(request, response);
	   }
	    
	    try {
		    request.setAttribute("categories",  categ.doRetrieveAll(sort));
	    } catch (SQLException e) {
	    	  RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "c'è stato un errore, nella mostra delle categorie...";
 			 response.setStatus(500);
 			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
 			 error.forward(request, response);
	   }
	    
	    RequestDispatcher dispatcher = null;
	    
	    String page = (String) getServletContext().getAttribute("page");	   
	    dispatcher = getServletContext().getRequestDispatcher("/"+page);
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
