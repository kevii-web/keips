package com.keviiweb.keips;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJSON {
    public static void main(String[] args) {
        // Serialize
        Student test = new Student("5519086W", "Ong Yu-He", "M", "1");
        CCA testCCA = new CCA("Flag", "Admin", 10, 10, 10);
        CCA testCCA2 = new CCA("Web", "Admin", 6, 1, 3);
        test.ccaList.add(testCCA);
        test.ccaList.add(testCCA2);
        String result = Student.toJson(test);
        //System.out.println(result);

        // Deserialize
        //System.out.println("Deserializing");
        Student testStudent = Student.fromJson(result);
        //System.out.println(testStudent);

        Student test2 = new Student("5629086A", "ABC AONG", "F", "2");
        CCA test2CCA = new CCA("Rag", "Admin", 12, 30, 1);
        CCA test2CCA2 = new CCA("Ball", "Sports", 6, 1, 4);
        test2.ccaList.add(test2CCA);
        test2.ccaList.add(test2CCA2);

        Student test3 = new Student("6667026K", "Thomas King", "M", "2");
        CCA test3CCA = new CCA("Rag", "Admin", 10, 5, 3);
        test3.ccaList.add(test3CCA);

        List<Student> studentsList = new LinkedList<>();
        studentsList.add(testStudent);
        studentsList.add(test2);
        studentsList.add(test3);

        Map<String, Student> studentsMap = new HashMap<>();
        for (Student stu : studentsList) {
            studentsMap.put(stu.getMagicNumber(), stu);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(studentsMap);
        System.out.println(json);
        /*
        try {
            Files.write(Paths.get("test.json"), json.getBytes());
        } catch (IOException e) {
            System.err.println(e);
        }*/
    }
}
