package model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import java.time.LocalDate;

/**
 * Diese Klasse repräsentiert ein Semester innerhalb eines Studienganges.
 */
public class Semester {

	/**
	 * Der Name des Semesters, z.B. Wintersemester 2020/2021.
	 */
	private StringProperty name;

	/**
	 * Das Datum, an dem das Semester beginnt.
	 */
	private ObjectProperty<LocalDate> startDate;

	/**
	 * Das Datum, an dem das Semester endet.
	 */
	private ObjectProperty<LocalDate> endDate;

	/**
	 * Liste aller Module, die in dem Semester belegt werden/wurden.
	 */
	private ListProperty<Module> modules;

	/**
	 * Anzahl aller in diesem Semester geplanten ECTS-Punkte.
	 */
	private IntegerProperty totalECTS;

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
		this.modules = new SimpleListProperty<>();
		this.totalECTS = new SimpleIntegerProperty();

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

	public int getTotalECTS() {
		return totalECTS.get();
	}

	public IntegerProperty totalECTSProperty() {
		return totalECTS;
	}

	public void setTotalECTS(int totalECTS) {
		this.totalECTS.set(totalECTS);
	}
}
