package control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.ArticoloBean;
import bean.ImageBean;
import dao.ArticoloDAO;
import dao.ImageDAO;
import model.ModelImageDAO;
import model.MusicalModelArticoloBean;

@MultipartConfig
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = -7560700470932626634L;
    private static ArticoloDAO modelArt = new MusicalModelArticoloBean();
    private static ImageDAO model = new ModelImageDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int code = Integer.parseInt(request.getParameter("code"));
        String action = request.getParameter("action");

        if(action!= null) {
        	if(action.equalsIgnoreCase("addImage")) {
              try {
                 List<Part> fileParts = new ArrayList<Part>(request.getParts()); // Ottieni la lista delle parti dei file
                 for (Part part : fileParts) {

                	String fileName = getFileName(part);
                	if(fileName!=null && !fileName.equalsIgnoreCase("")) {
                       System.out.println(fileName);
                       String filePath = getServletContext().getRealPath("/img") + File.separator + fileName; // Ottieni il percorso completo del file

                       // Esegui altre operazioni con il percorso del file
                       ImageBean image = new ImageBean();
                       image.setNome(fileName);
                       image.setPath(filePath);

                       ArticoloBean art = modelArt.doRetrieveByKey(code);
                       image.setArticolo(art);

                       model.doSave(image);
                	}
                  }

            // Altre operazioni dopo l'elaborazione dei file

             } catch (SQLException se) {
            	     RequestDispatcher error = null;
	    			 String header = "Client Error";
	    			 String details = "hai inserito un immagine gia salvata...";
	    			 response.setStatus(400);
	    			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
             } catch (Exception e) {
            	     RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "c'� stato un errore...";
	    			 response.setStatus(500);
	    			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
              } 
        } 
        	
        if(action.equalsIgnoreCase("delete")) {
        	String nome = request.getParameter("nome");
        	
        	try {
				model.doDelete(nome,code);
			} catch (SQLException e) {
				  RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "errore nell'eliminazione dell'immagine, riprova...";
	    			 response.setStatus(500);
	    			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
	    			 error.forward(request, response);
			}
        	
        }
        	
        
        }

        try {
            request.setAttribute("images", model.doRetrieveAllbyId(code));
        } catch (SQLException e) {
        	  RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "errore nella visualizzazione delle immagini, riprova...";
 			 response.setStatus(500);
 			 error = getServletContext().getRequestDispatcher("/errorAdmin.jsp?errorMessageHeader="+header+"&errorMessageDetails="+details);
 			 error.forward(request, response);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ImagePage.jsp?id=" + code);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    //metodo per prendere l'immagine
    private String getFileName(Part part) {
        String header = part.getHeader("content-disposition");
        String[] headerParts = header.split(";");

        for (String headerPart : headerParts) {
            if (headerPart.trim().startsWith("filename")) {
                return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return "";
    }
    






}
