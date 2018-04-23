import controller.LaboratoriesController;
import model.Laboratory;
import model.Student;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestLaboratoriesController {
    LaboratoriesController laboratoriesController = new LaboratoriesController("students.txt", "laboratories.txt");

    @Test
    public void testSaveStudent(){
        //EC 1 9 13
        Student student = new Student("aaaa1111", "Test Student", 111);
        assertTrue(laboratoriesController.saveStudent(student));

        //EC 4
        student.setRegNumber("aaa1111");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 5
        student.setRegNumber("aaaa111");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 6
        student.setRegNumber("1111aaaa");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 7
        student.setRegNumber("aaaaaa1111");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 8
        student.setRegNumber("aaaa11111");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 10
        student.setGroup(1001);
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 11
        student.setGroup(-1);
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 14
        student.setName("a aa aaa");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 15
        student.setName(".,.,.,';");
        assertFalse(laboratoriesController.saveStudent(student));

        //EC 16
        student.setName("123 123");
        assertFalse(laboratoriesController.saveStudent(student));

    }

    @Test
    public void testSaveLaboratory() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.JANUARY, 13);
        Date dateInThePast = calendar.getTime();
        calendar.set(2222, Calendar.JANUARY, 13);
        Date dateInTheFuture = calendar.getTime();

        try {
            //TC1
            Laboratory laboratory = new Laboratory(2, "22/12/2222", 2, "aaaa1111");
            assertTrue(laboratoriesController.saveLaboratory(laboratory));

            //TC2
            laboratory.setNumber(-2);
            assertFalse(laboratoriesController.saveLaboratory(laboratory));

            //TC2
            laboratory.setNumber(2);
            laboratory.setProblemNumber(11);
            assertFalse(laboratoriesController.saveLaboratory(laboratory));

            //TC4
            laboratory.setProblemNumber(2);
            laboratory.setDate(dateInThePast);
            assertFalse(laboratoriesController.saveLaboratory(laboratory));

            //TC5
            laboratory.setDate(dateInTheFuture);
            laboratory.setProblemNumber(-10);
            assertFalse(laboratoriesController.saveLaboratory(laboratory));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddGrade() throws IOException, ParseException {
        String student = "aaaa1111";
        String labNumber = "2";
        float grade = 9;

        assertTrue(laboratoriesController.addGrade(student, labNumber, grade));
    }

    @Test
    public void testPassedStudents() throws IOException, ParseException {
        Student student = new Student("aaaa1112", "Test Student2", 111);
        laboratoriesController.saveStudent(student);
        Laboratory laboratory = new Laboratory(3, "22/12/2222", 2, "aaaa1112");
        laboratoriesController.saveLaboratory(laboratory);
        String student2 = "aaaa1112";
        String labNumber = "3";
        float grade = 4;
        laboratoriesController.addGrade(student2, labNumber, grade);

        int length = laboratoriesController.passedStudents().size();

        assertTrue(length == 1);
    }

}
