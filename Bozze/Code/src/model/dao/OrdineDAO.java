package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.OrdineBean;

public class OrdineDAO {

	public void doSave(OrdineBean o){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Ordine value(?,?,?,?)");

			ps.setString(1, o.getId());
			ps.setString(2, o.getEmailCliente());
			ps.setString(3, o.getDataOrdine());
			ps.setDouble(4, o.getCosto());

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


	public synchronized OrdineBean doRetrieveByKey(String emailCliente, String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			OrdineBean o = new OrdineBean(); 
			conn = DriverManagerConnectionPool.getConnection();

			ps = conn.prepareStatement("select * from PROGETTOTSW.Ordine where ID = ? AND EmailCliente = ?");
			ps.setString(1, emailCliente);
			ps.setString(2, id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{

				o.setId(id);
				o.setEmailCliente(emailCliente);
				o.setDataOrdine(res.getString("dataOrdine"));
				o.setCosto(res.getDouble("costo"));

				return o;
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

	public synchronized ArrayList<OrdineBean> doRetrieveAllByEmail(String emailCliente){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ArrayList<OrdineBean> lista = new ArrayList<OrdineBean>();

			conn = DriverManagerConnectionPool.getConnection();

			ps = conn.prepareStatement("select * from PROGETTOTSW.Ordine where EmailCliente = ?");
			ps.setString(1, emailCliente);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				OrdineBean o = new OrdineBean(); 

				o.setId(res.getString("ID"));
				o.setEmailCliente(emailCliente);
				o.setDataOrdine(res.getString("dataOrdine"));
				o.setCosto(res.getDouble("costo"));

				lista.add(o);
			}
			
			return lista;

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
