package managerOrdine;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import managerUtente.DriverManagerConnectionPool;

public class ProdottoNellOrdineBeanDAOImpl implements ProdottoNellordineBeanDAO{
	public void doSave(ProdottoNellordineBean o){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.ProdottoNellOrdine value(?,?,?,?)");

			ps.setString(1, o.getId());
			ps.setString(2, o.getIdProdotto());
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

	public synchronized ProdottoNellordineBean doRetrieveByKey(String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ProdottoNellordineBean p =  new ProdottoNellordineBean(); 
			conn = DriverManagerConnectionPool.getConnection();

			ps = conn.prepareStatement("select * from PROGETTOTSW.Ordine where ID = ?");
			ps.setString(1, id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next())
			{

				p.setId(id);
				p.setIdProdotto(res.getString(2));
				p.setNome(res.getString(3));
				p.setPrezzo(res.getDouble(4));

				return p;
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

	public synchronized void doUpdate(ProdottoNellordineBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.ProdottoNellOrdine set IDProdotto=?, nome=?,prezzo=? where ID=?");


			ps.setString(1, p.getIdProdotto());
			ps.setString(2, p.getNome());
			ps.setDouble(3, p.getPrezzo() );
			ps.setString(4,p.getId());
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

	@Override
	public Set<String> retriveProductNames(String id) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Set<String> listaProdotti = new TreeSet<String>();

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.prodottonellordine where ID = ?");
			ps.setString(1, id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				listaProdotti.add(res.getString("nome"));
			}


			return listaProdotti;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
