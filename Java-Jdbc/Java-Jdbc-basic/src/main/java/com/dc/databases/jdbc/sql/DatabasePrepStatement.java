package com.dc.databases.jdbc.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dc.databases.jdbc.manager.DatabaseManager;
import com.dc.databases.jdbc.manager.EmployeeService;

public class DatabasePrepStatement {

	public void reUsePreparedStatement() throws SQLException {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = new DatabaseManager().getDatabaseConnection();

			// 2. Prepare statement
			statement = connection.prepareStatement("select * from employees where salary > ? and department=?");

			// 3. Set the parameters
			statement.setDouble(1, 80000);
			statement.setString(2, "Legal");

			// 4. Execute SQL query
			resultSet = statement.executeQuery();

			// 5. Display the result set
			EmployeeService.displayEmployee(resultSet);

			//
			// Reuse the prepared statement: salary > 25000, department = HR
			//

			System.out.println("\n\nReuse the prepared statement:  salary > 25000,  department = HR");

			// 6. Set the parameters
			statement.setDouble(1, 25000);
			statement.setString(2, "HR");

			// 7. Execute SQL query
			resultSet = statement.executeQuery();

			// 8. Display the result set
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

		DatabasePrepStatement reusePrepState = new DatabasePrepStatement();
		reusePrepState.reUsePreparedStatement();
	}
}
