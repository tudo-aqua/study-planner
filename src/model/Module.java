package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
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
	 * Die Anzahl an ECTS-Punkten, die das Modul beim erfolgreichen
	 * abschließen bringt.
	 */
	private transient IntegerProperty ects;

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
	 * @param ects Die zu erreichenden ECTS-Punkte des Moduls.
	 * @param examDate Der Prüfungstermin für das Modul.
	 */
	public Module(String name, int ects, LocalDate examDate) {
		//Alle Attribute der Klasse werden mit den übergebenen
		//Werten bzw. Default-Werten initialisiert.
		this.name = new SimpleStringProperty(name);
		this.ects = new SimpleIntegerProperty(ects);
		this.examDate = new SimpleObjectProperty<>(examDate);
		this.state = State.NO_RESULT;
		this.grade = new SimpleFloatProperty(0);
	}

	//Getter- und Setter-Methoden für die einzelnen Attribut-Werte und Getter-Methode für die Properties
	//(autogeneriert mit Intellij)
	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public int getEcts() {
		return ects.get();
	}

	public IntegerProperty ectsProperty() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects.set(ects);
	}

	public LocalDate getExamDate() {
		return examDate.get();
	}

	public ObjectProperty<LocalDate> examDateProperty() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate.set(examDate);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public float getGrade() {
		return grade.get();
	}

	public FloatProperty gradeProperty() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade.set(grade);
	}

	//Methoden zum Serialisieren des Objektes
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.writeUTF(name.getValueSafe());
		s.writeInt(ects.get());
		s.writeObject(examDate.get());
		s.writeFloat(grade.get());
		s.writeInt(state.getId());
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		name = new SimpleStringProperty(s.readUTF());
		ects = new SimpleIntegerProperty(s.readInt());
		examDate = new SimpleObjectProperty<LocalDate>((LocalDate) s.readObject());
		grade = new SimpleFloatProperty(s.readFloat());
		state = State.stateFromId(s.readInt());

	}

}
