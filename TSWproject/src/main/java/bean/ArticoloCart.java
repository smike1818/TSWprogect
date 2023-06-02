package bean;

/*
 * ArticoloCart è una classe che istanzia oggetti che andranno inseriti
 * nel carrello. 
 */

public class ArticoloCart {
    private ArticoloBean bean;
    private int qCorrente;   //quantità inserita nel carrello
    private int qTotale;     //quantità totale del bean;
    
    public ArticoloCart(ArticoloBean b) {
    	bean=b;
    	qCorrente=1;
    	qTotale = b.getQuantita();	
    }
    
    public void decrementa() {
    	if(qCorrente>1)
    		qCorrente--;
    }
    public void incrementa() {
     if(qCorrente<qTotale)
        qCorrente++;	
    }
    
    public void setBean(ArticoloBean ab) {
    	bean=ab;
    }
    
    public ArticoloBean getBean() {
    	return bean;
    }
    
    //la quantità corrente deve essere inferiore o uguale 
    //a quella totale 
    
    public boolean setQCorrente(int q) {
    	if(q<=qTotale-qCorrente)
    	   qCorrente+=q;
    	else
    		return false;
    	return true;
    }
    
    public int getQTotale() {
    	return qTotale;
    }
    
    public int getQCorrente() {
    	return qCorrente;
    }
    
    //ovverride del metodo equals di Object per confrontare
    //gli 'id' di 2 ArticoloBean
    
    public boolean equals(ArticoloCart ac) {
    	return this.bean.getID() == ac.bean.getID();
    }
}
