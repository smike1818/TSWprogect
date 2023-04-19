package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.ArticoloBean;
import model.MusicalModel;
import model.MusicalModelIDS;

@MultipartConfig
public class MusicalControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 102831973239L;
	MusicalModel model = new MusicalModelIDS();
	
	public MusicalControl(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		ServletContext sc = request.getServletContext();
		String tipo = null;
		
		try {
		   if(action != null) {
		      if(action.equalsIgnoreCase("choise")){
		    	  tipo = request.getParameter("type");
				  sc.setAttribute("tipo", tipo);
		    	  if(tipo.equalsIgnoreCase("strumento"))
		    		  sc.setAttribute(action,0);
		    	  else
		    	      sc.setAttribute(action,1);
		      }
		      if(action.equalsIgnoreCase("add")) {
		    	  //da aggiungere immagine
		    	  ArticoloBean ab = new ArticoloBean();
		    	  ab.setID(sc.getAttribute("idArticolo"));
		    	  ab.setColore(request.getParameter("colore"));
		    	  ab.setNome(request.getParameter("name"));
		    	  ab.setTipologia(request.getParameter("tipologia"));
		    	  ab.setDescrizione(request.getParameter("descrizione"));
		    	  ab.setMarca(request.getParameter("marca"));
		    	  ab.setQuantita(Integer.parseInt(request.getParameter("quantity")));
		    	  ab.setPrezzo(Double.parseDouble(request.getParameter("price")));
		    	  if(((String) sc.getAttribute("tipo")).equalsIgnoreCase("strumento")) {
		    	      ab.setTipo(0);
		    	      ab.setCorde(Integer.parseInt(request.getParameter("corde")));
		    	  } else 
		    		  ab.setTipo(1);
		    	  Part filePart = request.getPart("image"); // "file" è il nome dell'elemento di input del form
		    	  InputStream inputStream = filePart.getInputStream();
		    	  ab.setImage(inputStream);
		    	  model.doSave(ab);
		      }
		      if(action.equalsIgnoreCase("delete")) {
		    	  int id = Integer.parseInt(request.getParameter("id"));
				  model.doDelete(id);
		      }
			 
		   }	
		}catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		String sort="codice";
		
		try {
			request.setAttribute("products",  model.doRetrieveAll(sort));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String page = (String) sc.getAttribute("page");
		RequestDispatcher dispatcher = null;
		if(page.equalsIgnoreCase("admin")) 
		     dispatcher = getServletContext().getRequestDispatcher("/AdminControl.jsp");
		else
			 dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
