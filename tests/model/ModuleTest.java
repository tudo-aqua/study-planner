package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * Testklasse für die Model-Klasse Modul.
 */
public class ModuleTest {


    /**
     * Test des Construktors sowie der getter-Methoden für die Properties der Modul-Klasse.
     */
    @Test
    public void constructorTest(){

        String inputName = "Software Praktikum";
        int intputCreditPoints = 6;
        LocalDate inputExamDate = LocalDate.of(2021,7,23);
        float intputGrade = 1.0f;
        //Neues Modul mit Construktor erzeugen
        Module createdModule = new Module(inputName,intputCreditPoints,inputExamDate);
        //Parameterwerte testen
        assertEquals(inputName,createdModule.nameProperty().get());
        assertEquals(intputCreditPoints,createdModule.creditPointsProperty().get());
        assertEquals(inputExamDate,createdModule.examDateProperty().get());

        //Test des Noten-Parameters
        createdModule.setGrade(intputGrade);
        assertEquals(intputGrade,createdModule.gradeProperty().get(),0.01);
    }
}