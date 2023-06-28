package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ArticoloBean;
import bean.CategoriaBean;
import bean.IvaBean;
import dao.ArticoloDAO;
import dao.CategoriaDAO;
import dao.ImageDAO;
import dao.IvaDAO;
import model.ModelCategoriaDAO;
import model.ModelImageDAO;
import model.MusicalModelArticoloBean;
import model.ModelIvaDAO;

public class InsertAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 123131L;
	ArticoloDAO articoli = new MusicalModelArticoloBean();
	CategoriaDAO categorie = new ModelCategoriaDAO();
	ImageDAO images = new ModelImageDAO();
	IvaDAO ivamodel = new ModelIvaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action!=null) {
			 if(action.equalsIgnoreCase("add")) {    //[AMMINISTRATORE] gestione insert nel db
		    	  ArticoloBean ab = new ArticoloBean();
		    	  String choise = null;
		    	  
		    	  choise = request.getParameter("choise");
		    	  ab.setID(Integer.parseInt(request.getParameter("id")));
		    	  ab.setColore(request.getParameter("colore"));
		    	  ab.setNome(request.getParameter("name"));
		    	  ab.setTipologia(request.getParameter("tipologia"));
		    	  ab.setDescrizione(request.getParameter("descrizione"));
		    	  ab.setMarca(request.getParameter("marca"));
		    	  ab.setQuantita(Integer.parseInt(request.getParameter("quantity")));
		    	  ab.setPrezzo(Double.parseDouble(request.getParameter("price")));
		    	  
		    	  IvaBean iva = null;
		    	  
		    	  //aggiunta iva dal database
		    	  try {
					iva = ivamodel.getIvaByModel();
				} catch (SQLException e) {
					RequestDispatcher error = null;
	    			String header = "Server Error";
	    			String details = "errore nel prendere l'iva...";
	    			response.setStatus(500);
	    			error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			error.forward(request, response);
					e.printStackTrace();
				}
		    	  if(iva!=null) {
		    		  ab.setIva(iva);
		    		  System.out.print(iva.getPercentuale());
		    	  }
		    	  
		    	  if(choise.equalsIgnoreCase("strumento")) {
		    	      ab.setTipo(0);
		    	      ab.setCorde(Integer.parseInt(request.getParameter("corde")));
		    	  } else 
		    		  ab.setTipo(1);
		    	  CategoriaBean cat = new CategoriaBean();
		    	  cat.setID(Integer.parseInt(request.getParameter("categoria")));
		    	  
		    	  ab.setCategoria(cat); 	             
		    	  		    	 
		    	  
		    	  try {
					articoli.doSave(ab);
				  }catch (SQLException e) {
					     RequestDispatcher error = null;
		    			 String header = "Server Error";
		    			 String details = "errore nel salvataggio dell'articolo, riprova...";
		    			 response.setStatus(500);
		    			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
		    			 error.forward(request, response);
				  }
		      }
			 
			 if(action.equalsIgnoreCase("cat")) {
				 CategoriaBean cat = new CategoriaBean();
				 cat.setNome(request.getParameter("name"));
				 cat.setDescrizione(request.getParameter("descrizione"));
				 String tipo = request.getParameter("tipo");
				 cat.setTipo(tipo.equalsIgnoreCase("strumento"));
				 
				 try {
					categorie.doSave(cat);
				 } catch (SQLException e) {
					  e.printStackTrace();
					  request.setAttribute("error-statement", "nome categoria gi� inserito, riprova");
				 }
				 
			 }
		}
		
	    String sort="codice";
	
	    try {
		    request.setAttribute("products",  articoli.doRetrieveAll(sort));
		    request.setAttribute("categories", categorie.doRetrieveAll(sort));
	    } catch (SQLException e) {
	    	 RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "errore nel caricamento del catalogo, riprova...";
 			 response.setStatus(500);
 			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
 			 error.forward(request, response);
	   }
	    
	    RequestDispatcher dispatcher = null;
	    dispatcher = getServletContext().getRequestDispatcher("/InsertAdmin.jsp");
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
