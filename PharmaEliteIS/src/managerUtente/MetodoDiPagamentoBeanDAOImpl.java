package managerUtente;

import java.sql.*;

public class MetodoDiPagamentoBeanDAOImpl implements MetodoDiPagamentoBeanDAO{
	public void doSave(MetodoDiPagamentoBean m){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.MetodoDiPagamento value(?,?,?)");
			ps.setString(1, m.getNumeroCarta());
			ps.setString(2, m.getTipoCarta());
			ps.setString(3,m.getEmailCliente());
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

	public synchronized MetodoDiPagamentoBean doRetrieveByKey(String numCarta, String email){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			MetodoDiPagamentoBean m = new MetodoDiPagamentoBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.MetodoDiPagamento where numeroCarta=? AND EmailCliente=?");
			ps.setString(1,numCarta);
			ps.setString(2, email);
		

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				m.setNumeroCarta(res.getString("numeroCarta"));
				m.setTipoCarta(res.getString("tipo"));
				m.setEmailCliente(res.getString("EmailCliente"));
				
				return m;
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
	
	public synchronized void doUpdate(MetodoDiPagamentoBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.MetodoDiPagamento set tipo=?,emailCliente=? where numCarta=?");
			ps.setString(1, p.getTipoCarta());
			ps.setString(2, p.getEmailCliente());
			ps.setString(3, p.getNumeroCarta());
		

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
	
	public synchronized void doDeleteByKey(String numCarta){
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.MetodoDiPagamento where numCarta = ?");
			ps.setString(1, numCarta);

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
