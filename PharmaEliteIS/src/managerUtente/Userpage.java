package managerUtente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Userpage
 */
@WebServlet("/Userpage")
public class Userpage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Userpage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");



		if(cliente == null) {
			response.sendRedirect("Login.html");
		}else {

			String email = cliente.getEmail();
			
			GestoreUtente gestore = new GestoreUtente();
			gestore.userpage(email);

			request.setAttribute("cliente", cliente);
			request.setAttribute("datiAnagrafici", gestore.getDatiAnagrafici());
			request.setAttribute("indirizzo", gestore.getIndirizzoSpedizione());
			
			System.out.println(gestore.getIndirizzoSpedizione().getIndirizzo());

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/user.jsp");
			dispatcher.forward(request, response);
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
