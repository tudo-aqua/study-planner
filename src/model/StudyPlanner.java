package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * Diese Klasse repräsentiert den gesamten Studienplan eines Studienganges und dient als
 * Verbindungspunkt zur Controller-Schicht
 */
public class StudyPlanner implements Serializable {

	/**
	 * Name des Studienganges, für den der Studienplan verwendet wird.
	 */
	private transient StringProperty courseOfStudy;

	/**
	 * Anzahl an ECTS-Punkten, die in diesem Studiengang erreicht werden müssen.
	 */
	private transient IntegerProperty ectsOfCourseOfStudy;

	/**
	 * Liste an Semstern, die in diesem Studienplan verwaltet werden.
	 */
	private transient ListProperty<Semester> semesters;

	/**
	 * Liste aller in diesem Studienplan verwalteten Module.
	 */
	private transient ListProperty<Module> modules;

	/**
	 * Referenz auf das Statistics-Objekt.
	 */
	private transient Statistics statistics;


	/**
	 * Konstruktor zum erzeugen eines neuen Studienplanes.
	 * @param courseOfStudy Name des Studienganges.
	 * @param ectsOfCourseOfStudy Anzahl der zu erreichenden ECTS-Punkte.
	 */
	public StudyPlanner(String courseOfStudy, int ectsOfCourseOfStudy) {
		this.courseOfStudy = new SimpleStringProperty(courseOfStudy);
		this.ectsOfCourseOfStudy = new SimpleIntegerProperty(ectsOfCourseOfStudy);
		this.semesters = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.modules = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.statistics = new Statistics();

	}

	/**
	 * Die Methode fügt ein Modul zur Liste aller verwalteten Module hinzu.
	 * @param module Das zu hinzufügende Modul.
	 */
	public void addModule(Module module) {
		this.modules.add(module);
	}

	/**
	 * Die Methode entfernt ein Modul aus der Liste aller verwalteten Module.
	 * @param module Das zu entfernende Modul.
	 */
	public void removeModule(Module module) {
		this.modules.remove(module);
	}

	/**
	 * Die Methode fügt ein Semester zur Liste aller verwalteten Semester hinzu.
	 * @param semester Das zu hinzufügende Semester.
	 */
	public void addSemester(Semester semester) {
		this.semesters.add(semester);
	}

	/**
	 * Methode, die ermittelt, ob ein Modul zu einem Semester zugeordnet ist und wenn ja,
	 * zu welchem.
	 * @param module Das zu prüfende Modul.
	 * @return Referenz auf das Semester, in dem das Modul ist, oder null, wenn das Modul
	 * keinem Semester zugeordnet ist.
	 */
	public Semester getCurrentSemesterOfModule(Module module) {
		for(Semester semester : this.semesters){
			if(semester.getModules().contains(module)){
				return semester;
			}
		}
		return null;
	}

	//Getter- und Setter-Methoden für die einzelnen Attribut-Werte und Getter-Methode für die Properties
	//(autogeneriert mit Intellij)
	public String getCourseOfStudy() {
		return courseOfStudy.get();
	}

	public StringProperty courseOfStudyProperty() {
		return courseOfStudy;
	}

	public void setCourseOfStudy(String courseOfStudy) {
		this.courseOfStudy.set(courseOfStudy);
	}

	public int getEctsOfCourseOfStudy() {
		return ectsOfCourseOfStudy.get();
	}

	public IntegerProperty ectsOfCourseOfStudyProperty() {
		return ectsOfCourseOfStudy;
	}

	public void setEctsOfCourseOfStudy(int ectsOfCourseOfStudy) {
		this.ectsOfCourseOfStudy.set(ectsOfCourseOfStudy);
	}

	public ObservableList<Semester> getSemesters() {
		return semesters.get();
	}

	public ListProperty<Semester> semestersProperty() {
		return semesters;
	}

	public void setSemesters(ObservableList<Semester> semesters) {
		this.semesters.set(semesters);
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

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	//Methoden zum Serialisieren des Objektes
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.writeUTF(courseOfStudy.getValueSafe());
		s.writeInt(ectsOfCourseOfStudy.get());


		if (semesters == null || semesters.size() == 0) {
			s.writeInt(0);
		}
		else{
			s.writeInt(semesters.size());
		}
		for(Semester semester:semesters){
			s.writeObject(semester);
		}


		if (modules == null || modules.size() == 0) {
			s.writeInt(0);
		}
		else{
			s.writeInt(modules.size());
		}
		for(Module module:modules){
			s.writeObject(module);
		}
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		courseOfStudy = new SimpleStringProperty(s.readUTF());
		ectsOfCourseOfStudy = new SimpleIntegerProperty(s.readInt());

		semesters = new SimpleListProperty<>(FXCollections.observableArrayList());
		int numberOfSemester = s.readInt();
		for(int i = 0;i<numberOfSemester;i++){
			Semester semester = (Semester) s.readObject();
			semesters.add(semester);
		}

		modules = new SimpleListProperty<>(FXCollections.observableArrayList());
		int numberOfModuls = s.readInt();
		for(int i = 0;i<numberOfModuls;i++){
			Module module = (Module) s.readObject();
			modules.add(module);
		}
		this.statistics = new Statistics();
	}
}
