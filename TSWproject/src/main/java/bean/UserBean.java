package bean;

public class UserBean{
private int id;	
private String username;
private String password;
private String Nome;
private String Cognome;
private String CF;
private boolean valid;
	


public String getNome() {
	return Nome;
}

public void setNome(String newName) {
	Nome=newName;
}

public String getCognome() {
	return Cognome;
	}

public void setCognome(String newCognome) {
	Cognome=newCognome;
}

public int getId() {
	return id;
}

public void setId(int newId) {
	id=newId;
}

public String getCF() {
	return CF;}

public void setCF(String newCf){
	CF=newCf;
}

public String getPassword() {
	return password;
}

public void setPassword(String newPassword) {
	password=newPassword;
}

public String getUsername() {
	return username;
}

public void setUsername(String newUsername) {
	username=newUsername;
}

public boolean isValid() {
	return valid;
}

public void setValid(boolean newValid) {
	valid=newValid;
}
}
