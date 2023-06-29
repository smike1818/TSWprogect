package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.IndirizzoDAO;
import dao.TelefonoDAO;
import dao.ContoDAO;
import dao.UserDAO;
import model.ModelIndirizzoDAO;
import model.ModelTelefonoDAO;
import model.ModelContoDAO;
import model.ModelUserDAO;

public class AJAXvalidation extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1435235146L;
	private UserDAO usermodel = new ModelUserDAO();
	private IndirizzoDAO indirizzomodel = new ModelIndirizzoDAO();
	private ContoDAO contomodel = new ModelContoDAO();
	private TelefonoDAO telefonomodel = new ModelTelefonoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   	
		String action = request.getParameter("action");
		String data = request.getParameter("data");
		
		if(data==null)      response.sendError(403, "dato nullo");
		
		if(action!=null) {
			if(action.equalsIgnoreCase("username")) {
				try {
					usermodel.checkUsername(data);
					response.getWriter().write("username mai usata");
				} catch (SQLException e) {
					if(getServletContext().getAttribute("loginPageShowed")==null)
					    response.sendError(403);
					else
						response.getWriter().write("login effettuabile");
				}
			}if(action.equalsIgnoreCase("cf")) {
				try {
					usermodel.checkCF(data);
					response.getWriter().write("cf mai usato");
				} catch (SQLException e) {
					response.sendError(403);
				}
			}if(action.equalsIgnoreCase("num-carta")) {
				try {
					contomodel.checkNum(data);
					response.getWriter().write("num-carta mai usato");
				} catch (SQLException e) {
					response.sendError(403);
				}
			}if(action.equalsIgnoreCase("iban")) {
				try {
					contomodel.checkIban(data);
					response.getWriter().write("iban mai usato");
				} catch (SQLException e) {
					response.sendError(403);
				}
			}if(action.equalsIgnoreCase("address")) {
				try {
					String[] parts = data.split(" "); // Divide la stringa in base allo spazio
					String via = parts[0]; // Prima parola
					String civico = parts[1]; // Seconda parola
					String cap = parts[2]; // Terza parola 
					System.out.print(via+civico+cap);
					indirizzomodel.checkAddress(via,civico,cap);
					response.getWriter().write("indirizzo mai usato");
				} catch (SQLException e) {
					response.sendError(403);
				}
			}if(action.equalsIgnoreCase("phone")) {
				try {
					telefonomodel.checkPhone(data);
					response.getWriter().write("telefono mai usato");
				} catch (SQLException e) {
					response.sendError(403);
				}
			}
		}
	   
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}