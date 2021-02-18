package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Diese Klasse repräsentiert den gesamten Studienplan eines Studienganges und dient als
 * Verbindungspunkt zur Controller-Schicht
 */
public class StudyPlanner {

	/**
	 * Name des Studienganges, für den der Studienplan verwendet wird.
	 */
	private StringProperty courseOfStudy;

	/**
	 * Anzahl an ECTS-Punkten, die in diesem Studiengang erreicht werden müssen.
	 */
	private IntegerProperty ectsOfCourseOfStudy;

	/**
	 * Liste an Semstern, die in diesem Studienplan verwaltet werden.
	 */
	private ListProperty<Semester> semesters;

	/**
	 * Liste aller in diesem Studienplan verwalteten Module.
	 */
	private ListProperty<Module> modules;

	/**
	 * Durchschnitssnote aller bestandenen Module.
	 */
	private FloatProperty avgGrade;

	/**
	 * Anzahl aller erreichten ECTS-Punkte.
	 */
	private IntegerProperty collectedEcts;

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
		this.avgGrade = new SimpleFloatProperty();
		this.collectedEcts = new SimpleIntegerProperty();
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
}
