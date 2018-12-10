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
	
	Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			Database data = new Database("accounts-db.txt");
			ATM atm = new ATM();
			atm.mainMenu(data, atm);
			System.out.println("Thank you, come again!");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void mainMenu(Database data, ATM atm) {
			System.out.println("Welcome to your ATM. Pick 1 to open an account, Pick 2 to login, and Pick 3 to quit: ");
			int response = in.nextInt();
				switch (response) {
				case 1: //Open Account
					atm.openAct(data, atm);
					break;
				case 2: //Login
					BankAccount result = atm.verification(data, atm);
					if (result == null) {
							break;
					} else {
						BankAccount mainAct = result;
						atm.submenu(mainAct, data, atm);
					}
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid number. Please try again.");
					break;
				}
					System.out.println("Continue to main menu? Press 1 for yes, 2 for no:");
					int contin = in.nextInt();
					if (contin == 1) {
						atm.mainMenu(data, atm);
				}
				
	}
	
	public void submenu(BankAccount mainAct, Database data, ATM atm) {
		try {
			System.out.println("What would you like to do? Press 1 for deposit, 2 for withdraw, 3 for transfer, 4 for view balance, 5 for view personal information, 6 for update personal information, 7 for close account, and 8 for logout.");
			int choice = in.nextInt();
				switch (choice) {
					case 1: //Deposit
						System.out.println("How much would you like to deposit?");
						double amount = in.nextDouble();
						if (mainAct.deposit(amount) == 1) {
							System.out.println("Deposit successful. Your balance is now " + String.format("%.2f", mainAct.getBalance()));
							data.updateAccount(mainAct, null);
							System.out.println("Update successful.");
						} else {
							System.out.println("Amount invalid. Please try again.");
						}
						break;
					case 2: //Withdraw
						System.out.println("How much would you like to withdraw?");
						amount = in.nextDouble();
						if (mainAct.withdraw(amount) == 1) {
							System.out.println("Withdrawal successful. Your balance is now " + String.format("%.2f", mainAct.getBalance()));
							data.updateAccount(mainAct, null);
							System.out.println("Update successful.");
						} else {
							System.out.println("Error. Make sure the amount is valid, and that there is money in the account. Please try again.");
						}
						break;
					case 3: //Transfer
						System.out.println("What account would you like to send it to? Enter the account number");
						long num = in.nextLong();
						BankAccount dest = data.getAccount(num);
						if (dest == null) {
							System.out.println("Invalid account. Please try again.");
							break;
						} else { 
							System.out.println("How much would you like to transfer?");
							amount = in.nextDouble();
							int result = mainAct.transfer(dest, amount, data);
							if (result == 1) {
								data.updateAccount(mainAct, data.getAccount(num));
								System.out.println("Update successful.");
								System.out.println("Transfer successful. Your balance is now " + String.format("%.2f", mainAct.getBalance()) + " and the destination account balance is now " + String.format("%.2f", data.getAccount(num).getBalance()));
							} else {
								System.out.println("Error. Make sure the account number is valid and the amount is valid. Please try again.");
							}
						}
						break;
					case 4: //View balance
						System.out.println("Your balance is " + mainAct.getBalance());
						break;
					case 5: //View personal information
						System.out.println("First Name: " + mainAct.getUser().getFirstName());
						System.out.println("Last Name: " + mainAct.getUser().getLastName());
						System.out.println("PIN: " + mainAct.getUser().getPin());
						System.out.println("Date of Birth (YYYMMDD): " + mainAct.getUser().getDob());
						System.out.println("Phone: " + mainAct.getUser().getPhone());
						System.out.println("Street Address: " + mainAct.getUser().getStreet());
						System.out.println("City: " + mainAct.getUser().getCity());
						System.out.println("State: " + mainAct.getUser().getState());
						System.out.println("Zip Code: " + mainAct.getUser().getZipCode());
						break;
					case 6: //Update personal information
						atm.updatePersonal(mainAct, data, atm);
						//in.nextLine();
						break;
					case 7: //Close account
						System.out.println("Are you sure you would like to close this account? Press 1 for yes, 2 for no:");
						int respo = in.nextInt();
						if(respo == 1) {
							mainAct.setAccountStatus('N');
							System.out.println("Closing account successful.");
							data.updateAccount(mainAct, null);
							System.out.println("Update successful.");
						} else {
							break;
						}
						break;
					case 8:
						break;
					default:
						System.out.println("Invalid number. Please try again.");
						break;
				}
				System.out.println("Continue to submenu? Press 1 for yes, 2 for no.");
				int contin = in.nextInt();
					if (contin == 1) {
						atm.submenu(mainAct, data, atm);
					} 
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public String createAccount() {
		in.nextLine();
		long accountNumber = generatedAccountNumber++;
		double balance = 0.00;
		System.out.println("Enter your first name:");
		String firstName = in.nextLine();
		System.out.println("Enter your last name: ");
		String lastName = in.nextLine();
		System.out.println("Enter your PIN: ");
		String pin = in.nextLine();
		while (pin.length() != 4) {
			System.out.println("Invalid. Enter a valid PIN: ");
			pin = in.nextLine();
		}
		System.out.println("Enter your date of birth as YYYYMMDD");
		String dob = in.nextLine();
		while (Long.valueOf(dob.substring(4, 6)) > 12 || dob.length() != 8 || Long.valueOf(dob.substring(6, 8)) > 31) {
			System.out.println("Invalid. Enter a valid date of birth as YYYYMMDD");
			dob = in.nextLine();
		}
		System.out.println("Enter your phone number: ");
		String phone = in.nextLine();
		while (phone.length() != 10) {
			System.out.println("Invalid. Enter a valid phone number: ");
			phone = in.nextLine();
		}
		System.out.println("Enter your street address: ");
		String street = in.nextLine();
		System.out.println("Enter your city: ");
		String city = in.nextLine();
		System.out.println("Enter your state as two letter abbreviation: ");
		String state = in.nextLine();
		while (state.length() != 2) {
			System.out.println("Invalid. Enter a state as a two letter abbreviation: ");
			state = in.nextLine();
		}
		System.out.println("Enter your zip code: ");
		String zipCode = in.nextLine();
		while (zipCode.length() != 5) {
			System.out.println("Invalid. Make sure the zip code is 5 digits: ");
			zipCode = in.nextLine();
		}
		char accountStatus = 'Y';
		String line = String.format("%-9s", accountNumber) + String.format("%-4s", pin) + String.format("%-15.2f", balance) + String.format("%-20s", lastName) + String.format("%-15s", firstName) + String.format("%-8s", dob) + String.format("%-10s", phone) + String.format("%-30s", street) + String.format("%-30s", city) + String.format("%-2s", state) + String.format("%-5s", zipCode) + accountStatus;
		return line;
	}
	
	public void openAct(Database data, ATM atm) {
		try {
			System.out.println("Press 1 to open a new account, or 2 to reopen an existing one:");
			int openAcct = in.nextInt();
			if (openAcct == 1) {
				String line = atm.createAccount();
				BankAccount result = new BankAccount(line);
				System.out.println("Creation successful.");
				data.updateAccount(result, null);
				System.out.println("Updating successful.");
				System.out.println("Your account number is : " + result.getAccountNumber() + "\nYour PIN is: " + result.getUser().getPin());
			} else {
				System.out.println("Please enter your account number: ");
				long numb = in.nextLong();
				if (data.accountExists(numb) == null) {
					System.out.println("Invalid account number. Please try again.");
				}
				BankAccount reopen = data.accountExists(numb);
				reopen.setAccountStatus('Y');
				System.out.println("Reopening successful.");
				data.updateAccount(reopen, null);
				System.out.println("Update successful.");
			}
		}
		catch (IOException ex ) {
			ex.printStackTrace();
		}
	}
	
	public BankAccount verification(Database data, ATM atm) {
		System.out.println("Please enter your account number: ");
		long num = in.nextLong();
		if (data.getAccount(num) == null) {
			System.out.println("Invalid account number. Please try again.");
			return null;
		} else {
			BankAccount mainAct = data.getAccount(num);
			System.out.println("Enter your PIN:");
			String input = String.valueOf(in.nextInt());
			if (input.equals(mainAct.getUser().getPin()) == false ) {
				System.out.println("Incorrect PIN. Please try again.");
				return null;
			} else {
				return mainAct;
			}
		}
	}
	
	public void updatePersonal(BankAccount mainAct, Database data, ATM atm) {
		try {
			System.out.println("What would you like to update? Press 1 for PIN, 2 for phone, 3 for street address, 4 for city, 5 for state, 6 for zip code, or 7 for quit.");
			int person = in.nextInt();
				switch (person) {
					case 1: //PIN
						System.out.println("Enter your current PIN: ");
						String oldPin = String.valueOf(in.nextInt());
						if (oldPin.equals(mainAct.getUser().getPin()) == false) {
							System.out.println("Incorrect current PIN. Please try again.");
							break;
						} else { 
							System.out.println("What would you like your new PIN to be?");
							String newPin = String.valueOf(in.nextInt());
							if (mainAct.getUser().setPIN(newPin) == 1) {
								data.updateAccount(mainAct, null);
								System.out.println("Update successful.");
								System.out.println("Updating PIN successful. Your new PIN is " + mainAct.getUser().getPin());
							}
						}
						break;
					case 2: //phone
						System.out.println("What would you like your new phone to be?");
						String phoneNew = String.valueOf(in.nextLong());
						if (phoneNew.length() != 10 ) {
							System.out.println("Invalid phone number. Please try again.");
							break;
						}
						mainAct.getUser().setPhone(phoneNew);
						data.updateAccount(mainAct, null);
						System.out.println("Update successful.");
						System.out.println("Updating phone successful. Your new phone is " + mainAct.getUser().getPhone());
						break;
						
					case 3: //street
						System.out.println("What would you like your new street address to be?");
						in.nextLine();
						String streetNew = in.nextLine();
						mainAct.getUser().setStreet(streetNew);
						data.updateAccount(mainAct, null);
						System.out.println("Update successful.");
						System.out.println("Updating street address successful. Your new street address is " + mainAct.getUser().getStreet());
						break;
					case 4: //city
						System.out.println("What would you like your new city to be?");
						in.nextLine();
						String cityNew = in.nextLine();
						mainAct.getUser().setCity(cityNew);
						System.out.println("Updating city successful. Your new phone is " + mainAct.getUser().getCity());
						data.updateAccount(mainAct, null);
						System.out.println("Update successful.");
						break;
					case 5: //state
						System.out.println("What would you like your new state to be?");
						in.nextLine();
						String stateNew = in.nextLine();
						if (stateNew.length() != 2 ) {
							System.out.println("Invalid state abbreviation. Please try again.");
							break;
						}
						mainAct.getUser().setState(stateNew);
						System.out.println("Updating state successful. Your new phone is " + mainAct.getUser().getState());
						data.updateAccount(mainAct, null);
						System.out.println("Update successful.");
						break;
					case 6: //zip code
						System.out.println("What would you like your new zip code to be?");
						in.nextLine();
						String zipCodeNew = in.nextLine();
						if (zipCodeNew.length() != 5 ) {
							System.out.println("Invalid zip code. Please try again.");
							break;
						}
						mainAct.getUser().setZipCode(zipCodeNew);
						System.out.println("Updating zip code successful. Your new phone is " + mainAct.getUser().getZipCode());
						data.updateAccount(mainAct, null);
						System.out.println("Update successful.");
						break;
					case 7:
						break;
					default:
						System.out.println("Invalid number. Please try again.");
						break;
				}
				System.out.println("Continue to updating personal information? Press 1 for yes, 2 for no:");
				int contin = in.nextInt();
				if (contin == 1) {
					atm.updatePersonal(mainAct, data, atm);
				}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}