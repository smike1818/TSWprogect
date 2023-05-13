package bean;

public class UserBean{
	private String username;
	private String password;
	private String Nome;
	private String Cognome;
	private String CF;
	private String email;
	private String priviledges;
	private boolean valid;
		
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String Nome) {
		this.Nome=Nome;
	}
	
	public String getCognome() {
		return Cognome;
	}
	
	public void setCognome(String Cognome) {
		this.Cognome=Cognome;
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String CF){
		this.CF=CF;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid=valid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPriviledges() {
		return priviledges;
	}

	public void setPriviledges(String priviledges) {
		this.priviledges = priviledges;
	}
}
