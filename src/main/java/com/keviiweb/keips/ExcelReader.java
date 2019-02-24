package com.keviiweb.keips;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SuppressForbidden;
import org.apache.poi.xssf.eventusermodel.XSSFReader;

import java.util.*;
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
	
    public static void main( String[] args ) {
    	
    	ExcelReader newReader = new ExcelReader();
    	newReader.run();
    	
	}
	
	private void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			String fileName = sc.nextLine();
			System.out.println("User input: " + fileName);

			//terminate if user input is -1
			if (fileName.equals("-1")) {
				System.out.println("Terminating program ...");
				return;
			}

			if (!parseFile(fileName)) {
				System.out.println("Error parsing file.");
			} else {
				System.out.println("Successfully parsed file.");
			}
		}
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
		
    	int sheetCount = 0;
    	while (sheetiterator.hasNext()) {
    		parseSheet(sheetiterator.next());
    		sheetCount++;
		}
		
		System.out.println("Successfully parsed all sheets : " + sheetCount + " sheets.");
    	return true;
	}
	
	//takes in as input a sheet and parses it
	private boolean parseSheet(Sheet sheet) {
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
			processName(nameRow);
		}
		return true;
	}
	
    private void processName(List<String> row) {
        String nusnet = row.get(0);
        if (!nusnet.isEmpty()) {
            System.out.println(row);
            String matric = row.get(1);
            String name = row.get(2);
        }
    }
}
