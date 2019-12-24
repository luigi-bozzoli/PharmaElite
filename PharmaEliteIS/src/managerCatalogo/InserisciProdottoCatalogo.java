package managerCatalogo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import managerUtente.ClienteBean;

/**
 * Servlet implementation class InserisciProdottoCatalogo
 */
@WebServlet("/InserisciProdottoCatalogo")
public class InserisciProdottoCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserisciProdottoCatalogo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");

		if(cliente == null || !cliente.isAdmin()){
			response.setStatus(403);
			response.sendRedirect("errorPage.html");
			return;
		}

		ProdottoBean prodotto = new ProdottoBean();

		//prodotto.setId( request.getParameter("id"));
		prodotto.setUrlImmagine(request.getParameter("url"));
		prodotto.setCategoria(request.getParameter("categoria"));
		prodotto.setNome(request.getParameter("nome"));
		double prezzo = 0;
		int quantita = 0;

		try {
			prezzo = Double.parseDouble(request.getParameter("prezzo"));
			quantita = Integer.parseInt(request.getParameter("quantita"));
		}catch (NumberFormatException e) {
			response.sendRedirect("errorPage.html");
			return;
		}


		prodotto.setPrezzo(prezzo);
		prodotto.setQuantita(quantita);
		prodotto.setDescrizione(request.getParameter("descrizione"));
		prodotto.setFlagEliminato(false);

		GestoreCatalogo gestore = new GestoreCatalogo();

		try {
			gestore.aggiungiProdottoCatalogo(prodotto);
			response.setStatus(200);
			response.sendRedirect("OperationSuccess.html");
		}catch (ValidationException e) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
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
