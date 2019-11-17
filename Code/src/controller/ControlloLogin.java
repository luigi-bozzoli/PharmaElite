package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.ClienteBean;
import model.dao.ClienteDAO;

/**
 * Servlet implementation class ControlloLogin
 */
@WebServlet("/ControlloLogin")
public class ControlloLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userAgent = request.getHeader("x-requested-with");
		if(userAgent == null) {
			response.setStatus(403);
			response.sendRedirect("errorPage.html");
		}
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		ClienteDAO cDao = new ClienteDAO();
		ClienteBean c = cDao.login(email, password);
		
		if(c == null) {
			response.getWriter().print("false");
		}else {
			response.getWriter().print("true");
		}
		
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
