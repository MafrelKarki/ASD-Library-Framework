package edu.mum.asd.library.dbconfiguration;
/**
 *
 * @author YVAN G 
 * Short Description: The following defines the properties of the DBMS that will be used.
 */

public class DatabaseDescriptor {

	private String dbmsName;
	private String serverIP;
	private int portNo; 
	private String databaseName;
	private String username;
	private String password;

	public DatabaseDescriptor(String dbmsName, String serverIP, int portNo, String databaseName, String username, String password) {
		super();
		this.dbmsName = dbmsName;
		this.serverIP = serverIP;
		this.portNo = portNo;
		this.databaseName=databaseName;
		this.username = username;
		this.password = password;
	}

	public String getDbmsName() {
		return dbmsName;
	}

	public void setDbmsName(String dbmsName) {
		this.dbmsName = dbmsName;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getPortNo() {
		return portNo;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
