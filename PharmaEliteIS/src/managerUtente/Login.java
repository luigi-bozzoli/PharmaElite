package managerUtente;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managerCarrello.CarrelloBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		Set<CarrelloBean> carrello = (Set<CarrelloBean>) session.getAttribute("carrello");

		GestoreUtente gestore = new GestoreUtente();
		ClienteBean cliente = gestore.login(email, password, carrello);


		String x=request.getHeader("x-requested-with");


		if(x != null) {
			if(x.equalsIgnoreCase("XMLHttpRequest")) {

				if(cliente != null) {
					response.getWriter().append("true");
					return;
				}
				else {
					response.getWriter().append("false");
					return;
				}
			}
		}


		if(cliente != null) {

			session.setAttribute("cliente", cliente);		
			response.sendRedirect("home.html");

		}else {
			response.setStatus(404);
			response.sendRedirect("errorPage.html");
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
