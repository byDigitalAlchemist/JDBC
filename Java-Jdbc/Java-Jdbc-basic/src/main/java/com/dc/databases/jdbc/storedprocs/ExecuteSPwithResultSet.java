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

public class ExecuteSPwithResultSet {

	public void executeSP() throws SQLException {

		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = new DatabaseManager().getDatabaseConnection();

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			statement = connection.prepareCall("{call get_employees_for_department(?)}");

			// Set the parameter
			statement.setString(1, theDepartment);

			// Call stored procedure
			System.out.println("Calling stored procedure.  get_employees_for_department('" + theDepartment + "')");
			statement.execute();
			System.out.println("Finished calling stored procedure.\n");

			// Get the result set
			resultSet = statement.getResultSet();

			// Display the result set
			EmployeeService.displayEmployee(resultSet);

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

		ExecuteSPwithResultSet execSp = new ExecuteSPwithResultSet();
		execSp.executeSP();
	}

}
