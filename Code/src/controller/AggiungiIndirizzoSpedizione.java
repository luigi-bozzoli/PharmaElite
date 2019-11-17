package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.ClienteBean;
import model.beans.IndirizzoSpedizioneBean;
import model.dao.IndirizzoSpedizioneDAO;

/**
 * Servlet implementation class AggiungiIndirizzoSpedizione
 */
@WebServlet("/AggiungiIndirizzoSpedizione")
public class AggiungiIndirizzoSpedizione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiIndirizzoSpedizione() {
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
			response.sendRedirect("errorPage.html");
			return;
		}
		
		String indirizzo = request.getParameter("indirizzoSped");
		
		if(!checkIndirizzo(indirizzo) ||!(checkEsistenza(indirizzo, cliente.getEmail()))) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}
		
		IndirizzoSpedizioneDAO iDao = new IndirizzoSpedizioneDAO();
		IndirizzoSpedizioneBean iBean = new IndirizzoSpedizioneBean();
		
		iBean.setEmailCliente(cliente.getEmail());
		iBean.setIndirizzo(indirizzo);
				
		iDao.doSave(iBean);
		
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
	
	private boolean checkIndirizzo(String ind) {
		if(ind == null)
			return false;
		
		int len = ind.length();
		if(len > 50 || len < 1) {
			return false;
		}
		
		return true;
	}



private boolean checkEsistenza(String ind,String email) {
	IndirizzoSpedizioneDAO iDAO= new IndirizzoSpedizioneDAO();
	IndirizzoSpedizioneBean indBean= iDAO.doRetrieveByKey(email, ind);
	
	if(indBean == null) return true;
	else return false;
	}
}
