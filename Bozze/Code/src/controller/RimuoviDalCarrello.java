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
 * Servlet implementation class RimuoviDalCarrello
 */
@WebServlet("/RimuoviDalCarrello")
public class RimuoviDalCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviDalCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain");
		
		String idProdotto = request.getParameter("id");
		
		if(!checkInput(idProdotto)) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		HttpSession session = request.getSession();
		ClienteBean c = (ClienteBean) session.getAttribute("cliente");
		String x=request.getHeader("x-requested-with");
		if(c == null) {
			//carrello sessione
			ArrayList<ContenutoBean> listaProdotti = (ArrayList<ContenutoBean>) session.getAttribute("carrello");

			if(listaProdotti == null)
				return;
			else {
				rimuoviProdottoSessione(listaProdotti, idProdotto);
				if(x!=null) {
					if(x.equalsIgnoreCase("XMLHttpRequest")) {
						response.getWriter().append("true");
						return;
					}
				}else {
					response.setStatus(200);
					response.sendRedirect("OperationSuccess");
				}
			}

		}else {
			String email = c.getEmail();
			aggiornaCarrello(email, idProdotto);
			rimuoviProdotto(email, idProdotto);
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest")) {
					response.getWriter().append("true");
					return;
				}
			}else {
				response.setStatus(200);
				response.sendRedirect("OperationSuccess");

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
	
	private void rimuoviProdotto(String email, String idProdotto) {
		ContenutoDAO cDao = new ContenutoDAO();
		cDao.deleteByKey(email, idProdotto);
	}
	
	private void aggiornaCarrello(String email, String idProdotto) {
		//ritiro carrello
		CarrelloDAO carrDao = new CarrelloDAO();
		CarrelloBean carrello = carrDao.doRetrieveByKey(email);
		
		//ritiro prodotto
		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean prodotto = pDao.doRetrieveByKey(idProdotto);
		
		//ritiro prodotto carrello
		ContenutoDAO cDao = new ContenutoDAO();
		ContenutoBean c = cDao.doRetrieveByKey(email, idProdotto);
		
		//calcolo prezzo 	
		double prezzo = prodotto.getPrezzo()*c.getQuantità();
		
		//aggiorno costo Totale carrello 
		carrello.setCostoTot(carrello.getCostoTot() - prezzo);
		//aggiorno DB
		carrDao.doUpdate(carrello);
		
	}
	
	private void rimuoviProdottoSessione(ArrayList<ContenutoBean> carrello, String id) {
		for(int i = 0; i < carrello.size(); i++) {
			if(carrello.get(i).getIdProdotto().equalsIgnoreCase(id)) {
				carrello.remove(i);
				i--;
			}
		}
	}
	
	
	private boolean checkInput(String id) {
		
		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean prodotto = pDao.doRetrieveByKey(id);
		
		if(prodotto == null) {
			return false;
		}
		
		return true;
	}

}
