package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltersServlet extends HttpServlet {

	private ServletContext sc = null;
	private List<String>filters = new ArrayList<String>();
	
	private static final long serialVersionUID = -1282543034686303166L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		 sc = getServletContext();
		
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
		  
		  RequestDispatcher dispatcher = null;
		  dispatcher = sc.getRequestDispatcher("/catalogo.jsp");
		  dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
