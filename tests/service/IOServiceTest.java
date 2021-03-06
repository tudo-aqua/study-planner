package service;


import entity.StudyPlanner;
import org.junit.Before;
import org.junit.Test;
import testdata.TestDataFactory;
import java.io.IOException;


import static org.junit.Assert.*;

/**
 * Testklasse für die Service-Klasse IOService.
 */
public class IOServiceTest {


    /**
     * Referenz auf den StudyPlannerService der Testumgebung.
     */
    private StudyPlannerService studyPlannerService;
    /**
     * Referenz auf den IOService der Testumgebung.
     */
    private IOService ioService;


    /**
     * Testdaten für den Vergleich mit den geladenen Daten.
     */
    private TestDataFactory testDataFactory;

    /**
     * Methode zum initialisieren der Testumgebung mit einer vollständigen Model- und Controller-Schicht.
     */
    @Before
    public void setUp() {
        //Service- und Entity-Schicht erzeugen
        this.studyPlannerService = new StudyPlannerService();
        this.ioService = studyPlannerService.getIOService();
        //Beispielstudiengang für alle Testfälle erzeugen
        testDataFactory = new TestDataFactory();
        studyPlannerService.setStudyPlanner(testDataFactory.getExampleStudyPlanner());


    }


    /**
     * Test der load- and save-Methode durch Speichern und Laden eines Beispielmodels.
     * @throws IOException Wird geworden, wenn es einen IO-Fehler gibt.
     * @throws ClassNotFoundException Wird geworfen, wenn Daten für eine Klasse geladen
     * werden, die nicht im Model definiert ist.
     */
    @Test
    public void loadSaveTest() throws IOException, ClassNotFoundException {
        //Testdaten speichern
        ioService.storeData("test.sp");
        //Daten in Programm löschen
        studyPlannerService.setStudyPlanner(null);
        //Daten neu laden
        ioService.loadData("test.sp");
        StudyPlanner loadData = studyPlannerService.getStudyPlanner();
        //Geladene Daten mit ürsprunglichen Daten vergleichen
        //StudyPlanner
        assertEquals(testDataFactory.getExampleStudyPlanner().getCourseOfStudyName(),loadData.getCourseOfStudyName());
        assertEquals(testDataFactory.getExampleStudyPlanner().getCourseOfStudyCreditPoints(),loadData.getCourseOfStudyCreditPoints());
        //Semester und Moduls
        //Modul DAP 1 und DAP 2 in StudyPlanner
        assertEquals(testDataFactory.getExampleModuleDapOne().getName(),loadData.getModules().get(0).getName());
        assertEquals(testDataFactory.getExampleModuleDapTwo().getName(),loadData.getModules().get(1).getName());
        //Semester WiSe
        assertEquals(testDataFactory.getExampleSemesterWiSe().getName(),loadData.getSemesters().get(0).getName());
        assertEquals(testDataFactory.getExampleSemesterWiSe().getStartDate(),loadData.getSemesters().get(0).getStartDate());
        assertEquals(testDataFactory.getExampleSemesterWiSe().getEndDate(),loadData.getSemesters().get(0).getEndDate());
        assertEquals(testDataFactory.getExampleSemesterWiSe().getModules().size(),loadData.getSemesters().get(0).getModules().size());
        //Modul DAP 1 in WiSe
        assertEquals(testDataFactory.getExampleModuleDapOne().getName(),loadData.getSemesters().get(0).getModules().get(0).getName());
        assertEquals(testDataFactory.getExampleModuleDapOne().getCreditPoints(),loadData.getSemesters().get(0).getModules().get(0).getCreditPoints());
        assertEquals(testDataFactory.getExampleModuleDapOne().getExamDate(),loadData.getSemesters().get(0).getModules().get(0).getExamDate());
        assertEquals(testDataFactory.getExampleModuleDapOne().getGrade(),loadData.getSemesters().get(0).getModules().get(0).getGrade(), 0.01);
        assertEquals(testDataFactory.getExampleModuleDapOne().getState(),loadData.getSemesters().get(0).getModules().get(0).getState());
        //Semester SoSe
        assertEquals(testDataFactory.getExampleSemesterSuSe().getName(),loadData.getSemesters().get(1).getName());
        assertEquals(testDataFactory.getExampleSemesterSuSe().getStartDate(),loadData.getSemesters().get(1).getStartDate());
        assertEquals(testDataFactory.getExampleSemesterSuSe().getEndDate(),loadData.getSemesters().get(1).getEndDate());
        assertEquals(testDataFactory.getExampleSemesterSuSe().getModules().size(),loadData.getSemesters().get(1).getModules().size());
        //Modul DAP 2 in WiSe
        assertEquals(testDataFactory.getExampleModuleDapTwo().getName(),loadData.getSemesters().get(1).getModules().get(0).getName());
        assertEquals(testDataFactory.getExampleModuleDapTwo().getCreditPoints(),loadData.getSemesters().get(1).getModules().get(0).getCreditPoints());
        assertEquals(testDataFactory.getExampleModuleDapTwo().getExamDate(),loadData.getSemesters().get(1).getModules().get(0).getExamDate());
        assertEquals(testDataFactory.getExampleModuleDapTwo().getGrade(),loadData.getSemesters().get(1).getModules().get(0).getGrade(), 0.01);
        assertEquals(testDataFactory.getExampleModuleDapTwo().getState(),loadData.getSemesters().get(1).getModules().get(0).getState());
    }

}