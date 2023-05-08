package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Retrieve the current session, if it exists
        if (session != null) {
            session.invalidate(); // Invalidate the session to log the user out
        }
        response.sendRedirect("LoginPageUtente.jsp"); // Redirect the user to the login page
    }
}
