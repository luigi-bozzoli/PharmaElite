package managerCarrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import managerUtente.DriverManagerConnectionPool;

public class CarrelloBeanDAOImpl implements CarrelloBeanDAO{
	
	public synchronized void deleteAllByEmail(String email) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.Carrello where EmailCliente = ?");
			ps.setString(1, email);

			ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();

		}finally{

			try {

				ps.close();
				DriverManagerConnectionPool.releaseConnection(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
	}

	
	public synchronized void doSave(CarrelloBean c) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Carrello value(?,?,?)");

			ps.setString(1, c.getEmailCliente());
			ps.setString(2, c.getIdProdotto());
			ps.setInt(3, c.getQuantita());

			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			try {

				ps.close();
				DriverManagerConnectionPool.releaseConnection(con);

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
	}
	
	public synchronized Set<CarrelloBean> retriveAll(String email) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Set<CarrelloBean> listaProdotti = new TreeSet<CarrelloBean>();
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.carrello where EmailCliente = ?");
			ps.setString(1, email);
			
			ResultSet res = ps.executeQuery();
			

			// Prendi il risultato
			while(res.next())
			{
				CarrelloBean c = new CarrelloBean();
				c.setEmailCliente(res.getString("EmailCliente"));
				c.setIdProdotto(res.getString("IDProdotto"));
				c.setQuantita(res.getInt("quantita"));
				listaProdotti.add(c);
			}
			
			
			return listaProdotti;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CarrelloBean doRetrieveByKey(String email, String idProdotto) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			CarrelloBean c = new CarrelloBean();
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.carrello where EmailCliente = ? and IDProdotto = ?");
			ps.setString(1, email);
			ps.setString(2, idProdotto);
			
			ResultSet res = ps.executeQuery();
			

			// Prendi il risultato
			if(res.next())
			{
				c.setEmailCliente(res.getString("EmailCliente"));
				c.setIdProdotto(res.getString("IDProdotto"));
				c.setQuantita(res.getInt("quantita"));

			}
			
			
			return c;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void doDeleteByKey(String email, String idProdotto) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.carrello where EmailCliente = ? and IDProdotto = ?");
			ps.setString(1, email);
			ps.setString(2, idProdotto);

			ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		}finally{

			try {

				ps.close();
				DriverManagerConnectionPool.releaseConnection(conn);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
