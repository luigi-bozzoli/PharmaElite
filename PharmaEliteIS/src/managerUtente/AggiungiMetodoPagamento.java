package managerUtente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;

/**
 * Servlet implementation class AggiungiMetodoPagamento
 */
@WebServlet("/AggiungiMetodoPagamento")
public class AggiungiMetodoPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiMetodoPagamento() {
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
			response.sendRedirect("errorPage.html");
			response.setStatus(403);
			return;
		}

		String numeroCarta = request.getParameter("numCarta");
		String tipoCarta = request.getParameter("tipoCarta");
		
		MetodoDiPagamentoBean carta = new MetodoDiPagamentoBean();
		carta.setEmailCliente(cliente.getEmail());
		carta.setNumeroCarta(numeroCarta);
		carta.setTipoCarta(tipoCarta);
		
		GestoreUtente gestore = new GestoreUtente();
		gestore.setMetodoPagamento(carta);
		try {
			gestore.inserisciMetodoPagamento();
		} catch (ValidationException e) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		response.sendRedirect("Checkout");
		return;
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
