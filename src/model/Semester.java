package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Collection;

public class Semester {

	private StringProperty name;

	private ObjectProperty<LocalDate> startDate;

	private ObjectProperty<LocalDate> endDate;

	private Collection<Module> modules;

	public Semester(String name, LocalDate startDate, LocalDate endDate) {

	}

	public void addModule(Module module) {

	}

	public void removeModule(Module module) {

	}

	public boolean isModuleinSemester(Module module) {
		return false;
	}

}
