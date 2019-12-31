package managerUtente;

public class IndirizzoDiSpedizioneBean implements Comparable<IndirizzoDiSpedizioneBean>{
	private String Id,Indirizzo,EmailCliente;

	public IndirizzoDiSpedizioneBean() {

	}
	public String getEmailCliente() {
		return EmailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		EmailCliente = emailCliente;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}

	public boolean validate() {
		return checkIndirizzo(this.Indirizzo);
	}

	private boolean checkIndirizzo(String indirizzo) {
		if(indirizzo == null)
			return false;
		if(indirizzo.length() > 50 || indirizzo.length() < 1)
			return false;
		else
			return true;
	}
	@Override
	public int compareTo(IndirizzoDiSpedizioneBean o) {
		return this.Indirizzo.compareToIgnoreCase(o.getIndirizzo());
	}
}
