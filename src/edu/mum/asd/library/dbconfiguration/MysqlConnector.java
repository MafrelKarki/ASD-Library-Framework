package edu.mum.asd.library.dbconfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author YVAN G -> 
 * Short Description: The following is the implementation of IDbmsConnection Interface.
 * 					  We use the strategy pattern since our framework will help to connect to various RDBMS
 * 					  This implementation is for connecting to MYSQL
 */
public class MysqlConnector implements IDbmsConnection {

	private Connection connect;

	public Connection connect(DatabaseDescriptor dbDescription) throws SQLException, ClassNotFoundException {
		if(connect==null) {
			Properties connectionProps = new Properties();
			connectionProps.put("user", dbDescription.getUsername());
			connectionProps.put("password", dbDescription.getPassword());
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:" + dbDescription.getDbmsName() + "://" + dbDescription.getServerIP() + ":" + dbDescription.getPortNo() + "/" + dbDescription.getDatabaseName(), connectionProps);
		}
		return connect;
	}

	public void disconnect() throws SQLException {
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
