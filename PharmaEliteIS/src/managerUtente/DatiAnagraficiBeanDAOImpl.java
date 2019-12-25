package managerUtente;

import java.sql.*;

public class DatiAnagraficiBeanDAOImpl implements DatiAnagraficiBeanDAO{
	public synchronized void doSave(DatiAnagraficiBean d){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.DatiAnagrafici value(?,?,?,?,?,?)");


			ps.setString(1, d.getNome());
			ps.setString(2, d.getCognome());
			ps.setString(3, d.getSesso());
			ps.setString(4, d.getCitta());
			ps.setString(5, d.getTelefono());
			ps.setString(6, d.getEmailCliente());
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
				DatiAnagrafici.setCitta(res.getString("citta"));
				DatiAnagrafici.setTelefono(res.getString("numero"));
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
	
	public synchronized void doUpdate(DatiAnagraficiBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.DatiAnagrafici set nome=?,cognome=?,sesso=?,citta=?, numero=? where EmailCliente=?");
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCognome());
			ps.setString(3, p.getSesso());
			ps.setString(4, p.getCitta());
			ps.setString(5, p.getTelefono());
			ps.setString(6, p.getEmailCliente() );

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
	
	public synchronized void doDeleteByKey(String emailCliente){
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.DatiAnagrafici where EmailCliente = ?");
			ps.setString(1, emailCliente);

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
