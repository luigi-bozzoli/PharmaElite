package managerCatalogo;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.Test;

import managerCatalogo.GestoreCatalogo;
import managerCatalogo.ProdottoBean;
import managerCatalogo.ProdottoBeanDAO;
import managerCatalogo.ProdottoBeanDAOImpl;

class GestoreCatalogoTest {

	private GestoreCatalogo gestore = new GestoreCatalogo();

	@Test
	public void testAggiungiProdottoCatalogo() throws ValidationException {
		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setCategoria("Igiene orale");
		prodotto.setDescrizione("descrizione test");
		prodotto.setFlagEliminato(false);
		prodotto.setId("idtest");
		prodotto.setNome("Nome prodotto test");
		prodotto.setPrezzo(30);
		prodotto.setQuantita(30);
		prodotto.setUrlImmagine("urlImmagine");

		gestore.aggiungiProdottoCatalogo(prodotto);

		ProdottoBeanDAO pDao = new ProdottoBeanDAOImpl();

		assertEquals(prodotto, pDao.doRetrieveByKey(prodotto.getId()));

	}

	@Test
	public void testEliminaProdottoCatalogo() throws ValidationException {
		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setCategoria("Igiene orale");
		prodotto.setDescrizione("descrizione test");
		prodotto.setFlagEliminato(false);
		prodotto.setNome("Nome prodotto test");
		prodotto.setPrezzo(30);
		prodotto.setQuantita(30);
		prodotto.setUrlImmagine("urlImmagine");

		gestore.aggiungiProdottoCatalogo(prodotto);
		gestore.eliminaProdottoCatalogo(prodotto.getId());

		ProdottoBeanDAO pDao = new ProdottoBeanDAOImpl();
		assertEquals(true, pDao.doRetrieveByKey(prodotto.getId()).isFlagEliminato());

	}

	@Test
	public void testUpdateProdotto() throws ValidationException {
		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setCategoria("Igiene orale");
		prodotto.setDescrizione("descrizione test");
		prodotto.setFlagEliminato(false);
		prodotto.setNome("Nome prodotto test");
		prodotto.setPrezzo(30);
		prodotto.setQuantita(30);
		prodotto.setUrlImmagine("urlImmagine");

		gestore.aggiungiProdottoCatalogo(prodotto);
		prodotto.setQuantita(300);
		gestore.updateProdotto(prodotto);

		ProdottoBeanDAO pDao = new ProdottoBeanDAOImpl();
		assertEquals(prodotto, pDao.doRetrieveByKey(prodotto.getId()));
	}

	@Test
	public void testRitiraProdotto() throws ValidationException {
		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setCategoria("Igiene orale");
		prodotto.setDescrizione("descrizione test");
		prodotto.setFlagEliminato(false);
		prodotto.setNome("Nome prodotto test");
		prodotto.setPrezzo(30);
		prodotto.setQuantita(30);
		prodotto.setUrlImmagine("urlImmagine");

		gestore.aggiungiProdottoCatalogo(prodotto);

		assertEquals(prodotto, gestore.ritiraProdotto(prodotto.getId()));
	}

	@Test
	public void testProdottiCategoria() {
		Set<ProdottoBean> actual = gestore.prodottiCategoria("integratori");

		ProdottoBeanDAO pDao = new ProdottoBeanDAOImpl();
		Set<ProdottoBean> expected = pDao.searchByCategory("integratori");


		assertTrue(actual.equals(expected));

	}

	@Test
	public void testRitiraProdotti() {
		Set<ProdottoBean> actual = gestore.ritiraProdotti();

		ProdottoBeanDAO pDao = new ProdottoBeanDAOImpl();
		Set<ProdottoBean> expected = pDao.retriveAll();
		
		assertTrue(actual.equals(expected));
	}
}
