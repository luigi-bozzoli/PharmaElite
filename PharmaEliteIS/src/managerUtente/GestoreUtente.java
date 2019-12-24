package managerUtente;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import managerCarrello.CarrelloBean;
import managerCarrello.CarrelloDAO;
import managerCarrello.CarrelloDAOImpl;
import managerOrdine.ComposizioneBean;

public class GestoreUtente {

	private DatiAnagraficiBean datiAnagrafici;
	private MetodoDiPagamentoBean metodoPagamento;
	private IndirizzoDiSpedizioneBean indirizzoSpedizione;
	



	public ClienteBean login(String email, String password, List<CarrelloBean> carrello) {
		ClienteBeanDAO cDao = new ClienteBeanDAOImpl ();
		ClienteBean cliente;

		cliente = cDao.login(email, password);

		if(cliente != null) {

			salvaCarrello(carrello, email);
			return cliente;

		}else {
			return null;
		}
	}


	public void registrazione(String email, String password)	throws ValidationException {
		
		boolean datiAnagrafici = this.datiAnagrafici.validate();
		boolean metodoPagamento = this.metodoPagamento.validate();
		boolean indirizzoSpedizione = this.indirizzoSpedizione.validate();
		boolean emailBool = this.controllaEsistenzaEmail(email);
		
		metodoPagamento = metodoPagamento && this.checkNumeroCarta();
		
		
		if(datiAnagrafici && metodoPagamento && indirizzoSpedizione && emailBool) {
			ClienteBeanDAO cDao = new ClienteBeanDAOImpl();
			ClienteBean c = new ClienteBean();
			c.setEmail(email);
			c.setPassword(password);
			c.setAdmin(false);
			cDao.doSave(c);
			
			
			MetodoDiPagamentoBeanDAO cartaDao = new MetodoDiPagamentoBeanDAOImpl();
			this.metodoPagamento.setEmailCliente(email);
			cartaDao.doSave(this.metodoPagamento);
			
			IndirizzoDiSpedizioneBeanDAO indirizzoDao = new IndirizzoSpedizioneBeanDAOImpl();
			this.indirizzoSpedizione.setId(this.generaID());
			this.indirizzoSpedizione.setEmailCliente(email);
			indirizzoDao.doSave(this.indirizzoSpedizione);
			
		}else {
			throw new ValidationException("Formato non corretto");
		}
		
	}
	
	public void userpage(String emailCliente) {
		DatiAnagraficiBeanDAO datiAnagraficiDao = new DatiAnagraficiBeanDAOImpl();
		this.datiAnagrafici = datiAnagraficiDao.doRetrieveByKey(emailCliente);
		
		IndirizzoDiSpedizioneBeanDAO indDao = new IndirizzoSpedizioneBeanDAOImpl();
		this.indirizzoSpedizione = indDao.doRetrieveByKey(emailCliente);
		
		
	}
	
	public boolean controllaEsistenzaEmail(String email) {
		
		if(email == null)
			return false;
		
		ClienteBeanDAO cDao = new ClienteBeanDAOImpl();
		ClienteBean cliente = cDao.doRetrieveByKey(email);
		
		if(cliente == null)
			return true;
		else
			return false;		
	}
	
	private boolean checkNumeroCarta() {
		MetodoDiPagamentoBeanDAO cartaDao = new MetodoDiPagamentoBeanDAOImpl();
		MetodoDiPagamentoBean carta = cartaDao.doRetrieveByKey(this.metodoPagamento.getNumeroCarta());

		if(carta == null)
			return true;
		else
			return false;
	}

	private void salvaCarrello(List<CarrelloBean> carrello, String email) {

		if(carrello == null) {
			return;
		}else {

			CarrelloBean prodottoCarrello = new CarrelloBean();
			CarrelloDAO carrDao = new CarrelloDAOImpl();
			carrDao.deleteAllByEmail(email);

			Iterator<CarrelloBean> it = carrello.iterator();

			while(it.hasNext()) {
				prodottoCarrello = it.next();
				prodottoCarrello.setEmailCliente(email);
				carrDao.doSave(prodottoCarrello);
			}
		}

	}
	
	public MetodoDiPagamentoBean getMetodoPagamento() {
		return metodoPagamento;
	}


	public void setMetodoPagamento(MetodoDiPagamentoBean metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}


	public IndirizzoDiSpedizioneBean getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}


	public void setIndirizzoSpedizione(IndirizzoDiSpedizioneBean indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}


	public DatiAnagraficiBean getDatiAnagrafici() {
		return datiAnagrafici;
	}


	public void setDatiAnagrafici(DatiAnagraficiBean datiAnagrafici) {
		this.datiAnagrafici = datiAnagrafici;
	}
	
	private String generaID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID.substring(0,9);
	}


}
