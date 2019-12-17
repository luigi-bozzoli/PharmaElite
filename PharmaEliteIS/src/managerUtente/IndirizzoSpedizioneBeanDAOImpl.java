package managerUtente;

import java.sql.PreparedStatement;

public class IndirizzoSpedizioneBeanDAOImpl implements IndirizzoSpedizioneBean{
	
	public synchronized void doSave(IndirizzoSpedizioneBean i){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.IndirizzoSpedizione value(?,?,?)");
			ps.setString(1, i.getID());
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


	public synchronized IndirizzoSpedizioneBean doRetrieveByKey(String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			IndirizzoSpedizioneBean IndirizzoSpedizione = new IndirizzoSpedizioneBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.IndirizzoSpedizione where ID=?");
			ps.setString(1,id);

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
	
	public synchronized void doUpdate(IndirizzoSpedizioneBean p){

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

}
