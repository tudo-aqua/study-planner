package service;

import exceptions.DataNotValidException;
import entity.Semester;
import entity.Module;

import java.time.LocalDate;

/**
 * Die Klasse stellt Methoden für die Verwaltung von Semestern zur Verfügung.
 */
public class SemesterService {

	/**
	 * Referenz auf den zentralen Service der Service-Schicht.
	 */
	private StudyPlannerService studyPlannerService;

	/**
	 * Konstruktor, der den StudyPlannerService setzt.
	 * @param spc Referenz auf den zentralen StudyPlannerService.
	 */
	public SemesterService(StudyPlannerService spc) {
		this.studyPlannerService = spc;
	}

	/**
	 * Die Methode erzeugt auf basis der übergebenen Werte ein neues Semseter.
	 * @param name Der Name des Semsters.
	 * @param startDate Das Datum, an dem das Semester beginnt.
	 * @param endDate Das Daum, an dem das Semester endet.
	 * @return Das neu erstelle Semester-Objekt.
	 * @throws DataNotValidException Wird geworfen, wenn der Name des Semesters leer ist oder wenn eines der Datums-Felder
	 * einen ungültigen Wert repräsentiert.
	 */
	public Semester createSemester(String name, LocalDate startDate, LocalDate endDate)throws DataNotValidException {
		//Überprüfung, ob Eingaben valide sind.
		if(name == null || name.equals("") || startDate == null || endDate == null || startDate.isAfter(endDate))
			throw new DataNotValidException();

		//Neues Semester mit übergebenen Daten erzeugen
		Semester newSemester = new Semester(name,startDate,endDate);
		this.studyPlannerService.getStudyPlanner().addSemester(newSemester);
		StatisticsService statisticsService = this.studyPlannerService.getStatisticsService();
		statisticsService.calculateStatistics();
		return newSemester;
	}

	/**
	 * Die Methode bearbeitet ein bereits existierendes Semester.
	 * @param semesterToModify Das Semester, welches bearbeitet werden soll.
	 * @param name Der Name des Semsters.
	 * @param startDate Das Datum, an dem das Semester beginnt.
	 * @param endDate Das Daum, an dem das Semester endet.
	 * @throws DataNotValidException Wird geworfen, wenn der Name des Semesters leer ist oder wenn eines der Datums-Felder
	 * einen ungültigen Wert repräsentiert.
	 */
	public void modifySemester(Semester semesterToModify, String name, LocalDate startDate, LocalDate endDate)throws DataNotValidException {
		//Überprüfung, ob Eingaben valide sind.
		if(name == null || name.equals("") || startDate == null || endDate == null || startDate.isAfter(endDate))
			throw new DataNotValidException();
		//Setze neue Werte
		semesterToModify.setName(name);
		semesterToModify.setStartDate(startDate);
		semesterToModify.setEndDate(endDate);
		StatisticsService statisticsService = this.studyPlannerService.getStatisticsService();
		statisticsService.calculateStatistics();


	}

	/**
	 * Die Methode ordnet ein Modul einem Semester zu. Wenn es bereits einem anderen Semester zugeordet ist,
	 * wird diese Zuordnung aufgelöst.
	 * @param module Das zu verschiebene Modul.
	 * @param semester Das Zielsemester, dem das Modul zugeordet werden soll.
	 */
	public void moveModuleToSemester(Module module, Semester semester) {
		//Überprüfe, ob Modul bereits einem anderen Semester zugeordnet ist und entferne es gegebenenfalls
		Semester currentSemesterOfModule = this.studyPlannerService.getStudyPlanner().getCurrentSemesterOfModule(module);
		if(currentSemesterOfModule != null)
			currentSemesterOfModule.removeModule(module);
		semester.addModule(module);
	}

}
