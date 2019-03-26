import org.junit.jupiter.api.Test;

import com.keviiweb.keips.CCA;
import com.keviiweb.keips.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateOsaPointsTest {

    @Test
    public void lessThan4NoContrasting() {
        CCA cca1 = new CCA("Sports", 16);
        CCA cca2 = new CCA("Sports", 12);
        CCA cca4 = new CCA("Sports", 12);

        Student student1 = new Student("1");
        student1.addToCcaList(cca1);
        student1.addToCcaList(cca2);
        student1.addToCcaList(cca4);

        assertEquals(40, student1.calculateOsaPoints());
        assertEquals(false, student1.isHaveContrasting());
    }

    @Test
    public void lessThan4Contrasting() {
        CCA cca1 = new CCA("Sports", 16);
        CCA cca2 = new CCA("Sports", 12);
        CCA cca4 = new CCA("Culture", 12);

        Student student1 = new Student("1");
        student1.addToCcaList(cca1);
        student1.addToCcaList(cca2);
        student1.addToCcaList(cca4);

        assertEquals(40, student1.calculateOsaPoints());
        assertEquals(true, student1.isHaveContrasting());
    }

    @Test
    public void fourOrLessCCAsWithoutHittingCap() {

        CCA cca1 = new CCA("Sports", 16);
        CCA cca2 = new CCA("Sports", 14);
        CCA cca3 = new CCA("Culture", 15);
        CCA cca4 = new CCA("Sports", 17);
        CCA cca5 = new CCA("Admin", 12);

        Student student1 = new Student("1");
        student1.addToCcaList(cca1);
        student1.addToCcaList(cca2);
        student1.addToCcaList(cca3);
        assertEquals(45, student1.calculateOsaPoints());

        Student student2 = new Student("2");
        student2.addToCcaList(cca1);
        student2.addToCcaList(cca2);
        student2.addToCcaList(cca3);
        assertEquals(45, student2.calculateOsaPoints());

        Student student3 = new Student("0");
        assertEquals(0, student3.calculateOsaPoints());
    }

    @Test
    public void fourOrLessCCAsWithHittingCapSemester1() {

        CCA cca1 = new CCA("Sports", 16);
        CCA cca2 = new CCA("Sports", 14);
        CCA cca3 = new CCA("Culture", 15);
        CCA cca4 = new CCA("Sports", 17);
        CCA cca5 = new CCA("Admin", 13);
        CCA cca6 = new CCA("Culture", 13);


        Student student1 = new Student("1");
        student1.addToCcaList(cca1);
        student1.addToCcaList(cca2);
        student1.addToCcaList(cca4);
        assertEquals(45, student1.calculateOsaPoints());

        Student student3 = new Student("1");
        student3.addToCcaList(cca1);
        student3.addToCcaList(cca2);
        student3.addToCcaList(cca3);
        student3.addToCcaList(cca4);
        student3.addToCcaList(cca5);
        student3.addToCcaList(cca6);
        assertEquals(61, student3.calculateOsaPoints());
    }

    @Test
    public void fourOrLessCCAsWithHittingCapSemester2() {

        CCA cca1 = new CCA("Sports", 16);
        CCA cca2 = new CCA("Sports", 14);
        CCA cca3 = new CCA("Culture", 15);
        CCA cca4 = new CCA("Sports", 17);
        CCA cca5 = new CCA("Admin", 13);
        CCA cca6 = new CCA("Culture", 13);


        //no cap for sem 2
        Student student2 = new Student("2");
        student2.addToCcaList(cca1);
        student2.addToCcaList(cca2);
        student2.addToCcaList(cca4);
        assertEquals(47, student2.calculateOsaPoints());

        Student student4 = new Student("2");
        student4.addToCcaList(cca1);
        student4.addToCcaList(cca2);
        student4.addToCcaList(cca3);
        student4.addToCcaList(cca4);
        student4.addToCcaList(cca5);
        student4.addToCcaList(cca6);
        assertEquals(62, student4.calculateOsaPoints());
    }
}
