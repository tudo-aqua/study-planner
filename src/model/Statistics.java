package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Die Klasse repärsentiert alle berechneten Statistiken.
 */
public class Statistics {


    /**
     * Durchschnitssnote aller bestandenen Module.
     */
    private transient FloatProperty avgGrade;

    /**
     * Anzahl aller erreichten ECTS-Punkte.
     */
    private transient IntegerProperty collectedEcts;

    /**
     * Abbildung eines Semesters auf die erreichten ECTS-Punkte.
     */
    private transient ObservableMap<Semester,IntegerProperty> collectedEctsForSemester;


    public Statistics(){
        this.avgGrade = new SimpleFloatProperty();
        this.collectedEcts = new SimpleIntegerProperty();
        this.collectedEctsForSemester = new SimpleMapProperty<>(FXCollections.observableHashMap());
    }



    //Getter- und Setter-Methoden für die einzelnen Attribut-Werte und Getter-Methode für die Properties
    //(autogeneriert mit Intellij)
    public float getAvgGrade() {
        return avgGrade.get();
    }

    public FloatProperty avgGradeProperty() {
        return avgGrade;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade.set(avgGrade);
    }

    public int getCollectedEcts() {
        return collectedEcts.get();
    }

    public IntegerProperty collectedEctsProperty() {
        return collectedEcts;
    }

    public void setCollectedEcts(int collectedEcts) {
        this.collectedEcts.set(collectedEcts);
    }

    public void setCollectedEctsForSemester(Semester semester, int collectedEcts){
        if(collectedEctsForSemester.get(semester) != null){
            collectedEctsForSemester.get(semester).set(collectedEcts);
        }
        else{
            collectedEctsForSemester.put(semester,new SimpleIntegerProperty(collectedEcts));
        }

    }

    public int getCollectedEctsForSemester(Semester semester){
        return collectedEctsForSemester.get(semester).get();
    }

    public IntegerProperty collectedEctsForSemesterProperty(Semester semester){
        return collectedEctsForSemester.get(semester);

    }

}
