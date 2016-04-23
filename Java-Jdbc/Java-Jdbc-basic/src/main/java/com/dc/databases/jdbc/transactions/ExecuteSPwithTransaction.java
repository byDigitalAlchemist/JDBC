package com.dc.databases.jdbc.transactions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.dc.databases.jdbc.manager.DatabaseManager;
import com.dc.databases.jdbc.manager.EmployeeService;

public class ExecuteSPwithTransaction {

	public void executeSP() throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = new DatabaseManager().getDatabaseConnection();
			connection.setAutoCommit(false);

			// Show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			EmployeeService.showSalariesViaDepartment(connection, "HR");
			EmployeeService.showSalariesViaDepartment(connection, "Engineering");

			// Transaction Step 1: Delete all HR employees
			statement = connection.createStatement();
			statement.executeUpdate("delete from employees where department='HR'");

			// Transaction Step 2: Set salaries to 300000 for all Engineering
			// employees
			statement.executeUpdate("update employees set salary=300000 where department='Engineering'");

			System.out.println("\n>> Transaction steps are ready.\n");

			// Ask user if it is okay to save
			boolean ok = askUserIfOkToSave();

			if (ok) {
				// store in database
				connection.commit();
				System.out.println("\n>> Transaction COMMITTED.\n");
			} else {
				// discard
				connection.rollback();
				System.out.println("\n>> Transaction ROLLED BACK.\n");
			}

			// Show salaries AFTER
			System.out.println("Salaries AFTER\n");
			EmployeeService.showSalariesViaDepartment(connection, "HR");
			EmployeeService.showSalariesViaDepartment(connection, "Engineering");

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

		ExecuteSPwithTransaction execSp = new ExecuteSPwithTransaction();
		execSp.executeSP();
	}
	
	private static boolean askUserIfOkToSave() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Is it okay to save?  yes/no: ");
		String input = scanner.nextLine();

		scanner.close();

		return input.equalsIgnoreCase("yes");
	}

}
