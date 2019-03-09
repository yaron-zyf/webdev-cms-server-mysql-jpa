package models;

import java.util.List;

public class Course {
	
	private int id;
	private String title;
	private List<Module> modules;
	
	public Course() {
	}

	public Course(int id, String title, List<Module> modules) {
		this.id = id;
		this.title = title;
		this.modules = modules;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
}
