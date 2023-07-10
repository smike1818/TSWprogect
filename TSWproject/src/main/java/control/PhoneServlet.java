package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TelefonoBean;
import bean.UserBean;
import model.ModelTelefonoDAO;
import model.ModelUserDAO;

public class PhoneServlet extends HttpServlet {


	private ModelTelefonoDAO model = null;
	private ModelUserDAO usermodel = null;
	private static final long serialVersionUID = -10574138902693898L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		model = new ModelTelefonoDAO();
		String action = request.getParameter("action");        
        HttpSession session = request.getSession(false);
        
        UserBean user = (UserBean) session.getAttribute("user-details");
        if(user==null) {
        	String username = (String) session.getAttribute("un");
        	try {
        		user = usermodel.doRetrieveByUsr(username);
        	}catch(SQLException e) {
        		RequestDispatcher error = null;
				String header = "Server Error";
				String details = "errore con lo username...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/error.jsp");
		        error.forward(request, response);
            }
        }
        
        if(action!=null) {
        	if(action.equalsIgnoreCase("add")) {
                TelefonoBean phone = new TelefonoBean();
   	    		
   	    	    String prefisso = request.getParameter("prefisso");
   			    String numero = request.getParameter("numero");
   	    		
   	    		phone.setPrefisso(prefisso);
   	    		phone.setNumero(numero);
   	    		phone.setUtente(user);
   	    		
   	    		try {
					model.doSave(phone);
				} catch (SQLException e) {
					e.printStackTrace();
					 RequestDispatcher error = null;
				     String header = "Client Error";
				     String details = "errori nel salvataggio del numero...";
			   	        request.setAttribute("errorMessageHeader", header);
				        request.setAttribute("errorMessageDetails", details);
				        response.setStatus(500);
				        error = getServletContext().getRequestDispatcher("/error.jsp");
				        error.forward(request, response);
				}
        	}
        	if(action.equalsIgnoreCase("delete")) {
        		
        		 String numero = request.getParameter("phone");
        		
        		try {
					model.doDelete(numero);
				} catch (SQLException e) {
					e.printStackTrace();
					e.printStackTrace();
					 RequestDispatcher error = null;
				     String header = "Client Error";
				     String details = "errore nella cancellazione del telefono...";
			   	        request.setAttribute("errorMessageHeader", header);
				        request.setAttribute("errorMessageDetails", details);
				        response.setStatus(500);
				        error = getServletContext().getRequestDispatcher("/error.jsp");
				        error.forward(request, response);
				}
        	}
        	if(action.equalsIgnoreCase("prefer")) {
        		String numero = request.getParameter("phone");
        		
				try {
					model.doPrefer(numero,user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 e.printStackTrace();
					 RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "errore nella scelta del numero predefinito...";
	    	   	        request.setAttribute("errorMessageHeader", header);
	    		        request.setAttribute("errorMessageDetails", details);
	    		        response.setStatus(500);
	    		        error = getServletContext().getRequestDispatcher("/error.jsp");
	    		        error.forward(request, response);
				}
			}
        }
        
        try {
        	request.setAttribute("user-phones", model.doRetrieveAllPhones(user));
        }catch(SQLException e) {
        	// TODO Auto-generated catch block
			 e.printStackTrace();
			 RequestDispatcher error = null;
			 String header = "Server Error";
			 String details = "errore nella stampa dei numeri...";
			 response.setStatus(500);
			 error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
			 error.forward(request, response);
        }
        
        RequestDispatcher dispatcher = null;		    	   
	    dispatcher = getServletContext().getRequestDispatcher("/User.jsp");
	    dispatcher.forward(request, response);
       
        }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

}
