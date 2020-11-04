package com.cg.addressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressBookService {

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private AddressBookDB_IOService addressBookDB_IOService;
	private List<Contact> contactList;

	public AddressBookService() {
		addressBookDB_IOService = AddressBookDB_IOService.getInstance();
		this.contactList = new ArrayList<>();
	}

	public AddressBookService(List<Contact> contactList) {
		this();
		this.contactList = contactList;
	}

	public List<Contact> readData(IOService ioService) {
		if (ioService.equals(IOService.DB_IO)) {
			contactList = addressBookDB_IOService.readData();
			return contactList;
		}
		return null;
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.REST_IO)) {
			return this.contactList.size();
		}
		return this.contactList.size();
	}

	public void updateContactPhoneNumber(String name, String phoneNumber, IOService ioService) {
		if (ioService.equals(IOService.DB_IO)) {
			int result = addressBookDB_IOService.updateContactDetails(name,phoneNumber);
			if (result == 0)
				return;
			Contact contact = this.getContactDetails(name);
			if (contact != null)
				contact.setPhoneNumber(phoneNumber);;
		}
	}

	private Contact getContactDetails(String name) {
		Contact contact = this.contactList.stream()
				.filter(contactData -> contactData.getFirstName().equals(name)).findFirst()
				.orElse(null);
		return contact;
	}

	public boolean checkContactInSyncWithDB(String name) {
		List<Contact> checkList = addressBookDB_IOService.getContactData(name);
		return checkList.get(0).equals(getContactDetails(name));
	}

	public Map<String, Integer> readCity(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			return addressBookDB_IOService.readCity();
		return null;
	}

	public List<Contact> getContactListInStartDateRange(String date1, String date2, IOService ioService) {
		if (ioService.equals(IOService.DB_IO)) {
			return addressBookDB_IOService.getContactListInRange(date1, date2);
		}
		return null;
	}

}
