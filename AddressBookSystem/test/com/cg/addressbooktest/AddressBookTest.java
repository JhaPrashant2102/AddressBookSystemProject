package com.cg.addressbooktest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.cg.addressbook.AddressBookService;
import com.cg.addressbook.AddressBookService.IOService;
import com.google.gson.Gson;
import com.cg.addressbook.Contact;
import org.junit.Before;

class AddressBookTest {

	// UC16
	@Test
	public void givenAddressBookInDbWhenRetrievedShouldMatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		assertEquals(3, contactList.size());
	}

	// UC17
	@Test
	public void givenContactForAddressBookWhenUpdatedShouldSyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		addressBookService.updateContactPhoneNumber("Bill","987654321",IOService.DB_IO);
		boolean result = addressBookService.checkContactInSyncWithDB("Terisa");
		assertTrue(result);
	}

	// JsonServerRestAssured UC22
	@Test
	public void givenEmployeeDataInJsonServer_whenRetrieved_shouldMatchTheCount() {
		Contact[] arrayOfContact = getContactList();
		AddressBookService addressBookService = new AddressBookService(Arrays.asList(arrayOfContact));
		long entries = addressBookService.countEntries(IOService.REST_IO);
		assertEquals(2, entries);
	}

	private Contact[] getContactList() {
		Response response = RestAssured.get("/address_book");
		Contact[] arrayOfContacts = new Gson().fromJson(response.asString(), Contact[].class);
		return arrayOfContacts;
	}
}
