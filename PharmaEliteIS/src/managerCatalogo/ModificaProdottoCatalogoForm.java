package managerCatalogo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaProdottoCatalogoForm
 */
@WebServlet("/ModificaProdottoCatalogoForm")
public class ModificaProdottoCatalogoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdottoCatalogoForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//questa servlet (ListaProdottiModifica mostra la jsp modifica.jsp
		//
		//da modifica.jsp -> FormModifica.java (rinominato in ModificaProdottoCatalogoForm.java)
		//da FormModifica.java -> formModifica.jsp
		//da formModifica.jsp -> UpdateProdotto.java
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
