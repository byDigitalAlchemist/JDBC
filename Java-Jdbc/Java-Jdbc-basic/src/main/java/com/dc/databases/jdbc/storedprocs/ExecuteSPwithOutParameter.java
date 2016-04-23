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

public class ExecuteSPwithOutParameter {

	public void executeSP() throws SQLException {

		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = new DatabaseManager().getDatabaseConnection();

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			statement = connection
					.prepareCall("{call get_count_for_department(?, ?)}");

			// Set the parameters
			statement.setString(1, theDepartment);
			statement.registerOutParameter(2, Types.INTEGER);
			
			// Call stored procedure
			System.out.println("Calling stored procedure.  get_count_for_department('" + theDepartment + "', ?)");
			statement.execute();
			System.out.println("Finished calling stored procedure");			
			
			// Get the value of the OUT parameter
			int theCount = statement.getInt(2);
			
			System.out.println("\nThe count = " + theCount);

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

		ExecuteSPwithOutParameter execSp = new ExecuteSPwithOutParameter();
		execSp.executeSP();
	}

}
