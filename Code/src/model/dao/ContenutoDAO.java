package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.beans.ContenutoBean;



public class ContenutoDAO {
	public void doSave(ContenutoBean c){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Contenuto value(?,?,?)");

			ps.setString(1, c.getEmailCliente());
			ps.setString(2, c.getIdProdotto());
			ps.setInt(3, c.getQuantità());
			
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


	public synchronized ContenutoBean doRetrieveByKey(String email, String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ContenutoBean contenuto = new ContenutoBean(); 
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Contenuto where EmailCliente = ? and IDProdotto = ?");
			ps.setString(1, email);
			ps.setString(2, id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				contenuto.setEmailCliente(res.getString("EmailCliente"));
				contenuto.setIdProdotto(res.getString("idProdotto"));
				contenuto.setQuantità(res.getInt("quantità"));

				return contenuto;
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
	
	public synchronized ArrayList<ContenutoBean> doRetrieveAllByKey(String email){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ArrayList<ContenutoBean> carrello = new ArrayList<ContenutoBean>();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Contenuto where EmailCliente = ?");
			ps.setString(1, email);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				ContenutoBean contenuto = new ContenutoBean(); 
				contenuto.setEmailCliente(res.getString("EmailCliente"));
				contenuto.setIdProdotto(res.getString("idProdotto"));
				contenuto.setQuantità(res.getInt("quantità"));

				carrello.add(contenuto);
			}
			
			return carrello;

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
	
	public synchronized void deleteAllByKey(String email){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.Contenuto where EmailCliente = ?");
			ps.setString(1, email);

			ps.executeUpdate();
			
			return;

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

		return;
	}
	
	public synchronized void deleteByKey(String email, String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.Contenuto where EmailCliente = ? and IDProdotto = ?");
			ps.setString(1, email);
			ps.setString(2, id);

			ps.executeUpdate();
			
			return;

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

		return;
	}
	
	public synchronized void doUpdateQuantity(ContenutoBean c) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE PROGETTOTSW.Contenuto SET quantità = ? WHERE EmailCliente = ? AND IDProdotto = ?");
			ps.setDouble(1, c.getQuantità());
			ps.setString(2, c.getEmailCliente());
			ps.setString(3, c.getIdProdotto());
			

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
