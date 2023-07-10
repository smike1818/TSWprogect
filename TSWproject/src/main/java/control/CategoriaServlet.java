package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.ModelCategoriaDAO;
import bean.CategoriaBean;


public class CategoriaServlet extends HttpServlet{
	
	private static final long serialVersionUID = 5990937487736664991L;
	CategoriaDAO model = new ModelCategoriaDAO();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tipo = request.getParameter("tipo");
		if(tipo==null) {
			    RequestDispatcher error = null;
		        String header = "Client Error";
		        String details = "categorie null...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}else if(tipo.equalsIgnoreCase("")) {
			    RequestDispatcher error = null;
		        String header = "Client Error";
		        String details = "categorie emptys...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
		

		//prendo type solo se è un valore valido
		boolean type = Boolean.parseBoolean(tipo);
		
   	   
	   try {
		   List<CategoriaBean> cat = (List<CategoriaBean>) model.doRetrieveAll("");
		   List<CategoriaBean> risultatoFiltrato = cat.stream()
		       .filter(acquisto -> acquisto.getTipo() == type)
		       .collect(Collectors.toList());

		   request.setAttribute("categorieFiltrate", risultatoFiltrato);

	} catch (SQLException e) {
		    RequestDispatcher error = null;
	        String header = "Server Error";
	        String details = "errore nella mostra delle categorie...";
   	        request.setAttribute("errorMessageHeader", header);
	        request.setAttribute("errorMessageDetails", details);
	        response.setStatus(500);
	        error = getServletContext().getRequestDispatcher("/error.jsp");
	        error.forward(request, response);
	}
   	   
   	   RequestDispatcher dispatcher = null;   
       dispatcher = getServletContext().getRequestDispatcher("/categorie.jsp");
       dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

