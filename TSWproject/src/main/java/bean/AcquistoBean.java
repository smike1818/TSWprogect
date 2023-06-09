package bean;

import java.text.DecimalFormat;

public class AcquistoBean {
	 private int idAcquisto;
	 private UserBean consumer = null;
	 private ContoBean conto = null;
	 private String date = null;
	 private IndirizzoBean indirizzo = null;
	 private double importo;
	 
	 public void setID(int id) {
		 idAcquisto=id;
	 }
	 
	 public int getID() {
		 return idAcquisto;
	 }
	 
	 public void setConsumer(UserBean c) {
		 consumer=c;
	 }
	 
	 public UserBean getConsumer() {
		 return consumer;
	 }
	 
	 public void setConto(ContoBean c) {
		 conto=c;
	 }
	 
	 public ContoBean getConto() {
		 return conto;
	 }
	 
	 public void setDate(String d) {
		 date=d;
	 }
	 
	 public String getDate() {
		 return date;
	 }
	 
	 public void setImporto(double i) {
		 importo=i;
	 }
     
     public double getImporto() {
    	 DecimalFormat df = new DecimalFormat("#0.00");
         String totaleFormatted = df.format(importo);

         // Sostituisci la virgola con un punto nella stringa
         totaleFormatted = totaleFormatted.replace(",", ".");

         return Double.parseDouble(totaleFormatted);
     }

	public void setIndirizzo(IndirizzoBean ind) {
		indirizzo = ind;
	}
	
	public IndirizzoBean getIndirizzo() {
		return indirizzo;
	}
     
}
