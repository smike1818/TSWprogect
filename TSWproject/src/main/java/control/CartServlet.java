package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
	
	public CartServlet(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");    //action salva l'azione effettuata dall'utente
		ServletContext sc = request.getServletContext();
		HttpSession session = request.getSession();
		
		try {
		   if(action != null) {
		      if(action.equalsIgnoreCase("cart")) {      //[UTENTE]: Gestione dell'istanzazione, dell'inizializzazione e inserimento
		    	                                         // prodotti nel carrello
		    	  //prelevo il carrello dalla sessione  
                  @SuppressWarnings("unchecked")
				List<ArticoloCart> cart =  (List<ArticoloCart>) session.getAttribute("cart");
		    	  
		    	  //se la sessione non ha salvato come attributo il carrello, lo istanzio
		    	  if(cart==null) {
		    		   cart = new ArrayList<ArticoloCart>();
		    		   session.setAttribute("cart", cart);
		    	  }
		    	  //indico alla servlet che dopo aver effettuato queste operazioni  reindirizza alla pagina carrello.jsp
		    	  sc.setAttribute("page","catalogo");	
		    	  
		    	  //prendo elemento con codice 'id' e lo aggiungo al carrello
		    	  Integer id = Integer.parseInt(request.getParameter("id"));
		    	  
		    	  //se id non è nullo, vuol dire che sto aggiungendo prodotti al carrello
		    	  if(id!=null && cart!=null) {	  
			    	
		    		//prelevo articolo dal DB
		    	    ArticoloBean add = model.doRetrieveByKey(id);
		    	    //verifico le quantità inserite dall'utente
		    	    Integer q = Integer.parseInt(request.getParameter("quantity"));
		    	    
		    	     if(q!=null){
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
		    	    		aCart.setQCorrente(q);
		    	    		cart.add(aCart);
		    	    	}else {
		    	    		ArticoloCart ac = cart.get(i);
		    	    		if(ac.setQCorrente(q)) {
		    	    			//rimuovo l'articolo per poi aggiungerlo modificato
		    	    			cart.remove(i);
		    	    			cart.add(i, ac);
		    	    		}else {
		    	    			 RequestDispatcher error = null;
		    	    			 String header = "Client Error";
		    	    			 String details = "Hai inserito piu' elementi di quelli disponibili...";
		    	    			 response.setStatus(400);
		    	    			 error = sc.getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
		    	    			 error.forward(request, response);
		    	    		 }
		    	    	}
		    	    	//aggiungo il carrello modificato nella sessione
		    	    	  session.removeAttribute("cart");
					      session.setAttribute("cart", cart);				    	 
		    	  }	
		    	  
		      }	    	
		    	  
			  //rimuovo l'elemento dal carrello
		      }if(action.equalsIgnoreCase("deleteByCart")) {
				  Integer id = Integer.parseInt(request.getParameter("id"));
				  
				  @SuppressWarnings("unchecked")
				  List<ArticoloCart>cart = (List<ArticoloCart>) session.getAttribute("cart");
				  
				  //ciclo il carrelo per eliminare l'item
				  if(cart!=null && id!=null) {
					  int i=0;
					  for(ArticoloCart c : cart) {
						  if(c.getBean().getID()==id)
							  cart.remove(i);
						  i++;
					  }
				  }
				  
				  
			  }
		      //ordinamento degli elementi nel carrello in base al prezzo
			  }if(action.equalsIgnoreCase("sortCart")) {
				  @SuppressWarnings("unchecked")
				List<ArticoloCart>cart = (List<ArticoloCart>)session.getAttribute("cart");
				  
				  //uso gli stream per ordinare. sort(expression of comparator) 
				  //expression = (a1,a2) -> {tot = prezzo1 - prezzo2 (sottoforma di intero)      return tot }
				  cart.sort((a1,a2) -> {
					  Double d = (((ArticoloBean)a1.getBean()).getPrezzo()*100*a1.getQCorrente() - 
							      ((ArticoloBean)a2.getBean()).getPrezzo()*100*a2.getQCorrente());
					  return d.intValue(); 
				  });
				  session.setAttribute("cart", cart);
			  } if(action.equalsIgnoreCase("buy")) {      //[UTENTE]: GESTIONE dell'acquisto dell'utente
				  @SuppressWarnings("unchecked")
				  List<ArticoloCart>cart = (List<ArticoloCart>) session.getAttribute("cart");
				  if(session.getAttribute("un")!=null) {
				   if(cart==null) {     //se il carrello è nullo reindirizzo alla pagina di errore
					 RequestDispatcher error = null;
 	    			 String header = "Client Error";
 	    			 String details = "Carrello nullo ...";
 	    			 response.setStatus(400);
 	    			 error = sc.getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
 	    			 error.forward(request, response);
				   }
				   if(cart.size()>0) {
				    
					for(int i=0; i<cart.size(); i++) {        //scorro carrello e verifico gli articoli da eliminare o modificare 
				    	                                      // nel database
				    	ArticoloCart ac = cart.get(i);
				    	ArticoloBean bean = ac.getBean();
				    	
				    	ComposizioneBean comp = new ComposizioneBean();
				    	comp.setArticolo(bean);
				    	comp.setPrezzo(bean.getPrezzo()*ac.getQCorrente());
				    	
				    	bean.setQuantita(ac.getQTotale()-ac.getQCorrente());
					    if(bean.getQuantita()>0)               //se ci sono ancora articoli, modifico quantità
					        model.doChangeQuantity(bean.getID(), bean.getQuantita());
					    else                                   //altrimenti elimino l'articolo
					    	model.doDelete(bean.getID());
					    
					    listCart.add(comp);
					    
				    }
				    
				    
				  }
				  session.removeAttribute("cart");            //rimuovo il carrello
				  sc.setAttribute("page","catalogo");         //reindirizzo alla pagina iniziale 
			  }
				  else {     
					     sc.setAttribute("page", "login"); 
					     sc.setAttribute("buyAfterLogin", "buy");
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
		
		//page prende la stringa relativa al nome della pagina su cui bisogna indirizzarsi
		String page = (String) sc.getAttribute("page");
		RequestDispatcher dispatcher = null;
		if(page!=null) {
		  if(page.equalsIgnoreCase("catalogo")){
			 dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
		  }else if(page.equalsIgnoreCase("details")){
			 dispatcher = getServletContext().getRequestDispatcher("/dettaglio.jsp");
		  }else if(page.equalsIgnoreCase("carrello")){
			 dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
		  }else if(page.equalsIgnoreCase("login")) {
			 dispatcher = getServletContext().getRequestDispatcher("/LoginPageUtente.jsp");
		  }
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
