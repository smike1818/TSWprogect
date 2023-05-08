package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import model.ModelUserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
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
            
            ModelUserDAO mud = new ModelUserDAO();
            try {
				mud.doSave(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            response.sendRedirect("LoginPageUtente.jsp");
        } else { // If form data is invalid, display error message on registration page
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("Registrazione.jsp").forward(request, response);
        }
    }
}
