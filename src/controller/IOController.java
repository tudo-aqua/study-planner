package controller;

/**
 * Die Klasse stellt Methoden für das Laden und Speichern der Programmdaten zur Verfügung.
 */
public class IOController {

	/**
	 * Referenz auf den zentralen Controller der Controller-Schicht.
	 */
	private StudyPlannerController studyPlannerController;

	/**
	 * Konstruktor, der den StudyPlannerController setzt.
	 * @param spc Referenzu auf den zentralen StudyPlannerController.
	 */
	public IOController(StudyPlannerController spc) {
		this.studyPlannerController = spc;
	}

	public void loadData() {

	}

	public void storeData() {

	}

}
