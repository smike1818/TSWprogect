package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IvaDAO;
import model.ModelIvaDAO;
import bean.IvaBean;

public class ChangeIvaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 61556980010195L;
	IvaDAO model = null;
	IvaBean bean = null;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String data = request.getParameter("iva");
		
		//verifico se il dato è stato inserito correttamente
		if(data!=null) {
			try {
			     Double iva = Double.parseDouble(data);
			     if(iva<=0 || iva>100) {
			    	 response.setStatus(403);
			    	 response.setHeader("ErrorMessage", "Hai inserito numeri non esatti");
			     }else {
			     model = new ModelIvaDAO();
			     bean = model.getIvaByModel();
			     if(bean.getPercentuale()==iva){
			    	 response.setStatus(403);
			    	 response.setHeader("ErrorMessage", "Hai inserito la stessa iva salvata");
			     }else { 
			    	 bean.setPercentuale(iva);
			    	 if(model.changeIva(bean)){
			    	    response.getWriter().write("iva modificata con successo");
			         }
			      }
			     }
			} catch (Exception e) {
				RequestDispatcher dispatcher = null;   
				dispatcher = getServletContext().getRequestDispatcher("/500.jsp");
				dispatcher.forward(request, response);					
			}
				
		}else {
		     RequestDispatcher dispatcher = null;   
			 dispatcher = getServletContext().getRequestDispatcher("/500.jsp");
			 dispatcher.forward(request, response);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request,response);
	}

}
