
/**
 * 
 */
import java.util.*;
/**
 * @author prashant
 *
 */
public class AddressBookMain {

	private String bookName;
	// list to store contacts
	private LinkedList<Contact> bookList;

	// constructor of addressbook
	public AddressBookMain(String bookName) {
		this.bookList = new LinkedList<>();
		this.bookName = bookName;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of person in address Book");
		int n = sc.nextInt();
		sc.nextLine();
		while (n > 0) {
			n--;
			System.out.println("Enter First Name :");
			String firstName = sc.nextLine();
			System.out.println("Enter Last Name :");
			String lastName = sc.nextLine();
			System.out.println("Enter the City :");
			String city = sc.nextLine();
			System.out.println("Enter the State :");
			String state = sc.nextLine();
			System.out.println("Enter your Phone Number :");
			String phoneNumber = sc.nextLine();
			System.out.println("Enter your Email Address :");
			String emailId = sc.nextLine();
			this.addNewContact(firstName, lastName, city, state, phoneNumber, emailId);
		}
	}

	// function to add new contacts to the book
	public void addNewContact(String firstName, String lastName, String city, String state, String phoneNumber,
			String emailId) {
		Contact contactX = new Contact(firstName, lastName, city, state, phoneNumber, emailId);
		this.bookList.add(contactX);
		// bookMap.put(first, value)
	}

	public void changeDetails() {
		System.out.println("Enter the Full Name of person whose details are to be changed :");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		for (int i = 0; i < this.bookList.size(); i++) {
			if (this.bookList.get(i).fullName.equalsIgnoreCase(name)) {
				System.out.println("Enter new city :");
				this.bookList.get(i).city = sc.nextLine();
				System.out.println("Enter new State :");
				this.bookList.get(i).state = sc.nextLine();
				System.out.println("Enter new Phone Number :");
				this.bookList.get(i).phoneNumber = sc.nextLine();
				System.out.println("Enter new EmailId :");
				this.bookList.get(i).emailId = sc.nextLine();
				System.out.println("Changes made successfully");
				return;
			}
		}
		System.out.println("No record found for this person");
	}
	public void deleteContact(){
		System.out.println("Enter the First Name of person whose entry is to be deleted :");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		for (int i = 0; i < this.bookList.size(); i++) {
			if (this.bookList.get(i).fullName.equalsIgnoreCase(name)) {
				this.bookList.remove(this.bookList.get(i));
				System.out.println("Changes made successfully");
				return;
			}
		}
		System.out.println("No record found for this person");
		
	}
	/*
	public static void main(String args[]) {
		displayMessage();
		//AddressBookMain book = new AddressBookMain();
		//book.changeDetails();
		//book.deleteContact();
	}
	*/

	public void displayMessage() {
		System.out.println("Welcome to Address Book");
	}
}
