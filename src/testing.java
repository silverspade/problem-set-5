public class testing {
	public static long accountNumber;
	public static double balance;
	public static User user;
	public static char accountStatus;
	
	public static String temp;
	
	public String firstName;
	public String lastName;
	public String pin;
	public String DOB;
	public String phone;
	public String street;
	public String city;
	public String state;
	public String zipCode;
	
	public static void main(String[] args) {
		String line = "1000000011234123.45         Wilson              Ryan           197101105551234567123 Main Street               Scotch Plains                 NJ07076Y";
		temp = line.substring(0, 9);
		accountNumber = Long.valueOf(temp);
		temp = line.substring(13, 28);
		balance = Double.valueOf(temp);
		user = new User(line.substring(28, 48), line.substring(48, 63), line.substring(9, 13), line.substring(63, 71), line.substring(71, 81), line.substring(81, 111), line.substring(111, 141), line.substring(141, 143), line.substring(143, 148));
		accountStatus = line.charAt(148);
		String result = String.valueOf(accountNumber) + user.getPin() + String.format("%1$-" + 15 + "s", String.valueOf(balance)) + String.format("%1$-" + 20 + "s", user.getLastName()) + String.format("%1$-" + 10 + "s", user.getFirstName()) + user.getDob() + user.getPhone() + String.format("%1$-" + 30 + "s", user.getStreet()) + String.format("%1$-" + 30 + "s", user.getCity()) + user.getState() + user.getZipCode() + accountStatus;
		System.out.println(result);
	}
}