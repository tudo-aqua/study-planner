package controller;

import exceptions.DataNotValidException;
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
	private IOController ioController;

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
		this.ioController = new IOController(this);
		this.moduleController = new ModuleController(this);
		this.semesterController = new SemesterController(this);
		this.statisticsController = new StatisticsController(this);

	}

	/**
	 * Methode zum Erzeugen eines neuen leeren Studienplans.
	 * @param courseOfStudyName Name des Studienganges für den Studienplan.
	 * @param courseOfStudyCreditPoints Anzahl an Leistungspunkten, die für den Studiengang erreicht werden müssen.
	 * @throws DataNotValidException Wird geworfen, wenn die Anzahl an Leistungspunkten nicht valide ist (kleiner oder gleich 0) oder der Name des Studienganges leer ist.
	 */
	public void initializeStudyPlanner(String courseOfStudyName, int courseOfStudyCreditPoints)throws DataNotValidException {
		if(courseOfStudyCreditPoints <0 || courseOfStudyName == null || courseOfStudyName.equals(""))
			throw new DataNotValidException();
		this.studyPlanner = new StudyPlanner(courseOfStudyName,courseOfStudyCreditPoints);
	}

	//Getter- und Setter-Methoden für den Zugriff auf das Model

	/**
	 * Getter-Methode für die Basisklasse des Models StudyPlanner.
	 * @return Referenz auf den StudyPlanner.
	 */
	public StudyPlanner getStudyPlanner() {
		return studyPlanner;
	}

	/**
	 * Setter-Methode zum Setzen des Models.
	 * @param studyPlanner Die Basisklasse des neuem Models.
	 */
	public void setStudyPlanner(StudyPlanner studyPlanner) {
		this.studyPlanner = studyPlanner;
	}


	//Getter-Methoden für den Zugriff auf die anderen Controller
	/**
	 * Getter-Methode für den ModuleController.
	 * @return Referenz auf den ModuleController.
	 */
	public ModuleController getModuleController() {
		return moduleController;
	}

	/**
	 * Getter-Methode für den IOController.
	 * @return Referenz auf den IOController.
	 */
	public IOController getIOController() {
		return ioController;
	}

	/**
	 * Getter-Methode für den SemesterController.
	 * @return Referenz auf den SemesterController.
	 */
	public SemesterController getSemesterController() {
		return semesterController;
	}

	/**
	 * Getter-Methode für den StatisticsController.
	 * @return Referenz auf den StatisticsController.
	 */
	public StatisticsController getStatisticsController() {
		return statisticsController;
	}
}
