package application.datamodel;

public class Person {
	private int phoneNumber;
	private String name, email, address;
	
	public Person(int phoneNumber, String name, String email, String address) {
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
