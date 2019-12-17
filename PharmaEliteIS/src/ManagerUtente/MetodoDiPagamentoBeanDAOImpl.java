package DAOImpl;

public class MetodoDiPagamentoBeanDAOImpl implements MetodoDiPagamentoBean{
	public void doSave(MetodoDiPagamentoBean m){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.MetodoDiPagamento value(?,?,?)");
			ps.setString(1, m.getNumCarta());
			ps.setString(2, m.getTipo());
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
	public synchronized MetodoDiPagamentoBean doRetrieveByKey(String numCarta){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			MetodoDiPagamentoBean m = new MetodoDiPagamentoBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.MetodoDiPagamento where numeroCarta=?");
			ps.setString(1,numCarta);
		

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{
				m.setNumCarta(res.getString("numeroCarta"));
				m.setTipo(res.getString("tipo"));
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
			ps.setString(1, p.getTipo());
			ps.setString(2, p.getEmailCliente());
			ps.setString(3, p.getNumCarta());
		

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
