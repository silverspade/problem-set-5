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
		System.out.println(accountNumber);
		temp = line.substring(13, 28);
		balance = Double.valueOf(temp);
		System.out.println(balance);
		System.out.println("First name: " + line.substring(48, 63));
		System.out.println("Last name: " + line.substring(28, 48));
		System.out.println("PIN: " + line.substring(9,13));
		System.out.println("DOB: " + line.substring(63,71));
		System.out.println("Phone: " + line.substring(71,81));
	}
}