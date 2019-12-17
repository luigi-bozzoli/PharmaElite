package managerCatalogo;

import java.sql.*;
import java.sql.PreparedStatement;

import managerUtente.DriverManagerConnectionPool;


public class ProdottoBeanDAOImp {
	public synchronized void doSave(ProdottoBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("insert into PROGETTOTSW.Prodotto value(?,?,?,?,?,?,?,?)");

			ps.setString(1, p.getId());
			ps.setString(2, p.getUrlImmagine());
			ps.setString(3, p.getCategoria());
			ps.setString(4, p.getNome());
			ps.setDouble(5, p.getPrezzo());
			ps.setInt(6, p.getQuantita());
			ps.setString(7, p.getDescrizione());
			ps.setBoolean(8, p.getFlagEliminato());

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

	public synchronized ProdottoBean doRetrieveByKey(String id){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ProdottoBean p = new ProdottoBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Prodotto where ID = ?");
			ps.setString(1, id);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			if(res.next()){

				p.setId(id);
				p.setUrlImmagine(res.getString("urlImmagine"));
				p.setCategoria(res.getString("categoria"));
				p.setNome(res.getString("nome"));
				p.setPrezzo(res.getDouble("prezzo"));
				p.setQuantita(res.getInt("quantita"));
				p.setDescrizione(res.getString("descrizione"));
				p.setFlagEliminato(res.getBoolean("flagEliminato"));

				return p;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void doUpdate(ProdottoBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Prodotto set urlImmagine=?,categoria=?,nome=?,prezzo=?,quantita=?,descrizione=?, flagEliminato=? where ID=?");
			ps.setString(1, p.getUrlImmagine());
			ps.setString(2, p.getCategoria());
			ps.setString(3, p.getNome());
			ps.setDouble(4, p.getPrezzo());
			ps.setInt(5, p.getQuantita());
			ps.setString(6, p.getDescrizione() );
			ps.setBoolean(7,p.getFlagEliminato());
			ps.setString(7, p.getId() );

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
			ps = conn.prepareStatement("delete from PROGETTOTSW.Prodotto where ID = ?");
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
