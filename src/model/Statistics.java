package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * Die Klasse repärsentiert alle berechneten Statistiken.
 */
public class Statistics {


    /**
     * Durchschnitssnote aller bestandenen Module.
     */
    private transient FloatProperty avgGrade;

    /**
     * Anzahl aller erreichten Leistungspunkte.
     */
    private transient IntegerProperty collectedCreditPoints;

    /**
     * Abbildung eines Semesters auf die Leistungspunkte aller darin enthaltenden Module.
     */
    private transient ObservableMap<Semester,IntegerProperty> creditPointsForSemester;


    /**
     * Konstruktor zum Erzeugen eines Statistics-Objektes.
     */
    public Statistics(){
        this.avgGrade = new SimpleFloatProperty();
        this.collectedCreditPoints = new SimpleIntegerProperty();
        this.creditPointsForSemester = new SimpleMapProperty<>(FXCollections.observableHashMap());
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

    public int getCollectedCreditPoints() {
        return collectedCreditPoints.get();
    }

    public IntegerProperty collectedCreditPointsProperty() {
        return collectedCreditPoints;
    }

    public void setCollectedCreditPoints(int collectedCreditPoints) {
        this.collectedCreditPoints.set(collectedCreditPoints);
    }

    public void setCreditPointsForSemester(Semester semester, int creditPoints){
        if(creditPointsForSemester.get(semester) != null){
            creditPointsForSemester.get(semester).set(creditPoints);
        }
        else{
            creditPointsForSemester.put(semester,new SimpleIntegerProperty(creditPoints));
        }

    }

    public IntegerProperty creditPointsForSemesterProperty(Semester semester){
        return creditPointsForSemester.get(semester);

    }

}
