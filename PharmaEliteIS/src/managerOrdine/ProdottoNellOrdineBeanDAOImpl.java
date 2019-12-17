package managerOrdine;

import java.sql.PreparedStatement;

public class ProdottoNellOrdineBeanDAOImpl {
	public void doSave(ProdottoNellOrdineBean o){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.ProdottoNellOrdine value(?,?,?,?)");

			ps.setString(1, o.getID());
			ps.setString(2, o.getIDProdotto());
			ps.setString(3, o.getNome());
			ps.setDouble(4, o.getPrezzo());

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
	
	public synchronized ProdottoNellOrdineBean doRetrieveByKey(String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			OrdineBean o = new OrdineBean(); 
			conn = DriverManagerConnectionPool.getConnection();

			ps = conn.prepareStatement("select * from PROGETTOTSW.Ordine where ID = ?");
			ps.setString(1, id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{

				o.setID(id);
				o.setIDProdotto(res.getString("IDProdotto"));
				o.setNome(res.getString("nome"));
				o.setPrezzo(res.getDouble("prezzo"));

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
	public synchronized void doUpdate(ProdottoNellOrdineBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.ProdottoNellOrdine set IDProdotto=?, nome=?,prezzo=? where ID=?");
			
			
			ps.setString(1, p.getIDProdotto());
			ps.setString(2, p.getNome());
			ps.setDouble(3, p.getPrezzo() );
			ps.setString(4,p.getID());
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
			ps = conn.prepareStatement("delete from PROGETTOTSW.ProdottoNellOrdine where ID = ?");
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
