package entity;

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
    /**
     * Getter für die avgGrade-Property.
     * @return Die avgGrade-Property.
     */
    public FloatProperty avgGradeProperty() {
        return avgGrade;
    }

    /**
     * Setter-Methode für den avgGrade-Parameter.
     * @param avgGrade Die berechnete Durchschnittsnote.
     */
    public void setAvgGrade(float avgGrade) {
        this.avgGrade.set(avgGrade);
    }

    /**
     * Getter für die collectedCreditPoints-Property.
     * @return Die collectedCreditPoints-Property.
     */
    public IntegerProperty collectedCreditPointsProperty() {
        return collectedCreditPoints;
    }

    /**
     * Setter-Methode für den collectedCreditPoints-Parameter.
     * @param collectedCreditPoints Die Anzahl an erreichten Leistungspunkten.
     */
    public void setCollectedCreditPoints(int collectedCreditPoints) {
        this.collectedCreditPoints.set(collectedCreditPoints);
    }

    /**
     * Setter-Methode für die zugewiesenen Leistungspunkte zu einem Semester.
     * @param semester Das Semester, dem die Leistungspunkte zugewiesen werden.
     * @param creditPoints Die Leistungspunkte.
     */
    public void setCreditPointsForSemester(Semester semester, int creditPoints){
        if(creditPointsForSemester.get(semester) != null){
            creditPointsForSemester.get(semester).set(creditPoints);
        }
        else{
            creditPointsForSemester.put(semester,new SimpleIntegerProperty(creditPoints));
        }

    }

    /**
     * Getter für die creditPointsForSemester-Property für das übergebene Semester.
     * @param semester Das Semester, für die die Property zurückgegeben werden soll.
     * @return Die creditPointsForSemester-Property.
     */
    public IntegerProperty creditPointsForSemesterProperty(Semester semester){
        return creditPointsForSemester.get(semester);

    }

}
