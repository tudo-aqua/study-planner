package model;

import javafx.beans.property.*;
import model.enums.State;

import java.io.*;
import java.time.LocalDate;

/**
 * Diese Klasse repräsentiert ein Modul eines Studienganges.
 */
public class Module implements Serializable {

	/**
	 * Der Name des Moduls.
	 */
	private transient StringProperty name;

	/**
	 * Die Anzahl an Leistungspunkten, die das Modul beim erfolgreichen
	 * abschließen bringt.
	 */
	private transient IntegerProperty creditPoints;

	/**
	 * Datum der (geplanten) Modulprüfung.
	 */
	private transient ObjectProperty<LocalDate> examDate;

	/**
	 * Status des Moduls.
	 */
	private State state;

	/**
	 * Bei einem benoten Modul die erreichte Modulnote.
	 */
	private transient FloatProperty grade;

	/**
	 * Konstruktor zum Anlegen eines Moduls.
	 * @param name Der Name des Moduls.
	 * @param creditPoints Die Leistungspunkte des Moduls.
	 * @param examDate Der Prüfungstermin für das Modul.
	 */
	public Module(String name, int creditPoints, LocalDate examDate) {
		//Alle Attribute der Klasse werden mit den übergebenen
		//Werten bzw. Default-Werten initialisiert.
		this.name = new SimpleStringProperty(name);
		this.creditPoints = new SimpleIntegerProperty(creditPoints);
		this.examDate = new SimpleObjectProperty<>(examDate);
		this.state = State.NO_RESULT;
		this.grade = new SimpleFloatProperty(0);
	}

	//Getter- und Setter-Methoden für die einzelnen Attribut-Werte und Getter-Methode für die Properties
	//(autogeneriert mit Intellij)

	/**
	 * Getter-Methode für den name-Parameter.
	 * @return Den Namen des Moduls.
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * Getter für die name-Property.
	 * @return Die name-Property.
	 */
	public StringProperty nameProperty() {
		return name;
	}

	/**
	 * Getter-Methode für den creditPoints-Parameter.
	 * @return Die Leistungspunkte des Moduls.
	 */
	public int getCreditPoints() {
		return creditPoints.get();
	}

	/**
	 * Getter für die creditPoints-Property.
	 * @return Die creditPoints-Property.
	 */
	public IntegerProperty creditPointsProperty() {
		return creditPoints;
	}

	/**
	 * Setter-Methode für den creditPoints-Parameter.
	 * @param creditPoints Die Leistungspunkte des Moduls.
	 */
	public void setCreditPoints(int creditPoints) {
		this.creditPoints.set(creditPoints);
	}

	/**
	 * Getter-Methode für den examDate-Parameter.
	 * @return Den Prüfungstermin des Moduls.
	 */
	public LocalDate getExamDate() {
		return examDate.get();
	}

	/**
	 * Getter für die examDate-Property.
	 * @return Die examDate-Property.
	 */
	public ObjectProperty<LocalDate> examDateProperty() {
		return examDate;
	}

	/**
	 * Setter-Methode für den examDate-Parameter.
	 * @param examDate Der Prüfungstermin des Moduls.
	 */
	public void setExamDate(LocalDate examDate) {
		this.examDate.set(examDate);
	}

	/**
	 * Getter-Methode für den state-Parameter.
	 * @return Den Status des Moduls.
	 */
	public State getState() {
		return state;
	}

	/**
	 * Setter-Methode für den state-Parameter.
	 * @param state Der Status des Moduls.
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Getter-Methode für den grade-Parameter.
	 * @return Die Note des Moduls.
	 */
	public float getGrade() {
		return grade.get();
	}

	/**
	 * Getter für die grade-Property.
	 * @return Die grade-Property.
	 */
	public FloatProperty gradeProperty() {
		return grade;
	}

	/**
	 * Setter-Methode für den grade-Parameter.
	 * @param grade Die Note des Moduls.
	 */
	public void setGrade(float grade) {
		this.grade.set(grade);
	}

	//Methoden zum Serialisieren des Objektes
	/**
	 * Die Methode speichert alle Daten der Klasse.
	 * @param s Objekt, welches das Speichern der Daten übernimmt.
	 * @throws IOException Wird geworfen, wenn das Speichern fehlschlägt.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.writeUTF(name.getValueSafe());
		s.writeInt(creditPoints.get());
		s.writeObject(examDate.get());
		s.writeFloat(grade.get());
		s.writeInt(state.getId());
	}

	/**
	 * Die Methode lädt alle Daten der Klasse.
	 * @param s Objekt, welches das Laden der Daten übernimmt.
	 * @throws IOException Wird geworfen, wenn das Speichern fehlschlägt.
	 * @throws ClassNotFoundException Wird geworfen, wenn die zu ladende Klasse nicht im Model ist.
	 */
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		name = new SimpleStringProperty(s.readUTF());
		creditPoints = new SimpleIntegerProperty(s.readInt());
		examDate = new SimpleObjectProperty<LocalDate>((LocalDate) s.readObject());
		grade = new SimpleFloatProperty(s.readFloat());
		state = State.stateFromId(s.readInt());
	}

}
