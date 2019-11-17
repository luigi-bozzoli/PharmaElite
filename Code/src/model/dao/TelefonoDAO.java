package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.beans.TelefonoBean;

public class TelefonoDAO {


	public synchronized void doSave(TelefonoBean t){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("insert into PROGETTOTSW.Telefono value(?,?)");

			ps.setString(1, t.getNumero());
			ps.setString(2, t.getEmailCliente());

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


	public synchronized TelefonoBean doRetrieveByKey(String email){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			TelefonoBean t = new TelefonoBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Telefono where EmailCliente = ?");
			ps.setString(1, email);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{

				t.setEmailCliente(email);
				t.setNumero(res.getString("numero"));

				return t;
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
