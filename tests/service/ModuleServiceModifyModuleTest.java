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
 * Testklasse für die Methode modifyModule der Controller-Klasse ModuleController.
 */
public class ModuleServiceModifyModuleTest {
    /**
     * Referenz auf den StudyPlannerController der Testumgebung.
     */
    private StudyPlannerService studyPlannerService;
    /**
     * Referenz auf den ModuleService der Testumgebung.
     */
    private ModuleService moduleService;
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
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Service- und Entity-Schicht.
     * @throws Exception Wird geworfen, wenn es bei der Initialisierung einen Fehler gibt.
     */
    @Before
    public void setUp() throws Exception {
        //Service- und Entity-Schicht erzeugen
        this.studyPlannerService = new StudyPlannerService();
        this.moduleService = studyPlannerService.getModuleService();
        //Beispielstudiengang für alle Testfälle erzeugen
        this.studyPlannerService.initializeStudyPlanner("BA Informatik", 180);
        this.studyPlanner = studyPlannerService.getStudyPlanner();
        //Beispielmsemester für alle Testfälle erzeugen
        exampleSemesterOne = new Semester("Sommersemester 2021",
                LocalDate.of(2021,4,1),LocalDate.of(2021,9,30));
        studyPlanner.addSemester(exampleSemesterOne);
        exampleSemesterTwo = new Semester("Wintersemester 2021",
                LocalDate.of(2021,10,1),LocalDate.of(2022,3,31));
        studyPlanner.addSemester(exampleSemesterTwo);

        //Beispielmodul erstellen
        exampleModule = moduleService.createModule("Software Praktikum",6,LocalDate.of(2021,4,12),exampleSemesterOne);
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
        moduleService.modifyModule(exampleModule,inputECTS,inputExamDate,exampleSemesterTwo);

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
        moduleService.modifyModule(exampleModule,inputECTS,inputExamDate,exampleSemesterTwo);
    }

    /**
     *Test der createModule-Methode mit ungültigen Eingabe für Prüfungsdatum.
     *@throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void modifyModuleCaseThree() throws DataNotValidException {
        //Testdaten erzeugen
        int inputECTS = -6;

        //Zu testende Methode mit Testdaten aufrufen
        moduleService.modifyModule(exampleModule,inputECTS,null,exampleSemesterTwo);
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
        moduleService.modifyModule(exampleModule,inputECTS,inputExamDate,null);
    }

}