package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.ArticoloBean;
import bean.UserBean;
import dao.UserDAO;
import dao.AcquistoDAO;
import dao.ArticoloDAO;
import model.ModelUserDAO;
import model.MusicalModelArticoloBean;
import model.ModelAcquistoDAO;


public class StoricoUserServlet extends HttpServlet {

	UserDAO model = new ModelUserDAO();
	AcquistoDAO modelacquisto = new ModelAcquistoDAO();
	ArticoloDAO artmodel = new MusicalModelArticoloBean();
	UserBean user = null;
	HttpSession session = null;
	private static final long serialVersionUID = -6690581543480264L;
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		session = request.getSession();
		String username = (String) session.getAttribute("un");
		String action = request.getParameter("action");
		int IDAcquisto = -1;
		int IDArticolo = -1;
		
		//CHIAMATA AJAX PER PRELEVARE TUTTI GLI ARTICOLI
		if(action!=null) {
			if(action.equalsIgnoreCase("mostra-dettagli")) {
				String temp = request.getParameter("data");
				if(temp!=null) {
					try {
						IDAcquisto = Integer.parseInt(temp);
					}catch(NumberFormatException e) {
						 RequestDispatcher error = null;
			 		     String header = "Server Error";
			 		     String details = "impossibile convertire da string a int...";
			    	        request.setAttribute("errorMessageHeader", header);
			    	        request.setAttribute("errorMessageDetails", details);
			    	        response.setStatus(500);
			    	        error = getServletContext().getRequestDispatcher("/error.jsp");
			    	        error.forward(request, response);
					}
				}
				
				try {
					List<ArticoloBean>articoli = modelacquisto.getArticoliByAcquisti(IDAcquisto);
					String json = new Gson().toJson(articoli); // Converte la lista in una stringa JSON  
					
				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
				    
				} catch (SQLException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter().write("Errore durante l'accesso al database");
				}
			}if(action.equalsIgnoreCase("mostra-immagini")) {
				String temp = request.getParameter("data");
				if(temp!=null) {
					try {
						IDArticolo = Integer.parseInt(temp);
					}catch(NumberFormatException e) {	
						 RequestDispatcher error = null;
			 		     String header = "Server Error";
			 		     String details = "impossibile convertire da string a int...";
			    	        request.setAttribute("errorMessageHeader", header);
			    	        request.setAttribute("errorMessageDetails", details);
			    	        response.setStatus(500);
			    	        error = getServletContext().getRequestDispatcher("/error.jsp");
			    	        error.forward(request, response);
					}
				}
				
				try {
					List<String>images = artmodel.getImages(IDArticolo);
					String json = new Gson().toJson(images); // Converte la lista di stringhe in una stringa JSON 
					System.out.println(json);
					
				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
				    
				} catch (SQLException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter().write("Errore durante l'accesso al database");
				}
			}
		}else {
		
		if(username!=null) {

		  try {
			  user = model.doRetrieveByUsr(username);
		  } catch (SQLException e) {
			  RequestDispatcher error = null;
 		       String header = "Server Error";
 		       String details = "errore nel reperire l'utente dal database...";
 	   	        request.setAttribute("errorMessageHeader", header);
 		        request.setAttribute("errorMessageDetails", details);
 		        response.setStatus(500);
 		        error = getServletContext().getRequestDispatcher("/error.jsp");
 		        error.forward(request, response);
		  }
		
		}else{
			 RequestDispatcher dispatcher = null;   
			 dispatcher = getServletContext().getRequestDispatcher("/LoginPageUtente.jsp");
			 dispatcher.forward(request, response);
		}

		
		if(user!=null) {
			try {
				request.setAttribute("storico", modelacquisto.doRetrieveByUser(user.getCF()));
			} catch (SQLException e) {				
				 RequestDispatcher error = null;
	 		       String header = "Server Error";
	 		       String details = "errore nel reperire gli ordini dal database...";
	 	   	        request.setAttribute("errorMessageHeader", header);
	 		        request.setAttribute("errorMessageDetails", details);
	 		        response.setStatus(500);
	 		        error = getServletContext().getRequestDispatcher("/error.jsp");
	 		        error.forward(request, response);

			}
		}else{
			    RequestDispatcher error = null;
		        String header = "Server Error";
		        String details = "utente non trovato...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
		}
		
		  RequestDispatcher dispatcher = null;   
	       dispatcher = getServletContext().getRequestDispatcher("/Storico.jsp");
	       dispatcher.forward(request, response);
		
	}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

}
