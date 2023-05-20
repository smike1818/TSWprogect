package bean;

public class CategoriaBean {
     private int IDcat;
     private String nome;
     private String descrizione;
     
     public void setID(int i) {
     	IDcat =i;
     }
     
     public int getID() {
    	 return IDcat;
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
}
