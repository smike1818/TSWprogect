package bean;

public class ImageBean {
     private String path;
     private ArticoloBean art = null;
     private String nome;
     
     public void setPath(String p) {
    	 path=p;
     }
     
     public String getPath() {
    	 return path;
     }
     
     public void setArticolo(ArticoloBean ab) {
    	 art=ab;
     }
     
     public ArticoloBean getArticolo() {
    	 return art;
     }
     
     public String getNome() {
    	 return nome;
     }
     
     public void setNome(String p) {
    	 nome=p;
     }
     
}
