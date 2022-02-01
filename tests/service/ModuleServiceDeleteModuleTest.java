package service;


import entity.Module;
import entity.Semester;
import entity.StudyPlanner;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Testklasse für die Methode deleteModule der Service-Klasse ModuleService.
 */
public class ModuleServiceDeleteModuleTest {
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
    private Semester exampleSemester;


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
        exampleSemester = new Semester("Sommersemester 2021",
                LocalDate.of(2021,4,1),LocalDate.of(2021,9,30));
        studyPlanner.addSemester(exampleSemester);


        //Beispielmodul erstellen
        exampleModule = moduleService.createModule("Software Praktikum",6,LocalDate.of(2021,4,12),exampleSemester);
    }

    /**
     * Test der deleteModule-Method.
     */
    @Test
    public void deleteModule(){

        //Test, ob Modul vor dem Löschen exisitert
        assertTrue(exampleSemester.getModules().contains(exampleModule));
        assertTrue(studyPlanner.getModules().contains(exampleModule));

        //Zu testende Methode mit Testdaten aufrufen
        moduleService.deleteModule(exampleModule);

        //Test, ob Löschen erfolgreich war
        assertFalse(exampleSemester.getModules().contains(exampleModule));
        assertFalse(studyPlanner.getModules().contains(exampleModule));
    }

}