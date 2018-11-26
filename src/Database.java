import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
	
	private String temp;
	
	public Database() {
		//Put something in here
	}
	
	public void reader() throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("accounts-db.txt"))) { 
			String line;
				while ((line = br.readLine()) != null) {
					temp = line.substring(0, 9);
					this.accountNumber = Long.valueOf(temp);
					this.PIN = line.substring(9, 13);
					temp = line.substring(13, 28);
					this.balance = Double.valueOf(temp);
					this.firstName = line.substring(28, 48);
					this.lastName = line.substring(48, 63);
					this.DOB = line.substring(63, 71);
					this.phone = line.substring(71, 81);
					this.street = line.substring(81, 111);
					this.city = line.substring(111, 141);
					this.state = line.substring(141, 143);
					this.zipCode = line.substring(143, 148);
					this.accountStatus = line.charAt(148);
				}
		}
	}
	
	public Database create() {
		//Put something in here
	}
}