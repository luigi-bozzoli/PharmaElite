package managerUtente;

import managerCatalogo.ProdottoBean;

public class MetodoDiPagamentoBean implements Comparable<MetodoDiPagamentoBean>{
	private String NumeroCarta, TipoCarta, EmailCliente;
	public MetodoDiPagamentoBean() {

	}
	public String getNumeroCarta() {
		return NumeroCarta;
	}
	public void setNumeroCarta(String numeroCarta) {
		NumeroCarta = numeroCarta;
	}
	public String getEmailCliente() {
		return EmailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		EmailCliente = emailCliente;
	}
	public String getTipoCarta() {
		return TipoCarta;
	}
	public void setTipoCarta(String tipoCarta) {
		TipoCarta = tipoCarta;
	}
	
	public boolean validate() {
		boolean tipoCarta = checkTipoCarta(this.TipoCarta);
		boolean numeroCarta = checkNumero(this.NumeroCarta);
		
		return tipoCarta && numeroCarta;
	}
	
	private boolean checkNumero(String num) {
		if(num == null)
			return false;
		
		if(num.length() != 16)
			return false;
		
		return num.matches("[0-9]+");
	}
	
	private boolean checkTipoCarta(String t) {
		if(t == null)
			return false;

		if(t.equalsIgnoreCase("MasterCard") || t.equalsIgnoreCase("Visa") || t.equalsIgnoreCase("American Express"))
			return true;

		return false;
	}
	@Override
	public int compareTo(MetodoDiPagamentoBean o) {
		return this.NumeroCarta.compareToIgnoreCase(o.NumeroCarta);
	}

	public boolean equals(Object o) {
		  if (o == null) return false;
		    if (o == this) return true;
		    if (!(o instanceof MetodoDiPagamentoBean))return false;
		    MetodoDiPagamentoBean m = (MetodoDiPagamentoBean) o;

		    boolean email = this.EmailCliente.equalsIgnoreCase(m.getEmailCliente());
		    boolean numCarta =  this.NumeroCarta.equalsIgnoreCase(m.getNumeroCarta());
		    boolean tipo = this.TipoCarta.equalsIgnoreCase(m.TipoCarta);
		    
		  
		    
		    return email && numCarta && tipo;
	}
}
