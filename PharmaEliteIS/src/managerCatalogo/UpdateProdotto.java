package managerCatalogo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if(cliente == null || cliente.getTipo().equalsIgnoreCase("utente")){
			response.setStatus(403);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		String id = request.getParameter("id");
		String url = request.getParameter("url");
		String nomeP = request.getParameter("nomeProdotto");
		String prezzo = request.getParameter("prezzo");
		String quantita = request.getParameter("quantita");
		String descrizione = request.getParameter("descrizione");
		String categoria = request.getParameter("categoria");

		if(!checkParameter(nomeP, prezzo, quantita)){
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
		prodotto.setQuantita(Integer.parseInt(quantita));
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

}
