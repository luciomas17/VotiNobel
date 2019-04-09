package it.polito.tdp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static final String jdbcURL = "jdbc:mysql://localhost/esamitriennale?user=root&password=root&serverTimezone=UTC";

	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			
		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
		
		return conn;
	}
}