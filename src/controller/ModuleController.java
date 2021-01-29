package controller;

import exceptions.DataNotValidException;
import exceptions.ModuleAlreadyExistsException;
import model.Module;
import model.enums.Result;

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
	 * Kontroller, der den StudyPlannerController setzt.
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
	 * @throws DataNotValidException Wird geworfen, wenn die Daten nicht valide sind, z.B. wenn der Name leer ist
	 * oder die ECTS-Punte negativ/gleich 0 sind.
	 * @throws ModuleAlreadyExistsException Wird geworfen, wenn es bereits ein Modul mit dem selben Namen gibt.
	 */
	public void createModule(String name, int ects)throws DataNotValidException, ModuleAlreadyExistsException {
		//Überprüfung, ob Eingaben valide sind.
		if (name == null || name.equals("") || ects <= 0)
			throw new DataNotValidException();
		//Überprüfen, ob es ein Modul mit diesem Namen bereits gibt
		List<Module> allModules = this.studyPlannerController.getStudyPlanner().getModules();
		for(Module module :allModules){
			if(module.getName().equals(name))
				throw new ModuleAlreadyExistsException();
		}
		//Neues Modul mit den übergebenen Parametern erzeugen und der Liste aller Module hinzufügen.
		Module newModule = new Module(name,ects);
		allModules.add(newModule);


	}

	/**
	 * Die Methode bearbeitet ein bereits existierendes Modul, d.h. Name und/oder ECTS-Punkte können verändert werden.
	 *
	 * @param moduleToModify Das Modul, welches bearbeitet werden soll.
	 * @param name Der neue Name des Moduls.
	 * @param ects Die neue Anzahl an ECTS-Punkten.
	 * @throws DataNotValidException Wird geworfen, wenn die Daten nicht valide sind, z.B. wenn der Name leer ist
	 * oder die ECTS-Punte negativ/gleich 0 sind.
	 * @throws ModuleAlreadyExistsException Wird geworfen, wenn es bereits ein Modul mit dem selben Namen gibt.
	 */
	public void modifyModule(Module moduleToModify, String name, int ects)throws DataNotValidException, ModuleAlreadyExistsException {
		//Überprüfung, ob Eingaben valide sind.
		if (name == null || name.equals("") || ects <= 0)
			throw new DataNotValidException();
		//Überprüfen, ob es ein Modul mit diesem Namen bereits gibt
		List<Module> allModules = this.studyPlannerController.getStudyPlanner().getModules();
		for(Module module :allModules){
			if(module.getName().equals(name))
				throw new ModuleAlreadyExistsException();
		}
		moduleToModify.setName(name);
		moduleToModify.setEcts(ects);
	}

	/**
	 * Die Methode entfernt das übergebene Modul aus den Studienplan.
	 * @param module Das zu entfernende Modul.
	 */
	public void deleteModule(Module module) {
		this.studyPlannerController.getStudyPlanner().removeModule(module);
	}

	/**
	 * Die Methode setzt ein Ergebnis (und gegebenenfalls eine Note) zu einem Modul.
	 * @param result Das Ergebnis für das Modul.
	 * @param grade Die Note bei einem benoteten Modul, ansonsten 0.
	 * @param module Das Modul, dem das Ergebnis zugewiesen werden soll.
	 * @throws DataNotValidException Wird geworfen, wenn die Note einen ungüötigen Wert hat.
	 */
	public void setResultToModule(Result result, float grade, Module module)throws DataNotValidException {
		if(grade < 0 || grade > 5)
			throw new DataNotValidException();
		module.setResult(result);
		module.setGrade(grade);
	}


	/**
	 * Die Methode setzt den Prüfungstermin für ein Modul.
	 * @param examDate Der Prüfungstermin.
	 * @param module Das Modul, dem der Prüfungstermin zugewiesen werden soll.
	 */
	public void setExamDateToModule(LocalDate examDate, Module module) {
		module.setExamDate(examDate);
	}

}
