package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.beans.DatiAnagraficiBean;

public class DatiAnagraficiDAO {

	public synchronized void doSave(DatiAnagraficiBean d){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.DatiAnagrafici value(?,?,?,?,?)");


			ps.setString(1, d.getNome());
			ps.setString(2, d.getCognome());
			ps.setString(3, d.getSesso());
			ps.setString(4, d.getCittà());
			ps.setString(5, d.getEmailCliente());
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


	public synchronized DatiAnagraficiBean doRetrieveByKey(String emailCliente){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			DatiAnagraficiBean DatiAnagrafici = new DatiAnagraficiBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.DatiAnagrafici where EmailCliente = ?");
			ps.setString(1, emailCliente );

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				DatiAnagrafici.setEmailCliente(res.getString("EmailCliente"));
				DatiAnagrafici.setNome(res.getString("nome"));
				DatiAnagrafici.setCognome(res.getString("cognome"));
				DatiAnagrafici.setSesso(res.getString("sesso"));
				DatiAnagrafici.setCittà(res.getString("città"));
				return DatiAnagrafici;
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
