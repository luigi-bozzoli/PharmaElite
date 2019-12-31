package managerCarrello;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import managerCatalogo.ProdottoBean;
import managerCatalogo.ProdottoBeanDAO;
import managerCatalogo.ProdottoBeanDAOImpl;
import managerUtente.ClienteBean;

public class GestoreCarrello {

	private Set<ProdottoBean> listaProdotti;
	private Set<CarrelloBean> carrello;
	private double prezzo;

	public void eliminaProdottoCarrello(String idProdotto, ClienteBean cliente, Set<CarrelloBean> carrello) throws ValidationException {
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();
		ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);

		if(prodotto == null) {
			throw new ValidationException("");
		}

		if(cliente == null) {
			eliminaProdottoCarrelloSessione(carrello, idProdotto);
		}else {
			eliminaProdottoCarrelloDB(idProdotto, cliente.getEmail());
		}

	}

	private void eliminaProdottoCarrelloSessione(Set<CarrelloBean> carrello, String idProdotto) {
		Iterator<CarrelloBean> i = carrello.iterator();

		while(i.hasNext()) {
			CarrelloBean c = i.next();

			if(c.getIdProdotto().equalsIgnoreCase(idProdotto)) {
				i.remove();
				//carrello.remove(c);
			}
		}

	}

	private void eliminaProdottoCarrelloDB(String idProdotto, String emailCliente) {
		CarrelloBeanDAO carrelloDao = new CarrelloBeanDAOImpl();
		carrelloDao.doDeleteByKey(emailCliente, idProdotto);
	}


	public void modificaQuantitaProdottoCarrello(String idProdotto, int quantita, ClienteBean cliente, Set<CarrelloBean> carrello)throws ValidationException, QuantitaNonDisponibile {
		//ritiro prodotto
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();
		ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);

		if(prodotto == null || quantita <= 0) {
			throw new ValidationException("");
		}

		if(prodotto.getQuantita() < quantita) {
			throw new QuantitaNonDisponibile(prodotto.getQuantita());
		}
		
		System.out.println("QUANTITà "+quantita);

		if(cliente == null) {// utente non loggato
			aggiornaQuantitaProdottoSessione(carrello, idProdotto, quantita);
		}else {//utente loggato
			aggiornaQuantitaProdottoDB(cliente.getEmail(), idProdotto, quantita);
		}

	}

	private void aggiornaQuantitaProdottoSessione(Set<CarrelloBean> carrello, String idProdotto, int quantita) {
		CarrelloBean prodottoCarrello = new CarrelloBean();
		prodottoCarrello.setQuantita(quantita);
		prodottoCarrello.setIdProdotto(idProdotto);

		Iterator<CarrelloBean> i = carrello.iterator();
		while(i.hasNext()) {
			CarrelloBean c = i.next();

			if(c.getIdProdotto().equalsIgnoreCase(idProdotto)) {
				i.remove();			
			}
		}
		carrello.add(prodottoCarrello);
		
	}

	private void aggiornaQuantitaProdottoDB(String email, String idProdotto, int quantita) {
		CarrelloBeanDAO carrelloDao = new CarrelloBeanDAOImpl();
		carrelloDao.doDeleteByKey(email, idProdotto);

		CarrelloBean prodotto = new CarrelloBean();
		prodotto.setEmailCliente(email);
		prodotto.setIdProdotto(idProdotto);
		prodotto.setQuantita(quantita);

		carrelloDao.doSave(prodotto);
	}

	public void aggiungiProdottoCarrello(String idProdotto, int quantita, ClienteBean cliente, HttpSession session) throws ValidationException, QuantitaNonDisponibile {
		//ritiro prodotto
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();
		ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);
		Set<CarrelloBean> carrello = (Set<CarrelloBean>) session.getAttribute("carrello");


		if(carrello == null) {
			carrello = new TreeSet<CarrelloBean>();
		}

		if(prodotto == null || quantita <= 0) {
			throw new ValidationException("");
		}

		if(prodotto.getQuantita() < quantita) {
			throw new QuantitaNonDisponibile(prodotto.getQuantita());
		}

		if(cliente == null) {// utente non loggato
			aggiornaSessione(carrello, idProdotto, quantita);
		}else {//utente loggato
			aggiornaCarrello(cliente.getEmail(), idProdotto, quantita);
		}

		session.setAttribute("carrello", carrello);
	}


	private void aggiornaCarrello(String email, String idProdotto, int quantita) {

		verificaPresenzaProdotto(email, idProdotto);

		CarrelloBeanDAO carrelloDao = new CarrelloBeanDAOImpl();
		CarrelloBean carrello = new CarrelloBean();
		carrello.setEmailCliente(email);
		carrello.setIdProdotto(idProdotto);
		carrello.setQuantita(quantita);
		carrelloDao.doSave(carrello);
	}

	private void verificaPresenzaProdotto(String email, String idProdotto) {
		CarrelloBeanDAO carrelloDao = new CarrelloBeanDAOImpl();
		CarrelloBean c = carrelloDao.doRetrieveByKey(email, idProdotto);

		if(c != null) {
			carrelloDao.doDeleteByKey(email, idProdotto);
		}else {
			return;
		}

	}


	private void aggiornaSessione(Set<CarrelloBean> carrello, String idProdotto, int quantita) {
		CarrelloBean prodottoCarrello = new CarrelloBean();
		prodottoCarrello.setQuantita(quantita);
		prodottoCarrello.setIdProdotto(idProdotto);


		Iterator<CarrelloBean> i = carrello.iterator();
	
		while(i.hasNext()) {
			CarrelloBean c = i.next();


			if(c.getIdProdotto().equalsIgnoreCase(idProdotto)) {
				//carrello.remove(c);
				i.remove();

			}
		}


		
		carrello.add(prodottoCarrello);
	}

	public void ritiraCarrelloUtenteLoggato(String email) {
		CarrelloBeanDAO carrelloDao = new CarrelloBeanDAOImpl();
		this.carrello = carrelloDao.retriveAll(email);

		ritiraCarrello(this.carrello);
	}

	public void ritiraCarrello(Set<CarrelloBean> carrello) {
		calcolaPrezzo(carrello);
		ritiraInfoProdotti(carrello);
	}

	private void calcolaPrezzo(Set<CarrelloBean> carrello) {

		double prezzoTotale = 0;

		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();
		ProdottoBean prodotto = new ProdottoBean();

		Iterator<CarrelloBean> i = carrello.iterator();
		while(i.hasNext()) {
			CarrelloBean c = i.next();
			prodotto = prodottoDao.doRetrieveByKey(c.getIdProdotto());
			prezzoTotale = prezzoTotale + prodotto.getPrezzo() * c.getQuantita();
		}

		this.prezzo = prezzoTotale;
	}


	private void ritiraInfoProdotti(Set<CarrelloBean> carrello){

		Set<ProdottoBean> listaProdotti = new TreeSet<ProdottoBean>();
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();

		Iterator<CarrelloBean> i = carrello.iterator();

		while(i.hasNext()) {

			CarrelloBean c = i.next();
			ProdottoBean prodotto = prodottoDao.doRetrieveByKey(c.getIdProdotto());

			if(!listaProdotti.contains(prodotto)) {
				listaProdotti.add(prodotto);
			}
		}

		this.listaProdotti = listaProdotti;
	}

	public Set<ProdottoBean> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(Set<ProdottoBean> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Set<CarrelloBean> getCarrello() {
		return carrello;
	}

	public void setCarrello(Set<CarrelloBean> carrello) {
		this.carrello = carrello;
	}

}
