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
	
	public void search() {
		System.out.println("Enter the full name of the person to be searched");
		Scanner sc = new Scanner(System.in);
		String fName = sc.nextLine();
		for(Map.Entry m:addressBookMap.entrySet()) {
			System.out.println("Searching in "+m.getKey()+" address book :");
			((AddressBookMain) m.getValue()).search(fName);
		}
	}
	public static void main(String args[]) {
		
		MainClass object = new MainClass();
		object.search();
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
