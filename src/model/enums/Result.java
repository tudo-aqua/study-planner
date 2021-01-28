package model.enums;

/**
 * Das Enum repräsentiert den Status eines Moduls im Hinblick,
 * ob und wie es abgeschlossen wurde.
 */
public enum Result {

	/**
	 * Dieser Wert repräsentiert den erfolgreichen Abschluss eines benoteten Moduls.
	 */
	PASSED_WITH_GRADE,

	/**
	 * Dieser Wert repräsentiert den erfolgreichen Abschluss eines unbenoteten Moduls.
	 */
	PASSED_WITHOUT_GRADE,

	/**
	 * Dieser Wert repräsentiert den nicht erfolgreichen Abschluss eines Moduls.
	 */
	NOT_PASSED,

	/**
	 * Dieser Wert repräsentiert, dass das Modul noch nicht abgeschlossen wurde.
	 */
	NO_RESULT


}
