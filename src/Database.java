import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {
	private long accountNumber;
	private String PIN;
	private double balance;
	private String lastName;
	private String firstName;
	private String DOB;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private char accountStatus;
	
	private Database result;
	private String temp;
	
	public Database(BankAccount bankAccount, char accountStatus) {
		this.accountNumber = bankAccount.getAccountNumber();
		this.PIN = bankAccount.getUser().getPIN();
		this.balance = bankAccount.getBalance();
		this.lastName = bankAccount.getUser().getLastName();
		this.firstName = bankAccount.getUser().getFirstName();
		this.DOB = bankAccount.getUser().getDOB();
		this.phone = bankAccount.getUser().getPhone();
		this.street = bankAccount.getUser().getStreet();
		this.city = bankAccount.getUser().getCity();
		this.state = bankAccount.getUser().getState();
		this.zipCode = bankAccount.getUser().getZipCode();
		this.accountStatus = accountStatus;
	}
	
	public Database autoCreate() throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("accounts-db.txt"))) { 
			String line;
				while ((line = br.readLine()) != null) {
					temp = line.substring(0, 9);
					accountNumber = Long.valueOf(temp);
					PIN = line.substring(9, 13);
					temp = line.substring(13, 28);
					balance = Double.valueOf(temp);
					firstName = line.substring(28, 48);
					lastName = line.substring(48, 63);
					DOB = line.substring(63, 71);
					phone = line.substring(71, 81);
					street = line.substring(81, 111);
					city = line.substring(111, 141);
					state = line.substring(141, 143);
					zipCode = line.substring(143, 148);
					accountStatus = line.charAt(148);
					result = new Database(new BankAccount(new User(firstName, lastName, PIN, DOB, phone, street, city, state, zipCode), balance), accountStatus);
				}
				return result;
		}
	}
	
	public void retrieve(Database data) {
		if(data.accountStatus == 'Y') {
			System.out.println(data.accountNumber);
			System.out.println(data.PIN);
			System.out.println(data.balance);
			System.out.println(data.lastName);
			System.out.println(data.firstName);
			System.out.println(data.DOB);
			System.out.println(data.phone);
			System.out.println(data.street);
			System.out.println(data.city);
			System.out.println(data.state);
			System.out.println(data.zipCode);
		} else {
			System.out.println("Account is closed. Cannot retrieve information.");
		}
	}
	
	public void update(Database data) {
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Want to update PIN? Press 1 for yes, 2 for no");
		input = in.nextInt();
		if (input == 1) {
			//setPIN from user
		}
		System.out.println("Want to update date of birth? Press 1 for yes, 2 for no");
		input = in.nextInt();
		if (input == 1) {
			System.out.println("Enter the new value: ");
			//setDOB from user
		}
		in.close();
	}
}