package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ChoiseAdminServlet extends HttpServlet {

		private static final long serialVersionUID = 123131L;
		
		public static class Choise{
			String name = null;
			int value = 0;
			static Choise instance = null;
			
			protected Choise() {
				name = "choise";
			}
			
			public static Choise getInstance() {
				if(instance == null)
					instance=new Choise();
				return instance;
			}
			
			public void setValue(int v) {
				value=v;
			}
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    // Imposto il tipo di contenuto come JSON
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");

		    // Mi prendo la scelta
		    String tipo = request.getParameter("type");
		    String choise = tipo.equalsIgnoreCase("pezzidiricambio") ? "1" : "0";
		    
		    getServletContext().setAttribute("choise",choise);
            
		    //mi creo l'oggetto choise perchè la classe Gson non funziona dopo...
		    Choise c = Choise.getInstance();
		    c.setValue(Integer.parseInt(choise));

		    // Scrivo la stringa JSON come risposta
		    Gson gson = new Gson();
		    String json = gson.toJson(c);
		    response.getWriter().write(json);
		}

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}
}



