package bean;
/*
 * crea istanze per ogni articolo  
 */
import java.text.DecimalFormat;


public class ArticoloBean {
    private int id;
    private String nome;
    private double prezzo;
    private int quantita;
    private String colore;
    private String descrizione;
    private String marca;
    private int tipo;
    private int corde = -1;
    private String tipologia;
    private CategoriaBean cat = null;
    private IvaBean iva;
 

    public void setID(int i) {
    	id=i;
    }
    
    public int getID() {
   	 return id;
    }
    
    public void setTipo(int i) {
   	 tipo=i;
    }
    
    public int getTipo() {
   	 return tipo;
    }
    
    public void setCorde(int i) {
   	 corde=i;
    }
    
    public int getCorde() {
   	 return corde;
    }
    
    public void setQuantita(int i) {
   	 quantita=i;
    }
    
    public int getQuantita() {
   	 return quantita;
    }
    
    public void setPrezzo(double i) {
   	 prezzo=i;
    }
    
    //arrotondo a 2 numeri dopo la virgola
    //calcolo il prezzo da mostrare sul catalogo
    public double getPrezzo() {
        double importoIva = (prezzo) * (iva.getPercentuale() / 100);
        double totale = prezzo + importoIva;

        DecimalFormat df = new DecimalFormat("#0.00");
        String totaleFormatted = df.format(totale);

        // Sostituisci la virgola con un punto nella stringa
        totaleFormatted = totaleFormatted.replace(",", ".");

        return Double.parseDouble(totaleFormatted);
    }
    
    public double getPrezzoBase() {
    	DecimalFormat df = new DecimalFormat("#0.00");
        String totaleFormatted = df.format(prezzo);

        // Sostituisci la virgola con un punto nella stringa
        totaleFormatted = totaleFormatted.replace(",", ".");

        return Double.parseDouble(totaleFormatted);
    }
  
    public void setNome(String n) {
   	 nome=n;
    }
    
    public String getNome() {
   	 return nome;
    }
    
    public void setDescrizione(String n) {
   	 descrizione=n;
    }
    
    public String getDescrizione() {
   	 return descrizione;
    }
    
    public void setColore(String n) {
   	 colore=n;
    }
    
    public String getColore() {
   	 return colore;
    }
    
    public void setMarca(String n) {
   	 marca=n;
    }
    
    public String getMarca() {
   	 return marca;
    }
    
    public void setTipologia(String n) {
   	 tipologia=n;
    }
    
    public String getTipologia() {
   	 return tipologia;
    }
    
    public void setName(String n) {
      	nome=n;
    }
       
    public String getName() {
      	 return nome;
    }
    
    public void setCategoria(CategoriaBean c) {
    	this.cat=c;
    }
    
    public CategoriaBean getCategoria(){
   	 return cat;
    }
    
    public void setIva(IvaBean i) {
    	iva=i;
    }
    
    public IvaBean getIva() {
    	return iva;
    }
    
   
}

