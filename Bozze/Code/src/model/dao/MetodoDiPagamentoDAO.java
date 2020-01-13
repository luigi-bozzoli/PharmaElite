package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.IndirizzoSpedizioneBean;
import model.beans.MetodoDiPagamentoBean;

public class MetodoDiPagamentoDAO {

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
	
	public synchronized ArrayList<MetodoDiPagamentoBean> doRetrieveAllByKey(String emailCliente){
		
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			
			ArrayList<MetodoDiPagamentoBean> list = new ArrayList<MetodoDiPagamentoBean>();
			MetodoDiPagamentoBean metodoDiPagamento; 
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.MetodoDiPagamento where EmailCliente = ? ");
			ps.setString(1,emailCliente);
			

			ResultSet res = ps.executeQuery();

			
			while(res.next())
			{

				metodoDiPagamento = new MetodoDiPagamentoBean();
				metodoDiPagamento.setNumCarta(res.getString("numeroCarta"));
				metodoDiPagamento.setTipo(res.getString("tipo"));
				metodoDiPagamento.setEmailCliente(res.getString("EmailCliente"));

				list.add(metodoDiPagamento);
			}
			
			return list;

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
	
	public synchronized MetodoDiPagamentoBean doRetrieveByKey(String emailCliente,String numCarta){

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			MetodoDiPagamentoBean m = new MetodoDiPagamentoBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("select * from PROGETTOTSW.MetodoDiPagamento where EmailCliente = ? and numeroCarta=?");
			ps.setString(1,emailCliente);
			ps.setString(2, numCarta);

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
}
