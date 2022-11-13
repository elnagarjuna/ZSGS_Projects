package banking;

public class InputValidation {

	protected Boolean validatingName(String name) {
		return name.matches("^[aA-zZ][aA-zZ\\s.]{2,29}$");
	}

	protected Boolean validatingGender(char gen) {
		String gender = Character.toString(gen);
		return (gender.equals("F") || gender.equals("M") || gender.equals("O")) ? true : false;
	}

	protected Boolean validatingNumber(long number) {
		return String.valueOf(number).matches("^[6789][0-9]{9}$");
	}

	protected Boolean validatingDate(String date) {
		return date.matches("^(0?[1-9]|[12][0-9]|3[01])[/-](0?[1-9]|1[012])[/-]\\d{4}$");
	}

	protected Boolean validatingPan(String pan) {
		return pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}");
	}

	protected Boolean validatingUserName(String userName) {
		return userName.matches("^[a-z][a-z0-9+_.-]+@(.+)$");
	}

	protected Boolean validatingPassword(String password) {
		return password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}");
	}

	protected Boolean validatingPin(String pin) {
		return pin.matches("[0-9]{4}");
	}

	protected Boolean validatingInitialAmount(float initialAmount) {

		if (initialAmount < 500) {
			return false;
		} else if (initialAmount > 2000) {
			return false;
		} else {
			return true;
		}
	}

	public static Boolean validatingAmount(float amount) {

		return (amount > 0 && amount <= 50000) ? true : false;
	}

	public static Boolean validatingAccNo(short accNo) {

		return (accNo > 1000 && accNo < 10000) ? true : false;
	}
}