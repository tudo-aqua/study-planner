package controller;


import model.Module;
import model.Semester;
import model.Statistics;
import model.enums.State;

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
	 * Die Methode berechnet die in Anzahl der Leistungspunkten für alle einem Semester zugeordneten Module.
	 * @param semester Das Semester, für welches die Leistungspunkte berechnet werden sollen.
	 * @return Die Anzahl der Leistungspunkte.
	 */
	private int calculateCreditPointsForSemester(Semester semester) {
		int creditPoints = 0;
		for(Module module: semester.getModules()){
				creditPoints += module.getCreditPoints();
		}
		return creditPoints;
	}

	/**
	 * Die Methode berechnet die Anzahl der erreichten Leistungspunkte aus allen bestandenen Modulen.
	 * @return Die Anzahl der erreichten Leistungspunkte oder 0, wenn bisher keine Erreicht wurden.
	 */
	private int calculateCollectedCreditPointsOfFinishedModules() {
		int creditPoints = 0;
		for(Semester semester:studyPlannerController.getStudyPlanner().getSemesters()){
			for(Module module: semester.getModules()){
				if(module.getState() == State.PASSED_WITH_GRADE || module.getState() == State.PASSED_WITHOUT_GRADE)
					creditPoints += module.getCreditPoints();
			}
		}

		return creditPoints;
	}

	/**
	 * Die Methode berechnet die aktuelle Durchschnittsnote aus allen bestandenen Modulen.
	 * @return Die aktuelle Durchschnittsnote.
	 */
	private float calculateAverageGrade() {
		float avgGrade = 0;
		float creditPoints = 0;
		for(Semester semester:studyPlannerController.getStudyPlanner().getSemesters()){
			for(Module module: semester.getModules()){
				if(module.getState() == State.PASSED_WITH_GRADE){
					avgGrade += module.getCreditPoints()*module.getGrade();
					creditPoints += module.getCreditPoints();
				}

			}
		}
		int help = (int)((avgGrade/ creditPoints)*10);
		return ((float)(help))/10f;
	}

	/**
	 * Die Methode berechnet alle Statistiken neu.
	 */
	public void calculateStatistics(){

		float avgGrade = this.calculateAverageGrade();
		Statistics statistics = this.studyPlannerController.getStudyPlanner().getStatistics();
		statistics.setAvgGrade(avgGrade);
		int creditPoints = this.calculateCollectedCreditPointsOfFinishedModules();
		statistics.setCollectedCreditPoints(creditPoints);
		for(Semester semester: this.studyPlannerController.getStudyPlanner().getSemesters()){
			int creditPointsForSemester = this.calculateCreditPointsForSemester(semester);
			statistics.setCreditPointsForSemester(semester,creditPointsForSemester);
		}

	}

}
