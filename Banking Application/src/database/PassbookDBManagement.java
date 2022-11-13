package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import banking.User;

public class PassbookDBManagement extends JDBC_Connection {

	Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultset;

	private static String createPassBook;
	private static String insert;
	private static String dropPassbook;
	private static String fiveRecords;

	float amount;

	private static void queries(short accNo) {

		createPassBook = "CREATE TABLE " + accNo + "_passbook(" + "SlNo INT PRIMARY KEY AUTO_INCREMENT,"
				+ "AccNo SMALLINT NOT NULL," + "Date_and_Time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
				+ "Debit FLOAT(9,2) DEFAULT 0," + "Credit FLOAT(9,2) DEFAULT 0,"
				+ "Available_Balance FLOAT(9,2) DEFAULT 0" + ")";
		insert = "INSERT INTO " + accNo + "_passbook (AccNo, Debit, Credit, Available_Balance) VALUES (?, ?, ?, ?)";
		dropPassbook = "DROP TABLE " + accNo + "_passbook";
		fiveRecords = "SELECT DATE_FORMAT(Date_and_Time, \"%d-%m-%Y  %T\") AS Date_and_Time, Debit, Credit, Available_Balance FROM "
				+ accNo + "_passbook ORDER BY SlNo DESC LIMIT 5";
	}

	private Boolean createPassbook() {

		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(PassbookDBManagement.createPassBook);
			count = preparedStatement.executeUpdate();
			System.out.println("Passbook Created Successfully...!");
		} catch (SQLException e) {
			System.err.println("Unable to create your Passbook.");
		}
		if (count == 0) {

			return true;
		}
		return false;
	}

	private void updateCredit(short accNo, float balance) {

		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(PassbookDBManagement.insert);
			preparedStatement.setShort(1, accNo);
			preparedStatement.setFloat(2, 0);
			preparedStatement.setFloat(3, amount);
			preparedStatement.setFloat(4, balance);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Unable to insert this Credit Record.");
		}
		if (count > 0) {
			System.out.println("Credit Record inserted in your Passbook.");
		} else {
			System.out.println("Credit Record not inserted in your Passbook.");
		}
	}

	private void updateDebit(short accNo, float balance) {

		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(PassbookDBManagement.insert);
			preparedStatement.setShort(1, accNo);
			preparedStatement.setFloat(2, amount);
			preparedStatement.setFloat(3, 0);
			preparedStatement.setFloat(4, balance);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Unable to insert this Debit Record.");
		}
		if (count > 0) {
			System.out.println("Debit Record inserted in your Passbook.");
		} else {
			System.out.println("Debit Record not inserted in your Passbook.");
		}
	}

	private void dropPassbook(short accNo) {

		int count = 1;
		try {
			preparedStatement = connection.prepareStatement(PassbookDBManagement.dropPassbook);
			count = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Unable to insert this Debit Record.");
		}
		if (count == 0) {
			System.out.println("Passbook Deleted Succussfully.");
		} else {
			System.out.println("Passbook not Deleted.");
		}
	}

	public ArrayList<MiniStatement> lastFiveTransaction(short accNo) {

		ArrayList<MiniStatement> miniStatement = new ArrayList<MiniStatement>();
		queries(accNo);
		connection = connector();
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(PassbookDBManagement.fiveRecords);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				MiniStatement mst = new MiniStatement();
				mst.setDateAndTime(resultset.getString("Date_and_Time"));
				mst.setDebit(resultset.getFloat("Debit"));
				mst.setCredit(resultset.getFloat("Credit"));
				mst.setBalance(resultset.getFloat("Available_Balance"));
				miniStatement.add(mst);
				count++;
			}
		} catch (SQLException e) {
			System.err.println("Unable to Fetch your Passbook.");
		}
		if (count > 0) {
			closer();
			return miniStatement;
		}
		return null;
	}

	public void handlePassbook(User user, float amount, int function) {

		this.amount = (float) amount;
		short accNo = user.getAccountNumber();
		float balance = user.getBalance();
		queries(accNo);
		connection = connector();
		switch (function) {
		case 1 -> createPassbook();
		case 2 -> updateCredit(accNo, balance);
		case 3 -> updateDebit(accNo, balance);
		case 4 -> dropPassbook(accNo);
		}
		closer();
	}
}