package bean;

public class ContoBean {
    private UserBean intestatario = null;
    private String IBAN;
    private String num_carta;
    private String cvv;
    private boolean isPrimary = false;
    
    public void setIntestatario(UserBean i) {
		 intestatario=i;
	 }
	 
	 public UserBean getIntestatario() {
		 return intestatario;
	 }
	 
	 public void setIBAN(String c) {
		 IBAN=c;
	 }
	 
	 public String getIBAN() {
		 return IBAN;
	 }
	 
	 public void setNumCarta(String n) {
	    num_carta=n;	 
	 }
	 
	 public String getNumCarta() {
		 return num_carta;
	 }
	 
	 public void setCvv(String c) {
		 cvv=c;
	 }
    
     public String getCvv() {
   	    return cvv;
     }
     
     public void setIsPrimary(boolean value) {
    	 this.isPrimary = value;
     }
     
     public boolean getIsPrimary() {
    	 return this.isPrimary;
     }
}
