package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.IndirizzoSpedizioneBean;
import model.beans.MetodoDiPagamentoBean;

public class IndirizzoSpedizioneDAO {

	public synchronized void doSave(IndirizzoSpedizioneBean i){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.IndirizzoSpedizione value(?,?)");
			ps.setString(1, i.getIndirizzo());
			ps.setString(2, i.getEmailCliente());
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


	public synchronized IndirizzoSpedizioneBean doRetrieveByKey(String emailCliente, String ind){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			IndirizzoSpedizioneBean IndirizzoSpedizione = new IndirizzoSpedizioneBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.IndirizzoSpedizione where EmailCliente = ? and indirizzo=?");
			ps.setString(1,emailCliente);
			ps.setString(2, ind);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				IndirizzoSpedizione.setIndirizzo(res.getString("indirizzo"));
				IndirizzoSpedizione.setEmailCliente(res.getString("EmailCliente"));
				return IndirizzoSpedizione;
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
	
	public synchronized ArrayList<IndirizzoSpedizioneBean> doRetrieveAllByEmail(String emailCliente){

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			
			ArrayList<IndirizzoSpedizioneBean> list = new ArrayList<>();
			IndirizzoSpedizioneBean ind;
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.IndirizzoSpedizione where EmailCliente = ?");
			ps.setString(1,emailCliente);

			ResultSet res = ps.executeQuery();

			
			while(res.next())
			{

				ind = new IndirizzoSpedizioneBean();
				ind.setEmailCliente(res.getString("EmailCliente"));
				ind.setIndirizzo(res.getString("indirizzo"));
				
				list.add(ind);
			}
			
			return list;

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
