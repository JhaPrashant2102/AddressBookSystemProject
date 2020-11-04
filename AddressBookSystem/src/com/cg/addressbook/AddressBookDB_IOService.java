package com.cg.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddressBookDB_IOService {
	
	enum StatementType {
		PREPARED_STATEMENT, STATEMENT;
	}

	private PreparedStatement addressBookDataStatement;
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ContactList;
	}

	private List<Contact> getAddressBookData(ResultSet resultSet) {
		List<Contact> contactList = new ArrayList<>();
		try {
			while (resultSet.next()) {
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
		final String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?SSL=false";
		final String userName = "root";
		final String password = "root";
		Connection connection;
		System.out.println("Processing Thread: " + Thread.currentThread().getName() + "Connecting to database with Id:"
				+ connectionCounter);
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Processing Thread: " + Thread.currentThread().getName() + " Id: " + connectionCounter
				+ " Connection is successful!!!   " + connection);
		return connection;
	}

	public int updateContactDetails(String name, String phoneNumber) {
		try (Connection connection = this.getConnection()) {
			String sql = "update address_book set phone_number = ? where name = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, phoneNumber);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Contact> getContactData(String name) {
		List<Contact> contactList = null;
		if (this.addressBookDataStatement == null) {
			this.prepareStatementForContactData();
		}
		try {
			addressBookDataStatement.setString(1, name);
			ResultSet resultSet = addressBookDataStatement.executeQuery();
			contactList = this.getAddressBookData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	private void prepareStatementForContactData() {
		try {
			Connection connection = this.getConnection();
			String sql = "Select * from address_book where name = ?;";
			addressBookDataStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Map<String,Integer> readCity() {
		String sql = "select city, count(city) as count_of_city from address_book;";
		Map<String, Integer> countOfCityMap = new HashMap<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String city = resultSet.getString("city");
				Integer count = resultSet.getInt("count_of_city");
				countOfCityMap.put(city, count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countOfCityMap;
	}

}
