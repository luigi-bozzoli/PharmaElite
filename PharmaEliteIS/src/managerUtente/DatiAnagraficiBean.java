package managerUtente;

public class DatiAnagraficiBean {
	private String EmailCliente, Telefono, Nome,Cognome,Citta,Sesso;

	public DatiAnagraficiBean() {

	}
	public String getCitta() {
		return Citta;
	}

	public void setCitta(String citta) {
		Citta = citta;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getSesso() {
		return Sesso;
	}
	public void setSesso(String sesso) {
		Sesso = sesso;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getEmailCliente() {
		return EmailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		EmailCliente = emailCliente;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public boolean validate() {
		boolean telefono = checkNumTel(this.Telefono);
		boolean nome = checkNome(this.Nome);
		boolean cognome = checkNome(this.Cognome);
		boolean citta = checkCitta(this.Citta);
		boolean sesso = checkSesso(this.Sesso);
		
		return telefono && nome && cognome && citta && sesso ;
	}
	
	private boolean checkSesso(String sesso) {
		if(sesso == null)
			return false;
		if(sesso.equalsIgnoreCase("uomo") || sesso.equalsIgnoreCase("donna"))
			return true;
		else 
			return false;
	}
	
	private boolean checkNome(String nome) {
		if(nome == null)
			return false;
		if(nome.length() < 1 || nome.length() > 30)
			return false;
		return isAlpha(nome);
	}
	
	private boolean checkCitta(String citta) {
		if(citta == null)
			return false;

		return isAlpha(citta);
	}
	
	private boolean checkNumTel(String num) {
		if (num == null)
			return false;
		if(num.length() != 10)
			return false;

		return isNumber(num);
	}
	
	private boolean isNumber(String num) {
		return num.matches("[0-9]+");
	}
	
	private boolean isAlpha(String s) {
		char[] chars = s.toCharArray();

		for (char c : chars) {
			if(!Character.isLetter(c)) {
				return false;
			}
		}

		return true;
	}

}
