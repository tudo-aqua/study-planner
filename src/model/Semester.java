package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Diese Klasse repräsentiert ein Semester innerhalb eines Studienganges.
 */
public class Semester implements Serializable {

	/**
	 * Der Name des Semesters, z.B. Wintersemester 2020/2021.
	 */
	private transient StringProperty name;

	/**
	 * Das Datum, an dem das Semester beginnt.
	 */
	private transient ObjectProperty<LocalDate> startDate;

	/**
	 * Das Datum, an dem das Semester endet.
	 */
	private transient ObjectProperty<LocalDate> endDate;

	/**
	 * Liste aller Module, die in dem Semester belegt werden/wurden.
	 */
	private transient ListProperty<Module> modules;


	/**
	 * Konstruktor zum erzeugen eines Semesters.
	 * @param name Der Name des Semesters.
	 * @param startDate Das Startdatum des Semesters.
	 * @param endDate Das Enddatum des Semester.
	 */
	public Semester(String name, LocalDate startDate, LocalDate endDate) {
		//Alle Attribute der Klasse werden mit den übergebenen
		//Werten bzw. einer leeren Liste an Modulen.
		this.name = new SimpleStringProperty(name);
		this.startDate = new SimpleObjectProperty<>(startDate);
		this.endDate = new SimpleObjectProperty<>(endDate);
		this.modules = new SimpleListProperty<>(FXCollections.observableArrayList());
	}

	/**
	 * Die Methode fügt ein Modul zum Semester hinzu.
	 * @param module Das Modul, welches hinzugefügt werden soll.
	 */
	public void addModule(Module module) {
		this.modules.add(module);
	}

	/**
	 * Die Methode entfernt ein Modul aus dem Semester.
	 * @param module Das Modul, welches entfernt werden soll.
	 */
	public void removeModule(Module module) {
		this.modules.remove(module);
	}


	//Getter- und Setter-Methoden für die einzelnen Attribut-Werte und Getter-Methode für die Properties
	//(autogeneriert mit Intellij)
	/**
	 * Getter für den name-Property.
	 * @return Den Namen des Semesters.
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
	 * Setter-Methode für den name-Parameter.
	 * @param name Der Name des Semesters.
	 */
	public void setName(String name) {
		this.name.set(name);
	}

	/**
	 * Getter-Methode für den startDate-Parameter.
	 * @return Den Startdatum des Semesters.
	 */
	public LocalDate getStartDate() {
		return startDate.get();
	}

	/**
	 * Getter für die startDate-Property.
	 * @return Die startDate-Property.
	 */
	public ObjectProperty<LocalDate> startDateProperty() {
		return startDate;
	}

	/**
	 * Setter-Methode für den startDate-Parameter.
	 * @param startDate Das Startdatum des Semesters.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate.set(startDate);
	}

	/**
	 * Getter-Methode für den endDate-Parameter.
	 * @return Den Enddatum des Semesters.
	 */
	public LocalDate getEndDate() {
		return endDate.get();
	}

	/**
	 * Getter für die endDate-Property.
	 * @return Die endDate-Property.
	 */
	public ObjectProperty<LocalDate> endDateProperty() {
		return endDate;
	}

	/**
	 * Setter-Methode für den endDate-Parameter.
	 * @param endDate Das Enddatum des Semesters.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate.set(endDate);
	}

	/**
	 * Getter für den modules-Parameter.
	 * @return Liste aller dem Semester zugeordneten Module.
	 */
	public ObservableList<Module> getModules() {
		return modules.get();
	}


	//Methoden zum Serialisieren des Objektes
	/**
	 * Die Methode speichert alle Daten der Klasse.
	 * @param s Objekt, welches das Speichern der Daten übernimmt.
	 * @throws IOException Wird geworfen, wenn das Speichern fehlschlägt.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.writeUTF(name.getValueSafe());
		s.writeObject(startDate.get());
		s.writeObject(endDate.get());

		s.writeInt(modules.size());

		for(Module module:modules){
			s.writeObject(module);
		}

	}

	/**
	 * Die Methode lädt alle Daten der Klasse.
	 * @param s Objekt, welches das Laden der Daten übernimmt.
	 * @throws IOException Wird geworfen, wenn das Speichern fehlschlägt.
	 * @throws ClassNotFoundException Wird geworfen, wenn die zu ladende Klasse nicht im Model ist.
	 */
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		name = new SimpleStringProperty(s.readUTF());
		startDate = new SimpleObjectProperty<LocalDate>((LocalDate) s.readObject());
		endDate = new SimpleObjectProperty<LocalDate>((LocalDate)s.readObject());
		modules = new SimpleListProperty<>(FXCollections.observableArrayList());
		int size = s.readInt();
		for(int i = 0;i<size;i++){
			Module module = (Module) s.readObject();
			modules.add(module);
		}
	}
}
