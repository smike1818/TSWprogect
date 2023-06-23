package bean;

public class TelefonoBean {
     private String numero;
     private String prefisso;
     private UserBean user;
     private boolean isPrimary = false;
     
     public void setNumero(String n) {
    	 numero=n;
     }
     
     public String getNumero() {
    	 return numero;
     }
     
     public void setPrefisso(String p) {
    	 prefisso=p;
     }
     
     public String getPrefisso() {
    	 return prefisso;
     }
     
     public void setUtente(UserBean u) {
    	 user=u;
     }
     
     public UserBean getUtente() {
    	 return user;
     }
     
     public void setIsPrimary(boolean isp) {
    	 isPrimary=isp;
     }
     
     public boolean getIsPrimary() {
    	 return isPrimary;
     }
}
