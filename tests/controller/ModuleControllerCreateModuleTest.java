package controller;

import exceptions.DataNotValidException;
import model.Module;
import model.Semester;
import model.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Testklasse für die Methode createModule der Controller-Klasse ModuleController.
 */
public class ModuleControllerCreateModuleTest {

    /**
     * Referenz auf den StudyPlannerController der Testumgebung.
     */
    private StudyPlannerController studyPlannerController;
    /**
     * Referenz auf den ModuleController der Testumgebung.
     */
    private ModuleController moduleController;
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
        this.moduleController = studyPlannerController.getModuleController();
        //Beispielstudiengang für alle Testfälle erzeugen
        this.studyPlannerController.initializeStudyPlanner("BA Informatik", 180);
        this.studyPlanner = studyPlannerController.getStudyPlanner();
        //Beispielsemester für alle Testfälle erzeugen
        exampleSemester = new Semester("Sommersemester 2021",
                LocalDate.of(2021,4,1),LocalDate.of(2021,9,30));
        studyPlanner.addSemester(exampleSemester);
    }


    /**
     * Test der createModule-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void createModuleCaseOne() throws DataNotValidException {
        //Testdaten erzeugen
        String inputName = "Software Praktikum";
        int inputECTS = 6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);
        //Test, das noch kein Modul existiert
        assertEquals(0,studyPlanner.getModules().size());
        //Zu testende Methode mit Testdaten aufrufen
        moduleController.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
        //Test, das nun ein Modul existiert
        assertEquals(1,studyPlanner.getModules().size() );
        Module testModule = studyPlanner.getModules().get(0);
        //Test, ob alle Werte im neuen Modul mit den übergebenen übereinstimmen
        assertEquals(inputName,testModule.getName());
        assertEquals(inputECTS,testModule.getCreditPoints());
        assertEquals(inputExamDate, testModule.getExamDate());
        //Test, ob Modul dem Examplesemester hinzugefügt wurde
        assertTrue(exampleSemester.getModules().contains(testModule));
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Namen null.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseTwo() throws DataNotValidException {

        //Testdaten erzeugen

        int inputECTS = 6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);
        //Ungültige Eingaben definieren
        String inputName = null;

        moduleController.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Namen leerer String.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseThree() throws DataNotValidException {

        //Testdaten erzeugen

        int inputECTS = 6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);
        //Ungültige Eingaben definieren
        String inputName = "";

        moduleController.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
    }


    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Leistungspunkte.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseFour() throws DataNotValidException {

        //Testdaten erzeugen
        String inputName = "Software Praktikum";
        LocalDate inputExamDate = LocalDate.of(2021,7,27);
        //Ungültige Eingaben definieren
        int inputECTS = -6;

        moduleController.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Datum.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseFive() throws DataNotValidException {

        //Testdaten erzeugen
        String inputName = "Software Praktikum";
        int inputECTS = -6;

        //Ungültige Eingaben definieren
        LocalDate inputExamDate = null;

        moduleController.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
    }


    /**
     * Test der createModule-Methode mit ungültigen Eingabe für Semester.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseSix() throws DataNotValidException {

        //Testdaten erzeugen
        String inputName = "Software Praktikum";
        int inputECTS = -6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);
        //Ungültige Eingaben definieren
        exampleSemester = null;

        moduleController.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
    }
}