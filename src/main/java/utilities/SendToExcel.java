package utilities;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SendToExcel {

	// Create properties reference to read from properties file
	public static Properties p = new Properties();

	public static XSSFWorkbook workbook = new XSSFWorkbook();

	FileOutputStream fout = new FileOutputStream(
			"C:\\Users\\YASH\\eclipse-workspace\\Hackathon(IC)\\Hackathon(IC)\\Excel_output\\Message.xlsx");

	// Constructor to initialize reader object with our properties file and load it
	public SendToExcel() throws Exception {
		FileReader reader = new FileReader("project.properties");
		p.load(reader);
		// driver = d;
	}

	@Test
	public void searchResultToExcel(String name[], String hrs[], String r[]) throws IOException {
		// creating a sheet
		XSSFSheet sheet = workbook.createSheet("Search");

		// Increasing width of column
		sheet.setColumnWidth(0, 14000);
		sheet.setColumnWidth(1, 7000);
		sheet.setColumnWidth(2, 7000);
		int i = 0;
		for (int j = 0; j < 3; j++) {

			// Writing the data into the excel file
			Row row = sheet.createRow(j);

			Cell col1 = row.createCell(0);
			col1.setCellValue(name[i]);

			Cell col2 = row.createCell(1);
			col2.setCellValue(hrs[i]);

			Cell col3 = row.createCell(2);
			col3.setCellValue(r[i]);
			i++;
		}
		workbook.write(fout);
	}

	@Test
	public void levelResultToExcel(List<String> level, List<Integer> count) throws Exception {

		// creating a sheet
		XSSFSheet sheet = workbook.createSheet("Levels");

		// Increasing width of column
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);

		// To create first row
		Row row = sheet.createRow(0);
		// Assigning values to cells
		row.createCell(0).setCellValue("LEVEL NAME");
		row.createCell(1).setCellValue("TOTAL LEARNING");

		int rowCount = 1;
		// Iterating lists
		Iterator<String> itr = level.iterator();
		Iterator<Integer> itrt = count.iterator();

		while (itr.hasNext()) {
			Row row1 = sheet.createRow(rowCount++);

			// Values from level list stored in first column
			row1.createCell(0).setCellValue((String) itr.next());

			// Values from count list stored in second column
			row1.createCell(1).setCellValue((Integer) itrt.next());
		}

		// Creating row for storing total of total learning level count
		Row row2 = sheet.createRow(rowCount);
		row2.createCell(0).setCellValue("TOTAL COUNT");

		int sum = 0;
		Iterator<Integer> itrCount = count.iterator();
		while (itrCount.hasNext()) {
			sum = sum + (Integer) itrCount.next();
		}

		row2.createCell(1).setCellValue(sum);

		// Data is written using FileOutputStream
		workbook.write(fout);
	}

	public void languageToExcel(String[] list, String num) throws IOException {

		XSSFSheet sheet = workbook.createSheet("Language");

		// Increasing width of column
		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 11000);
		// Set heading
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Available Languages");
		sheet.getRow(0).createCell(1).setCellValue("Total number of Courses Available");
		// List of Available Languages
		for (int i = 0; i < list.length; i++) {
			sheet.createRow(i + 1);
			sheet.getRow(i + 1).createCell(0).setCellValue(list[i]);
		}
		sheet.getRow(1).createCell(1).setCellValue(num);
		workbook.write(fout);

	}

	@Test
	public void errorToResult(String message) throws IOException {

		// creating a sheet
		XSSFSheet sheet = workbook.createSheet("Error");
		sheet.setColumnWidth(0, 14000);
		// Writing the data into the excel file
		Row row = sheet.createRow(0);
		Cell col = row.createCell(0);
		col.setCellValue(message);

		workbook.write(fout);

		workbook.close();

		fout.close();
	}

}