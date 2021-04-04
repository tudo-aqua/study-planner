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
 * Testklasse für die Methode modifyModule der Controller-Klasse ModuleController.
 */
public class ModuleControllerModifyModuleTest {
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
    private Semester exampleSemesterOne;

    /**
     * Referenz auf ein Beispielsemester.
     */
    private Semester exampleSemesterTwo;

    /**
     * Referenz auf ein Beispielmodul.
     */
    private Module exampleModule;

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
        //Beispielmsemester für alle Testfälle erzeugen
        exampleSemesterOne = new Semester("Sommersemester 2021",
                LocalDate.of(2021,4,1),LocalDate.of(2021,9,30));
        studyPlanner.addSemester(exampleSemesterOne);
        exampleSemesterTwo = new Semester("Wintersemester 2021",
                LocalDate.of(2021,10,1),LocalDate.of(2022,3,31));
        studyPlanner.addSemester(exampleSemesterTwo);

        //Beispielmodul erstellen
        exampleModule = moduleController.createModule("Software Praktikum",6,LocalDate.of(2021,4,12),exampleSemesterOne);
    }

    /**
     * Test der modifyModule-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void modifyModuleCaseOne() throws DataNotValidException {
        //Testdaten erzeugen
        int inputECTS = 6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);

        //Zu testende Methode mit Testdaten aufrufen
        moduleController.modifyModule(exampleModule,inputECTS,inputExamDate,exampleSemesterTwo);

        //Test, ob alle Werte im Modul entspechend geändert wurden
        assertEquals(inputECTS,exampleModule.getCreditPoints());
        assertEquals(inputExamDate, exampleModule.getExamDate());
        //Test, ob Modul verschoben  wurde
        assertFalse(exampleSemesterOne.getModules().contains(exampleModule));
        assertTrue(exampleSemesterTwo.getModules().contains(exampleModule));
    }

    /**
     *Test der createModule-Methode mit ungültigen Eingabe für Leistungspunkte.
     *@throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifyModuleCaseTwo() throws DataNotValidException {
        //Testdaten erzeugen
        int inputECTS = -6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);

        //Zu testende Methode mit Testdaten aufrufen
        moduleController.modifyModule(exampleModule,inputECTS,inputExamDate,exampleSemesterTwo);
    }

    /**
     *Test der createModule-Methode mit ungültigen Eingabe für Prüfungsdatum.
     *@throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifyModuleCaseThree() throws DataNotValidException {
        //Testdaten erzeugen
        int inputECTS = -6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);

        //Zu testende Methode mit Testdaten aufrufen
        moduleController.modifyModule(exampleModule,inputECTS,null,exampleSemesterTwo);
    }

    /**
     *Test der createModule-Methode mit ungültigen Eingabe für Semester.
     *@throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifyModuleCaseFour() throws DataNotValidException {
        //Testdaten erzeugen
        int inputECTS = -6;
        LocalDate inputExamDate = LocalDate.of(2021,7,27);

        //Zu testende Methode mit Testdaten aufrufen
        moduleController.modifyModule(exampleModule,inputECTS,inputExamDate,null);
    }

}