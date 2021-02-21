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
	 * Anzahl aller in diesem Semester gesammelten ECTS-Punkte.
	 */
	private transient IntegerProperty collectedECTS;

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
		this.collectedECTS = new SimpleIntegerProperty();

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

	/**
	 * Methode zum Überprüfen, ob ein Modul bereits dem Semester
	 * zugeordnet ist.
	 * @param module Das Modul, welches überprüft werden soll.
	 * @return True, wenn das Modul bereits dem Semester zugeordet ist,
	 * sonst false.
	 */
	public boolean isModuleInSemester(Module module) {
		return this.modules.contains(module);
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

	public LocalDate getStartDate() {
		return startDate.get();
	}

	public ObjectProperty<LocalDate> startDateProperty() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate.set(startDate);
	}

	public LocalDate getEndDate() {
		return endDate.get();
	}

	public ObjectProperty<LocalDate> endDateProperty() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate.set(endDate);
	}

	public ObservableList<Module> getModules() {
		return modules.get();
	}

	public ListProperty<Module> modulesProperty() {
		return modules;
	}

	public void setModules(ObservableList<Module> modules) {
		this.modules.set(modules);
	}

	public int getCollectedECTS() {
		return collectedECTS.get();
	}

	public IntegerProperty collectedECTSProperty() {
		return collectedECTS;
	}

	public void setCollectedECTS(int collectedECTS) {
		this.collectedECTS.set(collectedECTS);
	}

	//Methoden zum Serialisieren des Objektes
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.writeUTF(name.getValueSafe());
		s.writeObject(startDate.get());
		s.writeObject(endDate.get());

		if (modules == null || modules.size() == 0) {
			s.writeInt(0);
		}
		else{
			s.writeInt(modules.size());
		}
		for(Module module:modules){
			s.writeObject(module);
		}
		s.writeInt(collectedECTS.get());

	}

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
		collectedECTS = new SimpleIntegerProperty(s.readInt());
	}
}
