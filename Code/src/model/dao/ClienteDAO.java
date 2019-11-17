package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.beans.ClienteBean;

public class ClienteDAO {

	public void doSave(ClienteBean c){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Cliente value(?,?,?)");

			ps.setString(1, c.getEmail());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getTipo());

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
			ps = conn.prepareStatement("select * from PROGETTOTSW.Cliente where IndirizzoEmail = ?");
			ps.setString(1, email);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				cliente.setEmail(res.getString("IndirizzoEmail"));
				cliente.setPassword(res.getString("password"));
				cliente.setTipo(res.getString("Tipo"));

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
	
	public synchronized ClienteBean login(String email, String password) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			
			ClienteBean cliente = new ClienteBean(); 
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Cliente where IndirizzoEmail = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				cliente.setEmail(res.getString("IndirizzoEmail"));
				cliente.setPassword(res.getString("password"));
				cliente.setTipo(res.getString("Tipo"));

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
