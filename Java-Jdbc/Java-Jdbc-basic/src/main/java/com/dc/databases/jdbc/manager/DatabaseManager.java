package com.dc.databases.jdbc.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {

	
	Properties properties = new Properties();
	String strDbUrl;
	String strUsername;
	String strPassword;
	
	public DatabaseManager() throws FileNotFoundException, IOException{
		properties.load(new FileInputStream("src/main/resources/dbconfig.properties"));
		strDbUrl  = properties.getProperty("dburl");
		strUsername  = properties.getProperty("username");
		strPassword  = properties.getProperty("password");
	}
	
	
	public Connection getDatabaseConnection() throws SQLException{
		
		Connection dbConnection = DriverManager.getConnection(strDbUrl,strUsername, strPassword);
		
		System.out.println("Database connection successful!\n");
		return dbConnection;
	}
	
	
	public static void close(Connection myConn, Statement myStmt,
			ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	public static void close(Statement myStmt, ResultSet myRs)
			throws SQLException {
		close(null, myStmt, myRs);
	}
	
	
	
}
