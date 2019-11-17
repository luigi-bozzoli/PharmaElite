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
import model.dao.ContenutoDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Carrello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sessione = request.getSession();
		ClienteBean cliente = (ClienteBean) sessione.getAttribute("cliente");
		
		if(cliente == null) {
			ArrayList<ContenutoBean> carrello = (ArrayList<ContenutoBean>) sessione.getAttribute("carrello");
			ArrayList<ProdottoBean> listaProdotti = null;
			double costoTot = 0;
			
			if(carrello != null) {
				listaProdotti = ritiraInfoProdotti(carrello);
				costoTot = calcolaPrezzo(carrello);
			}
			
			request.setAttribute("listaProdotti", listaProdotti);
			request.setAttribute("costoTot", costoTot);
			request.setAttribute("carrello", carrello);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
			dispatcher.forward(request, response);
		}else {
			ContenutoDAO cDao = new ContenutoDAO();
			ArrayList<ContenutoBean> carrello = cDao.doRetrieveAllByKey(cliente.getEmail());
			ArrayList<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();
			
			if(carrello != null) {
				listaProdotti = ritiraInfoProdotti(carrello);
			}
			
			
			CarrelloDAO carrDao = new CarrelloDAO();
			CarrelloBean c = carrDao.doRetrieveByKey(cliente.getEmail());
			request.setAttribute("listaProdotti", listaProdotti);
			request.setAttribute("carrello", carrello);
			request.setAttribute("costoTot", c.getCostoTot());
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private double calcolaPrezzo(ArrayList<ContenutoBean> carrello) {
		
		double prezzo = 0;
		
		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean prodotto = new ProdottoBean();
		
		
		for(int i = 0; i<carrello.size(); i++) {
			prodotto = pDao.doRetrieveByKey(carrello.get(i).getIdProdotto());
			prezzo = prezzo + prodotto.getPrezzo()*carrello.get(i).getQuantità();
		}
		
		return prezzo;
	}
	
	private ArrayList<ProdottoBean> ritiraInfoProdotti(ArrayList<ContenutoBean> carrello){
		
		ArrayList<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();
		ProdottoDAO pDao = new ProdottoDAO();
		
		for (int i = 0; i < carrello.size(); i++) {
			listaProdotti.add(pDao.doRetrieveByKey(carrello.get(i).getIdProdotto()));
		}
		
		return listaProdotti;
	}

}
