package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection implements Server {

	Connection con = null;

	private Connection getConnection() {

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Sorry Unable to Connect.");
		}
		return con;
	}

	public void closer() {

		try {
			con.close();
		} catch (Exception e) {
			System.err.println("Unable to close your Database.");
		}
	}

	public Connection connector() {

		return getConnection();
	}
}