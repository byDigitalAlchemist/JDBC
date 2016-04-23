package com.dc.database.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class Application {
	public static void main(String[] args) {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		System.out.println(" Connecting ...");
		
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rockstardb", "root", "*****");

			System.out.println("Database connection successful!\n");

			// 2. Create a statement
			myStmt = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from employees");

			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
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
