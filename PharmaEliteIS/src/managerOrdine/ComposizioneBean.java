package managerOrdine;

public class ComposizioneBean {
	private String IdProdotto, IdOrdine;
	private int quantita;
	
	public ComposizioneBean() {

	}
	public String getIdProdotto() {
		return IdProdotto;
	}
	public void setIdProdotto(String idProdotto) {
		IdProdotto = idProdotto;
	}
	public String getIdOrdine() {
		return IdOrdine;
	}
	public void setIdOrdine(String idOrdine) {
		IdOrdine = idOrdine;
	}
	
	public int getQuantita() {
		return this.quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
