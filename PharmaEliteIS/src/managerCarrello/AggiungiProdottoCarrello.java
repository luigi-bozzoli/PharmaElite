package managerCarrello;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

import managerUtente.ClienteBean;

/**
 * Servlet implementation class AggiungiProdottoCarrello
 */
@WebServlet("/AggiungiAlCarrello")
public class AggiungiProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiProdottoCarrello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain");
		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");
		Set<CarrelloBean> carrello = (Set<CarrelloBean>) session.getAttribute("carrello");
		
		String idProdotto = request.getParameter("id");
		String quantita=request.getParameter("quantita");
		int q = 0;

		try {
			q = Integer.parseInt(quantita);

		}catch(NumberFormatException e) {
			this.sendError(response);
			return;
		}

		GestoreCarrello gestore = new GestoreCarrello();
		String x=request.getHeader("x-requested-with");

		try {
			gestore.aggiungiProdottoCarrello(idProdotto, q, cliente, session);

		}catch (ValidationException e) {
			this.sendError(response);
			return;
		}catch (QuantitaNonDisponibile eccezioneQuantita ) {
			if(x!=null) {
				if(x.equalsIgnoreCase("XMLHttpRequest")) {
					response.getWriter().write(""+eccezioneQuantita.getQuantitaDisponibile());
					return;
				}
			}else {
				sendError(response);
				return;
			}
		}

		//successo
		if(x!=null) {
			if(x.equalsIgnoreCase("XMLHttpRequest"))
				response.getWriter().write("true");
		}else {
			response.setStatus(200);
			response.sendRedirect("OperationSuccess.html");
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void sendError(HttpServletResponse response) throws IOException {
		response.setStatus(400);
		response.sendRedirect("errorPage.html");
	}

}
