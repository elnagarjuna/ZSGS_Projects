package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisteredUser {

	public void checkUser() {

		User customer = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean validation = true;
		short accNo = 0;
		while (validation) {
			try {
				System.out.print("Enter your Account Number : ");
				accNo = sc.nextShort();
				validation = (InputValidation.validatingAccNo(accNo)) ? false : true;
			} catch (Exception e) {
				System.err.println("Account Number is not in correct Format.");
				sc.next();
			}
		}
		System.out.print("Enter your Password       : ");
		String password = sc.next();
		if (NewUser.dbm.accCheck(accNo, password)) {
			customer = NewUser.dbm.fetchUserDetail(accNo, 0);
			validation = true;
			while (validation) {
				Transactions bank = new Transactions(customer);
				System.out.println("+-----------------------+");
				System.out.println("|  W  E  L  C  O  M  E  |");
				System.out.println("+---+-------------------+");
				System.out.println("| 1 | Deposit.          |");
				System.out.println("| 2 | Withdraw.         |");
				System.out.println("| 3 | Account Transfer. |");
				System.out.println("| 4 | User Information. |");
				System.out.println("| 5 | Mini Statement    |");
				System.out.println("| 6 | Delete Account.   |");
				System.out.println("| 7 | Log out.          |");
				System.out.println("+---+-------------------+");
				byte choice = 0;
				try {
					System.out.println("Enter your choice : ");
					choice = sc.nextByte();
				} catch (InputMismatchException e) {
					System.out.println("Enter the 'Numeric' value.");
					sc.next();
				}
				switch (choice) {
				case 1 -> {
					bank.getDepositAmount();
				}
				case 2 -> {
					bank.getWithdrawAmount();
				}
				case 3 -> {
					bank.getTransferMode();
				}
				case 4 -> {
					bank.getUserInfo();
				}
				case 5 -> {
					bank.getMiniStatement();
				}
				case 6 -> {
					bank.deleteAccount();
				}
				case 7 -> {
					System.out.println("Successfully Logged out...!");
					validation = false;
				}
				default -> System.out.println("Enter the given options only.");
				}
			}
		} else {
			System.out.println("Invalid AccNo or Password.");
		}
	}
}