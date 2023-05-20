package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeginActionsServlet extends HttpServlet{

	ServletContext sc = null;
	
	private static final long serialVersionUID = -545822712986435817L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 sc = getServletContext();
		
		//[UTENTE]: una serie di azione effettuate avviata la prima richiesta
		  String type = request.getParameter("tipo");
		  if(type==null)                       //quando si apre per la prima volta la pagina del catalogo, viene mostrato il catalogo di strumenti	                                   //mostrato un elenco di soli strumenti 
		      sc.setAttribute("tipo", "Strumenti");
		  else
			  sc.setAttribute("tipo", type);
		  
		  RequestDispatcher dispatcher = null;
		  dispatcher = sc.getRequestDispatcher("/catalogo.jsp");
		  dispatcher.forward(request, response);
	}

}
