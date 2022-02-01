package service;

import exceptions.DataNotValidException;
import entity.Semester;
import entity.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testklasse für die Methode modifySemester der Service-Klasse SemesterService.
 */
public class SemesterServiceModifySemesterTest {

    /**
     * Referenz auf den StudyPlannerService der Testumgebung.
     */
    private StudyPlannerService studyPlannerService;
    /**
     * Referenz auf den SemesterService der Testumgebung.
     */
    private SemesterService semesterService;
    /**
     * Referenz auf das StudyPlanner-Objekt der Testumgebung.
     */
    private StudyPlanner studyPlanner;

    /**
     * Referenz auf ein Beispielsemester.
     */
    private Semester exampleSemester;

    /**
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Service- und Entity-Schicht.
     * @throws Exception Wird geworfen, wenn es bei der Initialisierung einen Fehler gibt.
     */
    @Before
    public void setUp() throws Exception {
        //Service- und Entity-Schicht erzeugen
        this.studyPlannerService = new StudyPlannerService();
        this.semesterService = studyPlannerService.getSemesterService();
        //Beispielstudiengang für alle Testfälle erzeugen
        this.studyPlannerService.initializeStudyPlanner("BA Informatik", 180);
        this.studyPlanner = studyPlannerService.getStudyPlanner();

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
        semesterService.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);

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
        semesterService.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
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
        semesterService.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
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
        semesterService.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
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
        semesterService.modifySemester(exampleSemester,inputName,inputStartDate,inputEndDate);
    }


}