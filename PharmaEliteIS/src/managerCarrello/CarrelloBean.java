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
}
