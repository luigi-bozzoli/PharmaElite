package managerUtente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import managerOrdine.ComposizioneBean;

public class GestoreUtente {

	public ClienteBean login(String email, String password) {
		ClienteBeanDAO cDao = new ClienteBeanDAOImpl ();
		ClienteBean cliente;

		cliente = cDao.login(email, password);

		if(cliente != null) {
			
			salvaCarrello();
			return cliente;
			
		}else {
			return null;
		}
	}


	public void registrazione(HttpServletRequest r) {

	}
	
	private void salvaCarrello() {
		List<ComposizioneBean> carrello;
		
	}
	

}
