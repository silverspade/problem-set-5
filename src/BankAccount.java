
/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {
	
	private long accountNumber;
	private double balance;
	private User user;
	private char accountStatus;
	
	private String temp;
	
	public BankAccount(String line) {
		temp = line.substring(0, 9);
		this.accountNumber = Long.valueOf(temp);
		temp = line.substring(13, 28);
		this.balance = Double.valueOf(temp);
		this.user = new User(line.substring(28, 48), line.substring(48, 63), line.substring(9, 13), line.substring(63, 71), line.substring(71, 81), line.substring(81, 111), line.substring(111, 141), line.substring(141, 143), line.substring(143, 148));
		this.accountStatus = line.charAt(148);
	}
	
	public int deposit (double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			this.balance = balance + amount;
			return 1;
		}
	}
	
	public int withdrawAble (double amount) {
		if (this.balance == 0) {
			return 0;
		} else if (amount <= 0) {
			return -1;
		} else if (amount > this.balance) {
			return -2;
		}  else {
			return 1;
		}
	}
	
	public int withdraw(double amount) {
		if(withdrawAble(amount) == 1) {
			this.balance = balance - amount;
			return 1;
		} else {
			return 0;
		}
	}
	
	public int transfer (BankAccount bankActDest, double amount, Database data) {
		long actNum = bankActDest.accountNumber;
		if (data.getAccount(actNum) == null) {
			return 0;
		} else if (amount <= 0.00) {
			return -2;
		} else if (amount > this.balance) {
			return -3;
		} else {
			bankActDest.setBalance(bankActDest.getBalance() + amount);
			this.balance = balance - amount;
			return 1;
		}
	}
	
	
	
	public String toString() {
		String result = String.valueOf(accountNumber) + user.getPin() + String.format("%1$-" + 15 + "s", String.valueOf(balance)) + String.format("%1$-" + 20 + "s", user.getLastName()) + String.format("%1$-" + 15 + "s", user.getFirstName()) + user.getDob() + user.getPhone() + String.format("%1$-" + 30 + "s", user.getStreet()) + String.format("%1$-" + 30 + "s", user.getCity()) + user.getState() + user.getZipCode() + accountStatus;
		return result;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setAccountStatus(char status) {
		this.accountStatus = status;
	}
}