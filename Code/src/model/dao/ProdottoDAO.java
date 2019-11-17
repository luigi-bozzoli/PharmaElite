package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ProdottoBean;

public class ProdottoDAO {
	
	public synchronized void doSave(ProdottoBean p){
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("insert into PROGETTOTSW.Prodotto value(?,?,?,?,?,?,?)");
			
			ps.setString(1, p.getId());
			ps.setString(2, p.getUrlImmagine());
			ps.setString(3, p.getCategoria());
			ps.setString(4, p.getNome());
			ps.setDouble(5, p.getPrezzo());
			ps.setInt(6, p.getQuantità());
			ps.setString(7, p.getDescrizione());

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
			if(res.next())
			{
				
				p.setId(id);
				p.setUrlImmagine(res.getString("urlImmagine"));
				p.setCategoria(res.getString("categoria"));
				p.setNome(res.getString("nome"));
				p.setPrezzo(res.getDouble("prezzo"));
				p.setQuantità(res.getInt("quantità"));
				p.setDescrizione(res.getString("descrizione"));

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
	
	public synchronized ArrayList<ProdottoBean> doRetrieveAllByCategory(String categoria){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ArrayList<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();
			
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
				p.setQuantità(res.getInt("quantità"));
				p.setDescrizione(res.getString("descrizione"));
				
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
	
	public synchronized ArrayList<ProdottoBean> searchByName(String name){
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ArrayList<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();
			String tmp ="%"+ name + "%";
			
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
				p.setQuantità(res.getInt("quantità"));
				p.setDescrizione(res.getString("descrizione"));
				
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
	
	public synchronized void deleteByKey(String id){
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
	
	public synchronized ArrayList<ProdottoBean> doRetrieveAll(){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			ArrayList<ProdottoBean> listaProdotti = new ArrayList<ProdottoBean>();
			
			
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
				p.setQuantità(res.getInt("quantità"));
				p.setDescrizione(res.getString("descrizione"));
				
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
	
	public synchronized void doUpdate(ProdottoBean p){
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Prodotto set urlImmagine=?,categoria=?,nome=?,prezzo=?,quantità=?,descrizione=? where ID=?");			
			ps.setString(1, p.getUrlImmagine());
			ps.setString(2, p.getCategoria());
			ps.setString(3, p.getNome());
			ps.setDouble(4, p.getPrezzo());
			ps.setInt(5, p.getQuantità());
			ps.setString(6, p.getDescrizione() );
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
}
