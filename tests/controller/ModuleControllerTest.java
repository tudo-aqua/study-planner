package controller;

import exceptions.DataNotValidException;
import exceptions.ModuleAlreadyExistsException;
import model.Module;
import model.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Testklasse für die Controller-Klasse ModuleController.
 */
public class ModuleControllerTest {

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
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Model- und Controller-Schicht.
     * @throws Exception Wird geworfen, wenn es bei der Initialisierung einen Fehler gibt.
     */
    @Before
    public void setUp() throws Exception {
        this.studyPlannerController = new StudyPlannerController();
        this.moduleController = studyPlannerController.getModuleController();
        this.studyPlannerController.initializeStudyPlanner("BA Informatik", 180);
        this.studyPlanner = studyPlannerController.getStudyPlanner();
    }


    /**
     * Test der createModule-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind
     * @throws ModuleAlreadyExistsException Wird geworfen, wenn ein Modul mit dem Namen
     * bereits existiert
     */
    @Test
    public void createModuleCaseOne() throws DataNotValidException, ModuleAlreadyExistsException {
        //Testdaten erzeugen
        String inputName = "Software Praktikum";
        int inputECTS = 6;
        LocalDate inputExamDate = LocalDate.of(2021,9,12);
        //Test, das noch kein Modul existiert
        assertTrue(studyPlanner.getModules().size() == 0);
        //Zu testende Methode mit Testdaten aufrufen
        moduleController.createModule(inputName,inputECTS,inputExamDate);
        //Test, das nun ein Modul existiert
        assertTrue(studyPlanner.getModules().size() == 1);
        Module testModule = studyPlanner.getModules().get(0);
        //Test, ob alle Werte im neuen Modul mit den übergebenen übereinstimmen
        assertEquals(inputName,testModule.getName());
        assertEquals(inputECTS,testModule.getEcts());
        assertEquals(inputExamDate, testModule.getExamDate());
    }

    /**
     * Test der createModule-Methode mit ungültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind
     * @throws ModuleAlreadyExistsException Wird geworfen, wenn ein Modul mit dem Namen
     * bereits existiert
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseTwo() throws DataNotValidException, ModuleAlreadyExistsException {
        String inputName = "";
        int inputECTS = -1;
        LocalDate inputExamDate = null;
        //Test, das noch kein Modul existiert
        assertTrue(studyPlanner.getModules().size() == 0);
        moduleController.createModule(inputName,inputECTS,inputExamDate);
    }

    /**
     * Test der createModule-Methode mit gültigen Eingaben aber einem bereits
     * existierenden Modul mit den gewählten Namen.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind
     * @throws ModuleAlreadyExistsException Wird geworfen, wenn ein Modul mit dem Namen
     * bereits existiert
     */
    @Test(expected = ModuleAlreadyExistsException.class)
    public void createModuleCaseThree() throws DataNotValidException, ModuleAlreadyExistsException {
        String inputName = "Software Praktikum";
        int inputECTS = 6;
        LocalDate inputExamDate = LocalDate.of(2021,9,12);
        //Test, das noch kein Modul existiert
        assertTrue(studyPlanner.getModules().size() == 0);
        moduleController.createModule(inputName,inputECTS,inputExamDate);
        //Erstellung eines Moduls mit dem selben Namen um Fehlerbehandlung zu testen
        moduleController.createModule(inputName,inputECTS,inputExamDate);
    }

    /**
     * Test-JavaDoc
     */
    @Test
    public void modifyModule() {
    }

    /**
     * Test-JavaDoc
     */
    @Test
    public void deleteModule() {
    }

    /**
     * Test-JavaDoc
     */
    @Test
    public void setStateToModule() {
    }
}