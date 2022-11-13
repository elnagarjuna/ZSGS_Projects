package banking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.CustomerDBManagement;
import database.PassbookDBManagement;

public class NewUser extends InputValidation {

	static InputStreamReader ISR = new InputStreamReader(System.in);
	static BufferedReader BR = new BufferedReader(ISR);
	static Scanner SC = new Scanner(System.in);
	static CustomerDBManagement dbm = new CustomerDBManagement();
	public static PassbookDBManagement pdm = new PassbookDBManagement();

	boolean validation = true;
	User user;

	public enum Genders {
		FEMALE, MALE, OTHERS;

//		OTHERS --> Transgender
	}

	private String getName() {

		String name = null;
		while (validation) {
			try {
				System.out.print("Enter your Name : ");
				name = BR.readLine();
			} catch (Exception e) {
				System.err.println("Enter a valid Name.");
			}
			validation = (validatingName(name)) ? false : true;
			if (validation) {
				System.out.println("Give a valid Name.");
			}
		}
		validation = true;
		return name;
	}

	private Genders getGender() {

		Genders gender = null;
		char gen = 0;
		while (validation) {
			try {
				System.out.print("Enter your Gender [F or M or O] : ");
				gen = SC.next().toUpperCase().charAt(0);
				validation = (validatingGender(gen)) ? false : true;
				if (!validation) {
					switch (gen) {
					case 'F' -> gender = Genders.FEMALE;
					case 'M' -> gender = Genders.MALE;
					case 'O' -> gender = Genders.OTHERS;
					}
				} else {
					System.out.println("Enter a valid gender.");
				}
			} catch (Exception e) {
				System.err.println("Give a valid gender.");
				SC.next();
			}
		}
		validation = true;
		return gender;
	}

	private long getContactNumber() {

		long contact = 0;
		while (validation) {
			try {
				System.out.print("Enter your Contact Number : ");
				contact = SC.nextLong();
				validation = (validatingNumber(contact)) ? false : true;
				if (!Menu.contactCheck.add(contact)) {
					System.out.println("This mobile number is already exists.");
					validation = true;
				}
			} catch (InputMismatchException e) {
				System.err.println("Give 'Numeric' values only.");
			}
			if (validation) {
				System.out.println("Give a valid contact number.");
			}
		}
		validation = true;
		return contact;
	}

	private String getDOB() {

		String date = null;
		while (validation) {
			try {
				System.out.print("Enter your DOB in order (dd-mm-yyyy) : ");
				String dob = SC.next();
				validation = (validatingDate(dob)) ? false : true;
				if (validation) {
					System.out.println("Enter a valid date.");
				} else {
					date = dob;
				}
			} catch (Exception e) {
				System.err.println("Enter the correct format.");
				SC.next();
			}
		}
		validation = true;
		return date;
	}

	private String getPan() {

		String pan = null;
		while (validation) {
			try {
				System.out.print("Enter your PAN Number : ");
				pan = SC.next();
				validation = (validatingPan(pan)) ? false : true;
				if (!Menu.panCheck.add(pan)) {
					System.out.println("Pan Number already exists.");
					validation = true;
				}
			} catch (Exception e) {
				System.err.println("Enter the valid format.");
				SC.next();
			}
			if (validation) {
				System.out.println("Give a valid PAN Number.");
			}
		}
		validation = true;
		return pan;
	}

	private String getUsername() {

		String userName = null;
		while (validation) {
			try {
				System.out.print("Create UPI ID : ");
				userName = BR.readLine();
				validation = (validatingUserName(userName)) ? false : true;
				if (!Menu.upiIdCheck.add(userName)) {
					System.out.println("Username already exists.");
					validation = true;
				}
			} catch (Exception e) {
				System.err.println("Enter a valid Username.");
			}
			if (validation) {
				System.out.println("Give a valid Username.");
			}
		}
		validation = true;
		return userName;
	}

	private String getPassword() {

		String password = null;
		System.out.println("Password Constrains :-");
		System.out.println("Minimum contains 8 Characters.");
		System.out.println("Minimum contains 1 Uppercase.");
		System.out.println("Minimum contains 1 Lowercase.");
		System.out.println("Minimum contains 1 Digit.");
		System.out.println("Minimum contains 1 Special Character.");
		while (validation) {
			try {
				System.out.print("Create a Password : ");
				password = BR.readLine();
			} catch (Exception e) {
				System.err.println("Enter a valid Password.");
			}
			validation = (validatingPassword(password)) ? false : true;
			if (validation) {
				System.out.println("Enter a valid Password.");
			}
		}
		validation = true;
		return password;
	}

	private String getUpiPin() {

		String pin = null;
		while (validation) {
			try {
				System.out.print("Create a Upi Pin : ");
				pin = SC.next();
			} catch (Exception e) {
				System.err.println("Enter Four Digit Numbers only.");
				SC.next();
			}
			validation = (validatingPin(pin)) ? false : true;
			if (validation) {
				System.out.println("Enter the valid pin.");
			}
		}
		validation = true;
		return pin;
	}

	private float getInitialAmount() {

		float amount = 0;
		while (validation) {
			try {
				System.out.print("Enter Initial Deposit : ");
				amount = SC.nextFloat();
				validation = (validatingInitialAmount(amount)) ? false : true;
			} catch (InputMismatchException e) {
				System.err.println("Enter 'numeric' vlaues only.");
				SC.next();
			}
			if (validation) {
				System.out.println("Deposit initial amount Rs.500/- to Rs. 2000/-");
			}
		}
		validation = true;
		return amount;
	}

	private void accountResult(User user) {

		System.out.println("Account Number is                : " + user.getAccountNumber());
		System.out.println("Account Holder Name is           : " + user.getName());
		System.out.println("Account Holder Gender is         : " + user.getGender());
		System.out.println("Account Holder Contact Number is : " + user.getContactNumber());
		System.out.println("Account Holder PAN Number is     : " + user.getPan());
		System.out.println("Account Holder UPI_ID is         : " + user.getUserName());
		System.out.println("Account Balance is               : Rs. " + user.getBalance() + "/-");
		System.out.println("Your Account is Successfully Created :-");
	}

	public void creatAccount() throws IOException {

		user = new User();

		System.out.println("Create an Account :-");

		user.setName(getName());
		user.setGender(getGender().toString());
		user.setContactNumber(getContactNumber());
		user.setDob(getDOB());
		user.setPan(getPan());
		user.setUserName(getUsername());
		user.setPassword(getPassword());
		user.setUpiPin(getUpiPin());
		user.setBalance(getInitialAmount());

		dbm.handleData(user, 1);

		pdm.handlePassbook(user, user.getBalance(), 1);
		pdm.handlePassbook(user, user.getBalance(), 2);

		accountResult(user);
	}
}