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
	 * Die Methode speichert alle im Model gespeicherten Daten in der Datei data.sp.
	 */
	public void loadData() {
		try {
			File file = new File("data.sp");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			StudyPlanner studyPlanner = (StudyPlanner) ois.readObject();
			studyPlannerController.setStudyPlanner(studyPlanner);
			ois.close();
		}
		catch (IOException | ClassNotFoundException e ){
			e.printStackTrace();
		}
	}

	/**
	 * Die Methode läd aus der Datei data.sp alle Daten und erzeugt auf
	 * dessen Basis ein neues Model.
	 */
	public void storeData() {
		try {
			StudyPlanner studyPlanner = studyPlannerController.getStudyPlanner();
			File file = new File("data.sp");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			oos.writeObject(studyPlanner);
			oos.close();
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

}
