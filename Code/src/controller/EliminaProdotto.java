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
 * Servlet implementation class EliminaProdotto
 */
@WebServlet("/EliminaProdotto")
public class EliminaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminaProdotto() {
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
		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean prodotto = pDao.doRetrieveByKey(id);

		if(prodotto == null) {
				response.setStatus(400);
				response.sendRedirect("errorPage.html");
				return;
		}else {
			pDao.deleteByKey(id);
				response.setStatus(200);
				response.sendRedirect("OperationSuccess.html");
				return;
		}
	}
/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

