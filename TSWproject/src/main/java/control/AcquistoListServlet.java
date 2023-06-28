package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.AcquistoBean;
import bean.ContoBean;
import bean.UserBean;
import dao.AcquistoDAO;
import dao.ContoDAO;
import dao.UserDAO;
import model.ModelAcquistoDAO;
import model.ModelContoDAO;
import model.ModelUserDAO;

public class AcquistoListServlet extends HttpServlet{
	
	private static final long serialVersionUID = 5990937487736664991L;
	AcquistoDAO model = new ModelAcquistoDAO();
	UserBean user = null;
	UserDAO usermodel = new ModelUserDAO();
	AcquistoBean acq = null;
	ContoDAO modelconto = new ModelContoDAO();
	ContoBean conto = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   	   
	   try {
		request.setAttribute("acquisti", model.doRetrieveAll("idAcquisto"));
	} catch (SQLException e) {
		    e.printStackTrace();
		    RequestDispatcher error = null;
	        String header = "Server Error";
	        String details = "errore nella mostra degli acquisti...";
	        response.setStatus(500);
	        error = getServletContext().getRequestDispatcher("/error.jsp?errorMessageHeader=" + header + "&errorMessageDetails=" + details);
	        error.forward(request, response);
	}
   	   
   	   RequestDispatcher dispatcher = null;   
       dispatcher = getServletContext().getRequestDispatcher("/orderList.jsp");
       dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
