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
 * Servlet implementation class ModificaCarrello
 */
@WebServlet("/ModificaCarrello")
public class ModificaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaCarrello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");


		String idProdotto = request.getParameter("id");
		String q=request.getParameter("quantità");
		if(!checkQuantita(q)) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}
		int quantita=Integer.parseInt(request.getParameter("quantità"));


		ProdottoDAO prodottoDao = new ProdottoDAO();
		ProdottoBean prodotto = prodottoDao.doRetrieveByKey(idProdotto);


		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");
		String x=request.getHeader("x-requested-with");
		//controllo quantitÃ 
		if(quantita > prodotto.getQuantità()) {
			
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest")) {
					response.getWriter().append("" +prodotto.getQuantità());
					return;
				}
			}else {
				response.setStatus(400);
				response.sendRedirect("errorPage.html");
				return;
			}

		}
		

		if(cliente == null) {
			aggiornaSessione(session, quantita, prodotto);
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest")) {
					response.getWriter().append("true");
					return;
				}
			}else {
				response.setStatus(200);
				response.sendRedirect("OperationSuccess.html");
				return;
			}


			
		
		}else {
			aggiornaDB(cliente, quantita, prodotto);
			
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest")) {
					response.getWriter().append("true");
					return;
				}
			}else {
				response.setStatus(200);
				response.sendRedirect("OperationSuccess.html");
				return;
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

	private void aggiornaSessione(HttpSession session, int quantita, ProdottoBean prodotto) {

		String idProdotto = prodotto.getId();

		ArrayList<ContenutoBean> carrello = (ArrayList<ContenutoBean>) session.getAttribute("carrello");

		if(carrello == null) {
			return;
		}else {
			for(int i = 0; i < carrello.size(); i++) {
				ContenutoBean c = carrello.get(i);
				if(c.getIdProdotto().equalsIgnoreCase(idProdotto)) {
					c.setQuantità(quantita);
					return;
				}

			}
		}
		return;
	}

	private void aggiornaDB(ClienteBean cliente, int quantita, ProdottoBean prodotto) {
		String emailCliente = cliente.getEmail();

		ContenutoDAO cDao = new ContenutoDAO();
		ContenutoBean prodottoCarrello = cDao.doRetrieveByKey(emailCliente, prodotto.getId());

		double prezzo = prodotto.getPrezzo();

		if(prodottoCarrello == null) {
			return;
		} else {
			int quantitaProd = prodottoCarrello.getQuantità();
			if(quantita < quantitaProd) {
				int differenza = quantitaProd - quantita;
				double diffPrezzo = prezzo * differenza;
				aggiornaPrezzo(emailCliente, diffPrezzo, false);
			}else { //quantitÃ  > quantitaProd
				int differenza = quantita - quantitaProd;
				double diffPrezzo = prezzo * differenza;
				aggiornaPrezzo(emailCliente, diffPrezzo, true);
			}

			prodottoCarrello.setQuantità(quantita);
			cDao.doUpdateQuantity(prodottoCarrello);
		}



		return;
	}

	private void aggiornaPrezzo(String email, double diffPrezzo, boolean b) {

		CarrelloDAO carrDao = new CarrelloDAO();
		CarrelloBean carrello = carrDao.doRetrieveByKey(email);
		double costoTotale = carrello.getCostoTot();

		if(!b) {//prodotti rimossi
			costoTotale = costoTotale - diffPrezzo;
			carrello.setCostoTot(costoTotale);
			carrDao.doUpdate(carrello);
		}else {//prodotti aggiunti
			costoTotale = costoTotale + diffPrezzo;
			carrello.setCostoTot(costoTotale);
			carrDao.doUpdate(carrello);
		}

		return;
	}
private boolean checkQuantita(String q) {
	try {
		Integer.parseInt(q);
		return true;
	}catch(Exception e) {
		return false;
	}
}


}
