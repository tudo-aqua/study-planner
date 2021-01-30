package controller;

import model.Module;
import model.Semester;
import model.enums.Result;

/**
 * Die Klasse stellt Methoden für das Generieren von Statistiken bzgl. eines Studienplanes bereit.
 */
public class StatisticsController {

	/**
	 * Referenz auf den zentralen Controller der Controller-Schicht.
	 */
	private StudyPlannerController studyPlannerController;

	/**
	 * Konstruktor, der den StudyPlannerController setzt.
	 * @param spc Referenzu auf den zentralen StudyPlannerController.
	 */
	public StatisticsController(StudyPlannerController spc) {
		this.studyPlannerController = spc;
	}

	/**
	 * Die Methode berechnet die in Anzahl der ECTS-Punkte für alle einem Semester zugeorndeten Module.
	 * @param semester Das Semester, für welches die ECTS-Punkte berechnet werden sollen.
	 * @return Die Anzahl der ECTS-Punkte.
	 */
	public int calculateEctsForSemester(Semester semester) {
		int totalECTS = 0;
		for(Module module: semester.getModules()){
				totalECTS += module.getEcts();
		}
		return totalECTS;
	}

	/**
	 * Die Methode berechnet die Anzahl der erreichten ECTS-Punkte aus allen bestandenen Modulen.
	 * @return Die Anzahl der erreichten ECTS-Punkte oder 0, wenn bisher keine Erreicht wurden.
	 */
	public int calculateTotalEctsOfFinishedModuls() {
		int totalECTS = 0;
		for(Semester semester:studyPlannerController.getStudyPlanner().getSemesters()){
			for(Module module: semester.getModules()){
				if(module.getResult() == Result.PASSED_WITH_GRADE || module.getResult() == Result.PASSED_WITHOUT_GRADE)
					totalECTS += module.getEcts();
			}
		}
		return totalECTS;
	}

	/**
	 * Die Methode berechnet die aktuelle Durchschnittsnote aus allen bestandenen Modulen.
	 * @return Die aktuelle Durchschnittsnote.
	 */
	public float calculateAverageGrade() {
		float avgGrade = 0;
		for(Semester semester:studyPlannerController.getStudyPlanner().getSemesters()){
			for(Module module: semester.getModules()){
				if(module.getResult() == Result.PASSED_WITH_GRADE)
					avgGrade += module.getEcts()*module.getGrade();
			}
		}
		return avgGrade/calculateTotalEctsOfFinishedModuls();
	}

}
