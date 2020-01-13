package model.beans;

public class ClienteBean {
private String email,password;
private boolean tipo;
public ClienteBean() {
	
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public boolean isTipo() {
	return tipo;
}
public void setTipo(boolean tipo) {
	this.tipo = tipo;
}

}
