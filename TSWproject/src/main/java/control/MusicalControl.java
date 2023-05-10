package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import bean.ArticoloCart;
import dao.ArticoloDAO;
import model.MusicalModelArticoloBean;

/*
 * MusicalControl è una servlet che gestisce ogni richiesta del client 
 * proveniente da ogni pagina (anche quelle non correlate tra loro)
 */

@MultipartConfig   //dichiarativa utilizzata per facilitare l'importazione e l'esportazione di immagini
public class MusicalControl extends HttpServlet{

	private static final long serialVersionUID = 102831973239L;
	ArticoloDAO model = new MusicalModelArticoloBean();
	
	public MusicalControl(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<String>filters = new ArrayList<String>();
		String action = request.getParameter("action");    //action salva l'azione effettuata dall'utente
		ServletContext sc = request.getServletContext();
		HttpSession session = request.getSession();
		String tipo = null;
		
		try {
		   if(action != null) {
		      if(action.equalsIgnoreCase("choise")){     //[AMMINISTRATORE]: scelta dell'articolo da inserire
		    	  tipo = request.getParameter("type");
				  sc.setAttribute("tipo", tipo);
		    	  if(tipo.equalsIgnoreCase("Pezzi di ricambio"))
		    		  sc.setAttribute(action,1);
		    	  else
		    	      sc.setAttribute(action,0);
		      }
		      if(action.equalsIgnoreCase("add")) {    //[AMMINISTRATORE] gestione insert nel db
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
		      if(action.equalsIgnoreCase("delete")) {   //[AMMINISTRATORE] gestione DELETE nel db
		    	  int id = Integer.parseInt(request.getParameter("id"));
				  model.doDelete(id);
		      }
		      if(action.equalsIgnoreCase("begin")){   //[UTENTE]: una serie di azione effettuate avviata la prima richiesta
		    	  String type = request.getParameter("tipo");
		    	  if(type==null)                       //quando si apre per la prima volta la pagina del catalogo, voglio che venga 
		    		                                   //mostrato un elenco di soli strumenti 
				      sc.setAttribute("tipo", "Strumenti");
		    	  else
		    		  sc.setAttribute("tipo", type);
				  request.setAttribute("filter","no");     //all'inzio i filtri non verranno visualizzati
				  sc.setAttribute("filtersList", filters);     //istanziazione lista di filtri che vengono salvati nel context
		      }
		      if(action.equalsIgnoreCase("ShowFilters")){   //[UTENTE]: GESTIONE della visualizzazione menù 
				  request.setAttribute("filter","si");
		      }
		      if(action.equalsIgnoreCase("Filters")){      //[UTENTE]: GESTIONE del menù. il menù è la serie di filtri usati per 
		    	                                           //mostrare il catalogo per categorie
		    	  
		    	  //prelevo tutti i valori dei filtri dal form in listProducs.jsp
				  String tip = (String) request.getParameter("Tipo");
				  if(tip!=null)
					  sc.setAttribute("tipo", tip);
				  String min = request.getParameter("min");
				  String max = request.getParameter("max");
				  String marca = request.getParameter("marca");
				  String tipologia = request.getParameter("tipologia");
				  
				  //salvo i valori nella lista filters che verrà salvata nel context
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
		      if(action.equalsIgnoreCase("Details")) {   //[UTENTE] gestione pagina di dettaglio
		    	  int id = Integer.parseInt(request.getParameter("id"));
		    	  request.setAttribute("bean", model.doRetrieveByKey(id));
		      }
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
		    	  request.setAttribute("filter","no");
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
					    bean.setQuantita(ac.getQTotale()-ac.getQCorrente());
					    if(bean.getQuantita()>0)               //se ci sono ancora articoli, modifico quantità
					        model.doChangeQuantity(bean.getID(), bean.getQuantita());
					    else                                   //altrimenti elimino l'articolo
					    	model.doDelete(bean.getID());
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
			System.out.println("Error:" + e.getMessage());
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
		  if(page.equalsIgnoreCase("admin")) { 
		     dispatcher = getServletContext().getRequestDispatcher("/AdminControl.jsp");
		  }else if(page.equalsIgnoreCase("catalogo")){
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
