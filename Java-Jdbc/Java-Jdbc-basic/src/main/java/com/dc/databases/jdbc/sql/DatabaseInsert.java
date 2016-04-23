package com.dc.databases.jdbc.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc.databases.jdbc.manager.DatabaseManager;

public class DatabaseInsert {
	
	
	public void insertData(){
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try{
			connection = new DatabaseManager().getDatabaseConnection();
			statement = connection.createStatement();
			
			
			System.out.println("Inserting a new employee to database\n");
			
			int rowsAffected = statement.executeUpdate(
				"insert into employees " +
				"(last_name, first_name, email, department, salary) " + 
				"values " + 
				"('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");
			
			// 4. Verify this by getting a list of employees
			resultSet = statement.executeQuery("select * from employees order by last_name");
			
			// 5. Process the result set
			while (resultSet.next()) {
				System.out.println(resultSet.getString("last_name") + ", " + resultSet.getString("first_name"));
			}
			
			
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		} catch (FileNotFoundException fileException) {
			fileException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			
				try {
					
					
					if(statement != null)
						statement.close();
					
					if(resultSet != null)
						resultSet.close();
					
					if(connection != null)
						connection.close();
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		DatabaseInsert dbInsert = new DatabaseInsert();
		dbInsert.insertData();
	}
}
