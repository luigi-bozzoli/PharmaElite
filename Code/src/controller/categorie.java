package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.ProdottoBean;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class categorie
 */
@WebServlet("/categorie")
public class categorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoria = request.getParameter("categoria");
		
		ProdottoDAO pDao = new  ProdottoDAO();
		ArrayList<ProdottoBean> listaProdotti = pDao.doRetrieveAllByCategory(categoria);
		
		request.setAttribute("listaProdotti", listaProdotti);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaProdotti.jsp");
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
