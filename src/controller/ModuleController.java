package controller;

import exceptions.DataNotValidException;
import exceptions.ModuleAlreadyExistsException;
import model.Module;
import model.Semester;
import model.StudyPlanner;
import model.enums.State;

import java.time.LocalDate;
import java.util.List;

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
	 * @param ects Die den Modul zugeordneten ECTS-Punkte.
	 * @param examDate Der Prüfungstermin des Moduls.
	 * @throws DataNotValidException Wird geworfen, wenn die Daten nicht valide sind, z.B. wenn der Name leer ist,
	 * die ECTS-Punte negativ/gleich 0 sind oder das Prüfungsdatum keinen gültigen Wert repräsentiert.
	 * @return Das neu erstelle Module-Objekt.
	 * @throws ModuleAlreadyExistsException Wird geworfen, wenn es bereits ein Modul mit dem selben Namen gibt.
	 */
	public Module createModule(String name, int ects,LocalDate examDate)throws DataNotValidException, ModuleAlreadyExistsException {
		//Überprüfung, ob Eingaben valide sind.
		if (name == null || name.equals("") || ects <= 0 || examDate == null)
			throw new DataNotValidException();
		//Überprüfen, ob es ein Modul mit diesem Namen bereits gibt
		StudyPlanner studyPlanner = this.studyPlannerController.getStudyPlanner();
		List<Module> allModules = studyPlanner.getModules();
		for(Module module :allModules){
			if(module.getName().equals(name))
				throw new ModuleAlreadyExistsException();
		}

		//Neues Modul mit den übergebenen Parametern erzeugen und der Liste aller Module hinzufügen.
		Module newModule = new Module(name,ects, examDate);
		studyPlanner.addModule(newModule);

		//Statistiken aktualisieren
		this.studyPlannerController.getStatisticsController().updateStatistics();

		return newModule;

	}

	/**
	 * Die Methode bearbeitet ein bereits existierendes Modul, d.h. Name und/oder ECTS-Punkte können verändert werden.
	 *
	 * @param moduleToModify Das Modul, welches bearbeitet werden soll.
	 * @param name Der neue Name des Moduls.
	 * @param ects Die neue Anzahl an ECTS-Punkten.
	 * @throws DataNotValidException Wird geworfen, wenn die Daten nicht valide sind, z.B. wenn der Name leer ist,
	 * die ECTS-Punte negativ/gleich 0 sind oder das Prüfungsdatum keinen gültigen Wert repräsentiert.
	 * @throws ModuleAlreadyExistsException Wird geworfen, wenn es bereits ein Modul mit dem selben Namen gibt.
	 */
	public void modifyModule(Module moduleToModify, String name, int ects, LocalDate examDate)throws DataNotValidException, ModuleAlreadyExistsException {
		//Überprüfung, ob Eingaben valide sind.
		if (name == null || name.equals("") || ects <= 0 || examDate == null)
			throw new DataNotValidException();
		//Überprüfen, ob es ein Modul mit diesem Namen bereits gibt
		List<Module> allModules = this.studyPlannerController.getStudyPlanner().getModules();
		for(Module module :allModules){
			if(module.getName().equals(name))
				throw new ModuleAlreadyExistsException();
		}
		//Setze neue Werte
		moduleToModify.setName(name);
		moduleToModify.setEcts(ects);
		moduleToModify.setExamDate(examDate);

		//Statistiken aktualisieren
		this.studyPlannerController.getStatisticsController().updateStatistics();
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
		//Statistiken aktualisieren
		this.studyPlannerController.getStatisticsController().updateStatistics();
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
		//Statistiken aktualisieren
		this.studyPlannerController.getStatisticsController().updateStatistics();
	}

}
