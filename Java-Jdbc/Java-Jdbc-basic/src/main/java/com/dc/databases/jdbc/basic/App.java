package com.dc.databases.jdbc.basic;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.dc.databases.jdbc.manager.EmployeeService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/dbconfig.properties"));
		
		
		String strDbUrl  = properties.getProperty("dburl");
		String strUsername  = properties.getProperty("username");
		String strPassword  = properties.getProperty("password");
		
		
		Connection myConn = null;	
		Statement myStmt = null;
		ResultSet myRs = null;

		System.out.println(" Connecting ...");
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(strDbUrl,strUsername, strPassword);

			System.out.println("Database connection successful!\n");

			// 2. Create a statement
			myStmt = myConn.createStatement();

			EmployeeService.displayEmployee(myConn);
		} catch (Exception exc) {
			
			System.out.println();
			exc.printStackTrace();
		} finally {
			
			try{
			
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
			catch(SQLException exception){
				exception.printStackTrace();
			}
			
			
		}

	}
}
