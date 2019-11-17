package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.CarrelloBean;
import model.beans.ClienteBean;
import model.beans.ComponenteBean;
import model.beans.ContenutoBean;
import model.beans.IndirizzoSpedizioneBean;
import model.beans.MetodoDiPagamentoBean;
import model.beans.MetodoDiSpedizioneBean;
import model.beans.OrdineBean;
import model.beans.ProdottoBean;
import model.dao.CarrelloDAO;
import model.dao.ComponenteDAO;
import model.dao.ContenutoDAO;
import model.dao.IndirizzoSpedizioneDAO;
import model.dao.MetodoDiPagamentoDAO;
import model.dao.MetodoDiSpedizioneDAO;
import model.dao.OrdineDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class ProcediAllOrdine
 */
@WebServlet("/ProcediAllOrdine")
public class ProcediAllOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcediAllOrdine() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ClienteBean cliente =(ClienteBean) session.getAttribute("cliente");

		if(cliente == null) {
			response.setStatus(403);
			response.sendRedirect("loginPerOrdine.html");
			return;
		}

		String email = cliente.getEmail();
		String metodoDiSped = request.getParameter("spedizione");
		String metodoDiPagamento = request.getParameter("tipoCarta");
		String indirizzo = request.getParameter("indirizzi");

		if(!(checkMetPag(metodoDiPagamento, email) && checkInd(indirizzo, email))) {

			response.setStatus(400);
			response.sendRedirect("errorPage.html");
			return;
		}


		//data ordine
		String data = generaData();


		//ID
		String id = generaID();


		//costo ordine
		CarrelloDAO cDao = new CarrelloDAO();
		CarrelloBean carrello = cDao.doRetrieveByKey(cliente.getEmail());
		double costoProdotti = carrello.getCostoTot();
		double costoOrdine = calcolaCostoOrdine(metodoDiSped, costoProdotti);

		salvaOrdine(costoOrdine, data, id, email);

		//metodo di spedizione
		String numeroTracking = generaID();
		String dettagli = dettagliOrdine(metodoDiSped, metodoDiPagamento, indirizzo);

		salvaSpedizione(id, email, numeroTracking, data, dettagli);


		//prodotti ordine
		componiOrdine(id, email);
		svuotaCarrello(email);
		response.sendRedirect("OrdineSuccess.html");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String generaID() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID.substring(0,9);
	}

	private String generaData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String data = dtf.format(localDate);
		return data;
	}

	private double calcolaCostoOrdine(String spedizione, double costo) {

		if(costo >= 49.99) {
			return costo;
		}

		if(spedizione.equalsIgnoreCase("gratuita")) {
			return costo;
		}
		if(spedizione.equalsIgnoreCase("standard")) {
			return costo+4.0;
		}
		if(spedizione.equalsIgnoreCase("rapida")) {
			return costo+5.0;
		}

		return 0.0;

	}

	private String dettagliOrdine(String sped, String pag, String ind) {

		String a = "METODO SPEDIZIONE: "+sped;
		String b = " METODO PAGAMENTO: "+pag;
		String c = " INDIRIZZO: "+ind;
		return a+b+c;
	}

	private void salvaOrdine(double costo, String data, String id, String email) {

		OrdineBean ordine = new OrdineBean();
		ordine.setCosto(costo);
		ordine.setDataOrdine(data);
		ordine.setId(id);
		ordine.setEmailCliente(email);
		OrdineDAO oDao = new OrdineDAO();
		oDao.doSave(ordine);
	}

	//salvaSpedizione(id, email, numeroTracking, data, dettagli);
	private void salvaSpedizione(String id, String email, String num, String data, String dettagli) {

		MetodoDiSpedizioneBean sped = new MetodoDiSpedizioneBean();
		sped.setDataArrivo(data);
		sped.setIdOrdine(id);
		sped.setEmailCliente(email);
		sped.setDettagli(dettagli);
		sped.setNumTracking(num);

		MetodoDiSpedizioneDAO spedDao = new MetodoDiSpedizioneDAO();
		spedDao.doSave(sped);
	}

	private void componiOrdine(String id, String email) {
		ContenutoDAO cDao = new ContenutoDAO();
		ArrayList<ContenutoBean> listaProdotti = cDao.doRetrieveAllByKey(email);
		ComponenteDAO compDao = new ComponenteDAO();
		ComponenteBean c = new ComponenteBean();

		for(int i = 0; i < listaProdotti.size(); i++) {

			ContenutoBean prod = listaProdotti.get(i);

			c.setEmailCliente(email);
			c.setIdOrdine(id);
			c.setQuantità(prod.getQuantità());
			c.setIdProdotto(prod.getIdProdotto());
aggiornaUnita(prod.getIdProdotto(),prod.getQuantità());
			compDao.doSave(c);
		}

		return;
	}
	private void aggiornaUnita(String id,int q) {
		ProdottoDAO pDao=new ProdottoDAO();
		ProdottoBean p=pDao.doRetrieveByKey(id);
		
		int x=p.getQuantità();
		x=x-q;
		p.setQuantità(x);
		pDao.doUpdate(p);
		
		return;
	}

	private boolean checkMetPag(String met, String email) {
		MetodoDiPagamentoDAO mDao = new MetodoDiPagamentoDAO();
		MetodoDiPagamentoBean m;

		int x = met.indexOf("-");
		met = met.substring(x+1, met.length());

		m=mDao.doRetrieveByKey(email, met);
		if(m!=null) {
			return true;
		}
		return false;
	}



	private boolean checkInd (String ind, String email) {
		IndirizzoSpedizioneDAO indDao = new IndirizzoSpedizioneDAO();
		IndirizzoSpedizioneBean indirizzo;
		indirizzo=indDao.doRetrieveByKey(email, ind);
		if(indirizzo!=null)
			return true;
		return false;
	}
	private void svuotaCarrello(String email) {
		ContenutoDAO cDao=new ContenutoDAO();
		cDao.deleteAllByKey(email);

		CarrelloBean carr= new CarrelloBean();
		carr.setEmailCliente(email);
		carr.setCostoTot(0);

		CarrelloDAO carrDao =new CarrelloDAO();
		carrDao.doUpdate(carr);

		return;

	}
}
