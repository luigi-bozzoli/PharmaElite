package managerUtente;

import java.util.regex.Pattern;

public class ClienteBean {
	private String email,password;
	private boolean admin;
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean tipo) {
		this.admin = tipo;
	}

	public boolean validate() {
		boolean email = this.checkEmail() ;
		boolean password = this.checkPassword();
		
		return email && password;
	}
	
	private boolean checkEmail() {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 

		Pattern pat = Pattern.compile(emailRegex); 

		if (email == null) 
			return false; 

		return pat.matcher(email).matches(); 
	}
	
	private boolean checkPassword() {
		if(this.password == null)
			return false;

		if(this.password.length() > 20)
			return false;

		if(!this.password.matches("^[a-zA-Z0-9]+$"))
			return false;

		return true;
	}
}
