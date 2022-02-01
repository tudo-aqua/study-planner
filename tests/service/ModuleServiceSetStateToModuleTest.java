package service;

import exceptions.DataNotValidException;
import entity.Module;
import entity.Semester;
import entity.StudyPlanner;
import entity.enums.State;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Testklasse für die Methode modifyModule der Service-Klasse ModuleService.
 */
public class ModuleServiceSetStateToModuleTest {
    /**
     * Referenz auf den StudyPlannerService der Testumgebung.
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


        //Beispielmodul erstellen
        exampleModule = moduleService.createModule("Software Praktikum",6,LocalDate.of(2021,4,12),exampleSemesterOne);
    }

    /**
     * Test der setStateToModule-Methode mit gültigen Eingaben.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test
    public void setStateToModuleCaseOne() throws DataNotValidException {
        //Testdaten erzeugen
        int inputGrade = 3;
        State intputState = State.PASSED_WITH_GRADE;

        //Test: Die Note vor dem Test ist nicht 3.0
        assertNotEquals(3f,exampleModule.getGrade(),0.01);
        assertNotEquals(State.PASSED_WITH_GRADE,exampleModule.getState());

        //Zu testende Methode mit Testdaten aufrufen
        this.moduleService.setStateToModule(exampleModule, intputState,inputGrade);

        //Test, ob alle Werte im Modul entspechend geändert wurden
        assertEquals(3f,exampleModule.getGrade(),0.01);
        assertEquals(State.PASSED_WITH_GRADE,exampleModule.getState());
    }

    /**
     * Test der setStateOfModule-Methode mit ungültigen Eingabe für grade.
     * @throws DataNotValidException Wird geworfen, wenn Eingaben ungültig sind.
     */
    @Test(expected = DataNotValidException.class)
    public void createModuleCaseTwo() throws DataNotValidException {

        //Testdaten erzeugen
        int inputGrade = 6;

        //Zu testende Methode mit Testdaten aufrufen
        this.moduleService.setStateToModule(exampleModule,State.PASSED_WITH_GRADE,inputGrade);

    }



}