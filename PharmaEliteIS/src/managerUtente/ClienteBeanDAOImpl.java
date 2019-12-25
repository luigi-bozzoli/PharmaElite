package managerUtente;

import java.sql.*;

public class ClienteBeanDAOImpl implements ClienteBeanDAO{
	
	public void doSave(ClienteBean c){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Cliente value(?,?,?)");

			ps.setString(1, c.getEmail());
			ps.setString(2, c.getPassword());
			ps.setBoolean(3, c.isAdmin());

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
	
	public synchronized ClienteBean doRetrieveByKey(String email){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ClienteBean cliente = new ClienteBean(); 
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Cliente where Email = ?");
			ps.setString(1, email);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				cliente.setEmail(res.getString("Email"));
				cliente.setPassword(res.getString("password"));
				cliente.setAdmin(res.getBoolean("Tipo"));

				return cliente;
			}

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

		return null;
	}
	
	public synchronized void doUpdate(ClienteBean p){
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Cliente set password=?, tipo=? where  Email=?");			
			
			ps.setString(1, p.getPassword());
			ps.setBoolean(2, p.isAdmin());
			ps.setString(3, p.getEmail());
			
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
	
	public synchronized void doDeleteByKey(String Email){
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.Cliente where Email = ?");
			ps.setString(1, Email);

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
	
	//select * from PROGETTOTSW.Cliente where Email = ?
	public synchronized ClienteBean login(String email, String password) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			
			ClienteBean cliente = new ClienteBean(); 
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Cliente where Email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				cliente.setEmail(res.getString("Email"));
				cliente.setPassword(res.getString("password"));
				cliente.setAdmin(res.getBoolean("tipo"));

				return cliente;
			}
			

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

		return null;
		
	}
	
	
	
}
	
	
