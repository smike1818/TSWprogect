package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ArticoloBean;
import bean.ArticoloCart;
import bean.ComposizioneBean;
import dao.ArticoloDAO;
import dao.UserDAO;
import model.ModelUserDAO;
import model.MusicalModelArticoloBean;

/*
 * MusicalControl è una servlet che gestisce ogni richiesta del client 
 * proveniente da ogni pagina (anche quelle non correlate tra loro)
 */


public class CartServlet extends HttpServlet{

	private static final long serialVersionUID = 102831973239L;
	ArticoloDAO model = new MusicalModelArticoloBean();
	List<ComposizioneBean>listCart = new ArrayList<ComposizioneBean>();
	UserDAO user = new ModelUserDAO();
	List<ArticoloCart> cart = null;
	
	public CartServlet(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");    //action salva l'azione effettuata dall'utente
		HttpSession session = request.getSession();
		cart =  (List<ArticoloCart>) session.getAttribute("cart");
		
		try {
		   if(action != null) {
		      if(action.equalsIgnoreCase("cart")) {      //[UTENTE]: Gestione dell'istanzazione, dell'inizializzazione e inserimento
		    	                                         // prodotti nel carrello

		    	  
		    	  //se la sessione non ha salvato come attributo il carrello, lo istanzio
		    	  if(cart==null) {
		    		   cart = new ArrayList<ArticoloCart>();
		    		   session.setAttribute("cart", cart);
		    	  }
		    	  
		    	  //prendo elemento con codice 'id' e lo aggiungo al carrello
		    	  Integer id = Integer.parseInt(request.getParameter("id"));    //da un eccezione che non da problemi al flusso di esecuzione del sito
		    	  
		    	  //se id non è nullo, vuol dire che sto aggiungendo prodotti al carrello
		    	  if(id!=null) {	  
			    	
		    		//prelevo articolo dal DB
		    	    ArticoloBean add = model.doRetrieveByKey(id);
		    	    		    	 
		    	    //istanzio un nuovo articoloCart da aggiungere al carrello
		    	    ArticoloCart aCart = new ArticoloCart(add);
		    	    	
		    	    //controllo se all'interno del carrello c'è articolo
		    	    int i=0;
		    	    for(ArticoloCart a : cart) {
		    	    	if(a.equals(aCart))
		    	    		break;
		    	    	i++;	
		    	    }
		    	    	
		    	    //se non ci sono duplicati aggiungo al carrello
		    	    if(cart.size()==i) {
		    	    	cart.add(aCart);
		    	    }
		    	    
		    	  //aggiungo il carrello modificato nella sessione
		    	     session.removeAttribute("cart");
					 session.setAttribute("cart", cart);				    	 
		    	  }	
		    	  
		    	  
			  //rimuovo l'elemento dal carrello
		      }if(action.equalsIgnoreCase("deleteByCart")) {
				  Integer id = Integer.parseInt(request.getParameter("id"));				 
				  
				  //ciclo il carrelo per eliminare l'item
				  if(cart!=null && id!=null) {
					  int i=0;
					  for(ArticoloCart c : cart) {
						  if(c.getBean().getID()==id) {
							  cart.remove(i);
							  break;
						  }
						  i++;
					  }
				  }  
			  }if(action.equalsIgnoreCase("increment")) {
				 String data = request.getParameter("data");
				 if(data!=null) {
					 int id = Integer.parseInt(data);
					 
					 //prendo l'elemento nel carrello 
					 ArticoloCart articolo = cart.stream()
						        .filter(a -> a.getBean().getID() == id)
						        .findFirst().get();
                     
					 //per poi rimuoverlo
					 cart.removeIf((a) -> a.getBean().getID()==id);
					 
					 //incremento la quantità selezionata nel carrello
					 articolo.incrementa();
                     cart.add(articolo);
                      
                     //aggiungo il carrello modificato nella sessione
		    	     session.removeAttribute("cart");
					 session.setAttribute("cart", cart);
				 }
				 
			  }
			  if(action.equalsIgnoreCase("decrement")) {
					 String data = request.getParameter("data");
					 if(data!=null) {
						 int id = Integer.parseInt(data);
						 
						 //prendo l'elemento nel carrello 
						 ArticoloCart articolo = cart.stream()
							        .filter(a -> a.getBean().getID() == id)
							        .findFirst().get();
	                     
						 //per poi rimuoverlo
						 cart.removeIf((a) -> a.getBean().getID()==id);
						 
						 //decremento la quantità selezionata nel carrello
						 articolo.decrementa();
	                     cart.add(articolo);
	                      
	                     //aggiungo il carrello modificato nella sessione
			    	     session.removeAttribute("cart");
						 session.setAttribute("cart", cart);
					 }
					 
				  }
		      
			}
		   }catch(Exception e) {
			e.printStackTrace();
		}
		
		String sort="codice";
		
		try {
			request.setAttribute("products",  model.doRetrieveAll(sort));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = null;
	    dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
