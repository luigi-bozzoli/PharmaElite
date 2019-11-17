package controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.CarrelloBean;
import model.beans.ClienteBean;
import model.beans.DatiAnagraficiBean;
import model.beans.IndirizzoSpedizioneBean;
import model.beans.MetodoDiPagamentoBean;
import model.beans.TelefonoBean;
import model.dao.CarrelloDAO;
import model.dao.ClienteDAO;
import model.dao.DatiAnagraficiDAO;
import model.dao.IndirizzoSpedizioneDAO;
import model.dao.MetodoDiPagamentoDAO;
import model.dao.TelefonoDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String emailCliente = request.getParameter("email");
		String x=request.getHeader("x-requested-with");
		if(x!=null) {
		if(x.equalsIgnoreCase("XMLHttpRequest")) {
			if(!ajaxCheck(emailCliente)){
				response.getWriter().append("false");
			}else {
				response.getWriter().append("true");
			}
			return;
		}
		}

		String password = request.getParameter("password");

		//controllo pass e email
		if(!(checkPassword(password) && isValidEmailAddress(emailCliente))) {
			sendError(response);
			return;
		}


		//controllo esistenza email
		if(!checkExistent(emailCliente)) {
				sendError(response);
				return;}

		//controllo indirizzo spedizione
		String indirizzo = request.getParameter("indSped");

		if(!checkIndirizzo(indirizzo)) {
			sendError(response);
			return;
		}

		//controllo metodo Pagamento
		String numCarta = request.getParameter("numCarta");
		String tipo = request.getParameter("tipoCarta");

		if(!checkMetodoPagamento(numCarta, tipo)) {
			sendError(response);
			return;}

		//controllo dati anagrafici
		String citta = request.getParameter("citta");
		String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String sesso = request.getParameter("sesso");

		if(!checkDatiAnagrafici(citta, cognome, nome, sesso)) {
			sendError(response);
			return;
		}

		//controllo numero tel
		String tel = request.getParameter("tel");

		if(!checkNumTel(tel)) {
			sendError(response);
			return;
			}



		//salva email e password cliente
		ClienteBean c = new ClienteBean();
		c.setEmail(emailCliente);
		c.setPassword(password);
		c.setTipo("utente");
		ClienteDAO cDao = new ClienteDAO();
		cDao.doSave(c);


		//salva indirizzo di spedizione

		IndirizzoSpedizioneBean i = new IndirizzoSpedizioneBean();
		i.setEmailCliente(emailCliente);
		i.setIndirizzo(indirizzo);
		IndirizzoSpedizioneDAO iDao = new  IndirizzoSpedizioneDAO();
		iDao.doSave(i);

		//salva metodo di pagamento

		salvaMetodoPagamento(numCarta, tipo, emailCliente);

		//salva dati anagrafici
		DatiAnagraficiBean d = new DatiAnagraficiBean();
		d.setEmailCliente(emailCliente);
		d.setCittà(citta);
		d.setCognome(cognome);
		d.setNome(nome);
		d.setSesso(sesso);
		salvaDatiAnagrafici(d);

		//salva numero di telefono
		TelefonoBean t = new TelefonoBean();
		t.setEmailCliente(emailCliente);
		t.setNumero(tel);
		TelefonoDAO tDao = new TelefonoDAO();
		tDao.doSave(t);

		//inizializza carrello
		CarrelloBean carrello = new CarrelloBean();
		carrello.setCostoTot(0);
		carrello.setEmailCliente(emailCliente);
		CarrelloDAO carrelloDao = new CarrelloDAO();
		carrelloDao.doSave(carrello);

		request.setAttribute("datiAnagrafici", d);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registerSuccess.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean ajaxCheck(String email) {
		if(email == null)
			return false;
		else
			return checkExistent(email);
	}

	private boolean checkExistent(String email) {

		ClienteDAO cDao = new ClienteDAO();
		ClienteBean c = cDao.doRetrieveByKey(email);

		if(c == null) {
			return true;
		}else {
			return false;
		}
	}

	private void salvaDatiAnagrafici(DatiAnagraficiBean d) {


		DatiAnagraficiDAO dDao = new DatiAnagraficiDAO();
		dDao.doSave(d);

	}

	private boolean checkDatiAnagrafici(String citta, String cognome, String nome, String sesso) {
		boolean b1 = checkCitta(citta);
		boolean b2 = checkNome(cognome);
		boolean b3 = checkNome(nome);
		boolean b4 = checkSesso(sesso);

		return b1&&b2&&b3&&b4;
	}

	private void salvaMetodoPagamento (String numCarta, String tipo, String emailCliente) {

		MetodoDiPagamentoBean m = new MetodoDiPagamentoBean();
		m.setEmailCliente(emailCliente);
		m.setNumCarta(numCarta);
		m.setTipo(tipo);
		MetodoDiPagamentoDAO mDao = new MetodoDiPagamentoDAO();
		mDao.doSave(m);
	}

	private boolean checkMetodoPagamento(String num, String tipo) {
		boolean b1 = checkTipoCarta(tipo);
		boolean b2;
		if(num == null)
			b2 = false;
		b2 = isNumber(num);

		return b1 && b2;
	}

	private boolean checkTipoCarta(String t) {
		if(t == null)
			return false;

		if(t.equalsIgnoreCase("MasterCard") || t.equalsIgnoreCase("Visa") || t.equalsIgnoreCase("American Express"))
			return true;

		return false;
	}

	private boolean checkNumTel(String num) {
		if (num == null)
			return false;
		if(num.length() != 10)
			return false;

		return isNumber(num);
	}

	private boolean isNumber(String num) {
		return num.matches("[0-9]+");
	}

	private boolean checkPassword(String pass) {

		if(pass == null)
			return false;

		if(pass.length() > 20)
			return false;

		if(!pass.matches("^[a-zA-Z0-9]+$"))
			return false;

		return true;
	}

	private boolean isValidEmailAddress(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 

		Pattern pat = Pattern.compile(emailRegex); 

		if (email == null) 
			return false; 

		return pat.matcher(email).matches(); 

	}

	private boolean checkIndirizzo(String indirizzo) {
		if(indirizzo.length() > 50 || indirizzo.length() < 1)
			return false;
		else
			return true;
	}

	private boolean isAlpha(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if(!Character.isLetter(c)) {
				return false;
			}
		}

		return true;
	}

	private boolean checkCitta(String citta) {
		if(citta == null)
			return false;

		return isAlpha(citta);
	}

	private boolean checkNome(String nome) {
		if(nome == null)
			return false;
		if(nome.length() < 1 || nome.length() > 30)
			return false;
		return isAlpha(nome);
	}

	private boolean checkSesso(String sesso) {
		if(sesso.equalsIgnoreCase("uomo") || sesso.equalsIgnoreCase("donne"))
			return true;
		else 
			return false;
	}


	private void sendError(HttpServletResponse response) throws IOException {
		response.setStatus(400);
		response.sendRedirect("errorPage.html");
	}


}
