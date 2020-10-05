
/**
 * 
 */
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author prashant
 *
 */
public class AddressBookMain {

	private String bookName;
	// list to store contacts
	private List<Contact> bookList;
	private Map<String, String> mapOfCityAndPerson;
	private Map<String, String> mapOfStateAndPerson;

	// constructor of addressbook
	public AddressBookMain(String bookName) {
		this.mapOfCityAndPerson = new HashMap<>();
		this.mapOfStateAndPerson = new HashMap<>();
		this.bookList = new LinkedList<>();
		this.bookName = bookName;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of person in address Book");
		int n = sc.nextInt();
		sc.nextLine();
		while (n > 0) {
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
			boolean check = this.addNewContact(firstName, lastName, city, state, phoneNumber, emailId);
			if (check)
				n--;
			else
				;
		}
		this.sortContactNames();
		this.sortByCity();
		this.sortByState();
	}

	private void sortContactNames() {
		List<Contact> sortedList = this.bookList.stream().sorted((n1,n2)->n1.getFullName().compareTo(n2.getFullName())).collect(Collectors.toList());
		//System.out.println("Sorted contact list is : "+sortedList);
		sortedList.stream().forEach(n->{
			System.out.println("Contact name is : "+n.getFullName());
		});
	}
	
	private void sortByCity() {
		List<Contact> sortedList = this.bookList.stream().sorted((n1,n2)->n1.getCity().compareTo(n2.getCity())).collect(Collectors.toList());
		//System.out.println("Sorted contact list is : "+sortedList);
	}
	
	private void sortByState() {
		List<Contact> sortedList = this.bookList.stream().sorted((n1,n2)->n1.getState().compareTo(n2.getState())).collect(Collectors.toList());
		//System.out.println("Sorted contact list is : "+sortedList);
	}

	/**
	 * function to add new contact
	 * 
	 * @param firstName   conat
	 * @param lastName
	 * @param city
	 * @param state
	 * @param phoneNumber
	 * @param emailId
	 * @return -- true if contact added successfully else false
	 */
	public boolean addNewContact(String firstName, String lastName, String city, String state, String phoneNumber,
			String emailId) {
		Contact contactX = new Contact(firstName, lastName, city, state, phoneNumber, emailId);
		for (int i = 0; i < bookList.size(); i++) {
			if (this.bookList.get(i).getFullName().equalsIgnoreCase(firstName + " " + lastName)) {
				System.out.println("Contact with this name already exists! \nPlease try some other name");
				return false;
			}
		}
		this.bookList.add(contactX);
		System.out.println("Contact added successfully:");
		this.mapOfCityAndPerson.put(contactX.getCity(), contactX.getFullName());
		this.mapOfStateAndPerson.put(contactX.getState(), contactX.getFullName());
		return true;
		// bookMap.put(first, value)
	}

	public void changeDetails() {
		System.out.println("Enter the Full Name of person whose details are to be changed :");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		for (int i = 0; i < this.bookList.size(); i++) {
			if (this.bookList.get(i).getFullName().equalsIgnoreCase(name)) {
				System.out.println("Enter new city :");
				this.bookList.get(i).setCity(sc.nextLine());
				System.out.println("Enter new State :");
				this.bookList.get(i).setState(sc.nextLine());
				System.out.println("Enter new Phone Number :");
				this.bookList.get(i).setPhoneNumber(sc.nextLine());
				System.out.println("Enter new EmailId :");
				this.bookList.get(i).setEmailId(sc.nextLine());
				System.out.println("Changes made successfully");
				return;
			}
		}
		System.out.println("No record found for this person");
	}

	public void deleteContact() {
		System.out.println("Enter the First Name of person whose entry is to be deleted :");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		for (int i = 0; i < this.bookList.size(); i++) {
			if (this.bookList.get(i).getFullName().equalsIgnoreCase(name)) {
				this.bookList.remove(this.bookList.get(i));
				System.out.println("Changes made successfully");
				return;
			}
		}
		System.out.println("No record found for this person");

	}
	/*
	 * public static void main(String args[]) { displayMessage(); AddressBookMain
	 * book = new AddressBookMain(); book.changeDetails(); book.deleteContact(); }
	 */

	public void displayMessage() {
		System.out.println("Welcome to Address Book");
	}

	public boolean search(String fName) {
		boolean flag = false;
		for (int i = 0; i < this.bookList.size(); i++) {
			if (this.bookList.get(i).getFullName().equalsIgnoreCase(fName)) {
				System.out.println(fName + " lives in city :" + this.bookList.get(i).getCity() + " and state :"
						+ this.bookList.get(i).getState());
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("No matches found for " + fName + " in " + this.bookName + " address book");
		}
		return flag;
	}

	public void viewPersonByCity(String city) {
		boolean flag = false;
		for (Map.Entry m : mapOfCityAndPerson.entrySet()) {
			if (((String) m.getKey()).equalsIgnoreCase(city)) {
				System.out.println(m.getValue() + " also lives in city :" + city);
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("No person lives in city : " + city + " in " + this.bookName + " address book");
		}
	}

	public void viewPersonByState(String state) {
		boolean flag = false;
		for (Map.Entry m : mapOfStateAndPerson.entrySet()) {
			if (((String) m.getKey()).equalsIgnoreCase(state)) {
				System.out.println(m.getValue() + " also lives in state :" + state);
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("No person lives in state : " + state + " in " + this.bookName + " address book");
		}
	}

	public void getNumberofContactPerson(String personName) {
		// first checking whether a person lives exist in the address book or not
		boolean check = this.search(personName);
		int c1 = 0, c2 = 0;
		if (check) {
			String personCity = null;
			String personState = null;
			for (Map.Entry m : mapOfStateAndPerson.entrySet()) {
				if (((String) m.getValue()).equalsIgnoreCase(personName)) {
					personState = (String) m.getKey();
				}
			}
			for (Map.Entry m : mapOfCityAndPerson.entrySet()) {
				if (((String) m.getValue()).equalsIgnoreCase(personName)) {
					personCity = (String) m.getKey();
				}
			}
			for (Map.Entry m : mapOfStateAndPerson.entrySet()) {
				if (((String) m.getKey()).equalsIgnoreCase(personCity)) {
					c1++;
				}
				if (((String) m.getKey()).equalsIgnoreCase(personState)) {
					c2++;
				}
			}
			System.out.println("number of people in same state as " + personName + " is/are " + c2);
			System.out.println("number of people in same city as " + personName + " is/are " + c1);
		}
		// else case not required
	}
}
