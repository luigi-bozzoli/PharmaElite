package managerCarrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import managerUtente.DriverManagerConnectionPool;

public class CarrelloDAOImpl implements CarrelloDAO{
	
	public void deleteAllByEmail(String email) {
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

	
	public void doSave(CarrelloBean c) {
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
}
