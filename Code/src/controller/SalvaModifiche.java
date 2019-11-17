package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.ClienteBean;
import model.beans.ProdottoBean;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class SalvaModifiche
 */
@WebServlet("/SalvaModifiche")
public class SalvaModifiche extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalvaModifiche() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");

		if(cliente == null || cliente.getTipo().equalsIgnoreCase("utente")){
			response.setStatus(403);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		String id = request.getParameter("id");
		String url = request.getParameter("url");
		String nomeP = request.getParameter("nomeProdotto");
		String prezzo = request.getParameter("prezzo");
		String quantità = request.getParameter("quantita");
		String descrizione = request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");

		if(!checkParameter(nomeP, prezzo, quantità)){
			if(cliente == null || cliente.getTipo().equalsIgnoreCase("utente")){
				response.sendRedirect("errorPage.html");
				response.setStatus(400);
				return;
			}
		}	 


		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setId(id);
		prodotto.setCategoria(categoria);
		prodotto.setUrlImmagine(url);
		prodotto.setNome(nomeP);
		prodotto.setPrezzo(Double.parseDouble(prezzo));
		prodotto.setQuantità(Integer.parseInt(quantità));
		prodotto.setDescrizione(descrizione);

		ProdottoDAO pDao = new ProdottoDAO();
		pDao.doUpdate(prodotto);

		//pagina successo
		response.setStatus(200);
		response.sendRedirect("OperationSuccess.html");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean checkParameter(String nomeP, String prezzo, String quantita) {

		boolean b1 = checkNome(nomeP);
		boolean b2 = checkPrezzo(prezzo);
		boolean b3 = checkQuantita(quantita);

		return (b1 && b2 && b3);
	}

	private boolean checkNome(String nome) {
		if(nome == null)
			return false;
		
		int len = nome.length();
		if(len > 30 || len < 1) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			if ((Character.isLetter(nome.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkPrezzo(String prezzo){
		try {
			Double.parseDouble(prezzo);
		}catch(Exception e) {
			return false;
		}

		return false;
	}

	private boolean checkQuantita(String quantita) {
		try {
			Integer.parseInt(quantita);
		}catch (Exception e) {
			return false;
		}

		return true;
	}
}
