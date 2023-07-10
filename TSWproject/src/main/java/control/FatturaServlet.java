package control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import bean.*;

import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import dao.*; 
import model.*;

public class FatturaServlet extends HttpServlet {
    private static final long serialVersionUID = -725712349886211710L;
    private AcquistoDAO modelacquisto = new ModelAcquistoDAO();
    private AcquistoBean acquisto = null;
    private UserBean consumer = null;
    private IndirizzoBean address = null;
    private ComposizioneDAO compmodel = new ModelComposizioneDAO();
    private double iva = -1;
    private static int idFattura = 0;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<ComposizioneBean> complist = null;
        
        // Imposta le intestazioni HTTP per indicare che il contenuto è un file PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"fattura.pdf\"");
        
        // Ottieni il flusso di output per scrivere il contenuto del documento
        OutputStream outStream = response.getOutputStream();

        // Istanza il documento
        Document documento = new Document();
        try {
            Integer id = Integer.parseInt(request.getParameter("acquisto")); // Prendo l'id dell'acquisto
            
            //mi ricavo tutti i dati necessari per scrivere la fattura
            acquisto = modelacquisto.doRetrieveByKey(id); // Prelevo l'acquisto dal database
            complist =  compmodel.doRetrieveByAcq(id); // Altri dettagli sull'acquisto (List<Composizione>)
            iva = complist.get(0).getIva();
            consumer = acquisto.getConsumer();
            address = acquisto.getIndirizzo();
                        	          
            // Crea il documento PDF
            PdfWriter writer = PdfWriter.getInstance(documento, outStream);
            
            // Apri il documento
            documento.open();
            
           // Create a blue color with a hint of aqua
            BaseColor color = new BaseColor(0, 128, 128); // RGB values: 0-255

            // Create fonts
            Font Font20BoldTitle = new Font(Font.FontFamily.HELVETICA, 30, Font.ITALIC);
            Font Font20Bold = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Font20Bold.setColor(color);
            Font Font12Bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font12Bold.setColor(color);
            Font Font15Bold = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
            Font15Bold.setColor(color);
            Font Font10 = new Font(Font.FontFamily.HELVETICA, 10);
            Font Font10Bold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
            Font Font12BoldBlack = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            
            //inserisco FATTURA#idfattura in alto a destra
            PdfContentByte canvas = writer.getDirectContent();


         // Aggiungi il testo principale
         ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase("MUSICAL STORE", Font20BoldTitle),
                             documento.right(), documento.top(), 0);

            ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase("MUSICAL STORE", Font20BoldTitle),
                    documento.right(), documento.top(), 0);
            
            //inserisco nome del cliente e indirizzo in alto a sinistra
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(consumer.getNome()+" "+consumer.getCognome(), Font12Bold),
                    documento.left(), documento.top(), 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Via "+address.getVia()+" "+address.getCivico(), Font10),
                    documento.left(), documento.top() - 20, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(address.getCap()+" "+address.getCitta(), Font10),
                    documento.left(), documento.top() - 32, 0);
            
            //INDIRIZZO DA DOVE VIENE CREATA LA FATTURA (l'indirizzo è generico)
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Indirizzo di fatturazione:", Font12Bold),
                    documento.left(), documento.top() - 80, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Via Marco Polo 129", Font10),
                    documento.left(), documento.top() - 95, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("00042 Roma (RM)", Font10),
                    documento.left(), documento.top() - 107, 0);
            
            //INDIRIZZO DI INVIO ORDINE
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Da inviare a:", Font12Bold),
                    documento.left() + 200, documento.top() - 80, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Via "+address.getVia()+" "+address.getCivico(), Font10),
                    documento.left() + 200, documento.top() - 95, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(address.getCap()+" "+address.getCitta(), Font10),
                    documento.left() + 200, documento.top() - 107, 0);
            
            
            // Create a Date instance for the current date
            Date currentDate = new Date();

            // Create a SimpleDateFormat instance with the desired format
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Format the current date as a string
            String formattedDate = dateFormat.format(currentDate);
                
            //DATI FATTURA
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("FATTURA N:", Font12Bold),
                    documento.right() - 180, documento.top() - 67, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Data Fattura: ", Font12Bold),
                    documento.right() - 180, documento.top() - 87, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Data di scadenza: ", Font12Bold),
                    documento.right() - 180, documento.top() - 107, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+(idFattura++), Font10),
                    documento.right() - 50, documento.top() - 67, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(formattedDate, Font10),
                    documento.right() - 50, documento.top() - 87, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(formattedDate, Font10),
                    documento.right() - 50, documento.top() - 107, 0);
            
            //TABELLA ARTICOLI
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("TABELLA AQUISTI", Font20Bold),
                    documento.left() + 170, documento.top() - 180, 0);
                        
            PdfPTable table = new PdfPTable(4); // Numero di colonne: 4
                       
            // Set the minimum width for each cell
            table.setWidths(new float[]{50f, 150f, 100f, 80f}); 
            
             // Define a custom light gray color
            BaseColor customLightGray = new BaseColor(245, 245, 245); // Adjust RGB values as needed

            // Creating the header cells
            PdfPCell headerCell1 = new PdfPCell(new Phrase("QTA'", Font12BoldBlack));
            PdfPCell headerCell2 = new PdfPCell(new Phrase("DESCRIZIONE", Font12BoldBlack));
            PdfPCell headerCell3 = new PdfPCell(new Phrase("PREZZO PER UNITA'", Font12BoldBlack));
            PdfPCell headerCell4 = new PdfPCell(new Phrase("VALORE", Font12BoldBlack));
            
            // Set the text alignment to center
            headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            headerCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            headerCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);   

            // Set the background color to custom light gray
            headerCell1.setBackgroundColor(customLightGray);
            headerCell2.setBackgroundColor(customLightGray);
            headerCell3.setBackgroundColor(customLightGray);
            headerCell4.setBackgroundColor(customLightGray);
            
            // Imposta il padding interno per le celle
            headerCell1.setPadding(5f);
            headerCell2.setPadding(5f);
            headerCell3.setPadding(5f);
            headerCell4.setPadding(5f);

            // Imposta l'altezza fissa per le celle
            headerCell1.setFixedHeight(40f);
            headerCell2.setFixedHeight(40f);
            headerCell3.setFixedHeight(40f);
            headerCell4.setFixedHeight(40f);

            
            // Adding the header cells to the table
            table.addCell(headerCell1);
            table.addCell(headerCell2);
            table.addCell(headerCell3);
            table.addCell(headerCell4);
            
            //ADD BODY OF TABLE
            double sum = 0;
            for(ComposizioneBean c : complist) {
            	
            	DecimalFormat df1 = new DecimalFormat("#0.00");
            	String SumFormatted = df1.format(c.getArticolo().getPrezzoBase()*c.getqAcquistate());
            	
            	// Sostituisci la virgola con un punto nella stringa
                SumFormatted = SumFormatted.replace(",", ".");
            	
            	PdfPCell Cell1 = new PdfPCell(new Phrase(""+c.getqAcquistate(), Font10));
                PdfPCell Cell2 = new PdfPCell(new Phrase(""+c.getArticolo().getName(), Font10));
                PdfPCell Cell3 = new PdfPCell(new Phrase(""+c.getArticolo().getPrezzoBase()+" euro", Font10));
                PdfPCell Cell4 = new PdfPCell(new Phrase(""+SumFormatted+" euro", Font10));
                
                // Set the text alignment to center
                Cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

                Cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                Cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

                Cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

                Cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                Cell4.setVerticalAlignment(Element.ALIGN_MIDDLE); 
                               
                // Imposta il padding interno per le celle
                Cell1.setPadding(5f);
                Cell2.setPadding(5f);
                Cell3.setPadding(5f);
                Cell4.setPadding(5f);

                // Imposta l'altezza fissa per le celle
                Cell1.setFixedHeight(40f);
                Cell2.setFixedHeight(40f);
                Cell3.setFixedHeight(40f);
                Cell4.setFixedHeight(40f);
                
                table.addCell(Cell1);
                table.addCell(Cell2);
                table.addCell(Cell3);
                table.addCell(Cell4);
                
                sum += c.getArticolo().getPrezzoBase()*c.getqAcquistate();
                           
            }
            
            //SOMMA PREZZI, IVA, TOTALE
            PdfPCell EmptyCell = new PdfPCell(); 
            EmptyCell.setBorder(Rectangle.NO_BORDER);
            
            Paragraph phrase = new Paragraph();           
            phrase.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase.add(new Phrase("Somma:  ",Font10Bold));
            phrase.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase.add(new Phrase("iva:  ", Font10Bold));
            phrase.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase.add(new Phrase("TOTALE:  ", Font12Bold));
            phrase.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea

            PdfPCell lastCell1 = new PdfPCell(phrase);
            lastCell1.setPadding(5f);
            lastCell1.setFixedHeight(80f);
            lastCell1.setBorder(Rectangle.NO_BORDER);
            lastCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);

            Paragraph phrase2 = new Paragraph();
            phrase2.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            
            DecimalFormat df = new DecimalFormat("#0.00");
            String SumFormatted = df.format(sum);
            String TotaleFormatted = df.format(acquisto.getImporto());

            // Sostituisci la virgola con un punto nella stringa
            SumFormatted = SumFormatted.replace(",", ".");
            TotaleFormatted = TotaleFormatted.replace(",", ".");
           
            phrase2.add(new Phrase(""+SumFormatted+" euro",Font10));
            phrase2.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase2.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase2.add(new Phrase(""+iva+"%", Font10));
            phrase2.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase2.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            phrase2.add(new Phrase(""+TotaleFormatted+" euro", Font12Bold));
            phrase2.add(Chunk.NEWLINE); // Aggiunge un carattere di nuova linea
            
            PdfPCell lastCell2 = new PdfPCell(phrase2);
            lastCell2.setPadding(5f);
            lastCell2.setFixedHeight(80f);
            lastCell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table.addCell(EmptyCell);
            table.addCell(EmptyCell);
            table.addCell(lastCell1);
            table.addCell(lastCell2);
            
            //li ho aggiunti per consentire l'inserimento della tabella in una posizione più bassa
            //ho avuto problemi con il posizionamento della tabella. perciò ho usato questo intermezzo
            
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            
            // Aggiungi la tabella al documento
            documento.add(table);
            
            //ULTIMI RITOCCHI
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("TERMINI E CONDIZIONI",Font12Bold),
                    documento.left(), documento.bottom() + 15, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("il pagamento è dovuto entro 15 giorni",Font10),
                    documento.left(), documento.bottom(), 0);
            
        } catch (DocumentException e) {
            e.printStackTrace();
            response.sendError(500,"Errore nella scrittura del documento");
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            response.sendError(500,"Errore nella conversione dell'id");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500,"Errore nell'esecuzione della query");
        } finally {
            // Chiudi il documento
            documento.close();
            
            // Svuota e chiudi il flusso di output
            outStream.flush();
            outStream.close();
        }
    }
}
