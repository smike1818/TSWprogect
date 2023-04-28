package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.ArticoloBean;
import model.MusicalModelDAO;
import model.MusicalModelArticoloBean;

@MultipartConfig
public class MusicalControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 102831973239L;
	MusicalModelDAO<ArticoloBean> model = new MusicalModelArticoloBean();
	
	public MusicalControl(){
		super();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<String>filters = new ArrayList<String>();
		String action = request.getParameter("action");
		ServletContext sc = request.getServletContext();
		HttpSession session = request.getSession();
		String tipo = null;
		
		try {
		   if(action != null) {
		      if(action.equalsIgnoreCase("choise")){
		    	  tipo = request.getParameter("type");
				  sc.setAttribute("tipo", tipo);
		    	  if(tipo.equalsIgnoreCase("Pezzi di ricambio"))
		    		  sc.setAttribute(action,1);
		    	  else
		    	      sc.setAttribute(action,0);
		      }
		      if(action.equalsIgnoreCase("add")) {
		    	  //da aggiungere immagine
		    	  ArticoloBean ab = new ArticoloBean();
		    	  ab.setID(Integer.parseInt(request.getParameter("id")));
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
		      if(action.equalsIgnoreCase("begin")){
		    	  String type = request.getParameter("tipo");
		    	  if(type==null)
				      sc.setAttribute("tipo", "Strumenti");
		    	  else
		    		  sc.setAttribute("tipo", type);
				  request.setAttribute("filter","no");
				  sc.setAttribute("filtersList", filters);
		      }
		      if(action.equalsIgnoreCase("ShowFilters")){
				  request.setAttribute("filter","si");
		      }
		      if(action.equalsIgnoreCase("Filters")){
				  String tip = (String) request.getParameter("Tipo");
				  if(tip!=null)
					  sc.setAttribute("tipo", tip);
				  String min = request.getParameter("min");
				  String max = request.getParameter("max");
				  String marca = request.getParameter("marca");
				  String tipologia = request.getParameter("tipologia");
				  if(tip==null)
					  filters.add("0");
				  else
				      filters.add((tip.equalsIgnoreCase("Strumenti"))?"0":"1");
				  if(min.equalsIgnoreCase("0") || max.equalsIgnoreCase("0")) {
					  filters.add("0");
					  filters.add("0");
				  }else{
				      filters.add(min);
				      filters.add(max);
				  }
				  filters.add(marca);
				  filters.add(tipologia);
				  sc.setAttribute("filtersList", filters);
		      }
		      if(action.equalsIgnoreCase("Details")) {
		    	  int id = Integer.parseInt(request.getParameter("id"));
		    	  request.setAttribute("bean", model.doRetrieveByKey(id));
		      }
		      if(action.equalsIgnoreCase("cart")) {		    	  
		    	  sc.setAttribute("page","carrello");		    	
		    	  Integer id = Integer.parseInt(request.getParameter("id"));
		    	  if(id!=null) {
		    		  @SuppressWarnings("unchecked")
			    	  Map<Integer,ArticoloBean>cart = (Map<Integer,ArticoloBean>) session.getAttribute("cart");
			    	  @SuppressWarnings("unchecked")
					  Map<Integer,Integer>quantity = (Map<Integer,Integer>) session.getAttribute("quantity");
			    	  if(cart==null) {
			    		  cart = new HashMap<Integer,ArticoloBean>();
			    		  if(quantity==null)
			    		      quantity = new HashMap<Integer,Integer>();
			    	  }
		    	    ArticoloBean add = model.doRetrieveByKey(id);
		    	    if(!cart.containsKey(id))
		    	       cart.put(add.getID(),add);
		    	    Integer q = Integer.parseInt(request.getParameter("quantity"));
		    	    if(q!=null){
		    	    	if(!quantity.containsKey(id))
		    	    		 quantity.put(id, q);
		    	    	else {
		    	    		 int tot = q+quantity.get(id);
							 if(tot<=add.getQuantita())		   
		    	    		     quantity.replace(id, tot);
		    	    		 else {
		    	    			 RequestDispatcher error = null;
		    	    			 error = sc.getRequestDispatcher("/error.jsp");
		    	    			 error.forward(request, response);
		    	    		 }
		    	    			  
		    	    	} 
		    	  }	
		    	    session.setAttribute("cart", cart); 
			    	session.setAttribute("quantity", quantity);
		       }		    	  
		      }if(action.equalsIgnoreCase("deleteByCart")) {
				  int id = Integer.parseInt(request.getParameter("id"));
				  @SuppressWarnings("unchecked")
				  Map<ArticoloBean,Integer>cart = (Map<ArticoloBean,Integer>) session.getAttribute("cart");
				  @SuppressWarnings("unchecked")
				  Map<Integer,Integer>quantity = (Map<Integer,Integer>) session.getAttribute("quantity");
				  if(cart!=null) {
					  cart.remove(id);
				  }
				  if(quantity!=null) {
					  quantity.remove(id);
				  }
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
		if(page!=null) {
		  if(page.equalsIgnoreCase("admin")) { 
		     dispatcher = getServletContext().getRequestDispatcher("/AdminControl.jsp");
		  }else if(page.equalsIgnoreCase("catalogo")){
			 dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
		  }else if(page.equalsIgnoreCase("details")){
			 dispatcher = getServletContext().getRequestDispatcher("/dettaglio.jsp");
		  }else if(page.equalsIgnoreCase("carrello")){
				 dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
		  }
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
