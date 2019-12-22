package managerOrdine;

import java.sql.*;

import managerUtente.DriverManagerConnectionPool;

public class OrdineBeanDAOImpl implements OrdineBeanDAO{
	
	public void doSave(OrdineBean o){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Ordine value(?,?,?,?)");

			ps.setString(1, o.getId());
			ps.setString(2, o.getEmailCliente());
			ps.setDate(3, (Date) o.getDataOrdine());
			ps.setDouble(4, o.getCosto());

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

	public synchronized OrdineBean doRetrieveByKey(String id){

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
				o.setId(id);
				o.setEmailCliente(res.getString("EmailCliente"));
				o.setDataOrdine(res.getDate("dataOrdine"));
				o.setCosto(res.getDouble("costo"));
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
	
	public synchronized void doUpdate(OrdineBean p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Ordine set EmailCliente=?,dataOrdine=?,costo=? where ID=?");
			
			ps.setString(1, p.getEmailCliente());
			ps.setDate(2, (Date) p.getDataOrdine());
			ps.setDouble(3, p.getCosto() );
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
			ps = conn.prepareStatement("delete from PROGETTOTSW.Ordine where ID = ?");
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
