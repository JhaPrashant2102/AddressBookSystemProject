package com.cg.addressbooktest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.cg.addressbook.AddressBookService;
import com.cg.addressbook.AddressBookService.IOService;
import com.cg.addressbook.Contact;
import com.google.gson.Gson;
import com.practice.fileIO.EmployeePayRollData;
import com.practice.fileIO.EmployeePayrollService;

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

	// UC18
	@Test
	public void givenDateRangeWhenRetrievedShouldMatchEmployeeCount() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		List<Contact> contactCheckList = addressBookService.getContactListInStartDateRange("2012-01-01", "2019-12-12",
				IOService.DB_IO);
		System.out.println(contactCheckList.size());
		assertEquals(1, contactCheckList.size());
	}

	// UC19
	@Test
	public void givenCityShouldReturnNumberOfContactsInParticularCityInDB() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		Map<String, Integer> cityCount = addressBookService.readCity(IOService.DB_IO);
		assertEquals((Integer) 2, cityCount.get("California"));
	}

	// UC20
	@Test
	public void givenNewContactWhenAddedShouldSyncWithDB() {
		AddressBookService addressBookService = new AddressBookService();
		List<Contact> contactList = addressBookService.readData(IOService.DB_IO);
		addressBookService.addContactToAddressBook("Mark", "Brown", "City 4", "Florida", "9087554654", "mark@gmail.com",
				LocalDate.now());
		boolean result = addressBookService.checkContactInSyncWithDB("Mark");
		assertTrue(result);
	}

	// UC21
	public void givenMultipleContactsWhenAddedToDBShouldSyncWithDB() {
		Contact[] contactList = {
				new Contact("Jeff", "Bezos", "Bangalore", "Karnataka", "9765432100", "jeff@amazon.com",
						LocalDate.now()),
				new Contact("Mukesh", "Ambani", "Mumbai", "Maharashtra", "9766432100", "mukesh@reliance.com",
						LocalDate.now()) };
		AddressBookService addressBookService = new AddressBookService();
		addressBookService.readData(IOService.DB_IO);
		addressBookService.addContactsToAddressBook(Arrays.asList(contactList));
		assertEquals(7, addressBookService.countEntries(IOService.DB_IO));
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
