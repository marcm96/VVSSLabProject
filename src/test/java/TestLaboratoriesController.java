import controller.LaboratoriesController;
import model.Laboratory;
import model.Student;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

public class TestLaboratoriesController {
    LaboratoriesController laboratoriesController = new LaboratoriesController("students.txt", "laboratories.txt");

    @Test
    public void testSaveStudent(){
        Student student = new Student("aaaa1111", "Test Student", 111);

        assertTrue(laboratoriesController.saveStudent(student));
    }

    @Test
    public void testSaveLaboratory() throws ParseException {
        Laboratory laboratory = new Laboratory(2, "22/12/2222", 2, "aaaa1111");

        assertTrue(laboratoriesController.saveLaboratory(laboratory));
    }

}
