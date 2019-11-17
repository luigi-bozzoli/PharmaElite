package model.beans;

public class MetodoDiPagamentoBean {
	private String numCarta, tipo, emailCliente;
	
	public MetodoDiPagamentoBean() {}

	public String getNumCarta() {
		return numCarta;
	}

	public void setNumCarta(String numCarta) {
		this.numCarta = numCarta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
}
