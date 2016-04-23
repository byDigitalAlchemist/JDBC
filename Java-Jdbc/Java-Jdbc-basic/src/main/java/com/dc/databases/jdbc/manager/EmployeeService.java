package com.dc.databases.jdbc.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {

	public static void displayEmployee(Connection myConn, String firstName, String lastName) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// Prepare statement
			
			StringBuilder prepStatement = new StringBuilder("select * from employees ");
			
			
			if(firstName != null ){
				prepStatement.append("first_name=? ");
				myStmt.setString(1, firstName);
			}
			
			if(lastName != null){
				
				if(firstName != null)
					prepStatement.append(" and ");
				
				prepStatement.append(" last_name=? ");
				myStmt.setString(2, lastName);
			}
			
			myStmt = myConn.prepareStatement(prepStatement.toString());

			
			
			// Execute SQL query
			myRs = myStmt.executeQuery();

			// Process result set
			while (myRs.next()) {
				String rsLastName = myRs.getString("last_name");
				String rsFirstName = myRs.getString("first_name");
				double salary = myRs.getDouble("salary");
				String department = myRs.getString("department");
				
				System.out.printf("%s, %s, %.2f, %s\n", rsLastName, rsFirstName, salary, department);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			DatabaseManager.close(myStmt, myRs);
		}

	}
	
	public static void displayEmployee(Connection myConn) throws SQLException{
		displayEmployee(myConn,null,null);
	}
	
	public static void displayEmployee(ResultSet resultSet) throws SQLException {

		try {


			// Process result set
			while (resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				double salary = resultSet.getDouble("salary");
				String department = resultSet.getString("department");
				
				System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			DatabaseManager.close(null, resultSet);
		}

	}
	
	
	public static void showSalariesViaDepartment(Connection myConn, String theDepartment) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			// Prepare statement
			statement = myConn
					.prepareStatement("select * from employees where department=?");

			statement.setString(1, theDepartment);
			
			// Execute SQL query
			resultSet = statement.executeQuery();

			// Process result set
			while (resultSet.next()) {
				String lastName = resultSet.getString("last_name");
				String firstName = resultSet.getString("first_name");
				double salary = resultSet.getDouble("salary");
				String department = resultSet.getString("department");
				
				System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			DatabaseManager.close(statement, resultSet);
		}

	}

	
}
