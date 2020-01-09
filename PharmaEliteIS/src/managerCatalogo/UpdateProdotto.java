package managerCatalogo;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerUtente.ClienteBean;

/**
 * Servlet implementation class UpdateProdotto
 */
@WebServlet("/UpdateProdotto")
public class UpdateProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProdotto() {
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
		
		String id = request.getParameter("id");
		String url = request.getParameter("url");
		String nomeP = request.getParameter("nomeProdotto");
		String descrizione = request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");
		double prezzo = 0;
		int quantita = 0;

		
		try {
			prezzo = Double.parseDouble(request.getParameter("prezzo"));
			quantita = Integer.parseInt(request.getParameter("quantita"));
		}catch (NumberFormatException e) {
			response.sendRedirect("errorPage.html");
			return;
		}
		
		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setId(id);
		prodotto.setUrlImmagine(url);
		prodotto.setNome(nomeP);
		prodotto.setPrezzo(prezzo);
		prodotto.setQuantita(quantita);
		prodotto.setDescrizione(descrizione);
		prodotto.setCategoria(categoria);
		
	

		if(!prodotto.validate()){
				response.sendRedirect("errorPage.html");
				response.setStatus(400);
				return;
		}	 
		
		GestoreCatalogo gestore = new GestoreCatalogo();
		gestore.updateProdotto(prodotto);


		
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

}
