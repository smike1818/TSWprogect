package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChoiseAdminServlet extends HttpServlet {

		private static final long serialVersionUID = 123131L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			String tipo = request.getParameter("type");
			request.setAttribute("tipo", tipo);
			if(tipo.equalsIgnoreCase("Pezzi di ricambio"))
				getServletContext().setAttribute("choise",1);
			else
				getServletContext().setAttribute("choise",0);
		    
			String page = (String) getServletContext().getAttribute("page");
		    RequestDispatcher dispatcher = null;
		    
		    dispatcher = getServletContext().getRequestDispatcher("/"+page);
		    dispatcher.forward(request, response);
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}
}



