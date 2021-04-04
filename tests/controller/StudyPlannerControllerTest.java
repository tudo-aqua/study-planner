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
     * Test des Konstruktors des StudyPlannerControllers.
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
     * Test der setStudyPlanner-Methode.
     */
    @Test
    public void setStudyPlannerTest() {
       String inputCourseOfStudyName = "BA Informatik";
       int intputCourseOfStudyCreditPoints = 180;
       StudyPlanner studyPlanner = new StudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);
       studyPlannerController.setStudyPlanner(studyPlanner);

       assertEquals(studyPlanner,studyPlannerController.getStudyPlanner());
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
        StudyPlanner studyPlanner = studyPlannerController.getStudyPlanner();
        assertEquals(inputCourseOfStudyName,studyPlanner.getCourseOfStudyName());
        assertEquals(intputCourseOfStudyCreditPoints,studyPlanner.getCourseOfStudyCreditPoints());
    }

    /**
     * Test der initializeStudyPlanner-Methode mit unülgiter Eingaben für courseOfStudyName null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void initializeStudyPlannerCaseTwo() throws DataNotValidException {

        String inputCourseOfStudyName = null;
        int intputCourseOfStudyCreditPoints = 180;
        studyPlannerController.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);

    }

    /**
     * Test der initializeStudyPlanner-Methode mit unülgiter Eingaben für courseOfStudyName leerer String.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void initializeStudyPlannerCaseThree() throws DataNotValidException {

        String inputCourseOfStudyName = "";
        int intputCourseOfStudyCreditPoints = 180;
        studyPlannerController.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);

    }

    /**
     * Test der initializeStudyPlanner-Methode mit unülgiter Eingaben für courseOfStudyName null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void initializeStudyPlannerCaseFour() throws DataNotValidException {

        String inputCourseOfStudyName = "BA Informatik";
        int intputCourseOfStudyCreditPoints = 0;
        studyPlannerController.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);

    }

}