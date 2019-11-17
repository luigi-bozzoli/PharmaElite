package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.beans.MetodoDiSpedizioneBean;

public class MetodoDiSpedizioneDAO {
	
		public void doSave(MetodoDiSpedizioneBean m){
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = DriverManagerConnectionPool.getConnection();
				ps = con.prepareStatement("INSERT INTO PROGETTOTSW.MetodoDiSpedizione value(?,?,?,?,?)");

				ps.setString(1, m.getEmailCliente());
				ps.setString(2, m.getIdOrdine());
				ps.setString(3, m.getNumTracking());
				ps.setString(4, m.getDataArrivo());
				ps.setString(5, m.getDettagli());

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


		public synchronized MetodoDiSpedizioneBean doRetrieveByKey(String emailCliente, String IdOrdine){

			Connection conn = null;
			PreparedStatement ps = null;

			try {

				MetodoDiSpedizioneBean m = new MetodoDiSpedizioneBean();
				conn = DriverManagerConnectionPool.getConnection();
				
				ps = conn.prepareStatement("select * from PROGETTOTSW.MetodoDiSpedizione where EmailCliente = ? AND IDOrdine = ?");
				ps.setString(1, emailCliente);
				ps.setString(2, IdOrdine);

				ResultSet res = ps.executeQuery();

				// Prende il risultato
				if(res.next())
				{
					m.setEmailCliente(emailCliente);
					m.setIdOrdine(IdOrdine);
					m.setDettagli(res.getString("dettagli"));
					m.setNumTracking(res.getString("numeroTracking"));
					m.setDataArrivo(res.getString("dataArrivo"));

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

