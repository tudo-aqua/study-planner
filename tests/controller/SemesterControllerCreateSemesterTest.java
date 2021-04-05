package controller;

import exceptions.DataNotValidException;
import model.Semester;
import model.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * Testklasse für die Methode createSemester der Controller-Klasse SemesterController.
 */
public class SemesterControllerCreateSemesterTest {

    /**
     * Referenz auf den StudyPlannerController der Testumgebung.
     */
    private StudyPlannerController studyPlannerController;
    /**
     * Referenz auf den SemesterController der Testumgebung.
     */
    private SemesterController semesterController;
    /**
     * Referenz auf das StudyPlanner-Objekt der Testumgebung.
     */
    private StudyPlanner studyPlanner;


    /**
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Model- und Controller-Schicht.
     * @throws Exception Wird geworfen, wenn es bei der Initialisierung einen Fehler gibt.
     */
    @Before
    public void setUp() throws Exception {
        //Controller- und Model-Schicht erzeugen
        this.studyPlannerController = new StudyPlannerController();
        this.semesterController = studyPlannerController.getSemesterController();
        //Beispielstudiengang für alle Testfälle erzeugen
        this.studyPlannerController.initializeStudyPlanner("BA Informatik", 180);
        this.studyPlanner = studyPlannerController.getStudyPlanner();


    }

    /**
     * Test der createSemester-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void createSemesterCaseOne() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Test: Noch kein Semester existiert
        assertEquals(0,studyPlanner.getModules().size());
        //Zu testende Methode mit Testdaten aufrufen
        Semester createdSemester = semesterController.createSemester(inputName,inputStartDate,inputEndDate);
        //Test: Nur ein Semester existiert
        assertEquals(1,studyPlanner.getSemesters().size() );
        assertEquals(createdSemester,studyPlanner.getSemesters().get(0));

        //Test, ob alle Werte im neuen Semester mit den übergebenen übereinstimmen
        assertEquals(inputName,createdSemester.getName());
        assertEquals(inputStartDate,createdSemester.getStartDate());
        assertEquals(inputEndDate, createdSemester.getEndDate());
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Namen null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createSemesterCaseTwo() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = null;
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.createSemester(inputName,inputStartDate,inputEndDate);
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Namen leerer String.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createSemesterCaseThree() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.createSemester(inputName,inputStartDate,inputEndDate);
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Startdatum null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createSemesterCaseFour() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = null;
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.createSemester(inputName,inputStartDate,inputEndDate);
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Enddatum null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createSemesterCaseFive() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = null;

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.createSemester(inputName,inputStartDate,inputEndDate);
    }

}