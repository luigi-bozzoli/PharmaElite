package managerCarrello;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.bind.ValidationException;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import managerUtente.ClienteBean;

class GestoreCarrelloTest {
	private GestoreCarrello gestore = new GestoreCarrello();
	private Set<CarrelloBean> testSet = new TreeSet<CarrelloBean>();
	private CarrelloBean carrelloTest = new CarrelloBean();
	


	@Test
	void testEliminaProdottoCarrelloSessione() throws ValidationException {
		carrelloTest.setEmailCliente("luigi@gmail.com");
		carrelloTest.setIdProdotto("27");
		carrelloTest.setQuantita(3);

		testSet.add(carrelloTest);

		gestore.eliminaProdottoCarrello("27", null, testSet);



		assertFalse(testSet.contains(carrelloTest));
	}

	@Test
	void testEliminaProdottoCarrelloDB() throws ValidationException {
		carrelloTest.setEmailCliente("luigi@gmail.com");
		carrelloTest.setIdProdotto("27");
		carrelloTest.setQuantita(3);

		testSet.add(carrelloTest);

		ClienteBean c = new ClienteBean();
		c.setEmail("luigi@gmail.com");

		CarrelloBeanDAO carrDao = new CarrelloBeanDAOImpl();
		carrDao.doDeleteByKey("luigi@gmail.com", "27");
		carrDao.doSave(carrelloTest);
		
		
		gestore.eliminaProdottoCarrello("27", c, testSet);

		
		CarrelloBean actual = carrDao.doRetrieveByKey("luigi@gmail.com", "27");

		assertEquals(null, actual.getIdProdotto());
	}
	
	@Test
	void testModificaQuantitaProdottoCarrelloSessione() throws ValidationException, QuantitaNonDisponibile {
	
		carrelloTest.setEmailCliente("luigi@gmail.com");
		carrelloTest.setIdProdotto("27");
		carrelloTest.setQuantita(3);

		testSet.add(carrelloTest);
		
		gestore.modificaQuantitaProdottoCarrello("27", 40, null, testSet);
		Iterator<CarrelloBean> i = testSet.iterator();
		CarrelloBean actual = i.next();
		
		assertEquals(40, actual.getQuantita());
	}
	
	@Test
	void testModificaQuantitaProdottoCarrelloDB() throws ValidationException, QuantitaNonDisponibile {
		carrelloTest.setEmailCliente("luigi@gmail.com");
		carrelloTest.setIdProdotto("27");
		carrelloTest.setQuantita(3);

		testSet.add(carrelloTest);
		ClienteBean c = new ClienteBean();
		c.setEmail("luigi@gmail.com");
		
		CarrelloBeanDAO carrDao = new CarrelloBeanDAOImpl();
		carrDao.doDeleteByKey("luigi@gmail.com", "27");
		carrDao.doSave(carrelloTest);
		
		gestore.modificaQuantitaProdottoCarrello("27", 40, c, testSet);
	
		CarrelloBean actual = carrDao.doRetrieveByKey("luigi@gmail.com", "27");
		
		assertEquals(40, actual.getQuantita());
	}
	
	/*
	@Test
	void testAggiungiProdottoCarrelloDB() throws ValidationException, QuantitaNonDisponibile {
		carrelloTest.setEmailCliente("luigi@gmail.com");
		carrelloTest.setIdProdotto("27");
		carrelloTest.setQuantita(3);

		testSet.add(carrelloTest);
		ClienteBean c = new ClienteBean();
		c.setEmail("luigi@gmail.com");
		
		CarrelloBeanDAO carrDao = new CarrelloBeanDAOImpl();
		carrDao.doDeleteByKey("luigi@gmail.com", "27");
		carrDao.doSave(carrelloTest);
		
		//nuova quantità 40
		gestore.aggiungiProdottoCarrello("27", 40, c, null);
		
		CarrelloBean actual = carrDao.doRetrieveByKey(c.getEmail(), carrelloTest.getIdProdotto());
		assertEquals(40, actual.getQuantita());
	}*/
	
	@Test
	void testRitiraCarrelloUtenteLoggato() {
		gestore.ritiraCarrelloUtenteLoggato("luigi@gmail.com");
		assertNotNull(gestore.getCarrello());
	}
}
