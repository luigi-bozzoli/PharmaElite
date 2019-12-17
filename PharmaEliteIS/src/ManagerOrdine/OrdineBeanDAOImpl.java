package DAOImpl;

import java.sql.PreparedStatement;

public class OrdineBeanDAOImpl implements OrdineBean{
	
	public void doSave(OrdineBean o){
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("INSERT INTO PROGETTOTSW.Ordine value(?,?,?,?)");

			ps.setString(1, o.getId());
			ps.setString(2, o.getEmailCliente());
			ps.setString(3, o.getDataOrdine());
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

				o.setID(id);
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
	
	public synchronized void doUpdate(Ordine p){

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManagerConnectionPool.getConnection();
			ps = con.prepareStatement("update PROGETTOTSW.Ordine set EmailCliente=?,dataOrdine=?,costo=? where ID=?");
			
			ps.setString(1, p.getEmailCliente());
			ps.setDate(2, p.getDataOrdine());
			ps.setDouble(3, p.getCosto() );
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
	public synchronized void deleteByKey(String id){
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
