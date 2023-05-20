package bean;
/*
 * crea istanze per ogni articolo  
 */

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
    
    public double getPrezzo() {
   	 return prezzo;
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
    
    
   
}

