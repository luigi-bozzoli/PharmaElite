package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.CarrelloBean;
import model.beans.ClienteBean;
import model.beans.ContenutoBean;
import model.beans.ProdottoBean;
import model.dao.CarrelloDAO;
import model.dao.ClienteDAO;
import model.dao.ContenutoDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ClienteDAO cDao = new ClienteDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		ClienteBean cliente = cDao.login(email, password);

		if(cliente != null) { //utente loggato

			HttpSession session = request.getSession();
			session.setAttribute("cliente", cliente);		


			//salviamo il carrello
			salvaCarrello(request.getSession(), email);

			//si reinderizza l'utente all'home
			response.sendRedirect("home.html");
		}else { //password o username errato
			
			response.setStatus(404);
			response.sendRedirect("errorPage.html");
		}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	private void salvaCarrello(HttpSession session, String email) {

		//ritiro carrello
		ArrayList<ContenutoBean> carrello = (ArrayList<ContenutoBean>) session.getAttribute("carrello");

		if(carrello == null) { //nessun prodotto aggiunto
			return;
		}else {

			ProdottoDAO pDao = new ProdottoDAO();
			ContenutoDAO cDao = new ContenutoDAO();
			cDao.deleteAllByKey(email);
			double prezzo = 0;

			//riempe il carrello nel DB
			for (int i = 0; i < carrello.size(); i++) {
				//prodotto aggiunto nel carrello
				ContenutoBean prodCarello = carrello.get(i);
				//info prodotto
				ProdottoBean prodotto = pDao.doRetrieveByKey(prodCarello.getIdProdotto());
				prezzo = prezzo + prodCarello.getQuantità()*prodotto.getPrezzo();
				
				prodCarello.setEmailCliente(email);
				cDao.doSave(prodCarello);
			}
			
			CarrelloBean c = new CarrelloBean();
			c.setCostoTot(prezzo);
			c.setEmailCliente(email);
			
			CarrelloDAO carrDao = new CarrelloDAO();
			carrDao.doUpdate(c);

			session.removeAttribute("carrello");

			return;
		}


	}
}
