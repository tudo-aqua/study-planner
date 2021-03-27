package controller;

import exceptions.DataNotValidException;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ProgressBar;
import model.StudyPlanner;

/**
 * Zentrale Controller-Klasse, die die anderen Controller verbindet, die Verbindung zum Model verwaltet und
 * als Zugriffspunkt für die GUI dient.
 */
public class StudyPlannerController {

	/**
	 * Referenz auf die zentrale Model-Klasse (und somit auf das Model).
	 */
	private StudyPlanner studyPlanner;

	/**
	 * Referenz auf dem ModuleController
	 */
	private ModuleController moduleController;

	/**
	 * Referenz auf den IOController.
	 */
	private IOController iOController;

	/**
	 * Referenz auf den SemesterController.
	 */
	private SemesterController semesterController;

	/**
	 * Referenz auf den StatisticsController.
	 */
	private StatisticsController statisticsController;

	/**
	 * Konstruktor, der die anderen Controller initialisiert.
	 */
	public StudyPlannerController() {
		//Initialisierung der einzelnen Controller
		this.iOController = new IOController(this);
		this.moduleController = new ModuleController(this);
		this.semesterController = new SemesterController(this);
		this.statisticsController = new StatisticsController(this);

	}

	/**
	 * Methode zum Erzeugen eines neuen leeren Studienplans.
	 * @param courseOfStudyName Name des Studienganges für den Studienplan.
	 * @param courseOfStudyEcts Anzahl an ECTS-Punkten, die für den Studiengang erreicht werden müssen.
	 * @throws DataNotValidException Wird geworfen, wenn die Anzahl an ECTS-Punkten nicht valide ist (kleiner oder gleich 0).
	 */
	public void initializeStudyPlanner(String courseOfStudyName, int courseOfStudyEcts)throws DataNotValidException {
		if(courseOfStudyEcts <0)
			throw new DataNotValidException();
		this.studyPlanner = new StudyPlanner(courseOfStudyName,courseOfStudyEcts);
	}

	//Getter- und Setter-Methoden für den Zugriff auf das Model
	public StudyPlanner getStudyPlanner() {
		return studyPlanner;
	}

	public void setStudyPlanner(StudyPlanner studyPlanner) {
		this.studyPlanner = studyPlanner;
	}


	//Getter-Methoden für den Zugriff auf die anderen Controller
	public ModuleController getModuleController() {
		return moduleController;
	}

	public IOController getiOController() {
		return iOController;
	}

	public SemesterController getSemesterController() {
		return semesterController;
	}

	public StatisticsController getStatisticsController() {
		return statisticsController;
	}
}
