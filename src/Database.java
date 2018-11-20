/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */

public class Database {
	public Database(String name) {
		int length = name.length();
		int index = name.indexOf(',');
		String firstName = name.substring(0, index);
		String lastName = name.substring(index + 2, length);
	}
}