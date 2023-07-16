package control;

import java.io.File;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
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
        String uploadPath = getServletContext().getRealPath("/img");

        if(action!= null) {
        	if(action.equalsIgnoreCase("addImage")) {
        	  
        	  Path destinationPath = null;
        	  
              try {
                 List<Part> fileParts = new ArrayList<Part>(request.getParts()); // Ottieni la lista delle parti dei file
                 for (Part part : fileParts) {

                	String fileName = getFileName(part);
                	if(fileName!=null && !fileName.equalsIgnoreCase("")) {
                	destinationPath = new File(uploadPath, fileName).toPath();
                    	if (!Files.exists(destinationPath)) {
                    		Files.copy(part.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    		
                    		// Controlla se l'immagine ha uno sfondo trasparente	
                    		BufferedImage image = ImageIO.read(destinationPath.toFile());
                    		if (image.getColorModel().hasAlpha()) {
                    			
                    			String filePath = destinationPath.toString();
                    			
                                // Esegui altre operazioni con il percorso del file
                    			
                                ImageBean img = new ImageBean();
                                img.setNome(fileName);
                                img.setPath(filePath);		

                                ArticoloBean art = modelArt.doRetrieveByKey(code);
                                img.setArticolo(art);

                                model.doSave(img);
                    		}else {
                    			request.setAttribute("error-statement", "devi inserire immagine con sfondo trasparente");
                    			Files.delete(destinationPath);
                    			
                    		}
                	}else {
                		request.setAttribute("error-statement", "hai già inserito questa immagine");
                	}
                }}

            // Altre operazioni dopo l'elaborazione dei file

             } catch (SQLException se) {
            	     request.setAttribute("error-statement", "hai già inserito questa immagine");
            	     if(destinationPath!=null)    Files.delete(destinationPath);
             } catch (Exception e) {
            	     RequestDispatcher error = null;
	    			 String header = "Server Error";
	    			 String details = "c'è stato un errore...";
	    	   	        request.setAttribute("errorMessageHeader", header);
	    		        request.setAttribute("errorMessageDetails", details);
	    		        response.setStatus(500);
	    		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
	    		        error.forward(request, response);
              } 
        } 
        	
        if(action.equalsIgnoreCase("delete")) {
        	String nome = request.getParameter("nome");
        	String filePath = uploadPath + File.separator + nome;
        	
        	File file = new File(filePath);
        	if(file.exists()) {
        	  if(file.delete()) {
        	
        	   try {
				   model.doDelete(nome,code);
			   } catch (SQLException e) {
				        RequestDispatcher error = null;
	    			    String header = "Server Error";
	    			    String details = "errore nell'eliminazione dell'immagine, riprova...";
	    	   	        request.setAttribute("errorMessageHeader", header);
	    		        request.setAttribute("errorMessageDetails", details);
	    		        response.setStatus(500);
	    		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
	    		        error.forward(request, response);
			  }
        	
            }else {
            	RequestDispatcher error = null;
			    String header = "Server Error";
			    String details = "errore nell'eliminazione dell'immagine, riprova...";
	   	        request.setAttribute("errorMessageHeader", header);
		        request.setAttribute("errorMessageDetails", details);
		        response.setStatus(500);
		        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
		        error.forward(request, response);
            }
        	
        
        }else {
        	RequestDispatcher error = null;
		    String header = "Server Error";
		    String details = "file non esiste";
   	        request.setAttribute("errorMessageHeader", header);
	        request.setAttribute("errorMessageDetails", details);
	        response.setStatus(500);
	        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
	        error.forward(request, response);
        }
        	
     }}

        try {
            request.setAttribute("images", model.doRetrieveAllbyId(code));
        } catch (SQLException e) {
        	  RequestDispatcher error = null;
 			 String header = "Server Error";
 			 String details = "errore nella visualizzazione delle immagini, riprova...";
    	        request.setAttribute("errorMessageHeader", header);
    	        request.setAttribute("errorMessageDetails", details);
    	        response.setStatus(500);
    	        error = getServletContext().getRequestDispatcher("/errorAdmin.jsp");
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
