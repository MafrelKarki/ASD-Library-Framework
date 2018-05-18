package edu.mum.asd.library.dbconfiguration;

import java.sql.Connection;
import java.sql.SQLException;

public class StrategyContext {
	
	private IDbmsConnection dbmsConnection;
	
	public Connection connect(DatabaseDescriptor dbDescription) throws ClassNotFoundException, SQLException {
		return dbmsConnection.connect(dbDescription);
	}
	
	public void disconnect()  throws ClassNotFoundException, SQLException {
		dbmsConnection.disconnect();
	}

	public void setDbmsConnection(IDbmsConnection dbmsConnection) {
		this.dbmsConnection = dbmsConnection;
	}
}
