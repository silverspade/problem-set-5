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
	private String pin;
	private String dob;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
	public User(String firstName, String lastName, String pin, String dob, String phone, String street, String city, String state, String zipCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.dob = dob;
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
	
	public String getPin() {
		return pin;
	}
	
	public String getDob() {
		return dob;
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
	public int setPIN(String newPin) {
		if (newPin.matches("\\d\\d\\d\\d")) {
			this.pin = newPin;
			return 1;
		} else {
			return 0;
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