package managerUtente;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

public class IndirizzoSpedizioneBeanDAOImpl implements IndirizzoDiSpedizioneBeanDAO{
	
	public synchronized void doSave(IndirizzoDiSpedizioneBean i){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.IndirizzoSpedizione value(?,?,?)");
			ps.setString(1, i.getId());
			ps.setString(2, i.getIndirizzo());
			ps.setString(3, i.getEmailCliente());
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


	public synchronized IndirizzoDiSpedizioneBean doRetrieveByKey(String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			IndirizzoDiSpedizioneBean IndirizzoSpedizione = new IndirizzoDiSpedizioneBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.IndirizzoSpedizione where ID=?");
			ps.setString(1,id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				IndirizzoSpedizione.setId(res.getString("ID"));
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
	
	public synchronized void doUpdate(IndirizzoDiSpedizioneBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.IndirizzoSpedizione set indirizzo=?,EmailCliente=?,where ID=?");
			ps.setString(1, p.getIndirizzo());
			ps.setString(2, p.getEmailCliente());
			ps.setString(3, p.getId() );

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
	
	public synchronized void doDeleteByKey(String id){
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.IndirizzoSpedizione where ID = ?");
			ps.setString(1, id);

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


	@Override
	public IndirizzoDiSpedizioneBean doRetrieveByEmail(String email) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			IndirizzoDiSpedizioneBean IndirizzoSpedizione = new IndirizzoDiSpedizioneBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.IndirizzoSpedizione where EmailCliente=?");
			ps.setString(1,email);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				IndirizzoSpedizione.setEmailCliente(res.getString("ID"));
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


	@Override
	public Set<IndirizzoDiSpedizioneBean> doRetrieveAll(String email) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Set<IndirizzoDiSpedizioneBean> indirizzi = new TreeSet<IndirizzoDiSpedizioneBean>();
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.indirizzospedizione where EmailCliente = ?");
			ps.setString(1, email);
			
			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				IndirizzoDiSpedizioneBean i = new IndirizzoDiSpedizioneBean();

				i.setEmailCliente(email);
				i.setId(res.getString("ID"));
				i.setIndirizzo(res.getString("indirizzo"));

				indirizzi.add(i);
			}
			
			
			return indirizzi;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}




}
