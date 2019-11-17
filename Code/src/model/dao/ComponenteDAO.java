package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.ComponenteBean;

public class ComponenteDAO {
	
		public void doSave(ComponenteBean c){
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = DriverManagerConnectionPool.getConnection();
				ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Componente value(?,?,?,?)");

				ps.setString(1, c.getIdOrdine());
				ps.setString(2,c.getEmailCliente());
				ps.setString(3,c.getIdProdotto());
				ps.setInt(4, c.getQuantità());
				
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


		public synchronized ComponenteBean doRetrieveByKey(String email){

			Connection conn = null;
			PreparedStatement ps = null;

			try {

				ComponenteBean componente = new ComponenteBean(); 
				conn = DriverManagerConnectionPool.getConnection();
				ps = conn.prepareStatement("select * from PROGETTOTSW.Componente where EmailCliente = ?");
				ps.setString(1, email);

				ResultSet res = ps.executeQuery();

				// Prendi il risultato
				if(res.next())
				{
					componente.setEmailCliente(res.getString("EmailCliente"));
					componente.setIdProdotto(res.getString("idProdotto"));
					componente.setIdOrdine(res.getString("idOrdine"));
					componente.setQuantità(res.getInt("quantità"));
					

					return componente;
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
		
		public synchronized ArrayList<ComponenteBean> doRetrieveByCliente(String email, String id){

			Connection conn = null;
			PreparedStatement ps = null;

			try {

				ArrayList<ComponenteBean> lista = new ArrayList<ComponenteBean>();				conn = DriverManagerConnectionPool.getConnection();
				ps = conn.prepareStatement("select * from PROGETTOTSW.Componente where EmailCliente = ? and IDOrdine = ?");
				ps.setString(1, email);
				ps.setString(2, id);

				ResultSet res = ps.executeQuery();

				// Prendi il risultato
				while(res.next())
				{
					ComponenteBean componente = new ComponenteBean(); 
					componente.setEmailCliente(res.getString("EmailCliente"));
					componente.setIdProdotto(res.getString("idProdotto"));
					componente.setIdOrdine(res.getString("idOrdine"));
					componente.setQuantità(res.getInt("quantità"));
					
					lista.add(componente);
				}
				
				return lista;

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
	}

