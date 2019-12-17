package ManagerUtente;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverManagerConnectionPool  {

	private static ArrayList<Connection> freeDbConnections;

	static {
		freeDbConnections = new ArrayList<Connection>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String username = "root";
		String password = "Pollococaina97";

		newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET",username, password);

		
		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
}
