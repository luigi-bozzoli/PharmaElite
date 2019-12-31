package managerOrdine;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerUtente.ClienteBean;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
			response.setStatus(403);
			response.sendRedirect("loginPerOrdine.html");
			return;
		}	
		
		GestoreOrdine gestore = new GestoreOrdine();
		gestore.checkout(cliente.getEmail());
		
		
		request.setAttribute("listaProdotti", gestore.getProdotti());
		request.setAttribute("listaCarte", gestore.getMetodiDiPagamento());
		request.setAttribute("listaInd", gestore.getIndirizziDiSpedizione());		
		request.setAttribute("prezzoTot", gestore.getPrezzo());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/checkout.jsp");
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
