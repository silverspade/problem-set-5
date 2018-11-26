/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class User {
	private String firstName;
	private String lastName;
	private String PIN;
	private String DOB;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
	public User(String firstName, String lastName, String PIN, String DOB, String phone, String street, String city, String state, String zipCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.PIN = PIN;
		this.DOB = DOB;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
	//getters All 9 have one
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPIN() {
		return PIN;
	}
	
	public String getDOB() {
		return DOB;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	//setters 6 have them; DOB, first, and last don't
	public void setPIN(String oldPIN, String newPIN) {
		if (oldPIN == getPIN()) {
			if (newPIN.matches("\\d\\d\\d\\d")) {
				this.PIN = newPIN;
			} else {
				System.out.println("PIN change unsuccessful. Enter a valid newPIN.");
			}
		} else {
			System.out.println("PIN change unsuccessful. Please enter the old PIN first.");
		}
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}