package entity;

import org.junit.Test;
import testdata.TestDataFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testklasse für die Model-Klasse Statistics.
 */
public class StatisticsTest {


    /**
     * Test des Construktors sowie der getter-Methoden für die Properties der Modul-Statistics.
     */
    @Test
    public void constructorTest(){

        //Neue Statistik mit Construktor erzeugen
        Statistics createdStatistics = new Statistics();
        //Parameterwerte testen
        assertEquals(0.0,createdStatistics.avgGradeProperty().get(),0.01);
        assertEquals(0,createdStatistics.collectedCreditPointsProperty().get());

    }

    /**
     * Test der setCreditPointsForSemester initial und als update.
     */
    @Test
    public void setCreditPointsForSemesterTest(){
        TestDataFactory testDataFactory = new TestDataFactory();
        Semester inputSemester = testDataFactory.getExampleSemesterSuSe();
        int inputCreditPointsInit = 12;
        int inputCreditPointsFollow = 42;
        Statistics createdStatistics = new Statistics();
        createdStatistics.setCreditPointsForSemester(inputSemester,inputCreditPointsInit);
        assertEquals(inputCreditPointsInit, createdStatistics.creditPointsForSemesterProperty(inputSemester).get());

        createdStatistics.setCreditPointsForSemester(inputSemester,inputCreditPointsFollow);
        assertEquals(inputCreditPointsFollow, createdStatistics.creditPointsForSemesterProperty(inputSemester).get());
    }
}