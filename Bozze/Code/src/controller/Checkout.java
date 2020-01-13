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
import model.beans.IndirizzoSpedizioneBean;
import model.beans.MetodoDiPagamentoBean;
import model.beans.ProdottoBean;
import model.dao.CarrelloDAO;
import model.dao.ContenutoDAO;
import model.dao.IndirizzoSpedizioneDAO;
import model.dao.MetodoDiPagamentoDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");

		if(cliente == null){
			response.setStatus(403);
			response.sendRedirect("loginPerOrdine.html");
			return;
		}

		ContenutoDAO cDao = new ContenutoDAO();
		ArrayList<ContenutoBean> listaP = cDao.doRetrieveAllByKey(cliente.getEmail());



		ArrayList<String> listaProdotti = ritiraProdotti(listaP);

		CarrelloDAO carrDao = new CarrelloDAO();
		CarrelloBean carrBean = carrDao.doRetrieveByKey(cliente.getEmail());

		MetodoDiPagamentoDAO pagDao = new MetodoDiPagamentoDAO();
		ArrayList<MetodoDiPagamentoBean> listaCarte = pagDao.doRetrieveAllByKey(cliente.getEmail());

		IndirizzoSpedizioneDAO indDao = new IndirizzoSpedizioneDAO();
		ArrayList<IndirizzoSpedizioneBean> listaInd = indDao.doRetrieveAllByEmail(cliente.getEmail());

		request.setAttribute("listaProdotti", listaProdotti);
		request.setAttribute("listaCarte", listaCarte);
		request.setAttribute("listaInd", listaInd);		
		request.setAttribute("prezzoTot", carrBean.getCostoTot());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/checkout.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private ArrayList<String> ritiraProdotti (ArrayList<ContenutoBean> lista){

		ArrayList<String> listaNomi = new ArrayList<String>();

		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean prodotto;

		for(int i = 0; i < lista.size(); i++) {
			prodotto = pDao.doRetrieveByKey(lista.get(i).getIdProdotto());
			listaNomi.add(prodotto.getNome());
		}

		return listaNomi;
	}

}
