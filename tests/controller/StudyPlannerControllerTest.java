package controller;

import exceptions.DataNotValidException;
import model.Semester;
import model.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * Testklasse für die Klasse StudyPlannerController.
 */

public class StudyPlannerControllerTest {

    /**
     * Referenz auf den StudyPlannerController der Testumgebung.
     */
    private StudyPlannerController studyPlannerController;

    private StudyPlanner studyPlanner;


    /**
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Model- und Controller-Schicht.
     * @throws Exception Wird geworfen, wenn es bei der Initialisierung einen Fehler gibt.
     */
    @Before
    public void setUp() throws Exception {
        //Controller- und Model-Schicht erzeugen
        this.studyPlannerController = new StudyPlannerController();

    }

    /**
     * Des des Konstruktors des StudyPlannerControllers.
     * Es wird getestet, ob alle Kontroller initialisiert wurden.
     */
    @Test
    public void ConstructorTest() {

        assertNotNull(studyPlannerController.getModuleController());
        assertNotNull(studyPlannerController.getIOController());
        assertNotNull(studyPlannerController.getSemesterController());
        assertNotNull(studyPlannerController.getStatisticsController());
    }

    /**
     * Test der initializeStudyPlanner-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void initializeStudyPlannerCaseOne() throws DataNotValidException {

        String inputCourseOfStudyName = "BA Informatik";
        int intputCourseOfStudyCreditPoints = 180;
        studyPlannerController.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);
        this.studyPlanner = studyPlannerController.getStudyPlanner();
        assertEquals(inputCourseOfStudyName,studyPlanner.getCourseOfStudyName());
        assertEquals(intputCourseOfStudyCreditPoints,studyPlanner.getCourseOfStudyCreditPoints());
    }

}