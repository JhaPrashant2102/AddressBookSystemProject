package com.cg.addressbook;

import java.util.ArrayList;
import java.util.List;

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
	
}
