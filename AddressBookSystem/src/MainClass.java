import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author prashant
 *
 */
public class MainClass {
	
	
	
	private Map<String,AddressBookMain> addressBookMap;
	public MainClass() {
		addressBookMap = new HashMap<>();
		createAddressBookList();
	}
	public static void main(String args[]) {
		
		MainClass object = new MainClass();
	}

	public void createAddressBookList() {
		System.out.println("Enter number of Address Book");
		Scanner sc  = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		for(int i = 0 ; i<n ;i++) {
			System.out.println("Enter the name of your address book :");
			String addressBookName = sc.nextLine();
			addressBookMap.put(addressBookName,new AddressBookMain(addressBookName));
		}
		
	}
}
