package bean;

public class IndirizzoBean {
     private String via;
     private int civico;
     private String citta;
     private int cap;
     private UserBean cliente = null;
     
     public void setVia(String v) {
    	  via=v;
     }
     
     public String getVia() {
    	 return via;
     }
     
     public void setCitta(String v) {
   	     citta=v;
     }
    
     public String getCitta() {
   	    return citta;
     }
     
     public void setCivico(int v) {
   	    civico=v;
     }
    
     public int getCivico() {
   	    return civico;
     }
     
     public void setCap(int v) {
    	cap=v;
     }
     
     public int getCap() {
    	return cap;
     }
     
     public void setCliente(UserBean c) {
    	    cliente=c;
      }
     
      public UserBean getCliente() {
    	    return cliente;
      }
}
