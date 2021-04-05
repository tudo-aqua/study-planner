package controller;

import model.StudyPlanner;

import java.io.*;

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

	/**
	 * Die Methode lädt aus der Datei mit dem übergebenen Namen alle Daten und erzeugt auf
	 * dessen Basis ein neues Model.
	 * @param fileName Name der Datei, aus der das Model geladen werden soll.
	 * @throws IOException Wird geworfen, wenn Laden es einen Fehler beim Laden gibt.
	 * @throws ClassNotFoundException Wird geworfen, wenn eine geladene Klasse nicht im Model ist.
	 */
	public void loadData(String fileName) throws IOException, ClassNotFoundException {
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		StudyPlanner studyPlanner = (StudyPlanner) ois.readObject();
		studyPlannerController.setStudyPlanner(studyPlanner);
		studyPlannerController.getStatisticsController().updateStatistics();
		ois.close();
	}

	/**
	 * Die Methode speichert alle im Model gespeicherten Daten in der Datei mit dem übergebenen Namen.
	 * @param fileName Name der Datei, in der das Model gespeichert werden soll.
	 * @throws IOException Wird geworfen, wenn Speichern nicht möglich ist.
	 */
	public void storeData(String fileName) throws IOException {
		StudyPlanner studyPlanner = studyPlannerController.getStudyPlanner();
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream (fos);
		oos.writeObject(studyPlanner);
		oos.close();
	}

}
