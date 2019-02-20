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

/**
 * Hello world!
 *
 * Code adapted from: https://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
 * and https://poi.apache.org/components/spreadsheet/quick-guide.html
 */
public class ExcelReader
{
    public static void main( String[] args ) {
        if (args.length < 2) {
            System.out.println("Usage: run java ExcelReader <excel file in .xlsx format> <password>");
            return;
        }

        final String FILENAME = args[0];
        final String password = args[1];
        System.out.println( "Reading from " + FILENAME + " with password: " + password);

        try {
            FileInputStream excelFile = new FileInputStream(new File(FILENAME));
            Workbook workbook = WorkbookFactory.create(excelFile, password);
            DataFormatter formatter = new DataFormatter();
            Sheet mainSheet = workbook.getSheetAt(0);
            Sheet bonusSheet = workbook.getSheetAt(1);

            Iterator<Row> iterator = mainSheet.iterator();
            iterator.next();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                List<String> nameRow = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    String cellText = formatter.formatCellValue(currentCell);
                    nameRow.add(cellText);
                }
                processName(nameRow);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private static void processName(List<String> row) {
        String nusnet = row.get(0);
        if (!nusnet.isEmpty()) {
            System.out.println(row);
            String matric = row.get(1);
            String name = row.get(2);
        }
    }
}
