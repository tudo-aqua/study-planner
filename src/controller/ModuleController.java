package controller;

import exceptions.DataNotValidException;
import model.Module;
import model.Semester;
import model.StudyPlanner;
import model.enums.State;

import java.time.LocalDate;

/**
 * Die Klasse stellt Methoden für die Verwaltung von Modulen zur Verfügung.
 */
public class ModuleController {

	/**
	 * Referenz auf den zentralen Controller der Controller-Schicht.
	 */
	private StudyPlannerController studyPlannerController;

	/**
	 * Konstruktor, der den StudyPlannerController setzt.
	 * @param spc Referenzu auf den zentralen StudyPlannerController.
	 */
	public ModuleController(StudyPlannerController spc) {
		this.studyPlannerController = spc;
	}


	/**
	 * Die Methode erzeugt auf basis der übergebenen Werte ein neues Modul. Dies geschieht nur, wenn
	 * es noch kein Modul mit diesem Namen gibt.
	 * @param name Der Name/Titel des Moduls.
	 * @param creditPoints Die den Modul zugeordneten Leistungspunkte.
	 * @param examDate Der Prüfungstermin des Moduls.
	 * @param semester Das Semester, dem das Modul zugeordnet werden soll.
	 * @return Das neu erstelle Module-Objekt.
	 * @throws DataNotValidException Wird geworfen, wenn die Daten nicht valide sind, z.B. wenn der Name leer ist,
	 * die Leistungspunkte negativ/gleich 0 sind oder das Prüfungsdatum keinen gültigen Wert repräsentiert.
	 */
	public Module createModule(String name, int creditPoints,LocalDate examDate, Semester semester)throws DataNotValidException {
		//Überprüfung, ob Eingaben valide sind.
		if (name == null || name.equals("") || creditPoints <= 0 || examDate == null || semester == null)
			throw new DataNotValidException();

		//Neues Modul mit den übergebenen Parametern erzeugen und der Liste aller Module hinzufügen.
		Module newModule = new Module(name,creditPoints, examDate);

		StudyPlanner studyPlanner = this.studyPlannerController.getStudyPlanner();
		studyPlanner.addModule(newModule);

		//Verschiebe das neu erstellte Modul in das entsprechende Semester
		SemesterController semesterController = this.studyPlannerController.getSemesterController();
		semesterController.moveModuleToSemester(newModule, semester);

		//Aktualisiere die Statistiken
		StatisticsController statisticsController = this.studyPlannerController.getStatisticsController();
		statisticsController.updateStatistics();
		return newModule;
	}

	/**
	 * Die Methode bearbeitet ein bereits existierendes Modul, d.h. Name, Leistungspunkte und/oder das Datum der Prüfung können verändert werden.
	 *
	 * @param moduleToModify Das Modul, welches bearbeitet werden soll.
	 * @param creditPoints Die neue Anzahl an Leistungspunkten.
	 * @param examDate Datum der Modulprüfung.
	 * @param semester Das Semester, in welches das Modul verschoben werden soll.
	 * @throws DataNotValidException Wird geworfen, wenn die Daten nicht valide sind, z.B. wenn der Name leer ist,
	 * die Leistungspunkte negativ/gleich 0 sind oder das Prüfungsdatum keinen gültigen Wert repräsentiert.
	 */
	public void modifyModule(Module moduleToModify, int creditPoints, LocalDate examDate,Semester semester)throws DataNotValidException {
		//Überprüfung, ob Eingaben valide sind.
		if (creditPoints <= 0 || examDate == null || semester == null)
			throw new DataNotValidException();

		//Überschreibe alte Werte mit neuen Werten
		moduleToModify.setCreditPoints(creditPoints);
		moduleToModify.setExamDate(examDate);

		//Verschiebe das neu erstellte Modul in das entsprechende Semester
		SemesterController semesterController = this.studyPlannerController.getSemesterController();
		semesterController.moveModuleToSemester(moduleToModify, semester);

		//Aktualisiere die Statistiken
		StatisticsController statisticsController = this.studyPlannerController.getStatisticsController();
		statisticsController.updateStatistics();
	}

	/**
	 * Die Methode entfernt das übergebene Modul aus den Studienplan.
	 * @param module Das zu entfernende Modul.
	 */
	public void deleteModule(Module module) {
		Semester currentSemester = this.studyPlannerController.getStudyPlanner().getCurrentSemesterOfModule(module);
		if(currentSemester != null)
			currentSemester.removeModule(module);
		this.studyPlannerController.getStudyPlanner().removeModule(module);
		StatisticsController statisticsController = this.studyPlannerController.getStatisticsController();
		statisticsController.updateStatistics();
	}

	/**
	 * Die Methode setzt ein Ergebnis (und gegebenenfalls eine Note) zu einem Modul.
	 * @param state Das Ergebnis für das Modul.
	 * @param grade Die Note bei einem benoteten Modul, ansonsten 0.
	 * @param moduleToModify Das Modul, dem das Ergebnis zugewiesen werden soll.
	 * @throws DataNotValidException Wird geworfen, wenn die Note einen ungüötigen Wert hat.
	 */
	public void setStateToModule(Module moduleToModify, State state, float grade)throws DataNotValidException {
		if(grade < 0 || grade > 5)
			throw new DataNotValidException();
		moduleToModify.setState(state);
		moduleToModify.setGrade(grade);
		StatisticsController statisticsController = this.studyPlannerController.getStatisticsController();
		statisticsController.updateStatistics();

	}

}
