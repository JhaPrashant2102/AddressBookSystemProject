package com.cg.addressbook;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cg.addressbook.AddressBookMain.IOAddressBookService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
/**
 * @author Prashant
 *
 */
public class MainClass {

	private Map<String, AddressBookMain> addressBookMap;

	public MainClass() {
		addressBookMap = new HashMap<>();
		createAddressBookList();
	}
	
	public void createAddressBookList() {
		System.out.println("Enter number of Address Book");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter the name of your address book :");
			String addressBookName = sc.nextLine();
			addressBookMap.put(addressBookName, new AddressBookMain(addressBookName));
		}
	}
	
	public void search() {
		System.out.println("Enter the full name of the person to be searched");
		Scanner sc = new Scanner(System.in);
		String fName = sc.nextLine();
		for (Map.Entry m : addressBookMap.entrySet()) {
			System.out.println("Searching in " + m.getKey() + " address book :");
			boolean check = ((AddressBookMain) m.getValue()).search(fName);
		}
	}

	public static void main(String args[]) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

		MainClass addressDiary = new MainClass();
		//addressDiary.search();
		//addressDiary.viewPersonByCity();
		//addressDiary.viewPersonByState();
		//addressDiary.getNumberOfContactPerson();
		//addressDiary.writeAddressBookDataIntoFile();
		//addressDiary.readFileDataAndCreateNewAddressBook();
		//addressDiary.printFileData();
		addressDiary.writeAddressBookDataIntoCSVFile();
		addressDiary.readCSVFileDataAndCreateNewAddressBook();
	}

	private void readCSVFileDataAndCreateNewAddressBook() throws IOException {
		for(Map.Entry m : addressBookMap.entrySet()) {
			((AddressBookMain) m.getValue()).readAddressBookCSVFile(IOAddressBookService.FILE_IO);
		}
	}

	private void writeAddressBookDataIntoCSVFile() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		for(Map.Entry m : addressBookMap.entrySet()) {
			((AddressBookMain) m.getValue()).writeAddressBookIntoCSVFile(IOAddressBookService.FILE_IO);
		}
	}

	private void printFileData() {
		for(Map.Entry m : addressBookMap.entrySet()) {
			((AddressBookMain) m.getValue()).printData(IOAddressBookService.FILE_IO);
		}
	}

	private void readFileDataAndCreateNewAddressBook() {
		for(Map.Entry m : addressBookMap.entrySet()) {
			((AddressBookMain) m.getValue()).readAddressBookData(IOAddressBookService.FILE_IO);
		}
	}

	private void writeAddressBookDataIntoFile() {
		for(Map.Entry m : addressBookMap.entrySet()) {
			((AddressBookMain) m.getValue()).writeAddressBookData(IOAddressBookService.FILE_IO);
		}
	}

	private void getNumberOfContactPerson() {
		System.out.println("Enter the full name of person for whom help is required");
		Scanner sc = new Scanner(System.in);
		String personName = sc.nextLine();
		for (Map.Entry m : addressBookMap.entrySet()) {
			System.out.println("Searching in " + m.getKey() + " address book :");
			((AddressBookMain) m.getValue()).getNumberofContactPerson(personName);
		}
	}

	private void viewPersonByState() {
		System.out.println("Enter the state to be searched :");
		Scanner sc = new Scanner(System.in);
		String state = sc.nextLine();
		for (Map.Entry m : addressBookMap.entrySet()) {
			System.out.println("Searching in " + m.getKey() + " address book :");
			((AddressBookMain) m.getValue()).viewPersonByState(state);
		}
	}

	private void viewPersonByCity() {
		System.out.println("Enter the city to be searched :");
		Scanner sc = new Scanner(System.in);
		String city = sc.nextLine();
		for (Map.Entry m : addressBookMap.entrySet()) {
			System.out.println("Searching in " + m.getKey() + " address book :");
			((AddressBookMain) m.getValue()).viewPersonByCity(city);
		}
	}


}
