package testdata;

import model.Module;
import model.Semester;
import model.StudyPlanner;
import model.enums.State;

import java.time.LocalDate;

/**
 * Klasse zum Erstellen von Beispieldaten für Tests.
 */
public class TestDataFactory {

    /**
     * Referenz auf das StudyPlanner-Objekt der Testdaten.
     */
    private StudyPlanner exampleStudyPlanner;

    /**
     *  Referenz auf das Sommersemester-Objekt der Testdaten.
     */
    private Semester exampleSemesterSuSe;

    /**
     *  Referenz auf das Wintersemester-Objekt der Testdaten.
     */
    private Semester exampleSemesterWiSe;

    /**
     *  Referenz auf das DAP1-Objekt der Testdaten.
     */
    private Module exampleModuleDapOne;

    /**
     *  Referenz auf das DAP2-Objekt der Testdaten.
     */
    private Module exampleModuleDapTwo;

    /**
     * Methode erzeugt einen Beispieldatensatz für den Studiengang BA Informatik
     * mit 180 Leistungspunkten. Es gibt zwei Semester mit einem bestanden und einem
     * geplanten Modul.
     */
    public TestDataFactory(){
        //Beispielstudiengang erzeugen
        exampleStudyPlanner = new StudyPlanner("BA Informatik",180);


        //Beispielsemester erzeugen
        Semester exampleSemesterWiSe = new Semester("Wintersemester 2020/2021",
                LocalDate.of(2020,10,1),LocalDate.of(2021,3,31));
        exampleStudyPlanner.addSemester(exampleSemesterWiSe);
        exampleSemesterSuSe = new Semester("Sommersemester 2021",
                LocalDate.of(2021,4,1),LocalDate.of(2021,9,30
                ));

        exampleStudyPlanner.addSemester(exampleSemesterSuSe);

        //Modul DAP 1 mit Status bestanden erzeugen
        exampleModuleDapOne = new Module("DAP 1",12, LocalDate.of(2021,2,12));
        exampleModuleDapOne.setState(State.PASSED_WITH_GRADE);
        exampleModuleDapOne.setGrade(2.0f);
        exampleStudyPlanner.addModule(exampleModuleDapOne);
        exampleSemesterWiSe.addModule(exampleModuleDapOne);

        //Modul DAP 2 mit Status geplant erzeugen
        exampleModuleDapTwo = new Module("DAP 2",12, LocalDate.of(2021,8,22));
        exampleStudyPlanner.addModule(exampleModuleDapTwo);
        exampleSemesterSuSe.addModule(exampleModuleDapTwo);
    }

    //Getter zum Zugriff auf die einzelnen Testobjekte
    /**
     * Getter-Methode für das StudyPlanner-Objekt.
     * @return Den StudyPlanner.
     */
    public StudyPlanner getExampleStudyPlanner() {
        return exampleStudyPlanner;
    }

    /**
     * Getter-Methode für das Sommersemester-Objekt.
     * @return Das Sommersemester.
     */
    public Semester getExampleSemesterSuSe() {
        return exampleSemesterSuSe;
    }

    /**
     * Getter-Methode für das Wintersemester-Objekt.
     * @return Das Wintersemster.
     */
    public Semester getExampleSemesterWiSe() {
        return exampleSemesterWiSe;
    }

    /**
     * Getter-Methode für das DAP1-Module-Objekt.
     * @return Das DAP1-Modul.
     */
    public Module getExampleModuleDapOne() {
        return exampleModuleDapOne;
    }

    /**
     * Getter-Methode für das DAP2-Module-Objekt.
     * @return Das DAP2-Modul.
     */
    public Module getExampleModuleDapTwo() {
        return exampleModuleDapTwo;
    }
}
