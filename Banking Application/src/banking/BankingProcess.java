package banking;

public interface BankingProcess {

	abstract void getDepositAmount();

	abstract void getWithdrawAmount();

	abstract void getTransferMode();

	abstract void getMiniStatement();

	abstract void getUserInfo();

	abstract void deleteAccount();
}