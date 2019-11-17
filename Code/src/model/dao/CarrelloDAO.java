package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.beans.CarrelloBean;


public class CarrelloDAO {
	
	public synchronized void doSave(CarrelloBean c){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Carrello value(?,?)");

			ps.setString(1, c.getEmailCliente());
			ps.setDouble(2,c.getCostoTot());
			
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


	public synchronized CarrelloBean doRetrieveByKey(String email){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			CarrelloBean carrello = new CarrelloBean(); 
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Carrello where EmailCliente = ?");
			ps.setString(1, email);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				carrello.setEmailCliente(res.getString("EmailCliente"));
				carrello.setCostoTot(res.getDouble("costoTotale"));
				

				return carrello;
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
	
	public synchronized void doUpdate(CarrelloBean c) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE PROGETTOTSW.Carrello SET costoTotale = ? WHERE EmailCliente = ?");
			ps.setDouble(1, c.getCostoTot());
			ps.setString(2, c.getEmailCliente());

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


