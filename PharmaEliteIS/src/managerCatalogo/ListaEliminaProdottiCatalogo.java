package managerCatalogo;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerUtente.ClienteBean;

/**
 * Servlet implementation class ListaProdottiCatalogo
 */
@WebServlet("/ListaEliminaProdottiCatalogo")
public class ListaEliminaProdottiCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaEliminaProdottiCatalogo() {
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
		
		GestoreCatalogo gestore = new GestoreCatalogo();
		
		request.setAttribute("listaProdotti", gestore.ritiraProdotti());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaEliminaProdotto.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
