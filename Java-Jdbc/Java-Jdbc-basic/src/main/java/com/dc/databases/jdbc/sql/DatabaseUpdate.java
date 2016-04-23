package com.dc.databases.jdbc.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc.databases.jdbc.manager.DatabaseManager;
import com.dc.databases.jdbc.manager.EmployeeService;

public class DatabaseUpdate {

	public void updateData() throws SQLException{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try{
			connection = new DatabaseManager().getDatabaseConnection();
			// Call helper method to display the employee's information
			System.out.println("BEFORE THE UPDATE...");
			EmployeeService.displayEmployee(connection, "John", "Doe");
			
			statement = connection.createStatement();
			
			// UPDATE the employee
			System.out.println("\nEXECUTING THE UPDATE FOR: John Doe\n");
			
			int rowsAffected = statement.executeUpdate(
					"update employees " +
					"set email='john.doe@digitalAlchemist.com' " + 
					"where last_name='Doe' and first_name='John'");
			
			// Call helper method to display the employee's information
			System.out.println("AFTER THE UPDATE...");
			EmployeeService.displayEmployee(connection, "John", "Doe");			
			
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		} catch (FileNotFoundException fileException) {
			fileException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			DatabaseManager.close(connection, statement, resultSet);
		}
		
		
	}
	
	
	
	
	public static void main(String[] args) throws SQLException {
		
		DatabaseUpdate dbUpdate = new DatabaseUpdate();
		dbUpdate.updateData();
	}

}
