package managerCatalogo;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import managerUtente.DriverManagerConnectionPool;


public class ProdottoBeanDAOImpl implements ProdottoBeanDAO{
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
			ps.setBoolean(8, p.isFlagEliminato());

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
		return null;
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
			ps.setBoolean(7,p.isFlagEliminato());
			ps.setString(8, p.getId() );

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

	@Override
	public synchronized Set<ProdottoBean> searchByName(String nome) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Set<ProdottoBean> listaProdotti = (Set<ProdottoBean>) new TreeSet();
			String tmp ="%"+ nome + "%";

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Prodotto where nome LIKE ?");
			ps.setString(1, tmp);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				ProdottoBean p = new ProdottoBean();

				p.setId(res.getString("ID"));
				p.setNome(res.getString("nome"));
				p.setUrlImmagine(res.getString("urlImmagine"));
				p.setCategoria(res.getString("categoria"));
				p.setPrezzo(res.getDouble("prezzo"));
				p.setQuantita(res.getInt("quantita"));
				p.setDescrizione(res.getString("descrizione"));
				p.setFlagEliminato(res.getBoolean("flagEliminato"));

				listaProdotti.add(p);
			}

			return listaProdotti;

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
	public synchronized Set<ProdottoBean> searchByCategory(String categoria) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Set<ProdottoBean> listaProdotti = (Set<ProdottoBean>) new TreeSet();

			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Prodotto where categoria=?");

			ps.setString(1, categoria);

			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				ProdottoBean p = new ProdottoBean();

				p.setId(res.getString("ID"));
				p.setNome(res.getString("nome"));
				p.setUrlImmagine(res.getString("urlImmagine"));
				p.setCategoria(res.getString("categoria"));
				p.setPrezzo(res.getDouble("prezzo"));
				p.setQuantita(res.getInt("quantita"));
				p.setDescrizione(res.getString("descrizione"));
				p.setFlagEliminato(res.getBoolean("flagEliminato"));

				listaProdotti.add(p);
			}

			return listaProdotti;

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
	public synchronized Set<ProdottoBean> retriveAll() {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Set<ProdottoBean> listaProdotti = new TreeSet<ProdottoBean>();
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.Prodotto");
	
			ResultSet res = ps.executeQuery();

			// Prendi il risultato
			while(res.next())
			{
				ProdottoBean p = new ProdottoBean();

				p.setId(res.getString("ID"));
				p.setNome(res.getString("nome"));
				p.setUrlImmagine(res.getString("urlImmagine"));
				p.setCategoria(res.getString("categoria"));
				p.setPrezzo(res.getDouble("prezzo"));
				p.setQuantita(res.getInt("quantita"));
				p.setDescrizione(res.getString("descrizione"));
				p.setFlagEliminato(res.getBoolean("flagEliminato"));

				listaProdotti.add(p);
			}
			
			
			return listaProdotti;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
