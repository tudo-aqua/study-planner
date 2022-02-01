package service;

import exceptions.DataNotValidException;
import entity.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testklasse für die Klasse StudyPlannerService.
 */

public class StudyPlannerServiceTest {

    /**
     * Referenz auf den StudyPlannerService der Testumgebung.
     */
    private StudyPlannerService studyPlannerService;



    /**
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Service- und Entity-Schicht.
     */
    @Before
    public void setUp(){
        //Service- und Entity-Schicht erzeugen
        this.studyPlannerService = new StudyPlannerService();

    }

    /**
     * Test des Konstruktors des StudyPlannerControllers.
     * Es wird getestet, ob alle Kontroller initialisiert wurden.
     */
    @Test
    public void constructorTest() {

        assertNotNull(studyPlannerService.getModuleService());
        assertNotNull(studyPlannerService.getIOService());
        assertNotNull(studyPlannerService.getSemesterService());
        assertNotNull(studyPlannerService.getStatisticsService());
    }

    /**
     * Test der setStudyPlanner-Methode.
     */
    @Test
    public void setStudyPlannerTest() {
       String inputCourseOfStudyName = "BA Informatik";
       int intputCourseOfStudyCreditPoints = 180;
       StudyPlanner studyPlanner = new StudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);
       studyPlannerService.setStudyPlanner(studyPlanner);

       assertEquals(studyPlanner, studyPlannerService.getStudyPlanner());
    }

    /**
     * Test der initializeStudyPlanner-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void initializeStudyPlannerCaseOne() throws DataNotValidException {

        String inputCourseOfStudyName = "BA Informatik";
        int intputCourseOfStudyCreditPoints = 180;
        studyPlannerService.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);
        StudyPlanner studyPlanner = studyPlannerService.getStudyPlanner();
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
        studyPlannerService.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);

    }

    /**
     * Test der initializeStudyPlanner-Methode mit unülgiter Eingaben für courseOfStudyName leerer String.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void initializeStudyPlannerCaseThree() throws DataNotValidException {

        String inputCourseOfStudyName = "";
        int intputCourseOfStudyCreditPoints = 180;
        studyPlannerService.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);

    }

    /**
     * Test der initializeStudyPlanner-Methode mit unülgiter Eingaben für courseOfStudyName null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void initializeStudyPlannerCaseFour() throws DataNotValidException {

        String inputCourseOfStudyName = "BA Informatik";
        int intputCourseOfStudyCreditPoints = 0;
        studyPlannerService.initializeStudyPlanner(inputCourseOfStudyName,intputCourseOfStudyCreditPoints);

    }

}