package controller;

import exceptions.DataNotValidException;
import exceptions.ModuleAlreadyExistsException;
import model.Semester;
import model.Module;

import java.time.LocalDate;
import java.util.List;

/**
 * Die Klasse stellt Methoden für die Verwaltung von Semestern zur Verfügung.
 */
public class SemesterController {

	/**
	 * Referenz auf den zentralen Controller der Controller-Schicht.
	 */
	private StudyPlannerController studyPlannerController;

	/**
	 * Konstruktor, der den StudyPlannerController setzt.
	 * @param spc Referenzu auf den zentralen StudyPlannerController.
	 */
	public SemesterController(StudyPlannerController spc) {
		this.studyPlannerController = spc;
	}

	/**
	 * Die Methode erzeugt auf basis der übergebenen Werte ein neues Semseter.
	 * @param name Der Name des Semsters.
	 * @param startDate Das Datum, an dem das Semester beginnt.
	 * @param endDate Das Daum, an dem das Semester endet.
	 * @throws DataNotValidException Wird geworfen, wenn der Name des Semesters leer ist oder wenn eines der Datums-Felder
	 * einen ungültigen Wert repräsentiert.
	 */
	public void createSemester(String name, LocalDate startDate, LocalDate endDate)throws DataNotValidException {
		//Überprüfung, ob Eingaben valide sind.
		if(name == null || name.equals("") || startDate == null || endDate == null)
			throw new DataNotValidException();

		//Neues Semester mit übergebenen Daten erzeugen
		Semester newSemester = new Semester(name,startDate,endDate);
		this.studyPlannerController.getStudyPlanner().addSemester(newSemester);
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
		if(name == null || name.equals("") || startDate == null || endDate == null)
			throw new DataNotValidException();
		//Setze neue Werte
		semesterToModify.setName(name);
		semesterToModify.setStartDate(startDate);
		semesterToModify.setEndDate(endDate);
	}

	/**
	 * Die Methode ordnet ein Modul einem Semester zu. Wenn es bereits einem anderen Semester zugeordet ist,
	 * wird diese Zuordnung aufgelöst.
	 * @param module Das zu verschiebene Modul.
	 * @param targetSemester Das Zielsemester, dem das Modul zugeordet werden soll.
	 */
	public void moveModuleToSemester(Module module, Semester targetSemester) {
		//Überprüfe, ob Modul bereits einem anderen Semester zugeordnet ist und entferne es gegebenenfalls
		List<Semester> allSemester = this.studyPlannerController.getStudyPlanner().getSemesters();
		for(Semester semester :allSemester){
			if(semester.isModuleInSemester(module))
				semester.removeModule(module);
		}
		targetSemester.addModule(module);
	}

}
