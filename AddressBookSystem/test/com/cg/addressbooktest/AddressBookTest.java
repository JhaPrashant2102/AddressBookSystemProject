package com.cg.addressbooktest;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cg.addressbook.AddressBookService;
import com.cg.addressbook.AddressBookService.IOService;
import com.cg.addressbook.Contact;

class AddressBookTest {

	// UC16
	@Test
	public void givenAddressBookInDbWhenRetrievedShouldMatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		assertEquals(3, contactList.size());
	}

	
}
