package managerOrdine;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import managerUtente.ClienteBean;

/**
 * Servlet implementation class CronologiaOrdini
 */
@WebServlet("/ListaOrdini")
public class CronologiaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CronologiaOrdini() {
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
		
		GestoreOrdine gestore = new GestoreOrdine();
		gestore.cronologiaOrdini(cliente.getEmail());
		
		
		request.setAttribute("lista", gestore.getListaOrdini());
		//request.setAttribute("listaNomi", listaNomi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaOrdini.jsp");
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
