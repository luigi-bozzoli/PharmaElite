package managerOrdine;

import java.sql.*;

import managerUtente.DriverManagerConnectionPool;

public class ComposizioneBeanDAOImpl implements ComposizioneBeanDAO{

	public void doSave(ComposizioneBean m){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Composizione value(?,?)");
			ps.setString(1, m.getIdOrdine());
			ps.setString(2, m.getIdProdotto());

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

	public ComposizioneBean doRetrieveByKey(String idOrdine,String idProdotto){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ComposizioneBean c = new ComposizioneBean();
			conn = DriverManagerConnectionPool.getConnection();

			ps = conn.prepareStatement("select * from PROGETTOTSW.Composizione where IDOrdine = ? and IDProdotto=?");
			ps.setString(1, idOrdine);
			ps.setString(2, idProdotto);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{

				c.setIdOrdine(res.getString(1));
				c.setIdProdotto(res.getString(2));
				c.setQuantita(res.getInt(3));

				return c;
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

	public void doUpdate(ComposizioneBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Composizione set quantita = ? where IDOrdine=? and IDProdotto=?");


			ps.setDouble(1, p.getQuantita() );
			ps.setString(2, p.getIdOrdine());
			ps.setString(3,p.getIdProdotto());
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

	public void doDeleteByKey(String idOrdine,String idProdotto){
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
