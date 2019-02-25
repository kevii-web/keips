package com.keviiweb.keips;

import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;

public class StudentManager {
	
	private List<Student> studentList = new LinkedList<>();
	private HashSet<String> nusnetSet = new HashSet<>();
	
	public static final int EXCELSHEET_NUSNET_INDEX = 0;
	public static final int EXCELSHEET_MATRIC_INDEX = 1;
	public static final int EXCELSHEET_NAME_INDEX = 2;
	public static final int EXCELSHEET_GENDER_INDEX = 3;
	public static final int EXCELSHEET_SEMESTER_INDEX = 4;
	public static final int EXCELSHEET_CATEGORY_INDEX = 5;
	public static final int EXCELSHEET_CCANAME_INDEX = 6;
	public static final int EXCELSHEET_ATTENDANCE_INDEX = 7;
	public static final int EXCELSHEET_PERFORMANCE_INDEX = 8;
	public static final int EXCELSHEET_OUTSTANDING_INDEX = 9;
	
	public static final int EXCELSHEET_BONUS_INDEX = 3;
	public static final int EXCELSHEET_BONUS_DESCP_INDEX = 4;
	
	/*
	 *Takes in the data of a student and calculates the OSA points as well as the room draw points
	 * 
	 *Returns updated student object
	 */
	public void ProcessStudent(List<String> nameRow, int sheetNumber) {
		String nusnet = nameRow.get(EXCELSHEET_NUSNET_INDEX);
		Student thisStudent = null;
		int i;
		for (i = 0; i < studentList.size(); i++) {
			if (!nusnet.equals(studentList.get(i).getnusnet())) {
				continue;
			} else {	//once a student is found, start to process the info
				thisStudent = studentList.get(i);
				
				if (sheetNumber == ExcelReader.EXCELSHEET_MAIN_INDEX) {
					String ccaName = nameRow.get(EXCELSHEET_CCANAME_INDEX);
					String category = nameRow.get(EXCELSHEET_CATEGORY_INDEX);
					String attendancePts = nameRow.get(EXCELSHEET_ATTENDANCE_INDEX);
					String performancePts = nameRow.get(EXCELSHEET_PERFORMANCE_INDEX);
					String outstandingPts = nameRow.get(EXCELSHEET_OUTSTANDING_INDEX);

					processCca(thisStudent, ccaName, category, attendancePts, performancePts, outstandingPts);

					System.out.println("Parsed " + nusnet + ": CCA = " + ccaName + " attendance = " + attendancePts +
							"performance = " + performancePts + " outstanding = " + outstandingPts);
				}
				
				else if (sheetNumber == ExcelReader.EXCELSHEET_BONUS_INDEX) {
					String bonusPts = nameRow.get(EXCELSHEET_BONUS_INDEX);
					String descp = nameRow.get(EXCELSHEET_BONUS_DESCP_INDEX);
					
					processBonusPts(thisStudent, bonusPts, descp);
					
					System.out.println("Parsed " + nusnet + ": Bonus Pts = " + bonusPts + " description = " + descp);
				}
				
				else {
					System.out.println("Unknown error encountered: invalid sheet number.");
					return;
				}
				break;
			}
		}
		if (i == studentList.size()) {
			//if we cant find the student, return an error
			System.out.println("Unable to find student: " + nusnet);
		}
	}
	
	private void processBonusPts(Student stu, String bonusPts, String description) {
		int bonusPtsInteger = Integer.parseInt(bonusPts);
		BonusCCA bonusCca = new BonusCCA(bonusPtsInteger, description);
		stu.addToBonusCcaList(bonusCca);
	}
	
	private void processCca(Student stu, String ccaName, String category, String attendancePts,
							String performancePts, String outstandingPts) {
		
		int attendanceInteger = Integer.parseInt(attendancePts);
		int performanceInteger = Integer.parseInt(performancePts);
		int outstandingInteger = Integer.parseInt(outstandingPts);
		
		CCA newCCA = new CCA(ccaName, category, attendanceInteger, performanceInteger, outstandingInteger);
		
		stu.addToCcaList(newCCA);
	}
	
	public void addToStudentList(Student student) {
		//check that the resident is not already inside the list of residents
		//coz i was too lazy and my test data has duplicates
		String nusnetNum = student.getnusnet();
		if ((!nusnetNum.equals("")) && nusnetSet.contains(nusnetNum)) {
			System.out.println("Encountered duplicate nusnet: " + nusnetNum);
			System.out.println("Ignoring sudent ...");
			return;
		} else {
			nusnetSet.add(nusnetNum);
		}
		this.studentList.add(student);
	}
	
	public void printStudentList() {
		for (int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i).toString());
		}
	}
	
	public List<Student> getAllStudents() {
		return this.studentList;
	}
}
