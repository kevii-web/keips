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

    @Test
    public void testChengWee() {
        Student cw = new Student("1");
        CCA cca1 = new CCA("Sports", 20); //tennis
        CCA cca2 = new CCA("Admin", 17); //block comm
        CCA cca3 = new CCA("Culture", 17); //choir
        CCA cca4 = new CCA("Admin", 17); //rag
        CCA cca5 = new CCA("Admin", 11); //kewoc ogl

        cw.addToCcaList(cca1);
        cw.addToCcaList(cca2);
        cw.addToCcaList(cca3);
        cw.addToCcaList(cca4);
        cw.addToCcaList(cca5);

        assertEquals(71, cw.calculateOsaPoints());
    }

    @Test
    public void testJiaHua() {
        Student jh = new Student("1");
        CCA cca1 = new CCA("Sports", 9); //badminton
        CCA cca2 = new CCA("Sports", 16); //squash
        CCA cca3 = new CCA("Admin", 14); //laos
        CCA cca4 = new CCA("Admin", 15); //convening

        jh.addToCcaList(cca1);
        jh.addToCcaList(cca2);
        jh.addToCcaList(cca3);
        jh.addToCcaList(cca4);

        assertEquals(54, jh.calculateOsaPoints());

    }

    @Test
    public void testZhenLun() {
        Student zl = new Student("1");
        CCA cca1 = new CCA("Sports", 16); //floorball
        CCA cca2 = new CCA("Sports", 10); //tt
        CCA cca3 = new CCA("Sports", 13); //sepak
        CCA cca4 = new CCA("Sports", 9); //softball
        CCA cca5 = new CCA("Sports", 2); //volleyball
        CCA cca6 = new CCA("Sports", 2); //track
        CCA cca7 = new CCA("Sports", 7); //handball
        CCA cca8 = new CCA("Admin", 1); //SMC


        zl.addToCcaList(cca1);
        zl.addToCcaList(cca2);
        zl.addToCcaList(cca3);
        zl.addToCcaList(cca4);
        zl.addToCcaList(cca5);
        zl.addToCcaList(cca6);
        zl.addToCcaList(cca7);
        zl.addToCcaList(cca8);

        assertEquals(40, zl.calculateOsaPoints());
    }

    @Test
    public void testTobi() {
        Student zl = new Student("1");
        CCA cca1 = new CCA("Admin", 16); //rag
        CCA cca2 = new CCA("Sports", 15); //swimming
        CCA cca3 = new CCA("Sports", 12); //rr
        CCA cca4 = new CCA("Admin", 17); //laos
        CCA cca5 = new CCA("Admin", 7); //kewoc ogl

        zl.addToCcaList(cca1);
        zl.addToCcaList(cca2);
        zl.addToCcaList(cca3);
        zl.addToCcaList(cca4);
        zl.addToCcaList(cca5);

        assertEquals(60, zl.calculateOsaPoints());
    }

    @Test
    public void testKim() {
        Student zl = new Student("1");
        CCA cca1 = new CCA("Admin", 16); //rag
        CCA cca2 = new CCA("Sports", 8); //track
        CCA cca3 = new CCA("Sports", 16); //rr
        CCA cca4 = new CCA("Culture", 16); //dance

        zl.addToCcaList(cca1);
        zl.addToCcaList(cca2);
        zl.addToCcaList(cca3);
        zl.addToCcaList(cca4);

        assertEquals(56, zl.calculateOsaPoints());

    }

    @Test
    public void testKaiwen() {
        Student zl = new Student("1");
        CCA cca1 = new CCA("Admin", 14); //block comm
        CCA cca2 = new CCA("Sports", 14); //frisbee
        CCA cca3 = new CCA("Sports", 20); //rr
        CCA cca4 = new CCA("Culture", 13); //dance
        CCA cca5 = new CCA("Admin", 16); //convening
        CCA cca6 = new CCA("Admin", 16); //ocip


        zl.addToCcaList(cca1);
        zl.addToCcaList(cca2);
        zl.addToCcaList(cca3);
        zl.addToCcaList(cca4);
        zl.addToCcaList(cca5);
        zl.addToCcaList(cca6);

        assertEquals(66, zl.calculateOsaPoints());
    }

    @Test
    public void testJieCong() {
        Student zl = new Student("1");
        CCA cca1 = new CCA("Admin", 17); //tdc
        CCA cca2 = new CCA("Sports", 10); //trug
        CCA cca3 = new CCA("Sports", 16); //vb
        CCA cca4 = new CCA("Admin", 11); //smc
        CCA cca5 = new CCA("Admin", 4); //convening

        zl.addToCcaList(cca1);
        zl.addToCcaList(cca2);
        zl.addToCcaList(cca3);
        zl.addToCcaList(cca4);
        zl.addToCcaList(cca5);

        assertEquals(54, zl.calculateOsaPoints());
    }

    @Test
    public void testYekCheong() {
        Student zl = new Student("1");
        CCA cca1 = new CCA("Admin", 13); //block comm
        CCA cca2 = new CCA("Admin", 14); //socs
        CCA cca3 = new CCA("Sports", 18); //tennis
        CCA cca4 = new CCA("Sports", 11); //rr
        CCA cca5 = new CCA("Admin", 11); //rag

        zl.addToCcaList(cca1);
        zl.addToCcaList(cca2);
        zl.addToCcaList(cca3);
        zl.addToCcaList(cca4);
        zl.addToCcaList(cca5);

        assertEquals(56, zl.calculateOsaPoints());
    }
}
