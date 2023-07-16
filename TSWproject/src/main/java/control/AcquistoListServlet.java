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

import java.util.logging.Logger;

public class AcquistoListServlet extends HttpServlet{
	
	private static final long serialVersionUID = 5990937487736664991L;
	private static final Logger LOGGER = Logger.getLogger(AcquistoListServlet.class.getName());
	
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
		    LOGGER.log(null, "context", e);
		    RequestDispatcher error = null;
	        String header = "Server Error";
	        String details = "errore nella mostra degli acquisti...";
	        request.setAttribute("errorMessageHeader", header);
	        request.setAttribute("errorMessageDetails", details);
	        response.setStatus(500);
	        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
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
