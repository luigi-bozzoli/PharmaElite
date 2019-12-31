package managerOrdine;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import managerCarrello.CarrelloBean;
import managerCarrello.CarrelloBeanDAO;
import managerCarrello.CarrelloBeanDAOImpl;
import managerCatalogo.ProdottoBean;
import managerCatalogo.ProdottoBeanDAO;
import managerCatalogo.ProdottoBeanDAOImpl;
import managerUtente.IndirizzoDiSpedizioneBean;
import managerUtente.IndirizzoDiSpedizioneBeanDAO;
import managerUtente.IndirizzoSpedizioneBeanDAOImpl;
import managerUtente.MetodoDiPagamentoBean;
import managerUtente.MetodoDiPagamentoBeanDAO;
import managerUtente.MetodoDiPagamentoBeanDAOImpl;

public class GestoreOrdine {

	private Set<OrdineBean> listaOrdini;
	private Set<CarrelloBean> carrello;
	private Set<String> prodotti;
	private Set<MetodoDiPagamentoBean> metodiDiPagamento;
	private Set<IndirizzoDiSpedizioneBean> indirizziDiSpedizione;
	private double prezzo;
	
	public GestoreOrdine() {
		this.listaOrdini = new TreeSet<OrdineBean>();
		this.carrello = new TreeSet<CarrelloBean>();
		this.prodotti = new TreeSet<String>();
		this.metodiDiPagamento = new TreeSet<MetodoDiPagamentoBean>();
		this.indirizziDiSpedizione = new TreeSet<IndirizzoDiSpedizioneBean>();
		this.prezzo = 0;
	}
	
	public void cronologiaOrdini(String email) {
		OrdineBeanDAO ordineDao  = new OrdineBeanDAOImpl();
		ProdottoNellordineBeanDAO prodottoOrdineDao = new ProdottoNellOrdineBeanDAOImpl();
		
		this.listaOrdini =  ordineDao.retriveAll(email);
		
		Iterator<OrdineBean> i = this.listaOrdini.iterator();
		
		while(i.hasNext()) {
			OrdineBean o = i.next();
			o.setListaProdotti(prodottoOrdineDao.retriveProductNames(o.getId()));
		}
	}

	public void ordinaOra(String tipoSpedizione, String emailCliente, String indirizzo, String metodoPagamento) throws ValidationException {

		if(!checkInd(indirizzo, emailCliente) && !checkMetodoPagamento(metodoPagamento, emailCliente)) {
			throw new ValidationException("");
		}

		CarrelloBeanDAO carrello = new CarrelloBeanDAOImpl();
		this.carrello = carrello.retriveAll(emailCliente);
		//genera data
		Date data = new Date();
		//java.sql.Date datasql = new java.sql.Date(data.getTime());

		this.calcolaCostoTot();
		double costoOrdine = this.calcolaCostoOrdine(tipoSpedizione);

		String idOrdine = this.generaID();
		salvaOrdine(data, emailCliente, costoOrdine, idOrdine);
		componiOrdine(idOrdine);
		svuotaCarrello(emailCliente);

	}
	
	private void svuotaCarrello(String email) {
		CarrelloBeanDAO carrello = new CarrelloBeanDAOImpl();
		carrello.deleteAllByEmail(email);
	}

	private void componiOrdine(String idOrdine) {
		ProdottoNellordineBeanDAO prodottoOrdineDao = new ProdottoNellOrdineBeanDAOImpl();
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();

		Iterator<CarrelloBean> i = this.carrello.iterator();

		while(i.hasNext()) {
			CarrelloBean c = i.next();
			ProdottoBean p = prodottoDao.doRetrieveByKey(c.getIdProdotto());

			ProdottoNellordineBean prodottoOrdine = new ProdottoNellordineBean();
			prodottoOrdine.setId(idOrdine);
			prodottoOrdine.setIdProdotto(p.getId());
			prodottoOrdine.setNome(p.getNome());
			prodottoOrdine.setPrezzo(p.getPrezzo());
			prodottoOrdineDao.doSave(prodottoOrdine);
		}

	}

	private void salvaOrdine(Date data, String email, double costoOrdine, String idOrdine) {
		OrdineBean ordine = new OrdineBean();
		ordine.setCosto(costoOrdine);
		ordine.setDataOrdine(data);
		ordine.setId(idOrdine);
		ordine.setEmailCliente(email);

		OrdineBeanDAO ordineDao = new OrdineBeanDAOImpl();
		ordineDao.doSave(ordine);
	}

	private double calcolaCostoOrdine(String spedizione) {

		if(this.prezzo >= 49.99) {
			return this.prezzo;
		}

		if(spedizione.equalsIgnoreCase("gratuita")) {
			return this.prezzo;
		}
		if(spedizione.equalsIgnoreCase("standard")) {
			return this.prezzo+4.0;
		}
		if(spedizione.equalsIgnoreCase("rapida")) {
			return this.prezzo+5.0;
		}

		return 0.0;

	}


	private boolean checkInd (String ind, String email) {
		IndirizzoDiSpedizioneBeanDAO indirizzoDao = new IndirizzoSpedizioneBeanDAOImpl();
		this.indirizziDiSpedizione = indirizzoDao.doRetrieveAll(email);

		Iterator<IndirizzoDiSpedizioneBean> i = this.indirizziDiSpedizione.iterator();
		while(i.hasNext()) {
			IndirizzoDiSpedizioneBean indirizzo = i.next();

			if(indirizzo.getIndirizzo().equalsIgnoreCase(ind))
				return true;
		}


		return false;
	}

	private boolean checkMetodoPagamento(String met, String email) {
		MetodoDiPagamentoBeanDAO metodoDao = new MetodoDiPagamentoBeanDAOImpl();	

		int x = met.indexOf("-");
		met = met.substring(x+1, met.length());

		if(metodoDao.doRetrieveByKey(met, email) != null) {
			return false;
		}

		return false;
	}

	public void checkout(String email) {
		CarrelloBeanDAO carrelloDao = new CarrelloBeanDAOImpl();
		this.carrello = carrelloDao.retriveAll(email);
		ritiraNomiProdotti();
		ritiraIndirizziSpedizione(email);
		ritiraMetodiPagamento(email);
		calcolaCostoTot();

	}

	private void calcolaCostoTot() {
		this.prezzo = 0;
		Iterator<CarrelloBean> i = this.carrello.iterator();
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();

		while(i.hasNext()) {
			CarrelloBean c = i.next();
			this.prezzo = this.prezzo + (c.getQuantita()*prodottoDao.doRetrieveByKey(c.getIdProdotto()).getPrezzo());
		}
	}

	private void ritiraMetodiPagamento(String email) {
		MetodoDiPagamentoBeanDAO metodoDao = new MetodoDiPagamentoBeanDAOImpl();
		this.metodiDiPagamento = metodoDao.doRetrieveAll(email);
		
	}

	private void ritiraIndirizziSpedizione(String email) {
		IndirizzoDiSpedizioneBeanDAO indirizzoDao = new IndirizzoSpedizioneBeanDAOImpl();
		this.indirizziDiSpedizione=indirizzoDao.doRetrieveAll(email);
	}

	private void ritiraNomiProdotti() {
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();

		Iterator<CarrelloBean> i = this.carrello.iterator();

		while(i.hasNext()) {
			CarrelloBean c = i.next();

			System.out.println(c.getIdProdotto());
			this.prodotti.add(prodottoDao.doRetrieveByKey(c.getIdProdotto()).getNome());
		}
	}

	public Set<CarrelloBean> getCarrello() {
		return carrello;
	}

	public void setCarrello(Set<CarrelloBean> carrello) {
		this.carrello = carrello;
	}

	public Set<String> getProdotti() {
		return prodotti;
	}

	public void setProdotti(Set<String> prodotti) {
		this.prodotti = prodotti;
	}

	public Set<MetodoDiPagamentoBean> getMetodiDiPagamento() {
		return metodiDiPagamento;
	}

	public void setMetodiDiPagamento(Set<MetodoDiPagamentoBean> metodiDiPagamento) {
		this.metodiDiPagamento = metodiDiPagamento;
	}

	public Set<IndirizzoDiSpedizioneBean> getIndirizziDiSpedizione() {
		return indirizziDiSpedizione;
	}

	public void setIndirizziDiSpedizione(Set<IndirizzoDiSpedizioneBean> indirizziDiSpedizione) {
		this.indirizziDiSpedizione = indirizziDiSpedizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	private String generaID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID.substring(0,9);
	}

	public Set<OrdineBean> getListaOrdini() {
		return listaOrdini;
	}

	public void setListaOrdini(Set<OrdineBean> listaOrdini) {
		this.listaOrdini = listaOrdini;
	}
}

