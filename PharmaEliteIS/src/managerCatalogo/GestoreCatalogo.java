package managerCatalogo;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GestoreCatalogo {
	
	public List<ProdottoBean> cercaProdotti(String nome){
		
		ProdottoBeanDAO pDao = new  ProdottoBeanDAOImpl();
		List<ProdottoBean> listaProdotti = pDao.searchByName(nome);
		
		return listaProdotti;
	}
	
	public List<ProdottoBean> prodottiCategoria(String categoria){
		ProdottoBeanDAO pDao = new  ProdottoBeanDAOImpl();
		List<ProdottoBean> listaProdotti = pDao.searchByCategory(categoria);
		
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
	
	private String generaID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID.substring(0,9);
	}
}
