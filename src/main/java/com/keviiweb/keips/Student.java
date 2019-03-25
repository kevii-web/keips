package com.keviiweb.keips;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Student class to represent each student's details and his/her list of CCAs.
 */
public class Student {
    private String matric;
    private String magicNumber;
    private String name;
    private Gender gender;
    private String semester;
    protected List<CCA> ccaList;
    protected List<BonusCCA> bonusCcaList;
    private OSAPoints osaPoints;

    public Student(String matric, String magicNumber, String name, String sex, String semester) {
        this.matric = matric;
        this.magicNumber = magicNumber;
        this.name = name;
        this.semester = semester;
        this.gender = Gender.getGender(sex);
        this.ccaList = new ArrayList<>();
        this.bonusCcaList = new ArrayList<>();
    }

    public void addToBonusCcaList(BonusCCA cca) {
    	this.bonusCcaList.add(cca);
	}

    public void addToCcaList(CCA newCca) {
    	this.ccaList.add(newCca);
	}

    public static String toJson(Student student) {
        Gson gson = new Gson();
        String json = gson.toJson(student);
        return json;
    }

    public static Student fromJson(String json) {
        Gson gson = new Gson();
        Student student = gson.fromJson(json, Student.class);
        return student;
    }

    public String toString() {
        String student = String.format("NUSNETMATRIC: %s, Name = %s\nCCAS:\n", magicNumber, name);
        StringBuilder studentInfo = new StringBuilder(student);
        for (CCA cca : ccaList) {
            studentInfo.append(cca);
            studentInfo.append("\n");
        }
        studentInfo.append("Total points: " + getTotalPoints());
        return studentInfo.toString();
    }

    //calculates the Osa points based on the semester he/she came in
    public int calculateOsaPoints () {
        osaPoints = new OSAPoints(this.ccaList, this.bonusCcaList);
        return osaPoints.calculate(Integer.parseInt(this.semester));

    }

    public String getName() {

    	return this.name;
	}

	public String getMagicNumber() {
    	return this.magicNumber;
	}

	public String getMatric() {
        return this.matric;
    }

	public String getTotalPoints() {
	  return String.valueOf(calculateTotalPoints());
	}

	public int calculateTotalPoints() {
    	int i = 0;
    	for (CCA cca : ccaList) {
    		i += cca.getTotalPoints();
		}
		for (BonusCCA bonuscca : bonusCcaList) {
    		i+= bonuscca.getPts();
		}
		return i;
	}
}

enum Gender {
    M, F;

    /**
     * Returns the gender enum constant that matches the string token exactly.
     * @param token string that matches enum constant exactly.
     */
    public static Gender getGender(String token) {return  Gender.valueOf(token);}
}
