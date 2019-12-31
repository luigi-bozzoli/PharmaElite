package managerOrdine;

import java.util.Date;
import java.util.Set;

public class OrdineBean implements Comparable<OrdineBean>{
	private String Id, EmailCliente;
	private Date DataOrdine;
	private double costo;
	private Set<String> listaProdotti;
	
	public OrdineBean() {

	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getEmailCliente() {
		return EmailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		EmailCliente = emailCliente;
	}
	public Date getDataOrdine() {
		return DataOrdine;
	}
	public void setDataOrdine(Date dataOrdine) {
		DataOrdine = dataOrdine;
	}
	public Set<String> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(Set<String> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public int compareTo(OrdineBean o) {
		if(this.DataOrdine.before(o.getDataOrdine())) {
			return 1;
		}else{
			return -1;
		}
		
	}

}
