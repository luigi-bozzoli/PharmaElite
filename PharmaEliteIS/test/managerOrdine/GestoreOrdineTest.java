package managerOrdine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.xml.bind.ValidationException;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import managerCarrello.CarrelloBean;
import managerCarrello.CarrelloBeanDAO;
import managerCarrello.CarrelloBeanDAOImpl;

class GestoreOrdineTest {
	
	private GestoreOrdine gestore = new GestoreOrdine();
	private static final String EMAIL = "luigi@gmail.com";
	private static final String METODO_SPEDIZIONE = "gratuita";
	private static final String INDIRIZZO_SPED = "nuovo indirizzo";
	private static final String METODO_PAGAMENTO = "1111222233334444";
	private static final String ID_PRODOTTO = "27";
	

	@Test
	void testCronologiaOrdini() {
		gestore.cronologiaOrdini(EMAIL);
		
		assertNotNull(gestore.getListaOrdini());
	}
	
	//@Test 
	void testOrdineOra() throws ValidationException {
		gestore.ordinaOra(METODO_SPEDIZIONE, EMAIL, INDIRIZZO_SPED, METODO_PAGAMENTO);
		
		OrdineBeanDAO ordineDao = new OrdineBeanDAOImpl();
		
		Set<OrdineBean> actual = ordineDao.retriveAll(EMAIL);
		Iterator<OrdineBean> i = actual.iterator();
	
	}
	
	//@Test
	void testCheckout() {
		gestore.checkout(EMAIL);
	}
	
	@BeforeEach
	public void init() {
		CarrelloBean test = new CarrelloBean();
		test.setEmailCliente(EMAIL);
		test.setIdProdotto(ID_PRODOTTO);
		test.setQuantita(20);
		
		CarrelloBeanDAO carrDao = new CarrelloBeanDAOImpl();
		carrDao.doSave(test);
	}
	
	@AfterEach
	public void clean() {
		
		OrdineBeanDAO ordineDao = new OrdineBeanDAOImpl();
		//ordineDao.doDeleteByKey(x);
		
		CarrelloBeanDAO carrDao = new CarrelloBeanDAOImpl();
		carrDao.doDeleteByKey(EMAIL, ID_PRODOTTO);
		
	}
}
