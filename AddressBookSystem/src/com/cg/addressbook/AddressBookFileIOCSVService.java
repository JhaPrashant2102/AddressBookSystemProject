package com.cg.addressbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookFileIOCSVService {
	private static String ADDRESS_BOOK_CSV_FILE = "address-csv-file.csv";
	private static String ADDRESS_BOOK_JSON_FILE = "address-json-file.json";

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
		try (Reader reader = Files.newBufferedReader(Paths.get(bookName + "-" + ADDRESS_BOOK_CSV_FILE));
				CSVReader csvReader = new CSVReader(reader);) {
			String nextRecord[];
			boolean flag = true;
			while ((nextRecord = csvReader.readNext()) != null) {
				if (flag) {
					flag = false;
					continue;
				}
				String city = nextRecord[0];
				String emailId = nextRecord[1];
				String firstName = nextRecord[2];
				String fullName = nextRecord[3];
				String lastName = nextRecord[4];
				String phoneNumber = nextRecord[5];
				String state = nextRecord[6];
				contactList.add(new Contact(firstName, lastName, city, state, phoneNumber, emailId));
			}
		}
		return contactList;
	}

	public void writeFromCsvWriteToJson(String bookName){
		try {
			Reader reader = Files.newBufferedReader(Paths.get(bookName + "-" + ADDRESS_BOOK_CSV_FILE));
			CsvToBeanBuilder<Contact> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(Contact.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<Contact> csvToBean = csvToBeanBuilder.build();
			List<Contact> contactList = csvToBean.parse();
			Gson gson = new Gson();
			String json  = gson.toJson(contactList);
			FileWriter writer = new FileWriter(bookName+"-"+ADDRESS_BOOK_JSON_FILE);
			writer.write(json);
			writer.close();
			BufferedReader br = new BufferedReader(new FileReader(bookName+"-"+ADDRESS_BOOK_JSON_FILE));
			Contact[] contactList1 = gson.fromJson(br,Contact[].class);
			System.out.println(contactList1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
