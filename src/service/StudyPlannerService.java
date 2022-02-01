package service;

import exceptions.DataNotValidException;
import entity.StudyPlanner;

/**
 * Zentrale Service-Klasse, die die anderen Service verbindet, die Verbindung zu den Entities verwaltet und
 * als Zugriffspunkt für die GUI dient.
 */
public class StudyPlannerService {

	/**
	 * Referenz auf die zentrale Entity-Klasse (und somit auf die Entities).
	 */
	private StudyPlanner studyPlanner;

	/**
	 * Referenz auf den ModuleService.
	 */
	private ModuleService moduleService;

	/**
	 * Referenz auf den IOService.
	 */
	private IOService ioService;

	/**
	 * Referenz auf den SemesterService.
	 */
	private SemesterService semesterService;

	/**
	 * Referenz auf den StatisticsService.
	 */
	private StatisticsService statisticsService;

	/**
	 * Konstruktor, der die anderen Services initialisiert.
	 */
	public StudyPlannerService() {
		//Initialisierung der einzelnen Controller
		this.ioService = new IOService(this);
		this.moduleService = new ModuleService(this);
		this.semesterService = new SemesterService(this);
		this.statisticsService = new StatisticsService(this);

	}

	/**
	 * Methode zum Erzeugen eines neuen leeren Studienplans.
	 * @param courseOfStudyName Name des Studienganges für den Studienplan.
	 * @param courseOfStudyCreditPoints Anzahl an Leistungspunkten, die für den Studiengang erreicht werden müssen.
	 * @throws DataNotValidException Wird geworfen, wenn die Anzahl an Leistungspunkten nicht valide ist (kleiner oder gleich 0) oder der Name des Studienganges leer ist.
	 */
	public void initializeStudyPlanner(String courseOfStudyName, int courseOfStudyCreditPoints)throws DataNotValidException {
		if(courseOfStudyCreditPoints <= 0 || courseOfStudyName == null || courseOfStudyName.equals(""))
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


	//Getter-Methoden für den Zugriff auf die anderen Services
	/**
	 * Getter-Methode für den ModuleService.
	 * @return Referenz auf den ModuleService.
	 */
	public ModuleService getModuleService() {
		return moduleService;
	}

	/**
	 * Getter-Methode für den IOService.
	 * @return Referenz auf den IOService.
	 */
	public IOService getIOService() {
		return ioService;
	}

	/**
	 * Getter-Methode für den SemesterService.
	 * @return Referenz auf den SemesterService.
	 */
	public SemesterService getSemesterService() {
		return semesterService;
	}

	/**
	 * Getter-Methode für den StatisticsService.
	 * @return Referenz auf den StatisticsService.
	 */
	public StatisticsService getStatisticsService() {
		return statisticsService;
	}
}
