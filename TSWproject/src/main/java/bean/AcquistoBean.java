package bean;

import java.util.Calendar;
import java.util.Date;

public class AcquistoBean {
	 private UserBean consumer = null;
	 private ContoBean conto = null;
	 private Calendar date = Calendar.getInstance();
	 private double importo;
	 
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
	 
	 public Date getDate() {
		 return date.getTime();
	 }
	 
	 public void setImporto(double i) {
		 importo=i;
	 }
     
     public double getImporto() {
    	 return importo;
     }
}
