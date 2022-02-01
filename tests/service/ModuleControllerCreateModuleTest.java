package service;

import exceptions.DataNotValidException;
import entity.Module;
import entity.Semester;
import entity.StudyPlanner;
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
    private StudyPlannerService studyPlannerService;
    /**
     * Referenz auf den ModuleController der Testumgebung.
     */
    private ModuleService moduleService;
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
        this.studyPlannerService = new StudyPlannerService();
        this.moduleService = studyPlannerService.getModuleService();
        //Beispielstudiengang für alle Testfälle erzeugen
        this.studyPlannerService.initializeStudyPlanner("BA Informatik", 180);
        this.studyPlanner = studyPlannerService.getStudyPlanner();
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
        //Test: Noch kein Modul existiert
        assertEquals(0,studyPlanner.getModules().size());
        //Zu testende Methode mit Testdaten aufrufen
        Module createdModule = moduleService.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
        //Test: Ein Modul existiert
        assertEquals(1,studyPlanner.getModules().size());
        assertEquals(createdModule,studyPlanner.getModules().get(0));

        //Test, ob alle Werte im neuen Modul mit den übergebenen übereinstimmen
        assertEquals(inputName,createdModule.getName());
        assertEquals(inputECTS,createdModule.getCreditPoints());
        assertEquals(inputExamDate, createdModule.getExamDate());
        //Test, ob Modul dem Examplesemester hinzugefügt wurde
        assertTrue(exampleSemester.getModules().contains(createdModule));
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

        moduleService.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
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

        moduleService.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
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

        moduleService.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
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

        moduleService.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
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

        moduleService.createModule(inputName,inputECTS,inputExamDate,exampleSemester);
    }
}