package edu.mum.asd.library.dbconfiguration;

/**
*
* @author YVAN G 
-> Short Description: The following is the implementation of IDbmsConnection Interface.
* We use the strategy pattern since our framework will help to connect to various RDBMS
* This implementation is for connecting to POSTGRESQL
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresqlConnector implements IDbmsConnection {

	private Connection connect;

	public Connection connect(DatabaseDescriptor dbDescription) throws SQLException, ClassNotFoundException {
		if (connect == null) {
			Properties connectionProps = new Properties();
			connectionProps.put("user", dbDescription.getUsername());
			connectionProps.put("password", dbDescription.getPassword());
			Class.forName("org.postgresql.Driver");
			connect = DriverManager
					.getConnection(
							"jdbc:" + dbDescription.getDbmsName() + "://" + dbDescription.getServerIP() + ":"
									+ dbDescription.getPortNo() + "/" + dbDescription.getDatabaseName(),
							connectionProps);
		}
		return connect;
	}

	public void disconnect() throws SQLException, ClassNotFoundException {
		if (connect != null) {
			try {
				connect.close();
				connect = null;
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
