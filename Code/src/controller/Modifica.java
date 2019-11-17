package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.ClienteBean;
import model.beans.ProdottoBean;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class Modifica
 */
@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ClienteBean cliente = (ClienteBean) session.getAttribute("cliente");
			
		if(cliente == null || cliente.getTipo().equalsIgnoreCase("utente")){
			response.setStatus(403);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		ProdottoDAO pDao = new ProdottoDAO();
		ArrayList<ProdottoBean> listaProdotti = pDao.doRetrieveAll();
		
		request.setAttribute("listaProdotti", listaProdotti);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/modifica.jsp");
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
