package controller;

import java.io.IOException;

import java.util.ArrayList;

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
import model.dao.ContenutoDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class AggiungiAlCarrello
 */
@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiAlCarrello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantita=0;
		response.setContentType("text/plain");
		String idProdotto = request.getParameter("id");
		String q=request.getParameter("quantità");
		
		if(isInt(q)) {
			quantita=Integer.parseInt(q);
		}else {
			sendError(response);
			return;
		}
		
		ProdottoDAO prodottoDao = new ProdottoDAO();
		ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);

		if (!checkInput(prodotto, quantita)) {
			sendError(response);
			return;
		}
		String x=request.getHeader("x-requested-with");
		//controllo quantità 
		if(prodotto.getQuantità() < quantita) {
		
			//verifica ajax
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest"))
					response.getWriter().write(""+prodotto.getQuantità());
			}else {
				sendError(response);
				return;
			}

		}else {
			//TRUE
			HttpSession session = request.getSession();
			ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");

			//VERIFICA LOGIN
			if(cliente == null) { 
				aggiornaSessione(session, idProdotto, quantita);
			}else {

				aggiornaCarrello(cliente, prodotto, quantita);
			}
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest")) 
					response.getWriter().write("true");
			}else {
				response.setStatus(200);
				response.sendRedirect("OperationSuccess.html");
			}



		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	//CLIENTE LOGGATO

	private void aggiornaCarrello(ClienteBean cliente, ProdottoBean prodotto, int quantita) {

		verificaProdotto(cliente.getEmail(), prodotto);

		salvaProdotto(cliente.getEmail(), prodotto.getId(), quantita);

		aggiornaPrezzoCarrello(cliente.getEmail(), prodotto.getPrezzo()*quantita);

	}

	//prodotto giÃ  inserito
	private void verificaProdotto(String email, ProdottoBean prodotto) {

		ContenutoDAO cDao = new ContenutoDAO();
		ContenutoBean c = cDao.doRetrieveByKey(email, prodotto.getId());

		if(c != null) {
			double prezzo = prodotto.getPrezzo()*c.getQuantità();

			//aggiorna costo totale carrello
			CarrelloDAO carrDao = new CarrelloDAO();
			CarrelloBean carr = carrDao.doRetrieveByKey(email);

			double costoTot = carr.getCostoTot();
			carr.setCostoTot(costoTot - prezzo);

			carrDao.doUpdate(carr);

			//elimina prodotto
			cDao.deleteByKey(email, prodotto.getId());

			return;

		} else {
			return;
		}
	}

	private void salvaProdotto(String emailCliente, String idProdotto, int quantita) {

		ContenutoBean prodottoCarrello =  new  ContenutoBean();

		prodottoCarrello.setEmailCliente(emailCliente);
		prodottoCarrello.setIdProdotto(idProdotto);
		prodottoCarrello.setQuantità(quantita);

		ContenutoDAO prodottoDao = new ContenutoDAO();
		prodottoDao.doSave(prodottoCarrello);

		return;
	}

	private void aggiornaPrezzoCarrello(String emailCliente, double prezzo) {

		CarrelloDAO cDao = new CarrelloDAO();
		CarrelloBean c = cDao.doRetrieveByKey(emailCliente);
		c.setCostoTot(c.getCostoTot()+prezzo);

		cDao.doUpdate(c);
		return;
	}

	// UTENTE NON LOGGATO

	private void aggiornaSessione(HttpSession session, String idProdotto, int quantita) {

		ArrayList<ContenutoBean> carrello = (ArrayList<ContenutoBean>) session.getAttribute("carrello");

		if(carrello == null) {
			carrello = new ArrayList<ContenutoBean>();
		}

		ContenutoBean prodotto = new ContenutoBean();
		prodotto.setIdProdotto(idProdotto);
		prodotto.setQuantità(quantita);

		carrello.add(prodotto);
		session.setAttribute("carrello", carrello);
		return;
	}

	private boolean checkInput(ProdottoBean p, int q) {
		if (q <= 0 || p == null)
			return false;
		else
			return true;
	}


	private void sendError(HttpServletResponse response) throws IOException {
		response.setStatus(400);
		response.sendRedirect("errorPage.html");
	}

	private boolean isInt(String intero) {
		try {
			Integer.parseInt(intero);
			return true;

		}catch(Exception e) {
			return false;
		}
	}

}


