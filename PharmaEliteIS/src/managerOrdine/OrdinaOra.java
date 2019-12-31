package managerOrdine;

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
 * Servlet implementation class OrdinaOra
 */
@WebServlet("/ProcediAllOrdine")
public class OrdinaOra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdinaOra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ClienteBean cliente =(ClienteBean) session.getAttribute("cliente");

		if(cliente == null) {
			response.setStatus(403);
			response.sendRedirect("loginPerOrdine.html");
			return;
		}

		String email = cliente.getEmail();
		String metodoDiSped = request.getParameter("spedizione");
		String metodoDiPagamento = request.getParameter("tipoCarta");
		String indirizzo = request.getParameter("indirizzi");

		GestoreOrdine gestore = new GestoreOrdine();
		try {
			gestore.ordinaOra(metodoDiSped, email, indirizzo, metodoDiPagamento);
		} catch (ValidationException e) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		response.sendRedirect("OrdineSuccess.html");
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
