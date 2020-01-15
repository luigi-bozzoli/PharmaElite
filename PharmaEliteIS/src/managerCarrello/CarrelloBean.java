package managerCarrello;

import managerCatalogo.ProdottoBean;

public class CarrelloBean implements Comparable<CarrelloBean> {
	private String emailCliente, idProdotto;
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getIdProdotto() {
		return idProdotto;
	}
	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	private int quantita;
	@Override
	public int compareTo(CarrelloBean o) {
		if(this.quantita >= o.getQuantita()) 
			return 1;
		if(this.quantita < o.getQuantita())
			return -1;
		return 0;
	}
	
	public boolean equals(Object o) {
		  if (o == null) return false;
		    if (o == this) return true;
		    if (!(o instanceof CarrelloBean))return false;
		    CarrelloBean c = (CarrelloBean) o;

		    boolean email = this.getEmailCliente().equalsIgnoreCase(c.getEmailCliente());
		    boolean id = this.getIdProdotto().equalsIgnoreCase(c.getIdProdotto());
		    boolean q = this.getQuantita() == c.getQuantita();
		  
		    
		    return id && email && q;
	}
}
