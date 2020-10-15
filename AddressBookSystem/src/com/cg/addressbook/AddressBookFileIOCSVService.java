package com.cg.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookFileIOCSVService {
	private static String ADDRESS_BOOK_CSV_FILE = "address-csv-file.csv";

	public void writeData(List<Contact> bookList, String bookName)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		try (Writer writer = Files.newBufferedWriter(Paths.get(bookName + "-" + ADDRESS_BOOK_CSV_FILE));) {
			StatefulBeanToCsv<Contact> beanToCSV = new StatefulBeanToCsvBuilder<Contact>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCSV.write(bookList);
		}

	}

	public List<Contact> readData(String bookName) throws IOException {
		List<Contact> contactList = new ArrayList<Contact>();
		try(Reader reader = Files.newBufferedReader(Paths.get(bookName+"-"+ADDRESS_BOOK_CSV_FILE));
				CSVReader csvReader = new CSVReader(reader);
			){
			String nextRecord[];
			boolean flag = true;
			while((nextRecord=csvReader.readNext())!=null) {
				if(flag)
					continue;
				String city = nextRecord[0];
				String emailId = nextRecord[1];
				String firstName = nextRecord[2];
				String fullName = nextRecord[3];
				String lastName = nextRecord[4];
				String phoneNumber =nextRecord[5];
				String state = nextRecord[6];
				contactList.add(new Contact(firstName, lastName, city, state, phoneNumber, emailId));
			}	
		}
		return contactList;
	}

}
