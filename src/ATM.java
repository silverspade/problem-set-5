import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class ATM {
	private static long generatedAccountNumber = 100000009L;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Database data = new Database("accounts-db.txt");
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to your ATM. Pick 1 to open an account, Pick 2 to login, and Pick 3 to quit: ");
		int response = in.nextInt();
		while (response != 3) {
			switch (response) {
			case 1: //Open Account
				System.out.println("Press 1 to open a new account, or 2 to reopen an existing one:");
				int openAcct = in.nextInt();
				if (openAcct == 1) {
					String line = createAccount();
					BankAccount result = new BankAccount(line);
					System.out.println("Creation successful.");
					data.updateAccount(result, null);
					System.out.println("Updating successful.");
					System.out.println("Your account number is : " + result.getAccountNumber() + "\nYour PIN is: " + result.getUser().getPin());
					break;
				} else {
					System.out.println("Please enter your account number: ");
					long numb = in.nextLong();
					if (data.accountExists(numb) == null) {
						System.out.println("Invalid account number. Please try again.");
						break;
					}
					BankAccount reopen = data.accountExists(numb);
					reopen.setAccountStatus('Y');
					System.out.println("Reopening successful.");
					data.updateAccount(reopen, null);
					System.out.println("Update successful.");
					break;
				}
				
			case 2: //Login
				System.out.println("Please enter your account number: ");
				long num = in.nextLong();
				if (data.getAccount(num) == null) {
					System.out.println("Invalid account number. Please try again.");
					break;
				}
				BankAccount main = data.getAccount(num);
				System.out.println("Enter your PIN");
				String input = String.valueOf(in.nextInt());
				if (input != main.getUser().getPin()) {
					System.out.println("Incorrect PIN. Please try again.");
					break;
				}
				System.out.println("What would you like to do? Press 1 for deposit, 2 for withdraw, 3 for transfer, 4 for view balance, 5 for view personal information, 6 for update personal information, 7 for close account, and 8 for logout.");
				int choice = in.nextInt();
				while (choice != 8) {
					switch (choice) {
						case 1: //Deposit
							System.out.println("How much would you like to deposit?");
							double amount = in.nextDouble();
							if (main.deposit(amount) == 1) {
								System.out.println("Deposit successful. Your balance is now " + main.getBalance());
								data.updateAccount(main, null);
								System.out.println("Update successful.");
							} else {
								System.out.println("Amount invalid. Please try again.");
							}
							break;
						case 2: //Withdraw
							System.out.println("How much would you like to withdraw?");
							amount = in.nextDouble();
							if (main.withdraw(amount) == 1) {
								System.out.println("Withdrawal successful. Your balance is now " + main.getBalance());
								data.updateAccount(main, null);
								System.out.println("Update successful.");
							} else {
								System.out.println("Error. Make sure the amount is valid, and that there is money in the account. Please try again.");
							}
							break;
						case 3: //Transfer
							System.out.println("What account would you like to send it to? Enter the account number");
							num = in.nextLong();
							System.out.println("How much would you like to transfer?");
							amount = in.nextDouble();
							if (main.transfer(data.getAccount(num), amount, data) == 1) {
								System.out.println("Transfer successful. Your balance is now " + main.getBalance() + " and the destination account balance is now " + data.getAccount(num).getBalance());
								data.updateAccount(main, data.getAccount(num));
								System.out.println("Update successful.");
							} else {
								System.out.println("Error. Make sure the account number is valid and the amount is valid. Please try again.");
							}
							break;
						case 4: //View balance
							System.out.println("Your balance is " + main.getBalance());
							break;
						case 5: //View personal information
							System.out.println("First Name: " + main.getUser().getFirstName());
							System.out.println("Last Name: " + main.getUser().getLastName());
							System.out.println("PIN: " + main.getUser().getPin());
							System.out.println("Date of Birth (YYYMMDD): " + main.getUser().getDob());
							System.out.println("Phone: " + main.getUser().getPhone());
							System.out.println("Street Address: " + main.getUser().getStreet());
							System.out.println("City: " + main.getUser().getCity());
							System.out.println("State: " + main.getUser().getState());
							System.out.println("Zip Code: " + main.getUser().getZipCode());
							break;
						case 6: //Update personal information
							System.out.println("What would you like to update? Press 1 for PIN, 2 for phone, 3 for street address, 4 for city, 5 for state, 6 for zip code, or 7 for quit.");
							int person = in.nextInt();
							while (person != 7) {
								switch (person) {
									case 1: //PIN
										System.out.println("Enter your current PIN: ");
										String oldPin = in.nextLine();
										if (oldPin != main.getUser().getPin()) {
											System.out.println("Incorrect current PIN. Please try again.");
											break;
										} else { 
											System.out.println("What would you like your new PIN to be?");
											String newPin = in.nextLine();
											if (main.getUser().setPIN(newPin) == 1) {
												System.out.println("Updating PIN successful. Your new PIN is " + main.getUser().getPin());
												data.updateAccount(main, null);
												System.out.println("Update successful.");
											}
										}
										break;
									case 2: //phone
										System.out.println("What would you like your new phone to be?");
										String phoneNew = in.nextLine();
										main.getUser().setPhone(phoneNew);
										System.out.println("Updating phone successful. Your new phone is " + main.getUser().getPhone());
										data.updateAccount(main, null);
										System.out.println("Update successful.");
										break;
									case 3: //street
										System.out.println("What would you like your new street address to be?");
										String streetNew = in.nextLine();
										main.getUser().setPhone(streetNew);
										System.out.println("Updating street address successful. Your new phone is " + main.getUser().getStreet());
										data.updateAccount(main, null);
										System.out.println("Update successful.");
										break;
									case 4: //city
										System.out.println("What would you like your new city to be?");
										String cityNew = in.nextLine();
										main.getUser().setPhone(cityNew);
										System.out.println("Updating city successful. Your new phone is " + main.getUser().getCity());
										data.updateAccount(main, null);
										System.out.println("Update successful.");
										break;
									case 5: //state
										System.out.println("What would you like your new state to be?");
										String stateNew = in.nextLine();
										main.getUser().setPhone(stateNew);
										System.out.println("Updating state successful. Your new phone is " + main.getUser().getState());
										data.updateAccount(main, null);
										System.out.println("Update successful.");
										break;
									case 6: //zip code
										System.out.println("What would you like your new zip code to be?");
										String zipCodeNew = in.nextLine();
										main.getUser().setPhone(zipCodeNew);
										System.out.println("Updating zip code successful. Your new phone is " + main.getUser().getZipCode());
										data.updateAccount(main, null);
										System.out.println("Update successful.");
										break;
									default:
										System.out.println("Invalid number. Please try again.");
										break;
								}
								System.out.println("Would you like to update anyting else? Press 1 for PIN, 2 for phone, 3 for street, 4 for city, 5 for state, 6 for zip code, or 7 for quit.");
								person = in.nextInt();
							}
							break;
						case 7: //Close account
							main.setAccountStatus('N');
							System.out.println("Closing account successful.");
							data.updateAccount(main, null);
							System.out.println("Update successful.");
							break;
						default:
							System.out.println("Invalid number. Please try again.");
							break;
					}
					System.out.println("Would you like to do anything else? Press 1 for deposit, 2 for withdraw, 3 for transfer, 4 for view balance, 5 for view personal information, 6 for update personal information, 7 for close account, and 8 for logout.");
					choice = in.nextInt();
				}
				break;
			default:
				System.out.println("Invalid number. Please try again.");
				break;
			}
			System.out.println("Would you like to do anything else? Pick 1 to open an account, Pick 2 to login, and Pick 3 to quit: ");
			response = in.nextInt();
		}
		System.out.println("Thank you, come again!");
		in.close();
	}
	
	public static String createAccount() {
		Scanner in2 = new Scanner(System.in);
		long accountNumber = generatedAccountNumber++;
		double balance = 0.00;
		System.out.println("Enter your first name:");
		String firstName = in2.nextLine();
		System.out.println("Enter your last name: ");
		String lastName = in2.nextLine();
		System.out.println("Enter your PIN: ");
		String pin = in2.nextLine();
		while (pin.length() > 4) {
			System.out.println("Invalid. Enter a valid PIN: ");
			pin = in2.nextLine();
		}
		System.out.println("Enter your date of birth as YYYYMMDD");
		String dob = in2.nextLine();
		while (Long.valueOf(dob.substring(4, 6)) > 12 || dob.length() != 8 || Long.valueOf(dob.substring(6, 8)) > 31) {
			System.out.println("Invalid. Enter a valid date of birth as YYYYMMDD");
			dob = in2.nextLine();
		}
		System.out.println("Enter your phone number: ");
		String phone = in2.nextLine();
		while (phone.length() > 10) {
			System.out.println("Invalid. Enter a valid phone number: ");
			phone = in2.nextLine();
		}
		System.out.println("Enter your street address: ");
		String street = in2.nextLine();
		System.out.println("Enter your city: ");
		String city = in2.nextLine();
		System.out.println("Enter your state as two letter abbreviation: ");
		String state = in2.nextLine();
		while (state.length() != 2) {
			System.out.println("Invalid. Enter a state as a two letter abbreviation: ");
			state = in2.nextLine();
		}
		System.out.println("Enter your zip code: ");
		String zipCode = in2.nextLine();
		while (zipCode.length() != 5) {
			System.out.println("Invalid. Make sure the zip code is 5 digits: ");
			zipCode = in2.nextLine();
		}
		char accountStatus = 'Y';
		in2.close();
		String line = String.valueOf(accountNumber) + pin + String.format("%15s", balance) + String.format("%-20s", firstName) + String.format("%-15s", lastName) + String.format("%-8s", dob) + phone + String.format("%30s", street) + String.format("%30s", city) + state + zipCode + accountStatus;
		return line;
	}
}