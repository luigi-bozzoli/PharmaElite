package managerCarrello;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import managerCatalogo.ProdottoBean;
import managerCatalogo.ProdottoBeanDAO;
import managerCatalogo.ProdottoBeanDAOImpl;

public class GestoreCarrello {

	private Set<ProdottoBean> listaProdotti;
	private Set<CarrelloBean> carrello;
	private double prezzo;

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
			listaProdotti.add(prodotto);
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
