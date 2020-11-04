package com.cg.addressbooktest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
		addressBookService.updateContactPhoneNumber("Bill", "987654321", IOService.DB_IO);
		boolean result = addressBookService.checkContactInSyncWithDB("Bill");
		assertTrue(result);
	}
	
	//UC18
	@Test
	public void givenDateRangeWhenRetrievedShouldMatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		List<Contact> contactCheckList = addressBookService
				.getContactListInStartDateRange("2012-01-01", "2019-12-12", IOService.DB_IO);
		System.out.println(contactCheckList.size());
		assertEquals(1, contactCheckList.size());
	}
	
	// UC19
	@Test
	public void givenCityShouldReturnNumberOfContactsInParticularCityInDB() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		Map<String, Integer> cityCount = addressBookService.readCity(IOService.DB_IO);
		assertEquals((Integer)2,cityCount.get("California"));
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
