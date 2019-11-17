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
import model.beans.ComponenteBean;
import model.beans.OrdineBean;
import model.beans.ProdottoBean;
import model.dao.ComponenteDAO;
import model.dao.OrdineDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class ListaOrdini
 */
@WebServlet("/ListaOrdini")
public class ListaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaOrdini() {
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
		
		OrdineDAO oDao = new OrdineDAO();
		ArrayList<OrdineBean> lista = oDao.doRetrieveAllByEmail(cliente.getEmail());
		ArrayList<ArrayList<String>> listaNomi = getListaProdotti(lista);
		
		request.setAttribute("lista", lista);
		request.setAttribute("listaNomi", listaNomi);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/listaOrdini.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private ArrayList<ArrayList<String>> getListaProdotti(ArrayList<OrdineBean> lista){
		
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		ArrayList<ComponenteBean> listaP = new ArrayList<ComponenteBean>();
		OrdineBean ordine;
		ComponenteDAO cDao = new ComponenteDAO();
		ProdottoDAO pDao = new ProdottoDAO();
		ProdottoBean p;
		
		for(int i = 0; i < lista.size(); i++) {
			ordine = lista.get(i);
			listaP = cDao.doRetrieveByCliente(ordine.getEmailCliente(), ordine.getId());

			ArrayList<String> listaNomiProdotti = new ArrayList<String>();
			for(int j = 0; j < listaP.size(); j++) {
				String idProdotto = listaP.get(j).getIdProdotto();
				p = pDao.doRetrieveByKey(idProdotto);
				listaNomiProdotti.add(p.getNome());
			}
			
			res.add(listaNomiProdotti);
		}
		
		return res;
	}
}
