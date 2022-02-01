package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testklasse für die Model-Klasse StudyPlanner.
 */
public class StudyPlannerTest {


    /**
     * Test des Construktors sowie der getter-Methoden für die Properties der StudyPlanner-Klasse.
     */
    @Test
    public void constructorTest(){

        String inputCourseOfStudyName = "BA Informatik";
        int inputCourseOfStudyCreditPoints = 180;

        //Neuer StudyPlanner mit Construktor erzeugen
        StudyPlanner createdStudyPlanner = new StudyPlanner(inputCourseOfStudyName,inputCourseOfStudyCreditPoints);
        //Parameterwerte testen
        assertEquals(inputCourseOfStudyName,createdStudyPlanner.getCourseOfStudyName());
        assertEquals(inputCourseOfStudyCreditPoints,createdStudyPlanner.getCourseOfStudyCreditPoints());
        assertNotNull(createdStudyPlanner.getModules());
        assertNotNull(createdStudyPlanner.getSemesters());
        assertNotNull(createdStudyPlanner.getStatistics());

    }
}