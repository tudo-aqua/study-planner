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
 * Testklasse für die Methode deleteModule der Controller-Klasse ModuleController.
 */
public class ModuleControllerDeleteModuleTest {
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
        exampleSemester = new Semester("Sommersemester 2021",
                LocalDate.of(2021,4,1),LocalDate.of(2021,9,30));
        studyPlanner.addSemester(exampleSemester);


        //Beispielmodul erstellen
        exampleModule = moduleController.createModule("Software Praktikum",6,LocalDate.of(2021,4,12),exampleSemester);
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
        moduleController.deleteModule(exampleModule);

        //Test, ob Löschen erfolgreich war
        assertFalse(exampleSemester.getModules().contains(exampleModule));
        assertFalse(studyPlanner.getModules().contains(exampleModule));
    }

}