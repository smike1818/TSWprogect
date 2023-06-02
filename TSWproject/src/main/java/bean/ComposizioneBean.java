package bean;

public class ComposizioneBean {
     private ArticoloBean art = null;
     private AcquistoBean acq = null;
     private int iva = 22;
     private double prezzo;
     private int qAcquistate;
     
     public void setqAcquistate(int q) {
		qAcquistate=q;
	 }
	 
	 public int getqAcquistate() {
		 return qAcquistate;
	 }
       
     public void setAcquisto(AcquistoBean i) {
		 acq=i;
	 }
	 
	 public AcquistoBean getAcquisto() {
		 return acq;
	 }
	 
	 public void setArticolo(ArticoloBean a) {
		 art=a;
	 }
	 
	 public ArticoloBean getArticolo() {
		 return art;
	 }
	 
	 public void setIva(int n) {
	    iva=n;	 
	 }
	 
	 public int getIva() {
		 return iva;
	 }
	 
	 public void setPrezzo(double p) {
		 prezzo = p;
	 }
    
     public double getPrezzo() {
   	    return prezzo;
     }
}
