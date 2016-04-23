package com.dc.databases.jdbc.storedprocs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc.databases.jdbc.manager.DatabaseManager;
import com.dc.databases.jdbc.manager.EmployeeService;
import com.dc.databases.jdbc.sql.DatabaseUpdate;

public class ExecuteSPwithInParameter {

	public void executeSP() throws SQLException {

		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = new DatabaseManager().getDatabaseConnection();

			String theDepartment = "Engineering";
			int theIncreaseAmount = 10000;

			// Show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			EmployeeService.showSalariesViaDepartment(connection, theDepartment);

			// Prepare the stored procedure call
			statement = connection.prepareCall("{call increase_salaries_for_department(?, ?)}");

			// Set the parameters
			statement.setString(1, theDepartment);
			statement.setDouble(2, theIncreaseAmount);

			// Call stored procedure
			System.out.println("\n\nCalling stored procedure.  increase_salaries_for_department('" + theDepartment
					+ "', " + theIncreaseAmount + ")");
			statement.execute();
			System.out.println("Finished calling stored procedure");

			// Show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			EmployeeService.showSalariesViaDepartment(connection, theDepartment);

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

		ExecuteSPwithInParameter execSp = new ExecuteSPwithInParameter();
		execSp.executeSP();
	}

}
