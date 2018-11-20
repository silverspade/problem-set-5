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
	private static long generatedAccountNumber = 100000001L;
	private long accountNumber;
	private double balance;
	private User user;
	
	public BankAccount(User user, double balance) {
		this.balance = balance;
		this.accountNumber = BankAccount.generatedAccountNumber++;
		this.user = user;
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
			return -1;
		} else if (amount <= 0) {
			return 1;
		} else if (amount > this.balance) {
			return 0;
		}  else {
			return 2;
		}
	}
	
	public void withdraw(double amount) {
		if(withdrawAble(amount) == 2) {
			this.balance = balance - amount;
		}
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
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}