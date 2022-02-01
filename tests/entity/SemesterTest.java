package entity;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testklasse für die Model-Klasse Semester.
 */
public class SemesterTest {


    /**
     * Test des Construktors sowie der getter-Methoden für die Properties der Semester-Klasse.
     */
    @Test
    public void constructorTest(){

        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Neues Semester mit Construktor erzeugen
        Semester createdSemester = new Semester(inputName,inputStartDate,inputEndDate);
        //Parameterwerte testen
        assertEquals(inputName,createdSemester.nameProperty().get());
        assertEquals(inputStartDate,createdSemester.startDateProperty().get());
        assertEquals(inputEndDate,createdSemester.endDateProperty().get());

        assertNotNull(createdSemester.modulesProperty().get());
    }
}