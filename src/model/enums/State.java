package model.enums;

import java.io.Serializable;

/**
 * Das Enum repräsentiert den Status eines Moduls im Hinblick,
 * ob und wie es abgeschlossen wurde.
 */
public enum State implements Serializable {

	/**
	 * Dieser Wert repräsentiert den erfolgreichen Abschluss eines benoteten Moduls.
	 */
	PASSED_WITH_GRADE(1),

	/**
	 * Dieser Wert repräsentiert den erfolgreichen Abschluss eines unbenoteten Moduls.
	 */
	PASSED_WITHOUT_GRADE(2),

	/**
	 * Dieser Wert repräsentiert den nicht erfolgreichen Abschluss eines Moduls.
	 */
	NOT_PASSED(3),

	/**
	 * Dieser Wert repräsentiert, dass das Modul noch nicht abgeschlossen wurde.
	 */
	NO_RESULT(4);

	private int id;

	/**
	 * Privater Konstruktor zum Setzen der id.
	 * @param id Die id des Zustandes.
	 */
	State(int id){
		this.id = id;
	}

	/**
	 * Statische Methode zum Erzeugen eines Status auf Basis der id.
	 * @param id Die id des Status.
	 * @return Das Status-Enum mit der entsprechenden id.
	 */
	public static State stateFromId(int id){
		switch (id){
			case 1: return State.PASSED_WITH_GRADE;
			case 2: return State.PASSED_WITHOUT_GRADE;
			case 3: return State.NOT_PASSED;
			default:return State.NO_RESULT;
		}
	}

	/**
	 * Getter-Methode für den id-Paramter.
	 * @return Die id des Status.
	 */
	public int getId(){
		return id;
	}

}
