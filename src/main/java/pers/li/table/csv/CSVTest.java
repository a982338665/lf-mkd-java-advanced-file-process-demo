package pers.li.table.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVTest {

	public static void main(String[] args) throws Exception {
		readCSVWithIndex();
		System.out.println("===========华丽丽的分割线1===================");
		readCSVWithName();
		System.out.println("===========华丽丽的分割线2===================");
		writeCSV();
		System.out.println("write done");
	}

	public static void readCSVWithIndex() throws Exception {
		Reader in = new FileReader("c:/temp/score.csv");
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		for (CSVRecord record : records) {
			System.out.println(record.get(0)); //0 代表第一列
		}
	}

	public static void readCSVWithName() throws Exception {
		Reader in = new FileReader("c:/temp/score.csv");
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Name", "Subject", "Score").parse(in);
		for (CSVRecord record : records) {
			System.out.println(record.get("Subject"));
		}
	}

	public static void writeCSV() throws Exception {
		try (CSVPrinter printer = new CSVPrinter(new FileWriter("person.csv"), CSVFormat.EXCEL)) {
			printer.printRecord("id", "userName", "firstName", "lastName", "birthday");
			printer.printRecord(1, "john73", "John", "Doe", LocalDate.of(1973, 9, 15));
			printer.println();  //空白行
			printer.printRecord(2, "mary", "Mary", "Meyer", LocalDate.of(1985, 3, 29));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
