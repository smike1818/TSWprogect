package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.UserBean;
import model.ModelUserDAO;

public class RegisterServlet extends HttpServlet {
	
    private static final long serialVersionUID = 14L;
    ModelUserDAO mud = new ModelUserDAO();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	String action = request.getParameter("action");
    	if(action!=null) {
    		if(action.equalsIgnoreCase("ajax")) {
    			String email = request.getParameter("data");
    			if(email==null) {
				     RequestDispatcher error = null;
	    			 String header = "Client Error";
	    			 String details = "errore nel controllo dell'email ...";
	    			 response.setStatus(403);
	    			 error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
    			}
    			try {
					mud.checkEmail(email);
					response.getWriter().write("email mai usata");
				} catch (SQLException e) {
					response.sendError(403);
				}
    		}
    	}else {
    	    	
        // Get the form data from request parameters
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("cognome");
        String CF = request.getParameter("cf");
        String email = request.getParameter("email");
        String userName = request.getParameter("us");
        String password = request.getParameter("pws");
        String confirmPassword = request.getParameter("pws1");

        // Validate the form data
        boolean hasError = false;
		String errorMsg = "";
        if (firstName == null || firstName.trim().length() == 0) {
            hasError = true;
            errorMsg = "First name is required.";
        } else if (lastName == null || lastName.trim().length() == 0) {
            hasError = true;
            errorMsg = "Last name is required.";
        } else if (email == null || email.trim().length() == 0) {
            hasError = true;
            errorMsg = "Email is required.";
        } else if (password == null || password.trim().length() == 0) {
            hasError = true;
            errorMsg = "Password is required.";
        } else if (!password.equals(confirmPassword)) {
            hasError = true;
            errorMsg = "Passwords do not match.";
        }

        // If form data is valid, create a new user and redirect to success page
        if (!hasError) {
            UserBean user = new UserBean();
            user.setNome(firstName);
            user.setCognome(lastName);
            user.setCF(CF);
            user.setPassword(password);
            user.setUsername(userName);
            user.setEmail(email);
            user.setValid(true);
                       
            try {
				mud.doSave(user);
				response.sendRedirect("LoginPageUtente.jsp");
			} catch (SQLException e) {
				     e.printStackTrace();
				     RequestDispatcher error = null;
	    			 String header = "Client Error";
	    			 String details = "sei gia' registrato ...";
	    			 response.setStatus(400);
	    			 error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
			}
        } else { // If form data is invalid, display error message on registration page
        	 RequestDispatcher error = null;
			 String header = "Client Error";
			 String details = errorMsg;
			 response.setStatus(400);
			 error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
			 error.forward(request, response);
        }
     }
                
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
