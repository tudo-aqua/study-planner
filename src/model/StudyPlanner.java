package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.Collection;

public class StudyPlanner {

	private StringProperty courseOfStudy;

	private IntegerProperty ects;

	private Collection<Semester> semesters;

	private Collection<Module> modules;

	public StudyPlanner(String courseOfStudy, int ects) {

	}

	public void addModule(Module module) {

	}

	public void removeModule(Module module) {

	}

	public void addSemester(Semester semester) {

	}

	public Semester currentSemesterOfModule(Module module) {
		return null;
	}

}
