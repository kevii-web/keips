package com.keviiweb.keips;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Student class to represent each student's details and his/her list of CCAs.
 */
public class Student {
    private String nusnet;
    private String name;
    private String matricNum;
    private Gender gender;
    protected int semester;
    protected List<CCA> ccaList;
    protected List<BonusCCA> bonusCcaList;
    private OSAPoints osaPoints;

    public Student(String nusnet, String name, String matric, String sex) {
        this.nusnet = nusnet;
        this.name = name;
        this.matricNum = matric;
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

    public static void main(String[] args) {
        // Serialize
        Student test = new Student("E0175519", "Ong Yu-He", "A0167086W", "M");
        CCA testCCA = new CCA("Flag", "Admin", 10, 10, 10);
        test.ccaList.add(testCCA);
        String result = Student.toJson(test);

        System.out.println(result);

        // Deserialize
        System.out.println("Deserializing");
        Student testStudent = Student.fromJson(result);
        System.out.println(testStudent);
    }

    public String toString() {
        String student = String.format("NUSNET: %s, Name = %s, Matric = %s\nCCAS:\n", nusnet, name, matricNum);
        StringBuilder studentInfo = new StringBuilder(student);
        for (CCA cca : ccaList) {
            studentInfo.append(cca);
            studentInfo.append("\n");
        }
        return studentInfo.toString();
    }
    
    public int calculateOsaPoints () {
        osaPoints = new OSAPoints(this.ccaList);
        return osaPoints.calculate(this.semester);
    }
    
    public String getName() {
    	return this.name;
	}
	
	public String getnusnet() {
    	return this.nusnet;
	}
	
	public String getMatricNum() {
    	return this.matricNum;
	}
	
	public String getTotalPoints() { return String.valueOf(calculateTotalPoints());}
	
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
