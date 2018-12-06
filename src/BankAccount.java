import java.util.Scanner;

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
	private static long generatedAccountNumber = 100000009L;
	
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
			return 0;
		} else if (amount > this.balance) {
			return 0;
		}  else {
			return 1;
		}
	}
	
	public void withdraw(double amount) {
		if(withdrawAble(amount) == 1) {
			this.balance = balance - amount;
		}
	}
	
	public void transfer(BankAccount bankActDest, double amount) {
		if ((Long) bankActDest.accountNumber == null) {
			
		} else (bankActDest.accountNumber < Database.getMaxAccountNumber()) {
			
		}
				 
		bankActDest.setBalance(bankActDest.balance + amount);
		this.balance = balance - amount;
		//Add in updateAccount and bankActOri and bankActDest so that both of them can updated.
		//Can use updateAccount with only one account 
	}
	
	public BankAccount createAccount() {
		Scanner in = new Scanner(System.in);
		accountNumber = generatedAccountNumber++;
		balance = 0.00;
		System.out.println("Enter your first name:");
		String firstName = in.nextLine();
		System.out.println("Enter your last name: ");
		String lastName = in.nextLine();
		System.out.println("Enter your PIN: ");
		String pin = in.nextLine();
		while (pin.length() > 4) {
			System.out.println("Invalid. Enter a valid PIN: ");
			pin = in.nextLine();
		}
		System.out.println("Enter your date of birth as YYYYMMDD");
		String dob = in.nextLine();
		while (Long.valueOf(dob.substring(4, 5)) > 12 || dob.length() != 8 || Long.valueOf(dob.substring(6, 7)) > 31) {
			System.out.println("Invalid. Enter a valid date of birth as YYYYMMDD");
			dob = in.nextLine();
		}
		System.out.println("Enter your phone number: ");
		String phone = in.nextLine();
		while (pin.length() > 10) {
			System.out.println("Invalid. Enter a valid phone number: ");
			phone = in.nextLine();
		}
		System.out.println("Enter your street address: ");
		String street = in.nextLine();
		System.out.println("Enter your city: ");
		String city = in.nextLine();
		System.out.println("Enter your state: ");
		String state = in.nextLine();
		System.out.println("Enter your zip code: ");
		String zipCode = in.nextLine();
		//Get this into a BankAccount
		//String line = toString()
		//BankAccount result = new BankAccount()
	}
	
	public String toString() {
		String result = String.valueOf(accountNumber) + user.getPin() + String.format("%1$-" + 15 + "s", String.valueOf(balance)) + String.format("%1$-" + 20 + "s", user.getLastName()) + String.format("%1$-" + 15 + "s", user.getFirstName()) + user.getDOB() + user.getPhone() + String.format("%1$-" + 30 + "s", user.getStreet()) + String.format("%1$-" + 30 + "s", user.getCity()) + user.getState() + user.getZipCode() + accountStatus;
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
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}