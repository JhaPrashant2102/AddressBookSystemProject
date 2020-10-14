package com.cg.addressbook;
/**
 * contact class for addressBook
 */

/**
 * @author prashant
 *
 */
public class Contact {

	// variables
	private String firstName;
	private String lastName;
	private String fullName;
	private String city;
	private String state;
	private String phoneNumber;
	private String emailId;

	// Constructor
	public Contact(String firstName, String lastName, String city, String state, String phoneNumber, String emailId) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.fullName = firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "firstName =" + this.getFirstName() + ",lastname =" + this.getLastName() + ",city =" + this.getCity()
				+ ",state =" + this.getState() + ",phonenumber =" + this.getPhoneNumber() + ",emailid ="
				+ this.getEmailId();
	}

}
