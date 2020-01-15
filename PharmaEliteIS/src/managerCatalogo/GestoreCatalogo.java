package managerCatalogo;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GestoreCatalogo {
	
	public Set<ProdottoBean> cercaProdotti(String nome){
		
		ProdottoBeanDAO pDao = new  ProdottoBeanDAOImpl();
		Set<ProdottoBean> listaProdotti = pDao.searchByName(nome);
		
		return listaProdotti;
	}
	
	public void updateProdotto(ProdottoBean prodotto) {
		ProdottoBeanDAO pDao = new  ProdottoBeanDAOImpl();
		pDao.doUpdate(prodotto);
	}
	
	public ProdottoBean ritiraProdotto(String id) {
		ProdottoBeanDAO pDao = new  ProdottoBeanDAOImpl();
		return pDao.doRetrieveByKey(id);
	}
	
	public Set<ProdottoBean> prodottiCategoria(String categoria){
		ProdottoBeanDAO pDao = new  ProdottoBeanDAOImpl();
		Set<ProdottoBean> listaProdotti = pDao.searchByCategory(categoria);
		
		return listaProdotti;
	}
	
	public void aggiungiProdottoCatalogo(ProdottoBean prodotto) throws ValidationException {
		
		prodotto.setId(generaID());
		boolean check = prodotto.validate();
		
		if(check) {
			ProdottoBeanDAO pDao = new ProdottoBeanDAOImpl();
			pDao.doSave(prodotto);
			
		}else {
			throw new ValidationException("Formato non corretto");
		}
	}
	
	public void eliminaProdottoCatalogo(String idProdotto) {
		//ritiro prodotto
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();
		ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);
		
		//lo elimino
		prodotto.setFlagEliminato(true);
		prodottoDao.doUpdate(prodotto);
	}
	
	public Set<ProdottoBean> ritiraProdotti(){
		ProdottoBeanDAO prodottoDao = new ProdottoBeanDAOImpl();
		Set<ProdottoBean> listaProdotti =  prodottoDao.retriveAll();
		
		return listaProdotti;
	}
	
	private String generaID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID.substring(0,9);
	}
}
