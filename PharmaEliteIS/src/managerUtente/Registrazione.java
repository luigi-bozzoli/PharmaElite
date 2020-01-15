package managerUtente;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GestoreUtente gestore = new GestoreUtente();

		String emailCliente = request.getParameter("email");

		String x=request.getHeader("x-requested-with");


		if(x != null) {
			if(x.equalsIgnoreCase("XMLHttpRequest")) {

				if(gestore.controllaEsistenzaEmail(emailCliente)) {
					response.getWriter().append("true");
					return;
				}else {
					response.getWriter().append("false");
					return;
				}
			}
		}


		String password = request.getParameter("password");
		String indirizzo = request.getParameter("indSped");
		String numCarta = request.getParameter("numCarta");
		String tipo = request.getParameter("tipoCarta");
		String citta = request.getParameter("citta");
		String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String sesso = request.getParameter("sesso");
		String tel = request.getParameter("tel");

		//DATI ANAGRAFICI
		DatiAnagraficiBean datiAnagrafici = new DatiAnagraficiBean();
		datiAnagrafici.setCitta(citta);
		datiAnagrafici.setCognome(cognome);
		datiAnagrafici.setEmailCliente(emailCliente);
		datiAnagrafici.setNome(nome);
		datiAnagrafici.setSesso(sesso);
		datiAnagrafici.setTelefono(tel);
		gestore.setDatiAnagrafici(datiAnagrafici);

		//METODO DI PAGAMENTO
		MetodoDiPagamentoBean carta = new MetodoDiPagamentoBean();
		carta.setEmailCliente(emailCliente);
		carta.setNumeroCarta(numCarta);
		carta.setTipoCarta(tipo);
		gestore.setMetodoPagamento(carta);

		//INDIRIZZO
		IndirizzoDiSpedizioneBean indirizzoSpedizione = new IndirizzoDiSpedizioneBean();
		indirizzoSpedizione.setEmailCliente(emailCliente);
		indirizzoSpedizione.setIndirizzo(indirizzo);
		gestore.setIndirizzoSpedizione(indirizzoSpedizione);

		try {
			gestore.registrazione(emailCliente, password);			
			request.setAttribute("datiAnagrafici", datiAnagrafici);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/registerSuccess.jsp");
			dispatcher.forward(request, response);	
		}catch(ValidationException e) {
			response.setStatus(400);
			response.sendRedirect("errorPage.html");
		}


	}








	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
