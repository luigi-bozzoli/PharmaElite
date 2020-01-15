package managerUtente;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.MBeanAttributeInfo;
import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.Test;

import managerOrdine.GestoreOrdine;

class GestoreUtenteTest {
	
	private GestoreUtente gestore = new GestoreUtente();
	private static final String EMAIL = "luigi@gmail.com";
	private static final String TIPO_CARTA = "mastercard";
	private static final String NUMERO_CARTA = "9999999999999999";
	private static final String INDIRIZZO= "indirizzoTest";


	@Test
	void testInserisciMetodoPagamento() throws ValidationException {
		MetodoDiPagamentoBean mBean = new MetodoDiPagamentoBean();
		mBean.setEmailCliente(EMAIL);
		mBean.setNumeroCarta(NUMERO_CARTA);
		mBean.setTipoCarta(TIPO_CARTA);
		
		gestore.setMetodoPagamento(mBean);
		gestore.inserisciMetodoPagamento();
		
		MetodoDiPagamentoBeanDAO mDao = new MetodoDiPagamentoBeanDAOImpl();
		MetodoDiPagamentoBean actual = mDao.doRetrieveByKey(NUMERO_CARTA, EMAIL);
		mDao.doDeleteByKey(NUMERO_CARTA, EMAIL);
		
		assertEquals(mBean, actual);
		
	}
	
	@Test
	void testInserisciIndirizzoSpedizione() throws ValidationException {
		IndirizzoDiSpedizioneBean expected = new IndirizzoDiSpedizioneBean();
		expected.setEmailCliente(EMAIL);
		expected.setIndirizzo(INDIRIZZO);
		
		gestore.setIndirizzoSpedizione(expected);
		gestore.inserisciIndirizzoSpedizione();
		
		
		IndirizzoDiSpedizioneBeanDAO iDao = new IndirizzoSpedizioneBeanDAOImpl();
		IndirizzoDiSpedizioneBean actual = iDao.doRetrieveByKey(gestore.getIndirizzoSpedizione().getId());
		
		assertEquals(expected, actual);
		iDao.doDeleteByKey(gestore.getIndirizzoSpedizione().getId());
		
	}

}
