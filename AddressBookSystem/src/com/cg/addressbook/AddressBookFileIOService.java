package com.cg.addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIOService {
	public String ADDRESSBOOK_FILE_NAME = "Address-file.txt";

	public void writeData(List<Contact> bookList, String bookName) {
		StringBuffer empBuffer = new StringBuffer();
		bookList.forEach(Contact -> {
			String contactData = Contact.toString().concat("\n");
			empBuffer.append(contactData);
		});

		try {
			Files.write(Paths.get(bookName + "-" + ADDRESSBOOK_FILE_NAME), empBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Contact> readData(String bookName) {
		List<Contact> contactList = new ArrayList<>();
		try {
			Files.lines(new File(bookName + "-" + "Address-file.txt").toPath()).map(line -> line.trim())
					.forEach(line -> {
						int check = 1;
						String firstName = "", lastName = "", city = "", state = "", phoneNumber = "", emailId = "";
						String data = line.toString();
						String dataList[] = data.split(",");
						for (String string : dataList) {
							if (check == 1) {
								firstName = string.replaceAll("firstname =", "");
								check++;
								continue;
							}
							if (check == 2) {
								lastName = string.replaceAll("lastname =", "");
								check++;
								continue;
							}
							if (check == 3) {
								city = string.replaceAll("city =", "");
								check++;
								continue;
							}
							if (check == 4) {
								state = string.replaceAll("state =", "");
								check++;
								continue;
							}
							if (check == 5) {
								phoneNumber = string.replaceAll("phonenumber =", "");
								check++;
								continue;
							}
							if (check == 6) {
								emailId = string.replaceAll("emailid =", "");
								check++;
								break;
							}
						}
						contactList.add(new Contact(firstName, lastName, city, state, phoneNumber, emailId));
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public void printData(String bookName) {
		try {
			Files.lines(new File(bookName + "-" + "Address-file.txt").toPath()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
