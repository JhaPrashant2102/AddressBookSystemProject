
/**
 * 
 */
import java.util.*;

/**
 * @author prashant
 *
 */
public class AddressBookMain {

	// list to store contacts
	private LinkedList<Contact> bookList;
	//private Map<String, Contact> bookMap;

	// constructor of addressbook
	public AddressBookMain() {
		bookList = new LinkedList<>();
		// bookMap = new HashMap<>();
	}

	// function to add new contacts to the book
	public void addNewContact(String firstName, String lastName, String city, String state, String phoneNumber,
			String emailId) {
		Contact contactX = new Contact(firstName, lastName, city, state, phoneNumber, emailId);
		bookList.add(contactX);
		// bookMap.put(first, value)
	}

	public void changeDetails() {
		System.out.println("Enter the First Name of person whose details are to be changed :");
		Scanner nsc = new Scanner(System.in);
		String personName = nsc.nextLine();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).firstName == personName) {
				System.out.println("Enter new city :");
				bookList.get(i).city = nsc.nextLine();
				return;
				// Similarly other changes can be made
			}
		}
		System.out.println("No record found for this person");
	}
	public void deleteContact(){
		System.out.println("Enter the First Name of person whose entry is to be deleted :");
		Scanner nsc = new Scanner(System.in);
		String personName = nsc.nextLine();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).firstName == personName) {
				bookList.remove(bookList.get(i));
				return;
				// Similarly other changes can be made
			}
		}
		System.out.println("No record found for this person");
		
	}

	public static void main(String args[]) {
		System.out.println("Welcome to Address Book");
		System.out.println("Enter number of person in address Book");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		AddressBookMain book = new AddressBookMain();
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
			book.addNewContact(firstName, lastName, city, state, phoneNumber, emailId);
		}
		book.changeDetails();
		book.deleteContact();
	}
}
