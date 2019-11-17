package controller;

import java.io.IOException;

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
 * Servlet implementation class SalvaProdotto
 */
@WebServlet("/SalvaProdotto")
public class SalvaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalvaProdotto() {
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
		
			response.sendRedirect("errorPage.html");
			response.setStatus(403);
			return;
		}

		String id = request.getParameter("id");
		String url = request.getParameter("url");
		String categoria = request.getParameter("categoria");
		String nome = request.getParameter("nome");
		String prezzo = request.getParameter("prezzo");
		String quantita = request.getParameter("quantita");
		String descrizione = request.getParameter("descrizione");

		boolean idBool = controlloID(id);
		
		/*String x=request.getHeader("x-requested-with");
		if(x!=null) {
			if(x.equalsIgnoreCase("XMLHttprequest")) {
				if( idBool==false) {
					response.getWriter().append("false");
				}else {
					response.getWriter().append("true");				
				}
			}return;
		}     */       
		
		boolean urlBool = controlloUrl(url);
		boolean nomeBool = controlloNome(nome);
		boolean prezzoBool = controlloPrezzo(prezzo);
		boolean catBool=checkCategoria(categoria);
		boolean qBool=checkQuantita(quantita);
		

		ProdottoBean prodotto = new ProdottoBean();
		
		if(!(idBool && urlBool && prezzoBool && nomeBool && catBool && qBool)) {
			

			response.sendRedirect("errorPage.html");
			return;
		}
			
		prodotto.setId(id);
		prodotto.setUrlImmagine(url);
		prodotto.setCategoria(categoria);
		prodotto.setNome(nome);
		prodotto.setCategoria(categoria);
		prodotto.setDescrizione(descrizione);
		prodotto.setPrezzo(Double.parseDouble(prezzo));
		prodotto.setQuantità(Integer.parseInt(quantita));

		ProdottoDAO pDao = new ProdottoDAO();
		pDao.doSave(prodotto);
		response.setStatus(200);
		response.sendRedirect("OperationSuccess.html");;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean controlloID(String id) {
		if(id.length() > 10)
			return false;

		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean p = pDao.doRetrieveByKey(id);
		if(p != null) {
			return false;
		}

		return true;
	}
	
	private boolean controlloUrl(String url) {
		if(url.length() > 256)
			return false;
		
		return true;
	}


	private boolean controlloNome(String nome) {
		if(nome.length() > 30)
			return false;

		return true;
	}

	private boolean controlloPrezzo(String prezzo) {
		double q = 0;
		try {

			q=Double.parseDouble(prezzo);

		}catch(NumberFormatException e) {
			return false;
		}
		if(q<0) {
			return false;
		}else {
		return true;
		}
	}
	
 private boolean checkCategoria(String cat) {
	 boolean b1 =cat.equalsIgnoreCase("Igiene orale");
	 boolean b2 = cat.equalsIgnoreCase("farmaci da banco");
	 boolean b3 = cat.equalsIgnoreCase("erboristeria");
	 boolean b4 =cat.equalsIgnoreCase("integratori");
	 
	 return(b1||b2||b3||b4);
 }
private boolean checkQuantita(String x) {
	int q=0;
	try {
		 q=Integer.parseInt(x);
	}
	catch(Exception e){
		return false;
	}
	
	if(q<0)
		return false;
	else return true;
}

}
