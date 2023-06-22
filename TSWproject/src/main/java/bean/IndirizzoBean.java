package bean;

public class IndirizzoBean {
     private String via = null;
     private int civico ;
     private String citta = null;
     private int cap;
     private UserBean cliente = null;
     private boolean isPrimary = false;
     private boolean empty = true;
     
     public void setIsPrimary(boolean value) {
    	 this.isPrimary = value;
     }
     
     public boolean getIsPrimary() {
    	 return this.isPrimary;
     }
     
     public void setVia(String v) {
    	  via=v;
    	  empty=false;
     }
     
     public String getVia() {
    	 return via;
     }
     
     public void setCitta(String v) {
   	     citta=v;
   	     empty=false;
     }
    
     public String getCitta() {
   	    return citta;
     }
     
     public void setCivico(int v) {
   	    civico=v;
   	    empty=false;
     }
    
     public int getCivico() {
   	    return civico;
     }
     
     public void setCap(int v) {
    	cap=v;
    	empty=false;
     }
     
     public int getCap() {
    	return cap;
     }
     
     public void setCliente(UserBean c) {
    	    cliente=c;
    	    empty=false;
      }
     
      public UserBean getCliente() {
    	    return cliente;
      }
      
      public boolean isEmpty() {
    	  return empty;
      }
}
