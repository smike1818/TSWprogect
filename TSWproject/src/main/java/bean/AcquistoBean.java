package bean;

public class AcquistoBean {
	 private int idAcquisto;
	 private UserBean consumer = null;
	 private ContoBean conto = null;
	 private String date = null;
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
	 
	 public String getDate() {
		 return date;
	 }
	 
	 public void setImporto(double i) {
		 importo=i;
	 }
     
     public double getImporto() {
    	 return importo;
     }
     
}
