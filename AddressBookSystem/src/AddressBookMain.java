
/**
 * 
 */
import java.util.*;

/**
 * @author prashant
 *
 */
public class AddressBookMain {

	public String firstName;
	public String lastName;
	public String city;
	public String state;
	public String phoneNumber;
	public String emailId;

	public AddressBookMain(String firstName, String lastName, String city, String state, String phoneNumber,
			String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	public static void main(String args[]) {
		System.out.println("Welcome to Address Book");
		System.out.println("Enter First Name :");
		Scanner sc = new Scanner(System.in);
		String fName = sc.nextLine();
		System.out.println("Enter Last Name :");
		String lName = sc.nextLine();
		System.out.println("Enter the City :");
		String cit = sc.nextLine();
		System.out.println("Enter the State :");
		String sta = sc.nextLine();
		System.out.println("Enter your Phone Number :");
		String phoneNo = sc.nextLine();
		System.out.println("Enter your Email Address :");
		String email = sc.nextLine();
		AddressBookMain newBoook = new AddressBookMain(fName,lName,cit,sta,phoneNo,email);
	}
}
