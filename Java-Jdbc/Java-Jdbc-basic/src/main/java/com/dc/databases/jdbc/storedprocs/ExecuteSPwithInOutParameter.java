package com.dc.databases.jdbc.storedprocs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.dc.databases.jdbc.manager.DatabaseManager;
import com.dc.databases.jdbc.manager.EmployeeService;
import com.dc.databases.jdbc.sql.DatabaseUpdate;

public class ExecuteSPwithInOutParameter {

	public void executeSP() throws SQLException {

		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = new DatabaseManager().getDatabaseConnection();

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			statement = connection
					.prepareCall("{call greet_the_department(?)}");

			// Set the parameters
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.setString(1, theDepartment);

			// Call stored procedure
			System.out.println("Calling stored procedure.  greet_the_department('" + theDepartment + "')");
			statement.execute();
			System.out.println("Finished calling stored procedure");			
			
			// Get the value of the INOUT parameter
			String theResult = statement.getString(1);
			
			System.out.println("\nThe result = " + theResult);

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (FileNotFoundException fileException) {
			fileException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			DatabaseManager.close(connection, statement, resultSet);
		}

	}

	public static void main(String[] args) throws SQLException {

		ExecuteSPwithInOutParameter execSp = new ExecuteSPwithInOutParameter();
		execSp.executeSP();
	}

}
