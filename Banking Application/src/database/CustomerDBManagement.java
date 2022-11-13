package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import banking.NewUser;
import banking.User;

public class CustomerDBManagement extends JDBC_Connection {

	Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultset;

	User user;

	private static final String ADD_CUSTOMER = "INSERT INTO customer_details (Customer_Name, Gender, Contact_Number, DOB, PAN_Card, UPI_ID, Password, UPI_PIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String FIND_ACC_NO = "SELECT AccNo FROM customer_details WHERE UPI_ID = ?";
	private static final String DELETE_CUSTOMER = "DELETE FROM customer_details WHERE AccNo = ?";
	private static final String ACC_CHECK = "SELECT AccNo,Password FROM customer_details WHERE AccNo = ?";
	private static final String ACC_EXIST = "SELECT AccNo FROM customer_details WHERE AccNo = ?";
	private static final String UPI_EXIST = "SELECT UPI_ID FROM customer_details WHERE UPI_ID = ?";
	private static final String PASS_CHECK = "SELECT Password FROM customer_details WHERE Password = ?";
	private static final String FETCH_DATA = "SELECT * FROM customer_details WHERE AccNo = ?";
	private static final String FETCH_ALLACCNO = "SELECT AccNo FROM customer_details";

	private Boolean getAccountNumber() {

		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(FIND_ACC_NO);
			preparedStatement.setString(1, user.getUserName());
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				user.setAccountNumber(resultset.getShort("AccNo"));
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Unable to get your Account Number.");
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	private Boolean addCustomer() {

		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(ADD_CUSTOMER);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getGender());
			preparedStatement.setLong(3, user.getContactNumber());
			preparedStatement.setString(4, user.getDob());
			preparedStatement.setString(5, user.getPan());
			preparedStatement.setString(6, user.getUserName());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setString(8, user.getUpiPin());
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Unable to add your Data.");
		}
		if (count > 0) {
			if (getAccountNumber()) {
				return true;
			}
		}
		return false;
	}

	private void deleteCustomer() {

		int count = 0;
		try {
			NewUser.pdm.handlePassbook(user, 0, 4);
			preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
			preparedStatement.setShort(1, user.getAccountNumber());
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Unable to fetch your Record");
		}
		if (count > 0) {
			System.out.println("Account deleted Successfully...!");
		} else {
			System.out.println("Account is not deleted.");
		}
	}

	public void handleData(User user, int function) {

		this.user = user;
		connection = connector();
		switch (function) {
		case 1 -> addCustomer();
		case 2 -> deleteCustomer();
		}
		closer();
	}

	public Boolean accCheck(short accNo) {

		connection = connector();
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(ACC_EXIST);
			preparedStatement.setShort(1, accNo);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Unable to identify your Account.");
		}
		if (count == 1) {
			return true;
		}
		return false;
	}

	public Boolean upiCheck(String upiId) {

		connection = connector();
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(UPI_EXIST);
			preparedStatement.setString(1, upiId);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				count++;
			}
		} catch (SQLException e) {
			System.err.println("UPI Id is not Exists.");
		}
		if (count == 1) {
			closer();
			return true;
		}
		return false;
	}

	public Boolean accCheck(short accNo, String password) {

		connection = connector();
		int count = 0;
		short accNo1 = 0;
		String password1 = null;
		try {
			preparedStatement = connection.prepareStatement(ACC_CHECK);
			preparedStatement.setShort(1, accNo);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				accNo1 = resultset.getShort("AccNo");
				password1 = resultset.getString("Password");
			}
			if (accNo1 == accNo && password1.equals(password)) {
				count = 1;
			}
		} catch (SQLException e) {
			System.err.println("Unable to identify your Account.");
		}
		if (count > 0) {
			closer();
			return true;
		}
		return false;
	}

	public Boolean passwordCheck(String password) {

		connection = connector();
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(PASS_CHECK);
			preparedStatement.setString(1, password);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Password is not exists.");
		}
		if (count > 0) {
			closer();
			return true;
		}
		return false;
	}

	public User fetchUserDetail(String upiId) {

		connection = connector();
		User user = null;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(FIND_ACC_NO);
			preparedStatement.setString(1, upiId);
			resultset = preparedStatement.executeQuery();
			user = new User();
			while (resultset.next()) {
				user.setAccountNumber(resultset.getShort("AccNo"));
				count++;
			}
			user = fetchUserDetail(user.getAccountNumber(), 1);
		} catch (SQLException e) {
			System.err.println("Unable to Fetch Your Account UPI.");
		}
		if (count > 0) {
			return user;
		}
		return null;
	}

	public User fetchUserDetail(short accNo, int check) {

		if (check != 1) {
			connection = connector();
		}
		User user = null;
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(FETCH_DATA);
			preparedStatement.setShort(1, accNo);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				user = new User();
				user.setAccountNumber(resultset.getShort("AccNo"));
				user.setName(resultset.getString("Customer_Name"));
				user.setGender(resultset.getString("Gender"));
				user.setContactNumber(resultset.getLong("Contact_Number"));
				user.setDob(resultset.getString("DOB"));
				user.setPan(resultset.getString("PAN_Card"));
				user.setUserName(resultset.getString("UPI_ID"));
				user.setPassword(resultset.getString("Password"));
				user.setUpiPin(resultset.getString("UPI_PIN"));
				count++;
			}
			preparedStatement = connection.prepareStatement("SELECT Available_Balance FROM " + user.getAccountNumber()
					+ "_passbook ORDER BY SlNo DESC LIMIT 1");
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				user.setBalance(resultset.getFloat("Available_Balance"));
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Unable to Fetch Your Account No.");
		}
		if (count > 0) {
			closer();
			return user;
		}
		return null;
	}

	public ArrayList<User> fetchCustomersDetails() {

		connection = connector();
		int count = 0;
		ArrayList<User> customer = new ArrayList<User>();
		try {
			preparedStatement = connection.prepareStatement(FETCH_ALLACCNO);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				user = fetchUserDetail(resultset.getShort("AccNo"), 1);
				customer.add(user);
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Unable to Fetch the Customers Data.");
		}
		if (count > 0) {
			return customer;
		}
		return null;
	}
}