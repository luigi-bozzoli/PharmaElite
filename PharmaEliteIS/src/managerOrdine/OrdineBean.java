package managerOrdine;

import java.util.Date;

public class OrdineBean {
	private String Id, EmailCliente;
	private Date DataOrdine;
	private double costo;
	
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
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}

}
