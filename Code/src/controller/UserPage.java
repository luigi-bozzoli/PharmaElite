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
import model.beans.DatiAnagraficiBean;
import model.beans.IndirizzoSpedizioneBean;
import model.beans.TelefonoBean;
import model.dao.DatiAnagraficiDAO;
import model.dao.IndirizzoSpedizioneDAO;
import model.dao.TelefonoDAO;

/**
 * Servlet implementation class UserPage
 */
@WebServlet("/UserPage")
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserPage() {
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
			
			DatiAnagraficiDAO dADao = new DatiAnagraficiDAO();
			DatiAnagraficiBean datiAnagrafici = dADao.doRetrieveByKey(email);
			
			TelefonoDAO telDao = new TelefonoDAO();
			TelefonoBean telefono = telDao.doRetrieveByKey(email);
			
			IndirizzoSpedizioneDAO indDao = new IndirizzoSpedizioneDAO();
			ArrayList<IndirizzoSpedizioneBean> listaInd = indDao.doRetrieveAllByEmail(email);
			IndirizzoSpedizioneBean indirizzo = indDao.doRetrieveByKey(email, listaInd.get(0).getIndirizzo());
					
			request.setAttribute("cliente", cliente);
			request.setAttribute("datiAnagrafici", datiAnagrafici);
			request.setAttribute("telefono", telefono);
			request.setAttribute("indirizzo", indirizzo);
		
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
