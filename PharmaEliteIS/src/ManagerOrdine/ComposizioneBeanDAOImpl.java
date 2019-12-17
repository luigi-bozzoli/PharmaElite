package DAOImpl;

import java.sql.PreparedStatement;

public class ComposizioneBeanDAOImpl implements ComposizioneBean{
	public void doSave(ComposizioneBean m){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Composizione value(?,?)");
			ps.setString(1, m.getIDOrdine());
			ps.setString(2, m.getIDProdotto());
			
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
	public synchronized ComposizioneBean doRetrieveByKey(String idOrdine,String idProdotto){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			OrdineBean o = new OrdineBean(); 
			conn = DriverManagerConnectionPool.getConnection();

			ps = conn.prepareStatement("select * from PROGETTOTSW.Composizione where IDOrdine = ? and IDProdotto=?");
			ps.setString(1, idOrdine);
			ps-setString(2, idProdotto);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{

				o.setID(idOrdine);
				o.setIDProdotto(IDProdotto);
				

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
	public synchronized void doUpdate(ComposizioneBean p,ComposizioneBean v){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Composizione set IDProdotto=?, IDOrdine=? where IDOrdine=? and IDProdotto=?");
			
			
			ps.setString(1, p.getIDProdotto());
			ps.setString(2, p.getIDOrdine());
			ps.setDouble(3, v.getIDOrdine() );
			ps.setString(4,v.getIDProdotto());
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
	public synchronized void doDeleteByKey(String idOrdine,String idProdotto){
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("delete from PROGETTOTSW.Composizione where IDOrdine = ? and IDProdotto=?");
			ps.setString(1, idOrdine);
			ps.setString(2,idProdotto);

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
