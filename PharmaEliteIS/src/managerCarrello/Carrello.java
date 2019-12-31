package managerCarrello;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerCatalogo.ProdottoBean;
import managerUtente.ClienteBean;

/**
 * Servlet implementation class Carrello
 */
@WebServlet("/Carrello")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessione = request.getSession();
		ClienteBean cliente = (ClienteBean) sessione.getAttribute("cliente");
		Set<ProdottoBean> listaProdotti = null;
		double costoTot = 0;
		Set<CarrelloBean> carrello = null;
		
		
		if(cliente == null) {
			carrello = (Set<CarrelloBean>)sessione.getAttribute("carrello");
			

			GestoreCarrello gestore = new GestoreCarrello();
			
			if(carrello != null) {
				gestore.ritiraCarrello(carrello);
			}
			
			listaProdotti = gestore.getListaProdotti();
			costoTot = gestore.getPrezzo();
			
		}else {
			
			GestoreCarrello gestore = new GestoreCarrello();
			gestore.ritiraCarrelloUtenteLoggato(cliente.getEmail());
			
			listaProdotti = gestore.getListaProdotti();
			costoTot = gestore.getPrezzo();
			carrello = gestore.getCarrello();
		}
		
		request.setAttribute("listaProdotti", listaProdotti);
		request.setAttribute("costoTot", costoTot);
		request.setAttribute("carrello", carrello);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/carrello.jsp");
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
