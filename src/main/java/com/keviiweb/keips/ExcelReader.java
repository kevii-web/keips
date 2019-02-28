package com.keviiweb.keips;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Hello world!
 *
 * Code adapted from: https://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
 * and https://poi.apache.org/components/spreadsheet/quick-guide.html
 */
public class ExcelReader {
	private FileInputStream excelFile;
	private Workbook workbook;
	private DataFormatter formatter;
	private StudentManager manager;
	
	public static final String MASTER_FILE = "READDATA.txt";
	public static final String DIRECTORY_SHEET = "sheet/";
	public static final int EXCELSHEET_MAIN_INDEX = 0;
	public static final int EXCELSHEET_BONUS_INDEX = 1;

	
	public static void main(String[] args) {

		ExcelReader newReader = new ExcelReader();
		newReader.run();

	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		boolean isResidentList = true;
		this.manager = new StudentManager();

		while (true) {
			String fileName = sc.nextLine();
			System.out.println("User input: " + fileName);

			//terminate if user input is -1
			if (fileName.equals("-1")) {
				System.out.println("Terminating program ...");
				return;
			}
			
			//all in one method to read all files
			if (fileName.equals("readallfiles")) {
				
				try {
					File masterfile = new File(DIRECTORY_SHEET + MASTER_FILE);
					FileReader reader = new FileReader(masterfile);
					BufferedReader br = new BufferedReader(reader);
					String s = br.readLine();
					parseResidentList(DIRECTORY_SHEET + s);
					s = br.readLine();
					while (s != null) {
						parseFile(DIRECTORY_SHEET +s);
						s = br.readLine();
					}
					continue;
				} catch (IOException e) {
					System.out.println("Cannot find " + DIRECTORY_SHEET + MASTER_FILE);
				}
			}
			
			//print out to console
			if (fileName.equals("print")) {
				manager.printStudentList();
				continue;
			}
			
			//print to a json object
			if (fileName.equals("printtojson")) {
				System.out.println(manager.printasjson());
				continue;
			}

			//creates a new excel file called output.xlsx and prints out the students total points
			if (fileName.equals("printtoexcel")) {
				try {
					FileOutputStream outFile = new FileOutputStream(new File("output.xlsx"));
					Workbook outWorkbook = new XSSFWorkbook();
					Sheet sheet = outWorkbook.createSheet();
					List<Student> students = manager.getAllStudents();
					for (int i = 0; i < students.size(); i++) {
						addStudentToSheet(students.get(i), sheet, i);
					}
					outWorkbook.write(outFile);
					workbook.close();
					outFile.close();
				} catch (FileNotFoundException e) {
					System.out.println(e);
				} catch (IOException e) {
					System.out.println(e);
				}
				continue;
			}

			//first file is the resident list
			if (isResidentList) {
				isResidentList = false;
				if (!parseResidentList(fileName)) {
					System.out.println("Cannot find resident list file.");
					return;
				}
				continue;
			}

			if (!parseFile(fileName)) {
				System.out.println("Error parsing file.");
			} else {
				System.out.println("Successfully parsed file.");
			}
		}
	}

	private boolean parseResidentList(String fileName) {
		try {
			excelFile = new FileInputStream(new File(fileName));
			workbook = WorkbookFactory.create(excelFile);
			formatter = new DataFormatter();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			return false;
		}

		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		if (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();

			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				//create an iterator for the row
				Iterator<Cell> cellIterator = currentRow.iterator();
				List<String> nameRow = new ArrayList<>();

				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					String cellText = formatter.formatCellValue(currentCell);
					nameRow.add(cellText);
				}

				parseNewStudent(nameRow);
			}

		}

		try {
			excelFile.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		return true;
	}

	private void parseNewStudent(List<String> row) {
		String nusnet = row.get(StudentManager.EXCELSHEET_NUSNET_INDEX);
		String studentName = row.get(StudentManager.EXCELSHEET_NAME_INDEX);
		String matric = row.get(StudentManager.EXCELSHEET_MATRIC_INDEX);
		String gender = row.get(StudentManager.EXCELSHEET_GENDER_INDEX);

		Student newStudent = new Student(nusnet, studentName, matric, gender);

		manager.addToStudentList(newStudent);

		System.out.println("added new student: " + nusnet + " name = " + studentName + " matric = " +
				matric + " gender = " + gender);
	}

	//takes in as input the filename and parses the file
	private boolean parseFile(String fileName) {
		//set up the excelfile
		try {
			excelFile = new FileInputStream(new File(fileName));
			workbook = WorkbookFactory.create(excelFile);
			formatter = new DataFormatter();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			return false;
		}

		Iterator<Sheet> sheetiterator = workbook.sheetIterator();

		//parse the first sheet of data
		if (sheetiterator.hasNext()) {
			if (!parseSheet(sheetiterator.next(), EXCELSHEET_MAIN_INDEX)) {
				System.out.println("Error parsing main sheet.");
				return false;
			}
		}
		//parse the second sheet of data
		if (sheetiterator.hasNext()) {
			if (!parseSheet(sheetiterator.next(), EXCELSHEET_BONUS_INDEX)) {
				System.out.println("Error parsing bonus sheet.");
				return false;
			}
		}

		try {
			excelFile.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return true;
	}

	//takes in as input a sheet and parses it
	private boolean parseSheet(Sheet sheet, int sheetNumber) {
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();

		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			//create an iterator for the row
			Iterator<Cell> cellIterator = currentRow.iterator();
			List<String> nameRow = new ArrayList<>();

			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				String cellText = formatter.formatCellValue(currentCell);
				nameRow.add(cellText);
			}
			manager.ProcessStudent(nameRow, sheetNumber);
		}
		return true;
	}

	//adds a student to a xlsx workbook sheet in the specified index
	private void addStudentToSheet(Student student, Sheet sheet, int index) {
		Row row = sheet.createRow(index);
		Cell cell = row.createCell(0);
		cell.setCellValue(student.getnusnet());
		
		cell = row.createCell(1);
		cell.setCellValue(student.getMatricNum());
		
		cell = row.createCell(2);
		cell.setCellValue(student.getTotalPoints());
	}
	
}
