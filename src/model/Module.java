package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Module {

	private StringProperty name;

	private IntegerProperty ects;

	private ObjectProperty<LocalDate> examDate;

	private Result result;

	public Module(String name, int exts) {

	}

}
