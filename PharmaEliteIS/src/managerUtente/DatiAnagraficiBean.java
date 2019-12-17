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

}
