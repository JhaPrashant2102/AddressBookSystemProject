package com.cg.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDB_IOService {
	
	enum StatementType {
		PREPARED_STATEMENT, STATEMENT;
	}

	private PreparedStatement employeePayrollDataStatement;
	private int connectionCounter = 0;
	private static AddressBookDB_IOService addressBookDB_IOService;

	private AddressBookDB_IOService() {
	}

	public static AddressBookDB_IOService getInstance() {
		if (addressBookDB_IOService == null) {
			addressBookDB_IOService = new AddressBookDB_IOService();
		}
		return addressBookDB_IOService;
	}

	public List<Contact> readData() {
		String sql = "select * from address_book;";
		List<Contact> ContactList = new ArrayList<>();
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			ContactList = this.getAddressBookData(result);
			connection.close();
			// connection needs to be closed - notes
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ContactList;
	}

	private List<Contact> getAddressBookData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				//TODO
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String phoneNumber = resultSet.getString("phone_number");
				String emailId = resultSet.getString("email_id");
				contactList.add(new Contact(firstName, lastName, city, state, phoneNumber, emailId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	private Connection getConnection() throws SQLException {
		connectionCounter++;
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?SSL=false";
		String userName = "root";
		String password = "root";
		Connection connection;
		System.out.println("Processing Thread: " + Thread.currentThread().getName() + "Connecting to database with Id:"
				+ connectionCounter);
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Processing Thread: " + Thread.currentThread().getName() + " Id: " + connectionCounter
				+ " Connection is successful!!!   " + connection);
		return connection;
	}

}
