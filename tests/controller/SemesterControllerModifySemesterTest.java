package controller;

import exceptions.DataNotValidException;
import model.Semester;
import model.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testklasse für die Methode modifySemester der Controller-Klasse SemesterController.
 */
public class SemesterControllerModifySemesterTest {

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
     * Referenz auf ein Beispielsemester.
     */
    private Semester exampleSemester;

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

        //Beispielsemester für alle Testfälle erzeugen mit falschen Daten erstellen
        exampleSemester = new Semester("SoSe 2021",
                LocalDate.of(2021,1,2),LocalDate.of(2020,9,30));
        studyPlanner.addSemester(exampleSemester);
    }

    /**
     * Test der modifySemester-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void modifySemesterCaseOne() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Test: Alle Daten im Examplesemester sind ungleich der Eingabedaten
        assertNotEquals(inputName,exampleSemester.getName());
        assertNotEquals(inputStartDate,exampleSemester.getStartDate());
        assertNotEquals(inputEndDate, exampleSemester.getEndDate());

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);

        //Test, ob alle Werte im neuen Semester mit den übergebenen übereinstimmen
        assertEquals(inputName,exampleSemester.getName());
        assertEquals(inputStartDate,exampleSemester.getStartDate());
        assertEquals(inputEndDate, exampleSemester.getEndDate());
    }

    /**
     * Test der modifySemester-Methode mit ungültiger Eingaben für name null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifySemesterCaseTwo() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = null;
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
    }


    /**
     * Test der modifySemester-Methode mit ungültiger Eingaben für name leerer String.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifySemesterCaseThree() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
    }


    /**
     * Test der modifySemester-Methode mit ungültiger Eingaben für Startdatum null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifySemesterCaseFour() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = null;
        LocalDate inputEndDate = LocalDate.of(2021,9,30);

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
    }


    /**
     * Test der modifySemester-Methode mit ungültiger Eingaben für Enddatum null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifySemesterCaseFive() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Sommersemester 2021";
        LocalDate inputStartDate = LocalDate.of(2021,4,1);
        LocalDate inputEndDate = null;

        //Zu testende Methode mit Testdaten aufrufen
        semesterController.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
    }


}