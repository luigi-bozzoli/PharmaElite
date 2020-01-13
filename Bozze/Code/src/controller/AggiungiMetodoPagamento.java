package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.ClienteBean;
import model.beans.MetodoDiPagamentoBean;
import model.dao.MetodoDiPagamentoDAO;

/**
 * Servlet implementation class AggiungiMetodoPagamento
 */
@WebServlet("/AggiungiMetodoPagamento")
public class AggiungiMetodoPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiMetodoPagamento() {
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
			response.sendRedirect("errorPage.html");
			response.setStatus(403);
			return;
		}

		String numeroCarta = request.getParameter("numCarta");

		MetodoDiPagamentoDAO mDao=new MetodoDiPagamentoDAO();
		MetodoDiPagamentoBean mBean =mDao.doRetrieveByKey(cliente.getEmail(),numeroCarta);

		String x=request.getHeader("x-requested-with");
		if(x!=null) {
			if(x.equalsIgnoreCase("XMLHttprequest")) {
				if(mBean !=null) {
					response.getWriter().append("false");
				}else {
					response.getWriter().append("true");				
				}
				return;
			}
		}else {
			if(mBean !=null) {
				response.setStatus(400);
				response.sendRedirect("errorPage.html");
				return;
			}
		}

		String tipoCarta = request.getParameter("tipoCarta");



		if(!checkNumCarta(numeroCarta)) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}
		if(mBean==null) {
			mBean=new MetodoDiPagamentoBean();
		}
		mBean.setEmailCliente(cliente.getEmail());
		mBean.setNumCarta(numeroCarta);
		mBean.setTipo(tipoCarta);

		mDao.doSave(mBean);

		response.sendRedirect("Checkout");

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean checkNumCarta(String num) {
		if(num.matches("[0-9]+") && num.length() == 16 ) {
			return true;
		}
		return false;
	}

}
